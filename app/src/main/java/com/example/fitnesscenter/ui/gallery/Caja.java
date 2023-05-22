package com.example.fitnesscenter.ui.gallery;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Caja {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String Fecha;
    @NonNull
    public String Cantidad;
    @NonNull
    public String Descripcion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String cantidad) {
        Cantidad = cantidad;
    }

    @NonNull
    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(@NonNull String descripcion) {
        Descripcion = descripcion;
    }
}
