package com.inteligenciacomercia.controlinventarioapp;

import com.inteligenciacomercia.controlinventarioapp.models.Movimiento;
import com.inteligenciacomercia.controlinventarioapp.models.Producto;

import java.util.ArrayList;
import java.util.List;

public class Constantes {

    public static List<Producto> mProductos;

    static {
        mProductos = new ArrayList<>();
        mProductos.add(new Producto(1, "Leche Gloria 500 ml", "https://vivanda.vteximg.com.br/arquivos/ids/168354-1000-1000/949.jpg", 32, "P-100001"));
        mProductos.add(new Producto(2, "Arroz Costeño 750 g", "https://plazavea.vteximg.com.br/arquivos/ids/167131-1000-1000/Arroz-COSTE%C3%91O-Parboiled-selecto-Bolsa-750g.jpg", 60, "P-100002"));
        mProductos.add(new Producto(3, "Coca Cola Zero 500 ml", "https://s2.dia.es/medias/hc3/h62/9311516164126.jpg", 50, "P-100003"));
        mProductos.add(new Producto(4, "Leche Laive 600 ml", "https://assets.trome.pe/files/article_multimedia/uploads/2017/06/09/593ada5fa5952.jpeg", 40, "P-100004"));
        mProductos.add(new Producto(5, "Colgate Total Original", "http://www.colgateprofesional.es/Professional/v1/es/es/locale-assets/img/heros/Products-Slider-Toothpaste.jpg", 24, "P-100005"));
        mProductos.add(new Producto(6, "Aceite primor 500 ml", "https://wongfood.vteximg.com.br/arquivos/ids/158281-1000-1000/Aceite-Vegetal-Primor-Corazon-Botella-1-L-492845.jpg", 12, "P-100006"));
        mProductos.add(new Producto(7, "Milo 400 g", "http://www.merkacombos.com/52-large_default/milo.jpg", 27, "P-100007"));
        mProductos.add(new Producto(8, "Azucar Bell 2 Kg", "https://plazavea.vteximg.com.br/arquivos/ids/167134-1000-1000/Azucar-BELL%E2%80%99S-Rubia-refinada-Bolsa-2kg.jpg", 42, "P-100008"));
        mProductos.add(new Producto(9, "Harina Blanca Flor", "https://plazavea.vteximg.com.br/arquivos/ids/178264-1000-1000/680.jpg", 21, "P-100009"));
        mProductos.add(new Producto(10, "Mayonesa AlaCena 500 g", "https://vivanda.vteximg.com.br/arquivos/ids/162870-1000-1000/20020503.jpg", 43, "P-100010"));
    }

    public static List<Movimiento> mMovimientos;

    static {
        mMovimientos = new ArrayList<>();
    }

}
