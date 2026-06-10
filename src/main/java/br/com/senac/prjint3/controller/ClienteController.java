package br.com.senac.prjint3.controller;

import br.com.senac.prjint3.common.StatusRegistro;
import br.com.senac.prjint3.dto.cliente.ClienteRequest;
import br.com.senac.prjint3.dto.cliente.ClienteResponse;
import br.com.senac.prjint3.service.ClienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "CRUD de clientes com apagado lógico")
public class ClienteController {
    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<ClienteResponse> listar() {
        return service.listar().stream().map(ClienteResponse::from).toList();
    }

    @GetMapping("/ativos")
    public List<ClienteResponse> listarAtivos() {
        return service.listarAtivos().stream().map(ClienteResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ClienteResponse buscar(@PathVariable Integer id) {
        return ClienteResponse.from(service.buscar(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponse criar(@RequestBody @Valid ClienteRequest request) {
        return ClienteResponse.from(service.criar(request));
    }

    @PutMapping("/{id}")
    public ClienteResponse atualizar(@PathVariable Integer id, @RequestBody @Valid ClienteRequest request) {
        return ClienteResponse.from(service.atualizar(id, request));
    }

    @PatchMapping("/{id}/ativar")
    public ClienteResponse ativar(@PathVariable Integer id) {
        return ClienteResponse.from(service.alterarStatus(id, StatusRegistro.ATIVO));
    }

    @PatchMapping("/{id}/inativar")
    public ClienteResponse inativar(@PathVariable Integer id) {
        return ClienteResponse.from(service.alterarStatus(id, StatusRegistro.INATIVO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Integer id) {
        service.apagarLogicamente(id);
        return ResponseEntity.noContent().build();
    }
}
