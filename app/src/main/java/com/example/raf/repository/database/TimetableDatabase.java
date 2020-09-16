package com.example.raf.repository.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.raf.repository.database.dao.TimetableDao;
import com.example.raf.repository.database.entity.TermEntity;

@Database(entities = {TermEntity.class}, version = 1)
public abstract class TimetableDatabase extends RoomDatabase {

    private static volatile TimetableDatabase DATABASE;

    private static final String DATABASE_NAME = "timetable.db";

    public abstract TimetableDao getTimetableDao();

    public static TimetableDatabase getDatabase(Context context){
        if(DATABASE == null){
            synchronized (TimetableDatabase.class){
                if(DATABASE == null){
                    DATABASE = Room.databaseBuilder(context,TimetableDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .fallbackToDestructiveMigrationOnDowngrade()
                            .build();
                }
            }
        }
        return DATABASE;
    }
}
