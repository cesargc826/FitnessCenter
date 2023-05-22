package com.example.fitnesscenter.model;

public class ItemList {
    private String titulo;
    private String descripcion;
    private int imgResource;

    public ItemList(String titulo, String descripcion, int imgResource) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imgResource = imgResource;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getPrecio() {
        return descripcion;
    }

    public int getImgResource() {
        return imgResource;
    }


}
