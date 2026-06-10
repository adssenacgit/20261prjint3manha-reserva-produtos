package br.com.senac.prjint3.controller;

import br.com.senac.prjint3.common.StatusRegistro;
import br.com.senac.prjint3.dto.produto.ProdutoRequest;
import br.com.senac.prjint3.dto.produto.ProdutoResponse;
import br.com.senac.prjint3.service.ProdutoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@Tag(name = "Produtos", description = "CRUD de produtos com apagado lógico")
public class ProdutoController {
    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProdutoResponse> listar() {
        return service.listar().stream().map(ProdutoResponse::from).toList();
    }

    @GetMapping("/ativos")
    public List<ProdutoResponse> listarAtivos() {
        return service.listarAtivos().stream().map(ProdutoResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ProdutoResponse buscar(@PathVariable Integer id) {
        return ProdutoResponse.from(service.buscar(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponse criar(@RequestBody @Valid ProdutoRequest request) {
        return ProdutoResponse.from(service.criar(request));
    }

    @PutMapping("/{id}")
    public ProdutoResponse atualizar(@PathVariable Integer id, @RequestBody @Valid ProdutoRequest request) {
        return ProdutoResponse.from(service.atualizar(id, request));
    }

    @PatchMapping("/{id}/ativar")
    public ProdutoResponse ativar(@PathVariable Integer id) {
        return ProdutoResponse.from(service.alterarStatus(id, StatusRegistro.ATIVO));
    }

    @PatchMapping("/{id}/inativar")
    public ProdutoResponse inativar(@PathVariable Integer id) {
        return ProdutoResponse.from(service.alterarStatus(id, StatusRegistro.INATIVO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Integer id) {
        service.apagarLogicamente(id);
        return ResponseEntity.noContent().build();
    }
}
