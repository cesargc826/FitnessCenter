package com.example.fitnesscenter.BDInventario;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DAOInventario {

    @Query("SELECT * FROM Inventario")
    List<Inventario> getInventarios();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTodo(Inventario inventario);

    @Query("SELECT fechaInventario FROM Inventario WHERE identificador=(SELECT MAX(identificador) FROM Inventario)")
    String LastFecha();

    @Query("SELECT proteina FROM Inventario WHERE identificador=(SELECT MAX(identificador) FROM Inventario)")
    int LastProteina();

    @Query("SELECT creatina FROM Inventario WHERE identificador=(SELECT MAX(identificador) FROM Inventario)")
    int LastCreatina();

    @Query("SELECT preworkout FROM Inventario WHERE identificador=(SELECT MAX(identificador) FROM Inventario)")
    int LastPreworkout();

    @Query("SELECT agua FROM Inventario WHERE identificador=(SELECT MAX(identificador) FROM Inventario)")
    int LastAgua();

    @Query("SELECT termogenico FROM Inventario WHERE identificador=(SELECT MAX(identificador) FROM Inventario)")
    int LastTermogenico();

    @Query("DELETE FROM Inventario")
    void DeleteInventario();

}
