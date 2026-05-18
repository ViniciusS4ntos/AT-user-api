package com.vinicius.user_api.business.dto.in;

import jakarta.annotation.Nonnull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UsuarioRequest {

    private String email;
    private String senha;

}
