package br.com.senac.prjint3.dto.reserva;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public record ReservaRequest(
        @NotNull Integer produtoId,
        @NotNull Integer clienteId,
        @NotNull LocalDate data,
        @NotNull @DecimalMin(value = "0.01") BigDecimal preco,
        Integer status
) {
}
