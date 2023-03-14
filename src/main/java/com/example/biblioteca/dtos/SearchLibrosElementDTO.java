package com.example.biblioteca.dtos;

import com.example.biblioteca.entities.Libro;

public class SearchLibrosElementDTO {
    private Libro libro;
    private Long copiasDisponibles;

    public SearchLibrosElementDTO(Libro libro, Long copiasDisponibles) {
        this.libro = libro;
        this.copiasDisponibles = copiasDisponibles;
    }

    public Libro getLibro() {
        return libro;
    }

    public Long getCopiasDisponibles() {
        return copiasDisponibles;
    }


}
