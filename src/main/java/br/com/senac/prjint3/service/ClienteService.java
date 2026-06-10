package br.com.senac.prjint3.service;

import br.com.senac.prjint3.common.StatusRegistro;
import br.com.senac.prjint3.dto.cliente.ClienteRequest;
import br.com.senac.prjint3.exception.RecursoNaoEncontradoException;
import br.com.senac.prjint3.model.Cliente;
import br.com.senac.prjint3.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> listar() {
        return repository.findByStatusNot(StatusRegistro.APAGADO);
    }

    public List<Cliente> listarAtivos() {
        return repository.findByStatus(StatusRegistro.ATIVO);
    }

    public Cliente buscar(Integer id) {
        return repository.findByIdAndStatusNot(id, StatusRegistro.APAGADO)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado."));
    }

    @Transactional
    public Cliente criar(ClienteRequest request) {
        Cliente cliente = new Cliente();
        preencher(cliente, request);
        return repository.save(cliente);
    }

    @Transactional
    public Cliente atualizar(Integer id, ClienteRequest request) {
        Cliente cliente = buscar(id);
        preencher(cliente, request);
        return repository.save(cliente);
    }

    @Transactional
    public Cliente alterarStatus(Integer id, Integer status) {
        StatusRegistro.validar(status);
        Cliente cliente = buscar(id);
        cliente.setStatus(status);
        return repository.save(cliente);
    }

    @Transactional
    public void apagarLogicamente(Integer id) {
        alterarStatus(id, StatusRegistro.APAGADO);
    }

    private void preencher(Cliente cliente, ClienteRequest request) {
        cliente.setNome(request.nome());
        cliente.setCpf(request.cpf());
        cliente.setTelefone(request.telefone());
        cliente.setEmail(request.email());
        cliente.setChavePix(request.chavePix());
        cliente.setSenha(request.senha());
        cliente.setStatus(StatusRegistro.normalizar(request.status()));
    }
}
