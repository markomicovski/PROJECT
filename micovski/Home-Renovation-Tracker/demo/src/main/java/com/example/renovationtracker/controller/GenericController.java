package com.example.renovationtracker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public abstract class GenericController<T, ID> {

    protected abstract T createEntity(T entity);
    protected abstract List<T> getAllEntities();
    protected abstract Optional<T> getEntityById(ID id);
    protected abstract T updateEntity(ID id, T entity);
    protected abstract void deleteEntity(ID id);

    @PostMapping
    public T create(@RequestBody T entity) {
        return createEntity(entity);
    }

    @GetMapping
    public List<T> getAll() {
        return getAllEntities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable ID id) {
        return getEntityById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable ID id, @RequestBody T entity) {
        return ResponseEntity.ok(updateEntity(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        deleteEntity(id);
        return ResponseEntity.noContent().build();
    }
}
