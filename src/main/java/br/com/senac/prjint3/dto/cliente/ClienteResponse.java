package br.com.senac.prjint3.dto.cliente;

import br.com.senac.prjint3.model.Cliente;

public record ClienteResponse(
        Integer id,
        String nome,
        String cpf,
        String telefone,
        String email,
        String chavePix,
        Integer status
) {
    public static ClienteResponse from(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getTelefone(),
                cliente.getEmail(),
                cliente.getChavePix(),
                cliente.getStatus()
        );
    }
}
