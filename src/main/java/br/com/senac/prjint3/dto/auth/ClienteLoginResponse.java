package br.com.senac.prjint3.dto.auth;

public class ClienteLoginResponse {

    private Boolean autenticado;
    private String token;
    private Integer clienteId;
    private String nome;
    private String email;

    public ClienteLoginResponse() {
    }

    public ClienteLoginResponse(Boolean autenticado, String token, Integer clienteId, String nome, String email) {
        this.autenticado = autenticado;
        this.token = token;
        this.clienteId = clienteId;
        this.nome = nome;
        this.email = email;
    }

    public Boolean getAutenticado() {
        return autenticado;
    }

    public void setAutenticado(Boolean autenticado) {
        this.autenticado = autenticado;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
