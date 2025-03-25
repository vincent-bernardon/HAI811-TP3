package com.example.tp3;
import androidx.room.Room;
public class BDutils {
    private static BD bd;

    private BDutils() {
    }

    //singleton
    public static synchronized BD getBDInstance(android.content.Context applicationContext) {
        if (bd == null) {
            bd = Room.databaseBuilder(applicationContext, BD.class, "bd").build();
        }
        return bd;
    }
}
