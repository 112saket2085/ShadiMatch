package com.example.shaadimatch.room.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.shaadimatch.room.dao.ShadiMatchesDAO;
import com.example.shaadimatch.room.entity.ShadiMatchesModel;

@Database(entities = {ShadiMatchesModel.class},version = 1,exportSchema = false)
public abstract class RoomSqliteDatabase extends RoomDatabase {

    private static RoomSqliteDatabase instance;

    /**
     * @param context Application Context
     * @return Database Instance
     */
    public static RoomSqliteDatabase getInstance(Context context) {
        if(instance==null) {
            instance = Room.databaseBuilder(context,RoomSqliteDatabase.class,"notes_db").build();
        }
        return instance;
    }

    /**
     *
     * @return NoteDAO instance to access CRUD operations.
     */
    public abstract ShadiMatchesDAO getShadiMatchDAO();

    /**
     *  Method to clear database instance
     */
    @Override
    public void clearAllTables() {
        instance=null;
    }
}
