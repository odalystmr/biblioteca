package com.example.biblioteca.services;

import com.example.biblioteca.dtos.SearchLibrosElementDTO;
import com.example.biblioteca.entities.Libro;
import com.example.biblioteca.interfaces.ICopiasService;
import com.example.biblioteca.interfaces.ILibrosService;
import com.example.biblioteca.model.ErrorException;
import com.example.biblioteca.model.Errores;
import com.example.biblioteca.repositories.LibrosRepository;
import com.example.biblioteca.responses.SearchLibrosResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibrosService implements ILibrosService {

    @Autowired
    private LibrosRepository repositorio;
    @Autowired
    private ICopiasService copiasService;

    @Override
    public List<Libro> findAll() throws ErrorException {
        List<Libro> libros = repositorio.findAll();
        if (libros.isEmpty())
            throw new ErrorException(Errores.VACIO, HttpStatus.INTERNAL_SERVER_ERROR);

        return libros;
    }

    @Override
    public SearchLibrosResponse search(String searchTerm) throws ErrorException {
        List<Libro> libros = repositorio.searchLibrosByTituloLikeOrAutorLikeOrAreaLike("%" + searchTerm + "%", "%" + searchTerm + "%", "%" + searchTerm + "%");

        if (libros.isEmpty())
            throw new ErrorException(Errores.VACIO, HttpStatus.INTERNAL_SERVER_ERROR);

        SearchLibrosResponse response = new SearchLibrosResponse();

        for (Libro libro : libros) {
            Long numCopiasDisponibles = libro.getCopias()
                    .stream()
                    .filter(copia -> copiasService.isDisponible(copia.getIdCopia()))
                    .count();

            SearchLibrosElementDTO element = new SearchLibrosElementDTO(libro,numCopiasDisponibles);
            response.addElement(element);
        }

        return response;
    }

    @Override
    public Libro findById(Long id) throws ErrorException {
        return repositorio.findById(id).orElseThrow(() -> new ErrorException(Errores.NO_ENCONTRADO, HttpStatus.OK));
    }

    @Override
    public Libro save(Libro libro) {
        libro.setArea(null);
//		libro.setAutor(null);
//		libro.setTitulo(null);
        return repositorio.save(libro);
    }

    @Override
    public Libro update(Libro libro) throws ErrorException {
        Libro actual = this.findById(libro.getIdLibro());

        libro.setArea(actual.getArea());
        libro.setAutor(actual.getAutor());
        libro.setTitulo(actual.getTitulo());

        return repositorio.save(libro);
    }

    @Override
    public Libro delete(Long id) throws ErrorException {
        Libro libro = this.findById(id);

        repositorio.delete(libro);

        return libro;
    }
}
