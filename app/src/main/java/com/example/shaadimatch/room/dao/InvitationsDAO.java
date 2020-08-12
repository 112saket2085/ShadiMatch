package com.example.shaadimatch.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.example.shaadimatch.room.entity.InvitationsModel;
import java.util.List;

/**
 * Created by SAKET on 11/08/2020
 * InvitationsDAO for accessing database queries
 */

@Dao
public interface InvitationsDAO {

    @Query("Select * from InvitationsModel")
    LiveData<List<InvitationsModel>> getInvitationsList();

    @Query("Select * from InvitationsModel where id=:id")
    InvitationsModel getInvitationModel(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addData(List<InvitationsModel> invitationsModelList);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateData(InvitationsModel invitationsModel);
}
