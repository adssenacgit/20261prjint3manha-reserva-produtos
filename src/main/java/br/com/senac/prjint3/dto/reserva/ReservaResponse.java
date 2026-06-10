package br.com.senac.prjint3.dto.reserva;

import br.com.senac.prjint3.model.Reserva;
import java.math.BigDecimal;
import java.time.LocalDate;

public record ReservaResponse(
        Integer id,
        Integer produtoId,
        Integer clienteId,
        LocalDate data,
        BigDecimal preco,
        Integer status
) {
    public static ReservaResponse from(Reserva reserva) {
        return new ReservaResponse(
                reserva.getId(),
                reserva.getProduto().getId(),
                reserva.getCliente().getId(),
                reserva.getData(),
                reserva.getPreco(),
                reserva.getStatus()
        );
    }
}
