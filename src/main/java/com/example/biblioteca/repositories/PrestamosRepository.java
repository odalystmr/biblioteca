package com.example.biblioteca.repositories;

import com.example.biblioteca.entities.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamosRepository extends JpaRepository<Prestamo, Long>{

    public List<Prestamo> getPrestamosByLector_IdLector(Long id);
}
