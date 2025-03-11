package com.example.tp3;
import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Utilisateur.class}, version = 1) // version = 1 car c'est la première version de la base de données ; et entities = {Utilisateur.class} car on a une seule table dans la base de données
public abstract class BD extends RoomDatabase  {
    public abstract UtilisateurDAO utilisateurDAO();
}
