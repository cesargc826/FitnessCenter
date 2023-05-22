package com.example.fitnesscenter.ui.gallery;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Caja.class}, version = 1)
public abstract class BDCaja extends RoomDatabase {

    public abstract MetodosDAO metodosDAO();

}
