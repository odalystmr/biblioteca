package com.example.biblioteca.controllers;

import com.example.biblioteca.entities.Copia;
import com.example.biblioteca.interfaces.ICopiasService;
import com.example.biblioteca.model.ErrorException;
import com.example.biblioteca.model.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/copias")
public class CopiasController {

    @Autowired
    private ICopiasService service;

    @GetMapping("/getAll")
    public ResponseEntity<?> findAll() {
        try {
            List<Copia> copias = service.findAll();
            return ResponseEntity.ok(copias);
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

    @PostMapping("/addCopia")
    public ResponseEntity<?> save(@RequestBody Copia copia) {
        return ResponseEntity.ok(service.save(copia));
    }

    @PutMapping("/updateCopia")
    public ResponseEntity<?> update(@RequestBody Copia copia) {
        try {
            return ResponseEntity.ok(service.update(copia));
        } catch (ErrorException e) {
            return new ResponseEntity<ResponseBase>(new ResponseBase(e), e.getIdStatus());
        }

    }

    @DeleteMapping("/deleteCopia/{idCopia}")
    public ResponseEntity<?> delete(@PathVariable("idCopia") Long idCopia) {
        try {
            return ResponseEntity.ok(service.delete(idCopia));
        } catch (ErrorException e) {
            return new ResponseEntity<ResponseBase>(new ResponseBase(e), e.getIdStatus());
        }
    }

    @GetMapping("/copiaDisponible/{id}")
    public ResponseEntity<?> copiaDisponible(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(service.copiaDisponible(id));
        } catch (ErrorException e) {
            return new ResponseEntity<ResponseBase>(new ResponseBase(e), e.getIdStatus());
        }

    }
}
