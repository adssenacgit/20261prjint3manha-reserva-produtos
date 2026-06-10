package br.com.senac.prjint3.dto.funcionario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FuncionarioRequest(
        @NotBlank @Size(max = 20) String login,
        @NotBlank @Size(max = 255) String senha,
        Integer status
) {
}
