package com.example.biblioteca.model;

public enum Errores {

	NO_ENCONTRADO(1L,"No encontrado"),
	DESCONOCIDO(2L,"Desconocido"),
	VACIO(3L,"Lista vacia"),
	MAX_PRESTAMOS(4L,"El lector ha alcanzado el número máximo de prestamos"),
	TARJETA_IS_EXPIRED(5L,"La tarjeta del lector está caducada"),
	LECTOR_IS_PUNISHED(6L,"El lector está castigado"),
	PRESTAMO_ALREADY_EXIST(7L,"La copia ya esta en prestamo");


	private Long id;
	private String mensaje;
	
	private Errores(Long id, String mensaje) {
		this.id = id;
		this.mensaje = mensaje;
	}
	
	public Long getId() {
		return id;
	}

	public String getMensaje() {
		return mensaje;
	}
	
}
