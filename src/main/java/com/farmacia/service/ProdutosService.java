package com.farmacia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmacia.model.Produtos;
import com.farmacia.repository.ProdutosRepository;

@Service
public class ProdutosService {

    @Autowired
    private ProdutosRepository produtoRepository;

    public List<Produtos> buscarTodos() {
        return produtoRepository.findAll();
    }

    public Optional<Produtos> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public List<Produtos> buscarPorNome(String nome) {
        return produtoRepository.findAllByNomeContainingIgnoreCase(nome);
    }

    public Produtos salvar(Produtos produto) {
        return produtoRepository.save(produto);
    }

    public Optional<Produtos> atualizar(Produtos produto) {
        if (produtoRepository.existsById(produto.getId())) {
            return Optional.of(produtoRepository.save(produto));
        }
        return Optional.empty();
    }

    public boolean deletar(Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}