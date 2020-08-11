package com.example.shaadimatch.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.example.shaadimatch.room.entity.ShadiMatchesModel;
import java.util.List;

/**
 * Created by SAKET on 11/08/2020
 */

@Dao
public interface ShadiMatchesDAO {

    @Query("Select * from ShadiMatchesModel")
    LiveData<List<ShadiMatchesModel>> getShadiMatchList();

    @Query("Select * from ShadiMatchesModel where id=:id")
    ShadiMatchesModel getShadiModel(long id);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addData(List<ShadiMatchesModel> shadiMatchesModelList);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateData(ShadiMatchesModel shadiMatchesModel);
}
