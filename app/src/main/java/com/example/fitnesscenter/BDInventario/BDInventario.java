package com.example.fitnesscenter.BDInventario;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
        entities = {Inventario.class},
        version = 1
)
public abstract class BDInventario extends RoomDatabase {
    public abstract DAOInventario daoInventario();
}
