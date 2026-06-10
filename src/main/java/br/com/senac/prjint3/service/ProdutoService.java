package br.com.senac.prjint3.service;

import br.com.senac.prjint3.common.StatusRegistro;
import br.com.senac.prjint3.dto.produto.ProdutoRequest;
import br.com.senac.prjint3.exception.RecursoNaoEncontradoException;
import br.com.senac.prjint3.model.Cliente;
import br.com.senac.prjint3.model.Produto;
import br.com.senac.prjint3.repository.ClienteRepository;
import br.com.senac.prjint3.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository repository;
    private final ClienteRepository clienteRepository;

    public ProdutoService(ProdutoRepository repository, ClienteRepository clienteRepository) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
    }

    public List<Produto> listar() {
        return repository.findByStatusNot(StatusRegistro.APAGADO);
    }

    public List<Produto> listarAtivos() {
        return repository.findByStatus(StatusRegistro.ATIVO);
    }

    public Produto buscar(Integer id) {
        return repository.findByIdAndStatusNot(id, StatusRegistro.APAGADO)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado."));
    }

    @Transactional
    public Produto criar(ProdutoRequest request) {
        Produto produto = new Produto();
        preencher(produto, request);
        return repository.save(produto);
    }

    @Transactional
    public Produto atualizar(Integer id, ProdutoRequest request) {
        Produto produto = buscar(id);
        preencher(produto, request);
        return repository.save(produto);
    }

    @Transactional
    public Produto alterarStatus(Integer id, Integer status) {
        StatusRegistro.validar(status);
        Produto produto = buscar(id);
        produto.setStatus(status);
        return repository.save(produto);
    }

    @Transactional
    public void apagarLogicamente(Integer id) {
        alterarStatus(id, StatusRegistro.APAGADO);
    }

    private void preencher(Produto produto, ProdutoRequest request) {
        Cliente cliente = clienteRepository.findByIdAndStatusNot(request.clienteId(), StatusRegistro.APAGADO)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente informado para o produto não foi encontrado."));

        produto.setCliente(cliente);
        produto.setDescricao(request.descricao());
        produto.setPreco(request.preco());
        produto.setDataDeCadastro(request.dataDeCadastro());
        produto.setTamanho(request.tamanho());
        produto.setGenero(request.genero());
        produto.setFaixaEtaria(request.faixaEtaria());
        produto.setStatus(StatusRegistro.normalizar(request.status()));
        produto.setImagem(request.imagem());
    }
}
