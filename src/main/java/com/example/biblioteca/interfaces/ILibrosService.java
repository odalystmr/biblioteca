package com.example.biblioteca.interfaces;

import com.example.biblioteca.entities.Libro;
import com.example.biblioteca.model.ErrorException;
import com.example.biblioteca.responses.SearchLibrosResponse;

import java.util.List;

public interface ILibrosService {

	public List<Libro> findAll() throws ErrorException;
	public SearchLibrosResponse search(String searchTerm) throws ErrorException;

	public Libro findById(Long id) throws ErrorException;
	
	public Libro save(Libro libro);
	
	public Libro update(Libro libro) throws ErrorException;
	
	public Libro delete(Long id) throws ErrorException;


}
