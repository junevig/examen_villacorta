package com.upn.examen_villacorta.entity;

public class Producto {
    private int id;
    private String marca;
    private String color;
    private String serie;
    private double precio_venta;
    private String anio_fabricacion;

    public Producto(int id, String marca, String color, String serie, double precio_venta, String anio_fabricacion) {
        this.id = id;
        this.marca = marca;
        this.color = color;
        this.serie = serie;
        this.precio_venta = precio_venta;
        this.anio_fabricacion = anio_fabricacion;
    }

    public Producto(String marca, String color, String serie, double precio_venta, String anio_fabricacion) {
        this.marca = marca;
        this.color = color;
        this.serie = serie;
        this.precio_venta = precio_venta;
        this.anio_fabricacion = anio_fabricacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public String getAnio_fabricacion() {
        return anio_fabricacion;
    }

    public void setAnio_fabricacion(String anio_fabricacion) {
        this.anio_fabricacion = anio_fabricacion;
    }
}
