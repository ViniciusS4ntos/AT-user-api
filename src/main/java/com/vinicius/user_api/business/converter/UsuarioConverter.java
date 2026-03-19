package com.vinicius.user_api.business.converter;

import com.vinicius.user_api.business.dto.EnderecoDTO;
import com.vinicius.user_api.business.dto.TelefoneDTO;
import com.vinicius.user_api.business.dto.UsuarioDTO;
import com.vinicius.user_api.insfrastructure.entity.Endereco;
import com.vinicius.user_api.insfrastructure.entity.Telefone;
import com.vinicius.user_api.insfrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioConverter {

    // DTO -> Entity
    public Usuario paraUsuario(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(usuarioDTO.getEnderecoDTOS() != null ?
                        paraListaEndereco(usuarioDTO.getEnderecoDTOS()) : null)
                .telefones(usuarioDTO.getTelefoneDTOS() != null ?
                        paraListaTelefones(usuarioDTO.getTelefoneDTOS()) : null)
                .build();
    }

    public List<Endereco> paraListaEndereco(List<EnderecoDTO> enderecoDTOS) {
        List<Endereco> enderecos = new ArrayList<>();
        for (EnderecoDTO enderecoDTO : enderecoDTOS) {
            enderecos.add(paraEndereco(enderecoDTO));
        }
        return enderecos;
    }

    public Endereco paraEndereco(EnderecoDTO enderecoDTO) {
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .complemento(enderecoDTO.getComplemento())
                .cidade(enderecoDTO.getCidade())
                .estado(enderecoDTO.getEstado())
                .cep(enderecoDTO.getCep())
                .build();
    }

    public List<Telefone> paraListaTelefones(List<TelefoneDTO> telefoneDTOS) {
        return telefoneDTOS.stream().map(this::paraTelefone).toList();
    }

    public Telefone paraTelefone(TelefoneDTO telefoneDTO) {
        return Telefone.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }

    // Entity -> DTO

    public UsuarioDTO paraDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .enderecoDTOS(usuario.getEnderecos() != null ?
                        paraListaEnderecoDTO(usuario.getEnderecos()) : null)
                .telefoneDTOS(usuario.getTelefones() != null ? paraListaTelefoneDTO(usuario.getTelefones()) : null)
                .build();
    }

    public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> enderecoList) {
        List<EnderecoDTO> enderecosDTO = new ArrayList<>();

        for (Endereco endereco : enderecoList) {
            enderecosDTO.add(paraEnderecoDTO(endereco));
        }

        return enderecosDTO;
    }

    public EnderecoDTO paraEnderecoDTO(Endereco endereco) {
        return EnderecoDTO.builder()
                .id(endereco.getId())
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .complemento(endereco.getComplemento())
                .cidade(endereco.getCidade())
                .estado(endereco.getEstado())
                .cep(endereco.getCep())
                .build();
    }

    public List<TelefoneDTO> paraListaTelefoneDTO(List<Telefone> telefones) {
        return telefones.stream().map(this::paraTelefoneDTO).toList();
    }

    public TelefoneDTO paraTelefoneDTO(Telefone telefone) {
        return TelefoneDTO.builder()
                .id(telefone.getId())
                .numero(telefone.getNumero())
                .ddd(telefone.getDdd())
                .build();
    }

    public Usuario updateUsuario(UsuarioDTO dto, Usuario entity) {
        return Usuario.builder()
                .id(entity.getId())
                .nome(dto.getNome() != null ? dto.getNome() : entity.getNome())
                .email(dto.getEmail() != null ? dto.getEmail() : entity.getEmail())
                .senha(dto.getSenha() != null ? dto.getSenha() : entity.getSenha())
                .enderecos(entity.getEnderecos())
                .telefones(entity.getTelefones())
                .build();
    }

    public Endereco updateEndereco(EnderecoDTO dto, Endereco entity) {
        return Endereco.builder()
                .id(entity.getId())
                .rua(dto.getRua() != null ? dto.getRua() : entity.getRua())
                .numero(dto.getNumero() != null ? dto.getNumero() : entity.getNumero())
                .complemento(dto.getComplemento() != null ? dto.getComplemento() : entity.getComplemento())
                .cidade(dto.getCidade() != null ? dto.getCidade() : entity.getCidade())
                .estado(dto.getEstado() != null ? dto.getEstado() : entity.getEstado())
                .cep(dto.getCep() != null ? dto.getCep() : entity.getCep())
                .build();
    }

    public Telefone updateTelefone(TelefoneDTO dto, Telefone entity) {
        return Telefone.builder()
                .id(entity.getId())
                .numero(dto.getNumero() != null ? dto.getNumero() : entity.getNumero())
                .ddd(dto.getDdd() != null ? dto.getDdd() : entity.getDdd())
                .build();
    }

    public Endereco paraEnderecoEntity(Long id, EnderecoDTO dto) {
        return Endereco.builder()
                .rua(dto.getRua())
                .numero(dto.getNumero())
                .complemento(dto.getComplemento())
                .cidade(dto.getCidade())
                .estado(dto.getEstado())
                .cep(dto.getCep())
                .usuario_id(id)
                .build();
    }

    public Telefone paraTelefoneEntity(Long id, TelefoneDTO entity) {
        return Telefone.builder()
                .numero(entity.getNumero())
                .ddd(entity.getDdd())
                .usuario_id(id)
                .build();
    }

}
