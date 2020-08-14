package com.example.shaadimatch.room.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.shaadimatch.room.dao.InvitationsDAO;
import com.example.shaadimatch.room.entity.InvitationsModel;

/**
 * Created by SAKET on 11/08/2020
 * RoomSqliteDatabase for accessing database queries
 */

@Database(entities = {InvitationsModel.class},version = 1,exportSchema = false)
public abstract class RoomSqliteDatabase extends RoomDatabase {

    private static RoomSqliteDatabase instance;

    /**
     * @param context Application Context
     * @return Database Instance
     */
    public static RoomSqliteDatabase getInstance(Context context) {
        if(instance==null) {
            instance = Room.databaseBuilder(context,RoomSqliteDatabase.class,"shadi_db").build();
        }
        return instance;
    }

    /**
     *
     * @return InvitationsDAO instance to access CRUD operations.
     */
    public abstract InvitationsDAO getInvitationsDAO();

    /**
     *  Method to clear database instance
     */
    @Override
    public void clearAllTables() {
        instance=null;
    }
}
