package br.com.senac.prjint3.dto.funcionario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FuncionarioUpdateRequest(
        @NotBlank @Size(max = 255) String senha,
        Integer status
) {
}
