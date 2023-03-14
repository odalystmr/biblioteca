package com.example.biblioteca.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_libro")
    private Long idLibro;

    @Column(name = "area")
    private String area;
    @Column(name = "autor")
    private String autor;

    @Column(name = "titulo")
    private String titulo;

    @OneToMany(mappedBy = "libro")
    @JsonIgnoreProperties("libro")
    private List<Copia> copias;

    public Libro(){
        super();
    }

    public Libro(Long idLibro, String area, String autor, String titulo) {
        this.idLibro = idLibro;
        this.area = area;
        this.autor = autor;
        this.titulo = titulo;
    }

    public Long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Long idLibro) {
        this.idLibro = idLibro;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Copia> getCopias() {
        return copias;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "idLibro=" + idLibro +
                ", area='" + area + '\'' +
                ", autor='" + autor + '\'' +
                ", titulo='" + titulo + '\'' +
                '}';
    }
}
