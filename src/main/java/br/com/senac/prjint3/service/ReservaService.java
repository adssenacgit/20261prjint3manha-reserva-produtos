package br.com.senac.prjint3.service;

import br.com.senac.prjint3.common.StatusRegistro;
import br.com.senac.prjint3.dto.reserva.ReservaRequest;
import br.com.senac.prjint3.exception.RecursoNaoEncontradoException;
import br.com.senac.prjint3.model.Cliente;
import br.com.senac.prjint3.model.Produto;
import br.com.senac.prjint3.model.Reserva;
import br.com.senac.prjint3.repository.ClienteRepository;
import br.com.senac.prjint3.repository.ProdutoRepository;
import br.com.senac.prjint3.repository.ReservaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservaService {
    private final ReservaRepository repository;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;

    public ReservaService(ReservaRepository repository, ProdutoRepository produtoRepository, ClienteRepository clienteRepository) {
        this.repository = repository;
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<Reserva> listar() {
        return repository.findByStatusNot(StatusRegistro.APAGADO);
    }

    public List<Reserva> listarAtivos() {
        return repository.findByStatus(StatusRegistro.ATIVO);
    }

    public Reserva buscar(Integer id) {
        return repository.findByIdAndStatusNot(id, StatusRegistro.APAGADO)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Reserva não encontrada."));
    }

    @Transactional
    public Reserva criar(ReservaRequest request) {
        Reserva reserva = new Reserva();
        preencher(reserva, request);
        return repository.save(reserva);
    }

    @Transactional
    public Reserva atualizar(Integer id, ReservaRequest request) {
        Reserva reserva = buscar(id);
        preencher(reserva, request);
        return repository.save(reserva);
    }

    @Transactional
    public Reserva alterarStatus(Integer id, Integer status) {
        StatusRegistro.validar(status);
        Reserva reserva = buscar(id);
        reserva.setStatus(status);
        return repository.save(reserva);
    }

    @Transactional
    public void apagarLogicamente(Integer id) {
        alterarStatus(id, StatusRegistro.APAGADO);
    }

    private void preencher(Reserva reserva, ReservaRequest request) {
        Produto produto = produtoRepository.findByIdAndStatusNot(request.produtoId(), StatusRegistro.APAGADO)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto informado para a reserva não foi encontrado."));

        Cliente cliente = clienteRepository.findByIdAndStatusNot(request.clienteId(), StatusRegistro.APAGADO)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente informado para a reserva não foi encontrado."));

        reserva.setProduto(produto);
        reserva.setCliente(cliente);
        reserva.setData(request.data());
        reserva.setPreco(request.preco());
        reserva.setStatus(StatusRegistro.normalizar(request.status()));
    }
}
