package com.example.biblioteca.controllers;

import com.example.biblioteca.entities.Tarjeta;
import com.example.biblioteca.interfaces.ITarjetasService;
import com.example.biblioteca.model.ErrorException;
import com.example.biblioteca.model.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarjetas")
public class TarjetasController {

    @Autowired
    private ITarjetasService service;

    @GetMapping("/getAll")
    public ResponseEntity<?> findAll() {
        try {
            List<Tarjeta> tarjetas = service.findAll();
            return ResponseEntity.ok(tarjetas);
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

    @PostMapping("/addTarjeta")
    public ResponseEntity<?> save(@RequestBody Tarjeta tarjeta) {
        return ResponseEntity.ok(service.save(tarjeta));
    }

    @PutMapping("/updateTarjeta")
    public ResponseEntity<?> update(@RequestBody Tarjeta tarjeta) {
        try {
            return ResponseEntity.ok(service.update(tarjeta));
        } catch (ErrorException e) {
            return new ResponseEntity<ResponseBase>(new ResponseBase(e), e.getIdStatus());
        }

    }

    @DeleteMapping("/deleteCopia/{idTarjeta}")
    public ResponseEntity<?> delete(@PathVariable("idTarjeta") Long idTarjeta) {
        try {
            return ResponseEntity.ok(service.delete(idTarjeta));
        } catch (ErrorException e) {
            return new ResponseEntity<ResponseBase>(new ResponseBase(e), e.getIdStatus());
        }
    }
}
