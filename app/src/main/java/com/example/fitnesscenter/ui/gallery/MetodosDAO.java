package com.example.fitnesscenter.ui.gallery;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MetodosDAO {

    @Query("SELECT * FROM Caja")
    List<Caja> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(Caja caja);

    @Query("SELECT Cantidad FROM Caja WHERE id=(SELECT MAX(id) FROM Caja)")
    String CantidadActual();

    @Query("SELECT COUNT(*) FROM Caja")
    int CantidadRegistros();

    @Query("SELECT SUM(Cantidad) FROM Caja")
    int SumaCantidades();

    @Update
    void Update(Caja caja);

    @Delete
    void Delete(Caja caja);

    @Query("DELETE FROM Caja")
    void DeleteALL();


}
