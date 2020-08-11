package com.example.shaadimatch.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.shaadimatch.repository.ShadiMatchesRepository;
import com.example.shaadimatch.room.entity.ShadiMatchesModel;
import com.example.shaadimatch.viewmodel.model.BaseApiResponse;

import java.util.List;

/**
 * Created by SAKET on 11/08/2020
 */
public class ShadiMatchesViewModel extends AndroidViewModel {

    public ShadiMatchesViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<BaseApiResponse.InvitationEvent> getResponse(int count) {
        return ShadiMatchesRepository.getInstance().getResponse(count);
    }

    public LiveData<List<ShadiMatchesModel>> getOfflineResponse() {
        return ShadiMatchesRepository.getInstance().getShadiMatchList();
    }

    public void addData(List<ShadiMatchesModel> shadiMatchesModelList) {
        ShadiMatchesRepository.getInstance().addData(shadiMatchesModelList);
    }

    public void updateData(ShadiMatchesModel shadiMatchesModel) {
        ShadiMatchesRepository.getInstance().updateData(shadiMatchesModel);
    }
}
