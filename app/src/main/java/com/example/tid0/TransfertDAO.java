package com.example.tid0;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TransfertDAO {

    @Insert
    void insert(Transfert transfert);

    @Update
    void update(Transfert transfert);

    @Delete
    void delete(Transfert transfert);

    @Query("DELETE FROM transfert")
    void deleteAllTransfers();

    @Query("select* FROM transfert")
    List<Transfert> SelectListeTransfer();

    @Query("select* FROM transfert where UNUMOF=:UNUMOF and UNUMP=:UNUMP")
   Transfert getTranfert(String UNUMOF,String UNUMP);



}
