package br.com.senac.prjint3.controller;

import br.com.senac.prjint3.common.StatusRegistro;
import br.com.senac.prjint3.dto.midia.MidiaRequest;
import br.com.senac.prjint3.dto.midia.MidiaResponse;
import br.com.senac.prjint3.service.MidiaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping("/api/midias")
@Tag(name = "Mídias", description = "CRUD de mídias com apagado lógico")
@CrossOrigin("*")    
public class MidiaController {
    private final MidiaService service;

    public MidiaController(MidiaService service) {
        this.service = service;
    }

    @GetMapping
    public List<MidiaResponse> listar() {
        return service.listar().stream().map(MidiaResponse::from).toList();
    }

    @GetMapping("/ativos")
    public List<MidiaResponse> listarAtivos() {
        return service.listarAtivos().stream().map(MidiaResponse::from).toList();
    }

    @GetMapping("/{id}")
    public MidiaResponse buscar(@PathVariable Integer id) {
        return MidiaResponse.from(service.buscar(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MidiaResponse criar(@RequestBody @Valid MidiaRequest request) {
        return MidiaResponse.from(service.criar(request));
    }

    @PutMapping("/{id}")
    public MidiaResponse atualizar(@PathVariable Integer id, @RequestBody @Valid MidiaRequest request) {
        return MidiaResponse.from(service.atualizar(id, request));
    }

    @PatchMapping("/{id}/ativar")
    public MidiaResponse ativar(@PathVariable Integer id) {
        return MidiaResponse.from(service.alterarStatus(id, StatusRegistro.ATIVO));
    }

    @PatchMapping("/{id}/inativar")
    public MidiaResponse inativar(@PathVariable Integer id) {
        return MidiaResponse.from(service.alterarStatus(id, StatusRegistro.INATIVO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Integer id) {
        service.apagarLogicamente(id);
        return ResponseEntity.noContent().build();
    }
}
