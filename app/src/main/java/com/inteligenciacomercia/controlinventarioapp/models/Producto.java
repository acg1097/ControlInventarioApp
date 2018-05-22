package com.inteligenciacomercia.controlinventarioapp.models;

import java.io.Serializable;

public class Producto implements Serializable{

    private int id;
    private String descripcion;
    private String urlImagen;
    private int stock;
    private String codigoBarra;

    public Producto(int id, String descripcion, String urlImagen, int stock, String codigoBarra) {
        this.id = id;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
        this.stock = stock;
        this.codigoBarra = codigoBarra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }
}
