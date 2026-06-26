package br.com.senac.prjint3.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ClienteLoginRequest {

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "Informe um email válido.")
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}