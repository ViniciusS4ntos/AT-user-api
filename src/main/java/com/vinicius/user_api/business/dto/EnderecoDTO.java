package com.vinicius.user_api.business.dto;

import com.vinicius.user_api.insfrastructure.entity.Usuario;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EnderecoDTO {

    private Long id;
    private String rua;
    private Long numero;
    private String complemento;
    private String cidade;
    private String estado;
    private String cep;
    private Long usuario_id;

}
