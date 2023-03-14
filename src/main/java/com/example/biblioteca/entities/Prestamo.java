package com.example.biblioteca.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "prestamos")
public class Prestamo {

    @Id
    @Column(name = "id_copia")
    private Long idCopia;

    @Column(name = "active")
    private Boolean active;
    @Column(name = "fd_devolver")
    private Date fdDevolver;

    @Column(name = "fd_prestamo")
    private Date fdPrestamo;

    @ManyToOne
    @JoinColumn(name = "id_lector", referencedColumnName = "id_lector")
    @JsonIgnoreProperties({"prestamos"})
    private Lector lector;

    @OneToOne(mappedBy = "prestamoDeCopia")
    @JsonIgnoreProperties({"prestamos", "prestamoDeCopia"})
    private Copia copiaEnPrestramo;


    public Prestamo() {
    }

    public Prestamo(Long idCopia, Boolean active, Date fdDevolver, Date fdPrestamo, Lector lector, Copia copiaEnPrestramo) {
        this.idCopia = idCopia;
        this.active = active;
        this.fdDevolver = fdDevolver;
        this.fdPrestamo = fdPrestamo;
        this.lector = lector;
        this.copiaEnPrestramo = copiaEnPrestramo;
    }


    public Long getIdCopia() {

        return idCopia;
    }

    public void setIdCopia(Long idCopia) {
        this.idCopia = idCopia;
    }

    public Boolean isActive() {
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

    public Date getFdPrestamo() {
        return fdPrestamo;
    }

    public void setFdPrestamo(Date fdPrestamo) {
        this.fdPrestamo = fdPrestamo;
    }

    public Lector getLector() {
        return lector;
    }


    public void setLector(Lector lector) {
        this.lector = lector;
    }

    public Copia getCopiaEnPrestramo() {
        return copiaEnPrestramo;
    }

    public void setCopiaEnPrestramo(Copia copiaEnPrestramo) {
        this.copiaEnPrestramo = copiaEnPrestramo;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "idCopia=" + idCopia +
                ", active=" + active +
                ", fdDevolver=" + fdDevolver +
                ", fdPrestamo=" + fdPrestamo +
                ", lector=" + lector +
                ", copiaEnPrestramo=" + copiaEnPrestramo +
                '}';
    }

    public Boolean isExpired() {
        if (fdDevolver == null) {
            return false;
        }
        return fdDevolver.before(new Date());
    }
}
