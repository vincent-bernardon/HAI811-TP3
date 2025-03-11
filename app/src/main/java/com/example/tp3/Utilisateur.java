package com.example.tp3;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "utilisateur")
public class Utilisateur {
    @PrimaryKey @NonNull
    private String mail;




    private String login;
    private String pswd;
    private String nom;
    private String dateNaissance;
    private String numTelephone;
    private String interets;

    public Utilisateur(String login, String pswd, String nom, String dateNaissance, String numTelephone, String mail, String interets) {
        this.login = login;
        this.pswd = pswd;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.numTelephone = numTelephone;
        this.mail = mail;
        this.interets = interets;
    }

    public String getLogin() {
        return login;
    }

    public String getPswd() {
        return pswd;
    }

    public String getNom() {
        return nom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public String getNumTelephone() {
        return numTelephone;
    }

    public String getMail() {
        return mail;
    }

    public String getInterets() {
        return interets;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setNumTelephone(String numTelephone) {
        this.numTelephone = numTelephone;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setInterets(String interets) {
        this.interets = interets;
    }
}
