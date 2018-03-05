package com.example.olina.gasosa.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by olina on 03/03/2018.
 */

@Dao
public interface EntradaDao {
    @Query("select * from entrada")
    public List<Entrada> getAll();

    @Query("select * from entrada where uid in (:ids)")
    public List<Entrada> loadAllByIds(int[] ids);

    @Insert
    public void insertEntradas(Entrada... entradas);

    @Update
    public void updateEntradas(Entrada... entradas);

    @Delete
    public void deleteEntradas(Entrada... entradas);
}
