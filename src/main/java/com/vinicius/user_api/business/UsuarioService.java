package com.vinicius.user_api.business;


import com.vinicius.user_api.business.converter.UsuarioConverter;
import com.vinicius.user_api.business.dto.EnderecoDTO;
import com.vinicius.user_api.business.dto.TelefoneDTO;
import com.vinicius.user_api.business.dto.UsuarioDTO;
import com.vinicius.user_api.insfrastructure.entity.Endereco;
import com.vinicius.user_api.insfrastructure.entity.Telefone;
import com.vinicius.user_api.insfrastructure.entity.Usuario;
import com.vinicius.user_api.insfrastructure.exception.ConflictException;
import com.vinicius.user_api.insfrastructure.exception.ResourceNotFoundException;
import com.vinicius.user_api.insfrastructure.repository.EnderecoRepository;
import com.vinicius.user_api.insfrastructure.repository.TelefoneRepository;
import com.vinicius.user_api.insfrastructure.repository.UsuarioRepository;
import com.vinicius.user_api.insfrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EnderecoRepository enderecoRepository;
    private final TelefoneRepository telefoneRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuarioEntity = usuarioConverter.paraUsuario(usuarioDTO);
        return  usuarioConverter.paraDTO(usuarioRepository.save(usuarioEntity));
    }

    public void  emailExiste(String email){
        try{
            boolean existe = verificarEmailExistente(email);
            if (existe) {
                throw new ConflictException("Email ja cadastrado " + email);
            }
        } catch (ConflictException e){
            throw new ConflictException("Email ja cadastrado " + e);
        }
    }

    public boolean verificarEmailExistente(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public UsuarioDTO buscarUsuarioPorEmail(String email){

        try {

            Usuario entity = usuarioRepository.findByEmail(email).orElseThrow(
                    () -> new ResourceNotFoundException("Email nao encontrado! " + email));

            return usuarioConverter.paraDTO(usuarioRepository.save(entity));

        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Email nao encontrado! " + email);
        }
    }

    public void deletarUsuarioPorEmail(String email){

        boolean existeEmail = verificarEmailExistente(email);

        if (existeEmail){
            usuarioRepository.deleteByEmail(email);
        } else {
            throw new ResourceNotFoundException("Email nao encontrado ou nao existente! " + email);
        }

    }

    public UsuarioDTO atualizarDadosUsuario(String token, UsuarioDTO dto){
        String email = jwtUtil.extractUsername(token.substring(7));
        dto.setSenha(dto.getSenha() != null ? passwordEncoder.encode(dto.getSenha()) : null);
        Usuario usuarioEntity =  usuarioRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email nao encontrado! : " +  email));
        Usuario usuario = usuarioConverter.updateUsuario(dto,usuarioEntity);

        return usuarioConverter.paraDTO(usuarioRepository.saveAndFlush(usuario));
    }

    public EnderecoDTO atualizarEndereco(Long id, EnderecoDTO enderecoDTO){
        Endereco entity = enderecoRepository.findById(id).orElseThrow(()  -> new ResourceNotFoundException("id nao encontrado! " + id));
        Endereco endereco1 = usuarioConverter.updateEndereco(enderecoDTO,entity);

        return usuarioConverter.paraEnderecoDTO(enderecoRepository.saveAndFlush(endereco1));
    }

    public TelefoneDTO atualizarTelefone(Long id, TelefoneDTO telefoneDTO){
        Telefone entity = telefoneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id nao encontrado! " + id));
        Telefone telefone = usuarioConverter.updateTelefone(telefoneDTO,entity);

        return usuarioConverter.paraTelefoneDTO(telefoneRepository.saveAndFlush(telefone));
    }

    public EnderecoDTO cadastraEndereco(String token, EnderecoDTO dto){
        String email = jwtUtil.extractUsername(token.substring(7));
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("email nao encontrado! " + email));

        Endereco enderecoEntity = usuarioConverter.paraEnderecoEntity(usuario.getId(), dto);
        Endereco endereco1 = enderecoRepository.saveAndFlush(enderecoEntity);
        return usuarioConverter.paraEnderecoDTO(endereco1);
    }

    public TelefoneDTO cadastraTelefone(String token, TelefoneDTO dto){
        String email = jwtUtil.extractUsername(token.substring(7));
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("email nao encontrado! " + email));

        Telefone telefoneEntity = usuarioConverter.paraTelefoneEntity(usuario.getId(), dto);
        Telefone telefone = telefoneRepository.saveAndFlush(telefoneEntity);
        return usuarioConverter.paraTelefoneDTO(telefone);
    }



}
