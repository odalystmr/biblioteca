package com.example.biblioteca.model;

public class ResponseBase {
	
	private Long id;
	private String mensaje;
	
	public ResponseBase(Long id, String mensaje) {
		super();
		this.id = id;
		this.mensaje = mensaje;
	}

	public ResponseBase(ErrorException e) {
		super();
		this.id = e.getId();
		this.mensaje = e.getMensaje();
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
	
}
