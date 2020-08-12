package com.example.shaadimatch.repository;

import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.shaadimatch.R;
import com.example.shaadimatch.app.App;
import com.example.shaadimatch.rest.response.ResponseView;
import com.example.shaadimatch.room.dao.InvitationsDAO;
import com.example.shaadimatch.room.database.RoomSqliteDatabase;
import com.example.shaadimatch.room.entity.InvitationsModel;
import com.example.shaadimatch.viewmodel.model.BaseApiResponse;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by SAKET on 11/08/2020
 * Class that call api and store results.
 */
public class InvitationsRepository {

    private static InvitationsRepository invitationRepository;
    private MutableLiveData<BaseApiResponse.InvitationEvent> mutableLiveData;
    private static InvitationsDAO shadiMatchesDAO;

    public static InvitationsRepository getInstance() {
        if(invitationRepository==null) {
            invitationRepository = new InvitationsRepository();
        }
        return invitationRepository;
    }

    private InvitationsRepository() {
        RoomSqliteDatabase roomSqliteDatabase = RoomSqliteDatabase.getInstance(App.getAppContext());
        shadiMatchesDAO = roomSqliteDatabase.getInvitationsDAO();
    }

    /**
     * Method to call invitation api and get result
     * @param count Total No of invitation
     * @return Invitation Results
     */
    public LiveData<BaseApiResponse.InvitationEvent> getResponse(int count) {
        mutableLiveData = new MutableLiveData<>();
        App.getInstance().getApiFactory().getShadiApi().getInvitationList().enqueue(new Callback<ResponseView.InvitationsResponseData>() {
            @Override
            public void onResponse(@NotNull Call<ResponseView.InvitationsResponseData> call, @NotNull Response<ResponseView.InvitationsResponseData> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.setValue(new BaseApiResponse.InvitationEvent(true, "",response.body()));
                } else {
                    mutableLiveData.setValue(new BaseApiResponse.InvitationEvent(true, App.getInstance().getString(R.string.str_server_error),null));
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResponseView.InvitationsResponseData> call, @NotNull Throwable t) {
                mutableLiveData.setValue(new BaseApiResponse.InvitationEvent(true, App.getInstance().getString(R.string.str_server_error),null));
            }
        });
        return mutableLiveData;
    }

    /**
     * @return LiveData Object that return list of all notes in room database.
     */
    public LiveData<List<InvitationsModel>> getShadiMatchList() {
        return shadiMatchesDAO.getInvitationsList();
    }


    /**
     * Method to add  data in Room Database.
     *
     * @param shadiMatchesModelList Shadi Match List
     */
    public void addData(List<InvitationsModel> shadiMatchesModelList) {
        new AddDataAsyncTask().execute(shadiMatchesModelList);
    }

    /**
     * Method to update  data in Room Database.
     *
     * @param shadiMatchesModel Shadi Match Model
     */
    public void updateData(InvitationsModel shadiMatchesModel) {
        new UpdateDataAsyncTask().execute(shadiMatchesModel);
    }


    /**
     * AddImageNote Async Task to add image note to room database.
     */
    private static class AddDataAsyncTask extends AsyncTask<List<InvitationsModel>, Void, Void> {
        @SafeVarargs
        @Override
        protected final Void doInBackground(List<InvitationsModel>... lists) {
            List<InvitationsModel> list=lists[0];
            shadiMatchesDAO.addData(list);
            return null;
        }
    }


    /**
     * AddImageNote Async Task to add image note to room database.
     */
    private static class UpdateDataAsyncTask extends AsyncTask<InvitationsModel, Void, Void> {
        @Override
        protected final Void doInBackground(InvitationsModel... shadiMatchesModels) {
            InvitationsModel shadiMatchesModel = shadiMatchesModels[0];
            InvitationsModel shadiDataModel = shadiMatchesDAO.getInvitationModel(shadiMatchesModel.getId());
            if(shadiDataModel!=null) {
                shadiDataModel.setInvitationStatus(shadiMatchesModel.getInvitationStatus());
                shadiMatchesDAO.updateData(shadiDataModel);
            }
            return null;
        }
    }

}
