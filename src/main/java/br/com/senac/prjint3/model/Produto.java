package br.com.senac.prjint3.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produto_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(name = "produto_descricao", nullable = false, length = 250)
    private String descricao;

    @Column(name = "produto_preco", nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(name = "produto_data_de_cadastro", nullable = false)
    private LocalDate dataDeCadastro;

    @Column(name = "produto_tamanho", nullable = false, length = 10)
    private String tamanho;

    @Enumerated(EnumType.STRING)
    @Column(name = "produto_genero", nullable = false)
    private GeneroProduto genero;

    @Column(name = "produto_faixa_etaria", nullable = false)
    private Integer faixaEtaria;

    @Column(name = "produto_status", nullable = false)
    private Integer status;

    @Column(name = "produto_imagem", length = 300)
    private String imagem;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    public LocalDate getDataDeCadastro() { return dataDeCadastro; }
    public void setDataDeCadastro(LocalDate dataDeCadastro) { this.dataDeCadastro = dataDeCadastro; }
    public String getTamanho() { return tamanho; }
    public void setTamanho(String tamanho) { this.tamanho = tamanho; }
    public GeneroProduto getGenero() { return genero; }
    public void setGenero(GeneroProduto genero) { this.genero = genero; }
    public Integer getFaixaEtaria() { return faixaEtaria; }
    public void setFaixaEtaria(Integer faixaEtaria) { this.faixaEtaria = faixaEtaria; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public String getImagem() { return imagem; }
    public void setImagem(String imagem) { this.imagem = imagem; }
}
