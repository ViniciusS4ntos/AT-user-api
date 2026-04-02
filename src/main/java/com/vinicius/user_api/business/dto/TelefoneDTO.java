package com.vinicius.user_api.business.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TelefoneDTO {

    private Long id;
    private String numero;
    private String ddd;
    private Long usuario_id;

}
