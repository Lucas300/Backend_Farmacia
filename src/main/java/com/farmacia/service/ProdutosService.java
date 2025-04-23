package com.farmacia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.farmacia.model.Produtos;
import com.farmacia.repository.ProdutosRepository;

import jakarta.validation.Valid;

@Service
public class ProdutosService {

    @Autowired
    private ProdutosRepository produtosRepository;

    public List<Produtos> getAll() {
        return produtosRepository.findAll();
    }

    public Produtos getById(Long id) {
        return produtosRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Produtos> getByNome(String nome) {
        return produtosRepository.findAllByNomeContainingIgnoreCase(nome);
    }

    public Produtos create(@Valid Produtos produto) {
        return produtosRepository.save(produto);
    }

    public Produtos update(@Valid Produtos produto) {
        if (!produtosRepository.existsById(produto.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return produtosRepository.save(produto);
    }

    public void delete(Long id) {
        Produtos produto = getById(id); // já verifica se existe
        produtosRepository.delete(produto);
    }
}
