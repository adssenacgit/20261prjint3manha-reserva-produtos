package br.com.senac.prjint3.controller;

import br.com.senac.prjint3.common.StatusRegistro;
import br.com.senac.prjint3.dto.reserva.ReservaRequest;
import br.com.senac.prjint3.dto.reserva.ReservaResponse;
import br.com.senac.prjint3.service.ReservaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@Tag(name = "Reservas", description = "CRUD de reservas com apagado lógico")
public class ReservaController {
    private final ReservaService service;

    public ReservaController(ReservaService service) {
        this.service = service;
    }

    @GetMapping
    public List<ReservaResponse> listar() {
        return service.listar().stream().map(ReservaResponse::from).toList();
    }

    @GetMapping("/ativos")
    public List<ReservaResponse> listarAtivos() {
        return service.listarAtivos().stream().map(ReservaResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ReservaResponse buscar(@PathVariable Integer id) {
        return ReservaResponse.from(service.buscar(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservaResponse criar(@RequestBody @Valid ReservaRequest request) {
        return ReservaResponse.from(service.criar(request));
    }

    @PutMapping("/{id}")
    public ReservaResponse atualizar(@PathVariable Integer id, @RequestBody @Valid ReservaRequest request) {
        return ReservaResponse.from(service.atualizar(id, request));
    }

    @PatchMapping("/{id}/ativar")
    public ReservaResponse ativar(@PathVariable Integer id) {
        return ReservaResponse.from(service.alterarStatus(id, StatusRegistro.ATIVO));
    }

    @PatchMapping("/{id}/inativar")
    public ReservaResponse inativar(@PathVariable Integer id) {
        return ReservaResponse.from(service.alterarStatus(id, StatusRegistro.INATIVO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Integer id) {
        service.apagarLogicamente(id);
        return ResponseEntity.noContent().build();
    }
}
