package com.example.shaadimatch.view.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.shaadimatch.R;

/**
 * Created by SAKET on 11/08/2020
 */
public class InvitationFragment extends BaseFragment {


    @Override
    int getLayoutId() {
        return R.layout.fragment_invitations;
    }

    @Override
    String getTitle() {
        return getString(R.string.str_invitations);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
