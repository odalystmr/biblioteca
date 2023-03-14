package com.example.biblioteca.requests;

public class AddPrestamoRequestBody {
    private Long idTarjeta;
    private Long idCopia;

    public Long getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(Long idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public Long getIdCopia() {
        return idCopia;
    }

    public void setIdCopia(Long idCopia) {
        this.idCopia = idCopia;
    }
}
