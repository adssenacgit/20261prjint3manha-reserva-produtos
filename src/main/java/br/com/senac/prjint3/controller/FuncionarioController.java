package br.com.senac.prjint3.controller;

import br.com.senac.prjint3.common.StatusRegistro;
import br.com.senac.prjint3.dto.funcionario.FuncionarioRequest;
import br.com.senac.prjint3.dto.funcionario.FuncionarioResponse;
import br.com.senac.prjint3.dto.funcionario.FuncionarioUpdateRequest;
import br.com.senac.prjint3.service.FuncionarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
@Tag(name = "Funcionários", description = "CRUD de funcionários com apagado lógico")
@CrossOrigin("*")    
public class FuncionarioController {
    private final FuncionarioService service;

    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<FuncionarioResponse> listar() {
        return service.listar().stream().map(FuncionarioResponse::from).toList();
    }

    @GetMapping("/ativos")
    public List<FuncionarioResponse> listarAtivos() {
        return service.listarAtivos().stream().map(FuncionarioResponse::from).toList();
    }

    @GetMapping("/{login}")
    public FuncionarioResponse buscar(@PathVariable String login) {
        return FuncionarioResponse.from(service.buscar(login));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FuncionarioResponse criar(@RequestBody @Valid FuncionarioRequest request) {
        return FuncionarioResponse.from(service.criar(request));
    }

    @PutMapping("/{login}")
    public FuncionarioResponse atualizar(@PathVariable String login, @RequestBody @Valid FuncionarioUpdateRequest request) {
        return FuncionarioResponse.from(service.atualizar(login, request));
    }

    @PatchMapping("/{login}/ativar")
    public FuncionarioResponse ativar(@PathVariable String login) {
        return FuncionarioResponse.from(service.alterarStatus(login, StatusRegistro.ATIVO));
    }

    @PatchMapping("/{login}/inativar")
    public FuncionarioResponse inativar(@PathVariable String login) {
        return FuncionarioResponse.from(service.alterarStatus(login, StatusRegistro.INATIVO));
    }

    @DeleteMapping("/{login}")
    public ResponseEntity<Void> apagar(@PathVariable String login) {
        service.apagarLogicamente(login);
        return ResponseEntity.noContent().build();
    }
}
