package com.example.biblioteca.interfaces;

import com.example.biblioteca.entities.Lector;
import com.example.biblioteca.model.ErrorException;

import java.util.List;

public interface ILectoresService {

	public List<Lector> findAll() throws ErrorException;
	
	public Lector findById(Long id) throws ErrorException;
	
	public Lector save(Lector lector);
	
	public Lector update(Lector lector) throws ErrorException;
	
	public Lector delete(Long id) throws ErrorException;

}
