package br.com.senac.prjint3.dto.venda;

import br.com.senac.prjint3.model.Venda;
import java.math.BigDecimal;
import java.time.LocalDate;

public record VendaResponse(
        Integer id,
        LocalDate data,
        BigDecimal valor,
        Integer produtoId,
        Integer status
) {
    public static VendaResponse from(Venda venda) {
        return new VendaResponse(
                venda.getId(),
                venda.getData(),
                venda.getValor(),
                venda.getProduto().getId(),
                venda.getStatus()
        );
    }
}
