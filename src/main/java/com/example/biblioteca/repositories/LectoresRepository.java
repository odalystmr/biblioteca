package com.example.biblioteca.repositories;

import com.example.biblioteca.entities.Lector;
import com.example.biblioteca.entities.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LectoresRepository extends JpaRepository<Lector, Long>{
    public Optional<Lector> getLectorByTarjetaDeLector(Tarjeta tarjetaDeLector);

}


