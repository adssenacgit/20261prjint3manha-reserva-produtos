package br.com.senac.prjint3.model;

import jakarta.persistence.*;

@Entity
@Table(name = "midias")
public class Midia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "midia_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Column(name = "midias_url", length = 300)
    private String url;

    @Column(name = "midias_status")
    private Integer status;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}
