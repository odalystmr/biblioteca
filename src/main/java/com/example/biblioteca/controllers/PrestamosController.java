package com.example.biblioteca.controllers;

import com.example.biblioteca.entities.Lector;
import com.example.biblioteca.entities.Prestamo;
import com.example.biblioteca.interfaces.IPrestamosService;
import com.example.biblioteca.model.ErrorException;
import com.example.biblioteca.model.ResponseBase;
import com.example.biblioteca.requests.AddPrestamoRequestBody;
import com.example.biblioteca.requests.DevolverPrestamoRequestBody;
import com.example.biblioteca.requests.UpdatePrestamoRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestamos")
public class PrestamosController {

    @Autowired
    private IPrestamosService service;

    @GetMapping("/getAll")
    public ResponseEntity<?> findAll() {
        try {
            List<Prestamo> prestamos = service.findAll();
//   Los prestamos llegan, dentro del ResponseEntity algo pasa y va mal
            return ResponseEntity.ok(prestamos);
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

    @PostMapping("/addPrestamo")
    public ResponseEntity<?> save(@RequestBody AddPrestamoRequestBody requestBody) {
        try {
            Prestamo createdPrestamo = service.save(
                    requestBody.getIdTarjeta(),
                    requestBody.getIdCopia()
            );

            return ResponseEntity.ok(createdPrestamo);
        } catch (ErrorException e) {
            return new ResponseEntity<ResponseBase>(new ResponseBase(e), e.getIdStatus());
        }
    }

    @PutMapping("/updatePrestamo")
    public ResponseEntity<?> update(@RequestBody UpdatePrestamoRequestBody requestBody) {
        try {
            Prestamo updatedPrestamo = service.update(
                    requestBody.getIdTarjeta(),
                    requestBody.getIdCopia(),
                    requestBody.getActive(),
                    requestBody.getFdDevolver()
            );

            return ResponseEntity.ok(updatedPrestamo);
        } catch (ErrorException e) {
            return new ResponseEntity<ResponseBase>(new ResponseBase(e), e.getIdStatus());
        }

    }

    @DeleteMapping("/deleteCopia/{idPrestamo}")
    public ResponseEntity<?> delete(@PathVariable("idPrestamo") Long idPrestamo) {
        try {
            return ResponseEntity.ok(service.delete(idPrestamo));
        } catch (ErrorException e) {
            return new ResponseEntity<ResponseBase>(new ResponseBase(e), e.getIdStatus());
        }
    }

    @GetMapping("/expired")
    public ResponseEntity<?> expired() {
        try {
            List<Lector> lectores = service.findAllExpired();
            return ResponseEntity.ok(lectores);
        } catch (ErrorException e) {
            return new ResponseEntity<ResponseBase>(new ResponseBase(e), e.getIdStatus());
        }

    }

    @PutMapping("/devolverPrestamo")
    public ResponseEntity<?> devolverPrestamo(@RequestBody DevolverPrestamoRequestBody requestBody) {
        try {
            Prestamo updatedPrestamo = service.devolverPrestamo(requestBody.getIdCopia());
            return ResponseEntity.ok(updatedPrestamo);
        } catch (ErrorException e) {
            return new ResponseEntity<ResponseBase>(new ResponseBase(e), e.getIdStatus());
        }

    }
}
