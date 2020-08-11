package com.example.shaadimatch.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.shaadimatch.app.App;
import com.example.shaadimatch.rest.response.ResponseView;
import com.example.shaadimatch.viewmodel.model.BaseApiResponse;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by SAKET on 11/08/2020
 * Class that call api and store results.
 */
public class InvitationRepository {

    private static InvitationRepository invitationRepository;
    private MutableLiveData<BaseApiResponse.InvitationEvent> mutableLiveData;

    public static InvitationRepository getInstance() {
        if(invitationRepository==null) {
            invitationRepository = new InvitationRepository();
        }
        return invitationRepository;
    }

    /**
     * Method to call invitation api and get result
     * @param count Total No of invitation
     * @return Invitation Results
     */
    public LiveData<BaseApiResponse.InvitationEvent> getResponse(int count) {
        mutableLiveData = new MutableLiveData<>();
        App.getInstance().getApiFactory().getShadiApi().getInvitationList(count).enqueue(new Callback<ResponseView.InvitationResponseData>() {
            @Override
            public void onResponse(@NotNull Call<ResponseView.InvitationResponseData> call, @NotNull Response<ResponseView.InvitationResponseData> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.setValue(new BaseApiResponse.InvitationEvent(true, response.body()));
                } else {
                    mutableLiveData.setValue(new BaseApiResponse.InvitationEvent(true, null));
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResponseView.InvitationResponseData> call, @NotNull Throwable t) {
                mutableLiveData.setValue(new BaseApiResponse.InvitationEvent(true, null));
            }
        });
        return mutableLiveData;
    }
}
