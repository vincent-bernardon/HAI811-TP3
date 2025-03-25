package com.example.tp3;
import androidx.room.Room;
public class BDutils {
    private static BD bd;

    private BDutils() {
    }

    //singleton
    public static synchronized BD getBDInstance(android.content.Context applicationContext) {
        if (bd == null) {
            bd = Room.databaseBuilder(applicationContext, BD.class, "bd").fallbackToDestructiveMigration().build(); //car entre temp on est passer de la version 1 a 2 donc cela ne correspon plus
        }
        return bd;
    }
}
