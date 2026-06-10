package br.com.senac.prjint3.model;

import jakarta.persistence.*;

@Entity
@Table(name = "funcionarios")
public class Funcionario {

    @Id
    @Column(name = "funcionario_login", nullable = false, length = 20)
    private String login;

    @Column(name = "funcionario_senha", nullable = false, length = 255)
    private String senha;

    @Column(name = "funcionario_status", nullable = false)
    private Integer status;

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}
