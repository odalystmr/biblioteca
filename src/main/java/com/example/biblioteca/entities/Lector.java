package com.example.biblioteca.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "lectores")
public class Lector {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lector")
    private Long idLector;
    @Column(name = "DNI")
    private String dni;
    @Column(name = "fd_nacimiento")
    private Date fdNacimiento;
    @Column(name = "nombre_completo")
    private String nombreCompleto;

    @OneToMany(mappedBy = "lector")
    @JsonIgnoreProperties("lector")
    private List<Prestamo> prestamos;

    @OneToOne(mappedBy = "propietarioTarjeta")
    @JsonIgnoreProperties({"lectores", "propietarioTarjeta"})
    private Tarjeta tarjetaDeLector;

    public Lector() {
        super();
    }

    public Lector(Long idLector, String dni, Date fdNacimiento, String nombreCompleto, List<Prestamo> prestamos, Tarjeta tarjetaDeLector) {
        this.idLector = idLector;
        this.dni = dni;
        this.fdNacimiento = fdNacimiento;
        this.nombreCompleto = nombreCompleto;
        this.prestamos = prestamos;
        this.tarjetaDeLector = tarjetaDeLector;
    }

    public Long getIdLector() {
        return idLector;
    }

    public void setIdLector(Long idLector) {
        this.idLector = idLector;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getFdNacimiento() {
        return fdNacimiento;
    }

    public void setFdNacimiento(Date fdNacimiento) {
        this.fdNacimiento = fdNacimiento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public Tarjeta getTarjetaDeLector() {
        return tarjetaDeLector;
    }

    public void setTarjetaDeLector(Tarjeta tarjetaDeLector) {
        this.tarjetaDeLector = tarjetaDeLector;
    }

    @Override
    public String toString() {
        return "Lector{" +
                "idLector=" + idLector +
                ", dni='" + dni + '\'' +
                ", fdNacimiento=" + fdNacimiento +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", prestamos=" + prestamos +
                ", tarjetaDeLector=" + tarjetaDeLector +
                '}';
    }



}
