package com.example.biblioteca.interfaces;

import com.example.biblioteca.entities.Copia;
import com.example.biblioteca.model.ErrorException;
import com.example.biblioteca.responses.CopiaDisponibleResponse;

import java.util.List;

public interface ICopiasService {

	public List<Copia> findAll() throws ErrorException;

	public Copia findById(Long id) throws ErrorException;

	public Copia save(Copia copia);

	public Copia update(Copia copia) throws ErrorException;

	public Copia delete(Long id) throws ErrorException;

	CopiaDisponibleResponse copiaDisponible(Long id) throws ErrorException;

	public boolean isDisponible(Long id);
}
