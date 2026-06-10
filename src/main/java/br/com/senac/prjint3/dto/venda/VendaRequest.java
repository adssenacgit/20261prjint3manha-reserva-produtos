package br.com.senac.prjint3.dto.venda;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public record VendaRequest(
        @NotNull LocalDate data,
        @NotNull @DecimalMin(value = "0.01") BigDecimal valor,
        @NotNull Integer produtoId,
        Integer status
) {
}
