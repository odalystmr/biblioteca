package com.example.biblioteca.model;

import org.springframework.http.HttpStatus;

public class ErrorException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7358330923614115585L;
	
	private Long id;
	private String mensaje;
	private HttpStatus idStatus;
	
	public ErrorException() {
		super();
	}

	public ErrorException(Errores error, HttpStatus idStatus) {
		super();
		this.id = error.getId();
		this.mensaje = error.getMensaje();
		this.idStatus = idStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public HttpStatus getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(HttpStatus idStatus) {
		this.idStatus = idStatus;
	}
	
}
