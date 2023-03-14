package com.example.biblioteca.services;

import com.example.biblioteca.entities.Copia;
import com.example.biblioteca.entities.Lector;
import com.example.biblioteca.entities.Libro;
import com.example.biblioteca.interfaces.ICopiasService;
import com.example.biblioteca.model.ErrorException;
import com.example.biblioteca.model.Errores;
import com.example.biblioteca.repositories.CopiasRepository;
import com.example.biblioteca.repositories.LectoresRepository;
import com.example.biblioteca.repositories.PrestamosRepository;
import com.example.biblioteca.responses.CopiaDisponibleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CopiasService implements ICopiasService {

    @Autowired
    private CopiasRepository repositorio;
    @Autowired
    private PrestamosRepository repositorioPrestamo;
    @Autowired
    private LectoresRepository repositorioLectores;

    @Override
    public List<Copia> findAll() throws ErrorException {
        List<Copia> copias = repositorio.findAll();

        if (copias.isEmpty())
            throw new ErrorException(Errores.VACIO, HttpStatus.NOT_FOUND);

        return copias;
    }

    @Override
    public Copia findById(Long id) throws ErrorException {
        return repositorio.findById(id).orElseThrow(() -> new ErrorException(Errores.NO_ENCONTRADO, HttpStatus.OK));
    }

    @Override
    public Copia save(Copia copia) {
        copia.setFdAdquisicion(new Date());

        return repositorio.save(copia);
    }

    @Override
    public Copia update(Copia copia) throws ErrorException {
        Copia actual = this.findById(copia.getIdCopia());

        copia.setEditorial(actual.getEditorial());
        copia.setFdAdquisicion(actual.getFdAdquisicion());

        return repositorio.save(copia);
    }

    @Override
    public Copia delete(Long id) throws ErrorException {
        Copia copia = this.findById(id);

        repositorio.delete(copia);

        return copia;
    }

    public CopiaDisponibleResponse copiaDisponible(Long id) throws ErrorException {
        Copia copiaEnPrestamo = this.findById(id);
        boolean disponible = isDisponible(id);
        Lector lectorConCopia = null;

        if (!disponible) {
            lectorConCopia = repositorioPrestamo
                    .findById(id)
                    .orElseThrow(() -> new ErrorException(Errores.NO_ENCONTRADO, HttpStatus.OK))
                    .getLector();
        }

        Libro libroEnPrestamo = copiaEnPrestamo.getLibro();

        return new CopiaDisponibleResponse(disponible, libroEnPrestamo, lectorConCopia);
    }

    public boolean isDisponible(Long id) {
        return !repositorioPrestamo.findById(id).isPresent();
    }

}
