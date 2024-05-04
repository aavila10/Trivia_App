package com.example.trivia_app;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = User.class, version = 1)
public abstract class AppDatabase extends RoomDatabase{
    public static final String DB_NAME = "TRIVIA_DATABASE";

    public static final String USER_TABLE = "USER_TABLE";

    public static final String SCORE_TABLE = "SCORE_TABLE";

    private static volatile AppDatabase instance;
    private static final Object LOCK = new Object();

    public static AppDatabase getInstance(Context context){
        if (instance == null){
            synchronized (LOCK){
                if (instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }
    public abstract UserDao getUserDao();
}
