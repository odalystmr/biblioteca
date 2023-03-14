package com.example.biblioteca.responses;

import com.example.biblioteca.entities.Lector;
import com.example.biblioteca.entities.Libro;


public class CopiaDisponibleResponse {
    private final Boolean disponible;
    private final Libro libro;
    private final Lector lector;

    public CopiaDisponibleResponse(Boolean disponible, Libro libro, Lector lector) {
        this.disponible = disponible;
        this.libro = libro;
        this.lector = lector;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public Libro getLibro() {
        return libro;
    }

    public Lector getLector() {
        return lector;
    }

}
