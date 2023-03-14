package com.example.biblioteca.repositories;

import com.example.biblioteca.entities.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface LibrosRepository extends JpaRepository<Libro, Long> {

    List<Libro> searchLibrosByTituloLikeOrAutorLikeOrAreaLike(String titulo, String autor, String area);
}
