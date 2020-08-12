package com.example.shaadimatch.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.shaadimatch.repository.InvitationsRepository;
import com.example.shaadimatch.room.entity.InvitationsModel;
import com.example.shaadimatch.viewmodel.model.BaseApiResponse;
import java.util.List;

/**
 * Created by SAKET on 11/08/2020
 * ViewModel class for preparing data for Invitation Fragment.
 */
public class InvitationsViewModel extends AndroidViewModel {

    public InvitationsViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<BaseApiResponse.InvitationEvent> getResponse(int count) {
        return InvitationsRepository.getInstance().getResponse(count);
    }

    public LiveData<List<InvitationsModel>> getOfflineResponse() {
        return InvitationsRepository.getInstance().getShadiMatchList();
    }

    public void addData(List<InvitationsModel> shadiMatchesModelList) {
        InvitationsRepository.getInstance().addData(shadiMatchesModelList);
    }

    public void updateData(InvitationsModel shadiMatchesModel) {
        InvitationsRepository.getInstance().updateData(shadiMatchesModel);
    }
}
