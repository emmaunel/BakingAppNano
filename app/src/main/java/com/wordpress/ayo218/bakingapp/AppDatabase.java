package com.wordpress.ayo218.bakingapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.wordpress.ayo218.bakingapp.model.Recipes;

@Database(entities = Recipes.class, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final Object LOCK = new Object();
    private static final String DATABASE_TABLE = "favoriteDB";
    private static AppDatabase database;

    public static AppDatabase getsInstance(Context context) {
        if (database == null) {
            synchronized (LOCK) {
                database = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_TABLE)
                        .build();
            }
        }

        return database;
    }

    public abstract FavoriteDao favoriteDao();
}
