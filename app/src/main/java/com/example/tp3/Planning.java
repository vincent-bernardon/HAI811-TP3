package com.example.tp3;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "planning",
        foreignKeys = @ForeignKey(
            entity = Utilisateur.class,
            parentColumns = "mail", //parent don mail
            childColumns = "userMail", //enfant donc userMail (afin de préciser de quel table il vient)
            onDelete = ForeignKey.CASCADE)
        )
public class Planning {
    @PrimaryKey(autoGenerate = true) @NonNull //on aura un id auto généré pour chaque planning
    private int id;


    private String userMail;
    private String date; //yyyy-mm-dd
    private String planning_08_10;
    private String planning_10_12;
    private String planning_14_16;
    private String planning_16_18;


    public Planning(String date,String planning_08_10, String planning_10_12, String planning_14_16, String planning_16_18,String userMail) {
        this.date = date;
        this.planning_08_10 = planning_08_10;
        this.planning_10_12 = planning_10_12;
        this.planning_14_16 = planning_14_16;
        this.planning_16_18 = planning_16_18;
        this.userMail = userMail;
    }

    public int getId() {
        return id;
    }

    public String getUserMail() {
        return userMail;
    }

    public String getDate() {
        return date;
    }

    public String getPlanning_08_10() {
        return planning_08_10;
    }

    public String getPlanning_10_12() {
        return planning_10_12;
    }

    public String getPlanning_14_16() {
        return planning_14_16;
    }

    public String getPlanning_16_18() {
        return planning_16_18;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPlanning_08_10(String planning_08_10) {
        this.planning_08_10 = planning_08_10;
    }

    public void setPlanning_10_12(String planning_10_12) {
        this.planning_10_12 = planning_10_12;
    }

    public void setPlanning_14_16(String planning_14_16) {
        this.planning_14_16 = planning_14_16;
    }

    public void setPlanning_16_18(String planning_16_18) {
        this.planning_16_18 = planning_16_18;
    }
}
