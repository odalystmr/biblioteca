package com.example.biblioteca.requests;

import java.util.Date;

public class UpdatePrestamoRequestBody {

    private Long idTarjeta;
    private Long idCopia;
    private Boolean active;
    private Date fdDevolver;

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getFdDevolver() {
        return fdDevolver;
    }

    public void setFdDevolver(Date fdDevolver) {
        this.fdDevolver = fdDevolver;
    }
}
