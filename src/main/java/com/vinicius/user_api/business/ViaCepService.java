package com.vinicius.user_api.business;

import com.vinicius.user_api.insfrastructure.clients.ViaCepClient;
import com.vinicius.user_api.insfrastructure.clients.ViaCepDTO;
import com.vinicius.user_api.insfrastructure.exception.InvalidCepException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ViaCepService {

    private final ViaCepClient viaCepClient;

    public ViaCepDTO buscarDadosEndereco(String cep){

        String cepLimpo = processarCep(cep);

        return viaCepClient.buscarDadosEndereco(cepLimpo);

    }

    private String processarCep(String cep){

        String cepFormatado = cep.replace(" ", "")
                .replace("-", "");

        if (!cepFormatado.matches("\\d+") || !Objects.equals(cepFormatado.length(), 8)){
            throw new InvalidCepException("O cep contém caracteres inválidos, por favor verificar: " + cepFormatado);
        }

        return cepFormatado;
    }

}
