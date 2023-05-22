package com.example.fitnesscenter.BDInventario;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Inventario {

    @PrimaryKey(autoGenerate = true)
    public int identificador;
    public String fechaInventario;
    public int proteina;
    public int creatina;
    public int preworkout;
    public int agua;
    public int termogenico;


    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getFechaInventario() {
        return fechaInventario;
    }

    public void setFechaInventario(String fechaInventario) {
        this.fechaInventario = fechaInventario;
    }

    public int getProteina() {
        return proteina;
    }

    public void setProteina(int proteina) {
        this.proteina = proteina;
    }

    public int getCreatina() {
        return creatina;
    }

    public void setCreatina(int creatina) {
        this.creatina = creatina;
    }

    public int getPreworkout() {
        return preworkout;
    }

    public void setPreworkout(int preworkout) {
        this.preworkout = preworkout;
    }

    public int getAgua() {
        return agua;
    }

    public void setAgua(int agua) {
        this.agua = agua;
    }

    public int getTermogenico() {
        return termogenico;
    }

    public void setTermogenico(int termogenico) {
        this.termogenico = termogenico;
    }
}
