package br.com.senac.prjint3.dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClienteRequest(
        @NotBlank @Size(max = 200) String nome,
        @NotBlank @Pattern(regexp = "\\d{11}", message = "CPF deve conter exatamente 11 dígitos") String cpf,
        @NotBlank @Size(max = 20) String telefone,
        @NotBlank @Email @Size(max = 150) String email,
        @Size(max = 40) String chavePix,
        @NotBlank @Size(max = 255) String senha,
        Integer status
) {
}
