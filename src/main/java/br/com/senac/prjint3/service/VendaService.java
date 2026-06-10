package br.com.senac.prjint3.service;

import br.com.senac.prjint3.common.StatusRegistro;
import br.com.senac.prjint3.dto.venda.VendaRequest;
import br.com.senac.prjint3.exception.RecursoNaoEncontradoException;
import br.com.senac.prjint3.model.Produto;
import br.com.senac.prjint3.model.Venda;
import br.com.senac.prjint3.repository.ProdutoRepository;
import br.com.senac.prjint3.repository.VendaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VendaService {
    private final VendaRepository repository;
    private final ProdutoRepository produtoRepository;

    public VendaService(VendaRepository repository, ProdutoRepository produtoRepository) {
        this.repository = repository;
        this.produtoRepository = produtoRepository;
    }

    public List<Venda> listar() {
        return repository.findByStatusNot(StatusRegistro.APAGADO);
    }

    public List<Venda> listarAtivos() {
        return repository.findByStatus(StatusRegistro.ATIVO);
    }

    public Venda buscar(Integer id) {
        return repository.findByIdAndStatusNot(id, StatusRegistro.APAGADO)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Venda não encontrada."));
    }

    @Transactional
    public Venda criar(VendaRequest request) {
        Venda venda = new Venda();
        preencher(venda, request);
        return repository.save(venda);
    }

    @Transactional
    public Venda atualizar(Integer id, VendaRequest request) {
        Venda venda = buscar(id);
        preencher(venda, request);
        return repository.save(venda);
    }

    @Transactional
    public Venda alterarStatus(Integer id, Integer status) {
        StatusRegistro.validar(status);
        Venda venda = buscar(id);
        venda.setStatus(status);
        return repository.save(venda);
    }

    @Transactional
    public void apagarLogicamente(Integer id) {
        alterarStatus(id, StatusRegistro.APAGADO);
    }

    private void preencher(Venda venda, VendaRequest request) {
        Produto produto = produtoRepository.findByIdAndStatusNot(request.produtoId(), StatusRegistro.APAGADO)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto informado para a venda não foi encontrado."));

        venda.setData(request.data());
        venda.setValor(request.valor());
        venda.setProduto(produto);
        venda.setStatus(StatusRegistro.normalizar(request.status()));
    }
}
