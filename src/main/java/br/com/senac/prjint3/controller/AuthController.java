package br.com.senac.prjint3.controller;

import br.com.senac.prjint3.dto.auth.ClienteLoginRequest;
import br.com.senac.prjint3.dto.auth.ClienteLoginResponse;
import br.com.senac.prjint3.model.Cliente;
import br.com.senac.prjint3.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final ClienteRepository clienteRepository;

    public AuthController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping("/cliente")
    public ResponseEntity<ClienteLoginResponse> autenticarCliente(@Valid @RequestBody ClienteLoginRequest request) {

        Optional<Cliente> clienteEncontrado = clienteRepository.findByEmailAndSenhaAndStatus(
                request.getEmail(),
                request.getSenha(),
                1
        );

        if (clienteEncontrado.isEmpty()) {
            ClienteLoginResponse response = new ClienteLoginResponse(
                    false,
                    null,
                    null,
                    null,
                    request.getEmail()
            );

            return ResponseEntity.ok(response);
        }

        Cliente cliente = clienteEncontrado.get();

        String tokenFicticio = "TOKEN-FICTICIO-CLIENTE-" + cliente.getId();

        ClienteLoginResponse response = new ClienteLoginResponse(
                true,
                tokenFicticio,
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail()
        );

        return ResponseEntity.ok(response);
    }
}