package com.example.tp3;
import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Utilisateur.class, Planning.class}, version = 2) // version = 2 car on a ajouté la table planning
public abstract class BD extends RoomDatabase  {
    public abstract UtilisateurDAO utilisateurDAO();
    public abstract PlaningDAO planingDAO();
}
