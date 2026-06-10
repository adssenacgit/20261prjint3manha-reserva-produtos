package br.com.senac.prjint3.dto.produto;

import br.com.senac.prjint3.model.GeneroProduto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record ProdutoRequest(
        @NotNull Integer clienteId,
        @NotBlank @Size(max = 250) String descricao,
        @NotNull @DecimalMin(value = "0.01") BigDecimal preco,
        @NotNull LocalDate dataDeCadastro,
        @NotBlank @Size(max = 10) String tamanho,
        @NotNull GeneroProduto genero,
        @NotNull @Min(0) Integer faixaEtaria,
        Integer status,
        @Size(max = 300) String imagem
) {
}
