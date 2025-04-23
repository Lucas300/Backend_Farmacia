package com.farmacia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.farmacia.model.Categorias;
import com.farmacia.service.CategoriasService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriasController {

    @Autowired
    private CategoriasService categoriasService;

    @GetMapping
    public ResponseEntity<List<Categorias>> getAll() {
        return ResponseEntity.ok(categoriasService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categorias> getById(@PathVariable Long id) {
        return ResponseEntity.ok(categoriasService.getById(id));
    }

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<Categorias>> getByDescricao(@PathVariable String descricao) {
        return ResponseEntity.ok(categoriasService.getByDescricao(descricao));
    }

    @PostMapping
    public ResponseEntity<Categorias> post(@Valid @RequestBody Categorias categoria) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoriasService.create(categoria));
    }

    @PutMapping
    public ResponseEntity<Categorias> put(@Valid @RequestBody Categorias categoria) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoriasService.update(categoria));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoriasService.delete(id);
    }
}
