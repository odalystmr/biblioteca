package com.example.biblioteca.services;

import com.example.biblioteca.entities.Tarjeta;
import com.example.biblioteca.interfaces.ITarjetasService;
import com.example.biblioteca.model.ErrorException;
import com.example.biblioteca.model.Errores;
import com.example.biblioteca.repositories.TarjetasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TarjetasService implements ITarjetasService {

    @Autowired
    private TarjetasRepository repositorio;

    @Override
    public List<Tarjeta> findAll() throws ErrorException {
        List<Tarjeta> tarjetas = repositorio.findAll();
        if (tarjetas.isEmpty())
            throw new ErrorException(Errores.VACIO, HttpStatus.INTERNAL_SERVER_ERROR);

        return tarjetas;
    }

    @Override
    public Tarjeta findById(Long id) throws ErrorException {
        return repositorio.findById(id).orElseThrow(() -> new ErrorException(Errores.NO_ENCONTRADO, HttpStatus.OK));
    }

    @Override
    public Tarjeta save(Tarjeta tarjeta) {
        tarjeta.setActive(true);
        tarjeta.setFdCaducidad(new Date());
        tarjeta.setFdCastigo(new Date());
        tarjeta.setFdCreacion(new Date());
        tarjeta.setPropietarioTarjeta(null);

        return repositorio.save(tarjeta);
    }

    @Override
    public Tarjeta update(Tarjeta tarjeta) throws ErrorException {
        Tarjeta actual = this.findById(tarjeta.getIdTarjeta());

        tarjeta.setActive(actual.isActive());
        tarjeta.setFdCaducidad(actual.getFdCaducidad());
        tarjeta.setFdCastigo(actual.getFdCastigo());
        tarjeta.setFdCreacion(actual.getFdCreacion());
        tarjeta.setPropietarioTarjeta(actual.getPropietarioTarjeta());

        return repositorio.save(tarjeta);
    }

    @Override
    public Tarjeta delete(Long id) throws ErrorException {
        Tarjeta tarjeta = this.findById(id);

        repositorio.delete(tarjeta);

        return tarjeta;
    }

}
