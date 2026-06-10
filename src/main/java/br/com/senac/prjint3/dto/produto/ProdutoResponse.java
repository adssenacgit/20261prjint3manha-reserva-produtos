package br.com.senac.prjint3.dto.produto;

import br.com.senac.prjint3.model.GeneroProduto;
import br.com.senac.prjint3.model.Produto;
import java.math.BigDecimal;
import java.time.LocalDate;

public record ProdutoResponse(
        Integer id,
        Integer clienteId,
        String descricao,
        BigDecimal preco,
        LocalDate dataDeCadastro,
        String tamanho,
        GeneroProduto genero,
        Integer faixaEtaria,
        Integer status,
        String imagem
) {
    public static ProdutoResponse from(Produto produto) {
        return new ProdutoResponse(
                produto.getId(),
                produto.getCliente().getId(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getDataDeCadastro(),
                produto.getTamanho(),
                produto.getGenero(),
                produto.getFaixaEtaria(),
                produto.getStatus(),
                produto.getImagem()
        );
    }
}
