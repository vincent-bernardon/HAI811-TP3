package com.example.tp3;
import androidx.room.Dao;
import androidx.room.Insert;
@Dao
public interface UtilisateurDAO {
    @Insert
    void insert(Utilisateur utilisateur);
}
