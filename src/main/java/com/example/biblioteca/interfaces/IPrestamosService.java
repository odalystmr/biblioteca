package com.example.biblioteca.interfaces;

import com.example.biblioteca.entities.Lector;
import com.example.biblioteca.entities.Prestamo;
import com.example.biblioteca.model.ErrorException;

import java.util.Date;
import java.util.List;

public interface IPrestamosService {

	public List<Prestamo> findAll() throws ErrorException;
	
	public Prestamo findById(Long id) throws ErrorException;
	
	public Prestamo save(Long idTarjeta, Long idLector) throws ErrorException;
	
	public Prestamo update(Long idTarjeta, Long idCopia, Boolean active, Date fdDevolver) throws ErrorException;
	
	public Prestamo delete(Long id) throws ErrorException;

	Prestamo devolverPrestamo(Long idCopia) throws ErrorException;
	List<Lector> findAllExpired();



}
