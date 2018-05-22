package com.inteligenciacomercia.controlinventarioapp.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Movimiento implements Serializable{

    private int id;
    private String fechaHora;
    private int tipo;
    private int cantidad;
    private Producto producto;

    public Movimiento(){
        fechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    }

    public Movimiento(int id, String fechaHora, int tipo, int cantidad, Producto producto) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
