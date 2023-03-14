package com.example.biblioteca.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Entity
@Table(name = "tarjetas")
public class Tarjeta {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_tarjeta")
    private Long idTarjeta;

    @Column(name = "active")
    private Boolean active;
    @Column(name = "fd_caducidad")
    private Date fdCaducidad;

    @Column(name = "fd_castigo")
    @JsonInclude (NON_NULL)
    private Date fdCastigo;

    @Column(name = "fd_creacion")
    private Date fdCreacion;

    @OneToOne
    @JoinColumn(name = "id_lector", referencedColumnName = "id_lector")
    @JsonIgnoreProperties({"tarjetas", "tarjetaDeLector"})
    private Lector propietarioTarjeta;

    public Tarjeta() {
    }

    public Tarjeta(Long idTarjeta, Boolean active, Date fdCaducidad, Date fdCastigo, Date fdCreacion, Lector propietarioTarjeta) {
        this.idTarjeta = idTarjeta;
        this.active = active;
        this.fdCaducidad = fdCaducidad;
        this.fdCastigo = fdCastigo;
        this.fdCreacion = fdCreacion;
        this.propietarioTarjeta = propietarioTarjeta;
    }

    public Long getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(Long idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getFdCaducidad() {
        return fdCaducidad;
    }

    public void setFdCaducidad(Date fdCaducidad) {
        this.fdCaducidad = fdCaducidad;
    }

    public Date getFdCastigo() {
        return fdCastigo;
    }

    public void setFdCastigo(Date fdCastigo) {
        this.fdCastigo = fdCastigo;
    }

    public Date getFdCreacion() {
        return fdCreacion;
    }

    public void setFdCreacion(Date fdCreacion) {
        this.fdCreacion = fdCreacion;
    }

    public Lector getPropietarioTarjeta() {
        return propietarioTarjeta;
    }

    public void setPropietarioTarjeta(Lector propietarioTarjeta) {
        this.propietarioTarjeta = propietarioTarjeta;
    }

    public Boolean isExpired() {
        if(fdCaducidad == null){
            return false;
        }
        return fdCaducidad.before(new Date());
    }

    public Boolean isPunished() {
        if(fdCastigo == null){
            return false;
        }
        return fdCastigo.after(new Date());
    }

    @Override
    public String toString() {
        return "Tarjeta{" +
                "idTarjeta=" + idTarjeta +
                ", active=" + active +
                ", fdCaducidad=" + fdCaducidad +
                ", fdCastigo=" + fdCastigo +
                ", fdCreacion=" + fdCreacion +
                ", propietarioTarjeta=" + propietarioTarjeta +
                '}';
    }
}
