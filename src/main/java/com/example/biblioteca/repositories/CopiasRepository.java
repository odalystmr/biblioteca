package com.example.biblioteca.repositories;

import com.example.biblioteca.entities.Copia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CopiasRepository extends JpaRepository<Copia, Long>{
}
