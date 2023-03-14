package com.example.biblioteca.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Entity
@Table(name = "copias")
public class Copia {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_copia")
    private Long idCopia;

    @Column(name = "editorial")
    private String editorial;
    @Column(name = "fd_adquisicion")
    @JsonInclude(NON_NULL)
    private Date fdAdquisicion;

    @ManyToOne
    @JoinColumn(name = "id_libro", referencedColumnName = "id_libro")
    @JsonIgnoreProperties({"copias"})
    private Libro libro;

    @OneToOne
    @JoinColumn(name = "id_copia", referencedColumnName = "id_copia")
    @JsonIgnoreProperties({"copias", "copiaEnPrestramo"})
    private Prestamo prestamoDeCopia;

    public Copia() {
        super();
    }

    public Copia(Long idCopia, String editorial, Date fdAdquisicion, Libro libro) {
        this.idCopia = idCopia;
        this.editorial = editorial;
        this.fdAdquisicion = fdAdquisicion;
        this.libro = libro;
    }

    public Long getIdCopia() {
        return idCopia;
    }

    public void setIdCopia(Long idCopia) {
        this.idCopia = idCopia;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public Date getFdAdquisicion() {
        return fdAdquisicion;
    }

    public void setFdAdquisicion(Date fdAdquisicion) {
        this.fdAdquisicion = fdAdquisicion;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Prestamo getPrestamoDeCopia() {
        return prestamoDeCopia;
    }

    @Override
    public String toString() {
        return "Copia{" +
                "idCopia=" + idCopia +
                ", editorial='" + editorial + '\'' +
                ", fdAdquisicion='" + fdAdquisicion + '\'' +
                ", libro=" + libro +
                '}';
    }
}
