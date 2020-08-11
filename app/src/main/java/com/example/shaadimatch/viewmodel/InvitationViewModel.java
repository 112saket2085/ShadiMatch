package com.example.shaadimatch.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.shaadimatch.repository.InvitationRepository;
import com.example.shaadimatch.viewmodel.model.BaseApiResponse;

/**
 * Created by SAKET on 11/08/2020
 */
public class InvitationViewModel extends AndroidViewModel {

    public InvitationViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<BaseApiResponse.InvitationEvent> getResponse(int count) {
        return InvitationRepository.getInstance().getResponse(count);
    }
}
