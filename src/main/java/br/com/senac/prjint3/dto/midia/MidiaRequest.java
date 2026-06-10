package br.com.senac.prjint3.dto.midia;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MidiaRequest(
        @NotNull Integer produtoId,
        @Size(max = 300) String url,
        Integer status
) {
}
