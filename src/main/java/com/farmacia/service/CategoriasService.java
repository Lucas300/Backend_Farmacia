package com.farmacia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.farmacia.model.Categorias;
import com.farmacia.repository.CategoriasRepository;

import jakarta.validation.Valid;

@Service
public class CategoriasService {

    @Autowired
    private CategoriasRepository categoriasRepository;

    public List<Categorias> getAll() {
        return categoriasRepository.findAll();
    }

    public Categorias getById(Long id) {
        return categoriasRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Categorias> getByDescricao(String descricao) {
        return categoriasRepository.findAllByDescricaoContainingIgnoreCase(descricao);
    }

    public Categorias create(@Valid Categorias categoria) {
        return categoriasRepository.save(categoria);
    }

    public Categorias update(@Valid Categorias categoria) {
        if (!categoriasRepository.existsById(categoria.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return categoriasRepository.save(categoria);
    }

    public void delete(Long id) {
        Categorias categoria = getById(id); // já trata NOT_FOUND
        categoriasRepository.delete(categoria);
    }
}
