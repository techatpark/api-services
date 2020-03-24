package com.example.demo.tracker.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.tracker.model.Namespace;
import com.example.demo.tracker.service.NamespaceService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/namespaces")
public class NamespaceAPIController {

    private final NamespaceService namespaceService;

    NamespaceAPIController(final NamespaceService namespaceService) {
        this.namespaceService = namespaceService;
    }

    @GetMapping
    public ResponseEntity<List<Namespace>> findAll() {
        return ResponseEntity.ok(namespaceService.list());
    }

    @PostMapping
    public ResponseEntity<Namespace> create(@Valid @RequestBody Namespace namespace) {
        return ResponseEntity.ok(namespaceService.create(namespace));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Namespace> findById(@PathVariable Integer id) {
        return ResponseEntity.of(namespaceService.read(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Namespace> update(@PathVariable Integer id, @Valid @RequestBody Namespace namespace) {
        return ResponseEntity.ok(namespaceService.update(id, namespace));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        namespaceService.delete(id);
        return ResponseEntity.ok().build();
    }
}