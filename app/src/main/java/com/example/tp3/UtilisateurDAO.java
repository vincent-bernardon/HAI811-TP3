package com.example.tp3;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UtilisateurDAO {
    @Insert
    void insert(Utilisateur utilisateur);

    @Query("SELECT COUNT(*) FROM utilisateur WHERE mail = :email")
    int isEmailInUsed(String email);

    @Query("SELECT * FROM utilisateur WHERE mail = :email")
    Utilisateur getUtilisateurByEmail(String email);

    @Query("SELECT COUNT(*) FROM utilisateur WHERE login = :login")
    int isLoginInUsed(String login);

    @Query("SELECT * FROM utilisateur WHERE login = :login AND pswd = :password")
    Utilisateur getUtilisateur(String login, String password);
}
