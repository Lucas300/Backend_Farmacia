package com.farmacia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmacia.model.Categorias;
import com.farmacia.service.CategoriasService;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriasController {

    @Autowired
    private CategoriasService categoriaService;

    @GetMapping
    public ResponseEntity<List<Categorias>> getAll() {
        return ResponseEntity.ok(categoriaService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categorias> getById(@PathVariable Long id) {
        return categoriaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<Categorias>> getByDescricao(@PathVariable String descricao) {
        return ResponseEntity.ok(categoriaService.buscarPorDescricao(descricao));
    }

    @PostMapping
    public ResponseEntity<Categorias> post(@RequestBody Categorias categoria) {
        return ResponseEntity.status(201).body(categoriaService.salvar(categoria));
    }

    
    @PutMapping
    public ResponseEntity<Categorias> put(@RequestBody Categorias categoria) {
        return ResponseEntity.ok(categoriaService.atualizar(categoria));
    }

    
//    @PutMapping
//    public ResponseEntity<Categoria> put(@RequestBody Categoria categoria) {
//        return categoriaService.atualizar(categoria)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return categoriaService.deletar(id) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
}