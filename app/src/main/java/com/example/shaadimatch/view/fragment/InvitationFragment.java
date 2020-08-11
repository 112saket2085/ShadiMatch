package com.example.shaadimatch.view.fragment;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shaadimatch.R;

import butterknife.BindView;

/**
 * Created by SAKET on 11/08/2020
 */
public class InvitationFragment extends BaseFragment {

    @BindView(R.id.recyclerView_invitations) RecyclerView recyclerView;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.textview_no_data_found) TextView textViewNoDataFound;
    @BindView(R.id.text_view_progress) TextView textViewProgress;

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
