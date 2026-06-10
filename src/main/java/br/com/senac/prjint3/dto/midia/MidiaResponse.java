package br.com.senac.prjint3.dto.midia;

import br.com.senac.prjint3.model.Midia;

public record MidiaResponse(
        Integer id,
        Integer produtoId,
        String url,
        Integer status
) {
    public static MidiaResponse from(Midia midia) {
        Integer produtoId = midia.getProduto() == null ? null : midia.getProduto().getId();
        return new MidiaResponse(midia.getId(), produtoId, midia.getUrl(), midia.getStatus());
    }
}
