package br.com.senac.prjint3.service;

import br.com.senac.prjint3.common.StatusRegistro;
import br.com.senac.prjint3.dto.midia.MidiaRequest;
import br.com.senac.prjint3.exception.RecursoNaoEncontradoException;
import br.com.senac.prjint3.model.Midia;
import br.com.senac.prjint3.model.Produto;
import br.com.senac.prjint3.repository.MidiaRepository;
import br.com.senac.prjint3.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MidiaService {
    private final MidiaRepository repository;
    private final ProdutoRepository produtoRepository;

    public MidiaService(MidiaRepository repository, ProdutoRepository produtoRepository) {
        this.repository = repository;
        this.produtoRepository = produtoRepository;
    }

    public List<Midia> listar() {
        return repository.findByStatusNot(StatusRegistro.APAGADO);
    }

    public List<Midia> listarAtivos() {
        return repository.findByStatus(StatusRegistro.ATIVO);
    }

    public Midia buscar(Integer id) {
        return repository.findByIdAndStatusNot(id, StatusRegistro.APAGADO)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Mídia não encontrada."));
    }

    @Transactional
    public Midia criar(MidiaRequest request) {
        Midia midia = new Midia();
        preencher(midia, request);
        return repository.save(midia);
    }

    @Transactional
    public Midia atualizar(Integer id, MidiaRequest request) {
        Midia midia = buscar(id);
        preencher(midia, request);
        return repository.save(midia);
    }

    @Transactional
    public Midia alterarStatus(Integer id, Integer status) {
        StatusRegistro.validar(status);
        Midia midia = buscar(id);
        midia.setStatus(status);
        return repository.save(midia);
    }

    @Transactional
    public void apagarLogicamente(Integer id) {
        alterarStatus(id, StatusRegistro.APAGADO);
    }

    private void preencher(Midia midia, MidiaRequest request) {
        Produto produto = produtoRepository.findByIdAndStatusNot(request.produtoId(), StatusRegistro.APAGADO)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto informado para a mídia não foi encontrado."));

        midia.setProduto(produto);
        midia.setUrl(request.url());
        midia.setStatus(StatusRegistro.normalizar(request.status()));
    }
}
