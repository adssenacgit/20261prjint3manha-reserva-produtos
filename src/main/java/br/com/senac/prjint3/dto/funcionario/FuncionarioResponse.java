package br.com.senac.prjint3.dto.funcionario;

import br.com.senac.prjint3.model.Funcionario;

public record FuncionarioResponse(
        String login,
        Integer status
) {
    public static FuncionarioResponse from(Funcionario funcionario) {
        return new FuncionarioResponse(funcionario.getLogin(), funcionario.getStatus());
    }
}
