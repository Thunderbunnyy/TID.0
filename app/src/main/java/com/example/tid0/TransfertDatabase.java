package com.example.tid0;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Transfert.class}, version = 7)
public abstract class TransfertDatabase extends RoomDatabase {

    private static TransfertDatabase instance ;

    public abstract TransfertDAO transfertDAO();

    public static synchronized TransfertDatabase getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                     TransfertDatabase.class, "transfer_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }


}
