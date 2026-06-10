package br.com.senac.prjint3.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Integer id;

    @Column(name = "cliente_nome", nullable = false, length = 200)
    private String nome;

    @Column(name = "cliente_cpf", nullable = false, length = 11, unique = true)
    private String cpf;

    @Column(name = "cliente_telefone", nullable = false, length = 20)
    private String telefone;

    @Column(name = "cliente_email", nullable = false, length = 150, unique = true)
    private String email;

    @Column(name = "cliente_chavePix", length = 40)
    private String chavePix;

    @Column(name = "cliente_senha", nullable = false, length = 255)
    private String senha;

    @Column(name = "cliente_status", nullable = false)
    private Integer status;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getChavePix() { return chavePix; }
    public void setChavePix(String chavePix) { this.chavePix = chavePix; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}
