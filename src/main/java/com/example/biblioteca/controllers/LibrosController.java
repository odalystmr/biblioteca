package com.example.biblioteca.controllers;

import com.example.biblioteca.entities.Libro;
import com.example.biblioteca.interfaces.ILibrosService;

import com.example.biblioteca.model.ErrorException;
import com.example.biblioteca.model.ResponseBase;

import com.example.biblioteca.responses.SearchLibrosResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibrosController {
    @Autowired
    private ILibrosService service;

    @GetMapping("/getAll")
    public ResponseEntity<?> findAll() {
        try {
            List<Libro> libros = service.findAll();
            return ResponseEntity.ok(libros);
        } catch (ErrorException e) {
            return new ResponseEntity<ResponseBase>(new ResponseBase(e), e.getIdStatus());
        }

    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (ErrorException e) {
            return new ResponseEntity<ResponseBase>(new ResponseBase(e), e.getIdStatus());
        }

    }

    @PostMapping("/addLibro")
    public ResponseEntity<?> save(@RequestBody Libro libro) {
        return ResponseEntity.ok(service.save(libro));
    }

    @PutMapping("/updateLibro")
    public ResponseEntity<?> update(@RequestBody Libro libro) {
        try {
            return ResponseEntity.ok(service.update(libro));
        } catch (ErrorException e) {
            return new ResponseEntity<ResponseBase>(new ResponseBase(e), e.getIdStatus());
        }

    }

    @DeleteMapping("/deleteLibro/{idLibro}")
    public ResponseEntity<?> delete(@PathVariable("idLibro") Long idLibro) {
        try {
            return ResponseEntity.ok(service.delete(idLibro));
        } catch (ErrorException e) {
            return new ResponseEntity<ResponseBase>(new ResponseBase(e), e.getIdStatus());
        }
    }


    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(name = "s", defaultValue = "") String searchTerm) {
        try {
            SearchLibrosResponse response = service.search(searchTerm);
            return ResponseEntity.ok(response);
        } catch (ErrorException e) {
            return new ResponseEntity<ResponseBase>(new ResponseBase(e), e.getIdStatus());
        }

    }

}
