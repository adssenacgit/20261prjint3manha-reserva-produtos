package br.com.senac.prjint3.controller;

import br.com.senac.prjint3.common.StatusRegistro;
import br.com.senac.prjint3.dto.venda.VendaRequest;
import br.com.senac.prjint3.dto.venda.VendaResponse;
import br.com.senac.prjint3.service.VendaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendas")
@Tag(name = "Vendas", description = "CRUD de vendas com apagado lógico")
public class VendaController {
    private final VendaService service;

    public VendaController(VendaService service) {
        this.service = service;
    }

    @GetMapping
    public List<VendaResponse> listar() {
        return service.listar().stream().map(VendaResponse::from).toList();
    }

    @GetMapping("/ativos")
    public List<VendaResponse> listarAtivos() {
        return service.listarAtivos().stream().map(VendaResponse::from).toList();
    }

    @GetMapping("/{id}")
    public VendaResponse buscar(@PathVariable Integer id) {
        return VendaResponse.from(service.buscar(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendaResponse criar(@RequestBody @Valid VendaRequest request) {
        return VendaResponse.from(service.criar(request));
    }

    @PutMapping("/{id}")
    public VendaResponse atualizar(@PathVariable Integer id, @RequestBody @Valid VendaRequest request) {
        return VendaResponse.from(service.atualizar(id, request));
    }

    @PatchMapping("/{id}/ativar")
    public VendaResponse ativar(@PathVariable Integer id) {
        return VendaResponse.from(service.alterarStatus(id, StatusRegistro.ATIVO));
    }

    @PatchMapping("/{id}/inativar")
    public VendaResponse inativar(@PathVariable Integer id) {
        return VendaResponse.from(service.alterarStatus(id, StatusRegistro.INATIVO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Integer id) {
        service.apagarLogicamente(id);
        return ResponseEntity.noContent().build();
    }
}
