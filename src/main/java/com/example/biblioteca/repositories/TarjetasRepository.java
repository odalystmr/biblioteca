package com.example.biblioteca.repositories;

import com.example.biblioteca.entities.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarjetasRepository extends JpaRepository<Tarjeta, Long>{
}
