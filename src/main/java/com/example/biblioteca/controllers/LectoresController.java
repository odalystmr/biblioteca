package com.example.biblioteca.controllers;

import com.example.biblioteca.entities.Lector;
import com.example.biblioteca.interfaces.ILectoresService;
import com.example.biblioteca.model.ErrorException;
import com.example.biblioteca.model.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lectores")
public class LectoresController {

    @Autowired
    private ILectoresService service;

    @GetMapping("/getAll")
    public ResponseEntity<?> findAll() {
        try {
            List<Lector> lectores = service.findAll();
            return ResponseEntity.ok(lectores);
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

    @PostMapping("/addLector")
    public ResponseEntity<?> save(@RequestBody Lector lector) {
        return ResponseEntity.ok(service.save(lector));
    }

    @PutMapping("/updateLector")
    public ResponseEntity<?> update(@RequestBody Lector lector) {
        try {
            return ResponseEntity.ok(service.update(lector));
        } catch (ErrorException e) {
            return new ResponseEntity<ResponseBase>(new ResponseBase(e), e.getIdStatus());
        }

    }

    @DeleteMapping("/deleteCopia/{idLector}")
    public ResponseEntity<?> delete(@PathVariable("idLector") Long idLector) {
        try {
            return ResponseEntity.ok(service.delete(idLector));
        } catch (ErrorException e) {
            return new ResponseEntity<ResponseBase>(new ResponseBase(e), e.getIdStatus());
        }
    }


}
