package com.example.tp3;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlaningDAO {
    @Insert
    void insert(Planning planning);

    @Query("SELECT * FROM planning WHERE userMail = :mail ORDER BY date DESC") //on récupère les plannings de l'utilisateur triés par date
    List<Planning> getPlanningsForUser(String mail);
}
