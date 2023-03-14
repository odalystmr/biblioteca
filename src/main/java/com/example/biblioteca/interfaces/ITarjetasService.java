package com.example.biblioteca.interfaces;

import com.example.biblioteca.entities.Tarjeta;
import com.example.biblioteca.model.ErrorException;

import java.util.List;

public interface ITarjetasService {

	public List<Tarjeta> findAll() throws ErrorException;
	
	public Tarjeta findById(Long id) throws ErrorException;
	
	public Tarjeta save(Tarjeta tarjeta);
	
	public Tarjeta update(Tarjeta tarjeta) throws ErrorException;
	
	public Tarjeta delete(Long id) throws ErrorException;
}
