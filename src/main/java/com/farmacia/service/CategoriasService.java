package com.farmacia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.farmacia.model.Categorias;
import com.farmacia.repository.CategoriasRepository;

@Service
public class CategoriasService {

    @Autowired
    private CategoriasRepository categoriasRepository;

    public List<Categorias> buscarTodos() {
        return categoriasRepository.findAll();
    }

    public Optional<Categorias> buscarPorId(Long id) {
        return categoriasRepository.findById(id);
    }

    public List<Categorias> buscarPorDescricao(String descricao) {
        return categoriasRepository.findAllByDescricaoContainingIgnoreCase(descricao);
    }

    public Categorias salvar(Categorias categoria) {
        return categoriasRepository.save(categoria);
    }

//    public Optional<Categoria> atualizar(Categoria categoria) {
//        if (categoriaRepository.existsById(categoria.getId())) {
//            return Optional.of(categoriaRepository.save(categoria));
//        }
//        return Optional.empty();
//    }
    
    public Categorias atualizar(Categorias categoria) {
        if (categoria.getId() == null || !categoriasRepository.existsById(categoria.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada!");
        }

        return categoriasRepository.save(categoria);
    }


    public boolean deletar(Long id) {
        if (categoriasRepository.existsById(id)) {
            categoriasRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
