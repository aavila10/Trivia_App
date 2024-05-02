package com.example.trivia_app;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = User.class, version = 1)
public abstract class AppDatabase extends RoomDatabase{
    public static final String DB_NAME = "TRIVIA_DATABASE";

    public static final String USER_TABLE = "USER_TABLE";

    public static final String SCORE_TABLE = "SCORE_TABLE";


    public abstract UserDao getUserDao();
}
