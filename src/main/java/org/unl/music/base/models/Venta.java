package org.unl.music.base.models;

import java.time.LocalDate;
import java.util.Date;

public class Venta {
    private Integer id;
    private Date fecha;
    private String nombreProducto;
    private Integer cantidad;
    private MetodoPagoEnum tipo;
    private Double precio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public MetodoPagoEnum getTipo() {
        return tipo;
    }

    public void setTipo(MetodoPagoEnum tipo) {
        this.tipo = tipo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
