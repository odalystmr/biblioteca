package com.example.biblioteca.services;

import com.example.biblioteca.entities.Lector;
import com.example.biblioteca.interfaces.ILectoresService;
import com.example.biblioteca.model.ErrorException;
import com.example.biblioteca.model.Errores;
import com.example.biblioteca.repositories.LectoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LectoresService implements ILectoresService {

    @Autowired
    private LectoresRepository repositorio;

    @Override
    public List<Lector> findAll() throws ErrorException {
        List<Lector> lectores = repositorio.findAll();
        if (lectores.isEmpty())
            throw new ErrorException(Errores.VACIO, HttpStatus.INTERNAL_SERVER_ERROR);

        return lectores;
    }

    @Override
    public Lector findById(Long id) throws ErrorException {
        return repositorio.findById(id).orElseThrow(() -> new ErrorException(Errores.NO_ENCONTRADO, HttpStatus.OK));
    }

    @Override
    public Lector save(Lector lector) {
        lector.setDni(null);
        lector.setFdNacimiento(new Date());
        lector.setNombreCompleto(null);

        return repositorio.save(lector);
    }

    @Override
    public Lector update(Lector lector) throws ErrorException {
        Lector actual = this.findById(lector.getIdLector());

        lector.setDni((actual.getDni()));
        lector.setFdNacimiento(actual.getFdNacimiento());
        lector.setNombreCompleto(actual.getNombreCompleto());

        return repositorio.save(lector);
    }

    @Override
    public Lector delete(Long id) throws ErrorException {
        Lector lector = this.findById(id);

        repositorio.delete(lector);

        return lector;
    }



}
