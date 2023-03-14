package com.example.biblioteca.services;

import com.example.biblioteca.entities.Copia;
import com.example.biblioteca.entities.Lector;
import com.example.biblioteca.entities.Prestamo;
import com.example.biblioteca.entities.Tarjeta;
import com.example.biblioteca.interfaces.IPrestamosService;
import com.example.biblioteca.model.ErrorException;
import com.example.biblioteca.model.Errores;
import com.example.biblioteca.repositories.CopiasRepository;
import com.example.biblioteca.repositories.LectoresRepository;
import com.example.biblioteca.repositories.PrestamosRepository;
import com.example.biblioteca.repositories.TarjetasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrestamosService implements IPrestamosService {

    @Autowired
    private PrestamosRepository repositorio;
    @Autowired
    private LectoresRepository repositorioLectores;
    @Autowired
    private TarjetasRepository repositorioTarjetas;
    @Autowired
    private CopiasRepository repositorioCopias;


    @Override
    public List<Prestamo> findAll() throws ErrorException {
        List<Prestamo> prestamos = repositorio.findAll();

        if (prestamos.isEmpty()){
            throw new ErrorException(Errores.VACIO, HttpStatus.NOT_FOUND);
        }

        return prestamos;
    }

    @Override
    public Prestamo findById(Long id) throws ErrorException {
        return repositorio.findById(id).orElseThrow(() -> new ErrorException(Errores.NO_ENCONTRADO, HttpStatus.OK));
    }

    @Override
    public Prestamo save(Long idTarjeta, Long idCopia) throws ErrorException {
        Tarjeta tarjeta = repositorioTarjetas.findById(idTarjeta).orElseThrow(() -> new ErrorException(Errores.NO_ENCONTRADO, HttpStatus.OK));
        validateTarjetaLectorIsNotExpired(tarjeta);
        validateLectorIsNotPunished(tarjeta);

        Lector lector = repositorioLectores.getLectorByTarjetaDeLector(tarjeta).orElseThrow(() -> new ErrorException(Errores.NO_ENCONTRADO, HttpStatus.OK));
        validateLectorHasLessThanTwoPrestamos(lector);

        Copia copia = repositorioCopias.findById(idCopia).orElseThrow(() -> new ErrorException(Errores.NO_ENCONTRADO, HttpStatus.OK));
        validateCopiaIsNotInPrestamo(copia.getIdCopia());

        Date today = new Date();
        Date nextWeek = new Date(today.getTime() + (1000 * 60 * 60 * 24 * 7));
        Boolean isActive = true;

        Prestamo prestamo = new Prestamo(copia.getIdCopia(), isActive, nextWeek, today, lector, copia);


        return repositorio.save(prestamo);
    }

    private void validateCopiaIsNotInPrestamo(Long idCopia) {
        boolean prestamoExist = repositorio.findById(idCopia).isPresent();
        if(prestamoExist){
            throw new ErrorException(Errores.PRESTAMO_ALREADY_EXIST, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Prestamo update(Long idTarjeta, Long idCopia, Boolean active, Date fdDevolver) throws ErrorException {
        Copia copia = repositorioCopias.findById(idCopia).orElseThrow(() -> new ErrorException(Errores.NO_ENCONTRADO, HttpStatus.OK));

        Tarjeta tarjeta = repositorioTarjetas.findById(idTarjeta).orElseThrow(() -> new ErrorException(Errores.NO_ENCONTRADO, HttpStatus.OK));

        Lector lector = repositorioLectores.getLectorByTarjetaDeLector(tarjeta).orElseThrow(() -> new ErrorException(Errores.NO_ENCONTRADO, HttpStatus.OK));


        Prestamo prestamo = findById(copia.getIdCopia());
        prestamo.setActive(active);
        prestamo.setFdDevolver(fdDevolver);
        prestamo.setLector(lector);

        return repositorio.save(prestamo);
    }

    @Override
    public Prestamo delete(Long id) throws ErrorException {
        Prestamo prestamo = this.findById(id);

        repositorio.delete(prestamo);

        return prestamo;
    }

    private void validateTarjetaLectorIsNotExpired(Tarjeta tarjeta) {
        if (tarjeta.isExpired()) {
            throw new ErrorException(Errores.TARJETA_IS_EXPIRED, HttpStatus.BAD_REQUEST);
        }
    }

    private void validateLectorHasLessThanTwoPrestamos(Lector lector) {
        Long idLector = lector.getIdLector();
        int numPrestamos = repositorio.getPrestamosByLector_IdLector(idLector).size();

        if (numPrestamos >= 2) {
            throw new ErrorException(Errores.MAX_PRESTAMOS, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    private void validateLectorIsNotPunished(Tarjeta tarjeta) {
        if (tarjeta.isPunished()) {
            throw new ErrorException(Errores.LECTOR_IS_PUNISHED, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public List<Lector> findAllExpired() {
       List<Prestamo> prestamos = repositorio.findAll();
       List<Lector> expiredPrestamos = prestamos
               .stream()
               .filter(Prestamo::isExpired)
               .filter(Prestamo::isActive)
               .map(Prestamo::getLector)
               .collect(Collectors.toList());

       expiredPrestamos.forEach(this::punish);

       return expiredPrestamos;


    }

    private void punish(Lector lector) {
        Date today = new Date(); //lo castigo desde el día en que veo que no me ha dado el libro
        Date inThreeMonths = new Date(today.getTime() + (1000L * 60 * 60 * 24 * 30 * 3));

        Tarjeta tarjeta = lector.getTarjetaDeLector();
        tarjeta.setFdCastigo(inThreeMonths);

        repositorioTarjetas.save(tarjeta);
    }

    public Prestamo devolverPrestamo(Long idCopia)throws ErrorException{
        Prestamo prestamo =  repositorio.findById(idCopia).orElseThrow(() -> new ErrorException(Errores.NO_ENCONTRADO, HttpStatus.OK));

        prestamo.setActive(false);

        repositorio.save(prestamo);

        return prestamo;

    }
}
