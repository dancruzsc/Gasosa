package com.example.olina.gasosa.util;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.olina.gasosa.model.Entrada;
import com.example.olina.gasosa.model.EntradaDao;

/**
 * Created by olina on 03/03/2018.
 */

@Database(entities = {Entrada.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    private static final Object sLock = new Object();
    private static AppDatabase INSTANCE;
    public abstract EntradaDao entradaDao();


    public static AppDatabase getInstance(Context context) {
        synchronized (sLock) {
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,
                        "gasosa-app").build();
            }
            return INSTANCE;
        }
    }


}
