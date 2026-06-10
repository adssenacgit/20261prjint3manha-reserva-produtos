package br.com.senac.prjint3.service;

import br.com.senac.prjint3.common.StatusRegistro;
import br.com.senac.prjint3.dto.funcionario.FuncionarioRequest;
import br.com.senac.prjint3.dto.funcionario.FuncionarioUpdateRequest;
import br.com.senac.prjint3.exception.RecursoNaoEncontradoException;
import br.com.senac.prjint3.model.Funcionario;
import br.com.senac.prjint3.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FuncionarioService {
    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public List<Funcionario> listar() {
        return repository.findByStatusNot(StatusRegistro.APAGADO);
    }

    public List<Funcionario> listarAtivos() {
        return repository.findByStatus(StatusRegistro.ATIVO);
    }

    public Funcionario buscar(String login) {
        return repository.findByLoginAndStatusNot(login, StatusRegistro.APAGADO)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Funcionário não encontrado."));
    }

    @Transactional
    public Funcionario criar(FuncionarioRequest request) {
        if (repository.existsById(request.login())) {
            throw new IllegalArgumentException("Já existe funcionário com esse login.");
        }
        Funcionario funcionario = new Funcionario();
        funcionario.setLogin(request.login());
        funcionario.setSenha(request.senha());
        funcionario.setStatus(StatusRegistro.normalizar(request.status()));
        return repository.save(funcionario);
    }

    @Transactional
    public Funcionario atualizar(String login, FuncionarioUpdateRequest request) {
        Funcionario funcionario = buscar(login);
        funcionario.setSenha(request.senha());
        funcionario.setStatus(StatusRegistro.normalizar(request.status()));
        return repository.save(funcionario);
    }

    @Transactional
    public Funcionario alterarStatus(String login, Integer status) {
        StatusRegistro.validar(status);
        Funcionario funcionario = buscar(login);
        funcionario.setStatus(status);
        return repository.save(funcionario);
    }

    @Transactional
    public void apagarLogicamente(String login) {
        alterarStatus(login, StatusRegistro.APAGADO);
    }
}
