package com.example.shaadimatch.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.shaadimatch.R;
import com.example.shaadimatch.rest.response.ResponseView;
import com.example.shaadimatch.room.entity.InvitationsModel;
import com.example.shaadimatch.view.adapter.InvitationsAdapter;
import com.example.shaadimatch.viewmodel.InvitationsViewModel;
import com.example.shaadimatch.viewmodel.model.BaseApiResponse;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by SAKET on 11/08/2020
 */
public class InvitationsFragment extends BaseFragment implements InvitationsAdapter.OnItemClickListener {

    @BindView(R.id.recyclerView_invitations) RecyclerView recyclerView;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.textview_no_data_found) TextView textViewNoDataFound;
    @BindView(R.id.text_view_progress) TextView textViewProgress;
    @BindView(R.id.text_view_retry) TextView textViewRetry;
    private InvitationsViewModel invitationsViewModel;
    private List<InvitationsModel> invitationsModelList = new ArrayList<>();
    private InvitationsAdapter invitationsAdapter;


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
        initViewModel();
        initRecyclerView();
        observeOfflineInvitationsData();
    }

    private void initViewModel() {
        invitationsViewModel = new ViewModelProvider(getParentActivity()).get(InvitationsViewModel.class);
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getParentActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        invitationsAdapter = new InvitationsAdapter(invitationsModelList);
        invitationsAdapter.setListener(this);
        recyclerView.setAdapter(invitationsAdapter);
    }

    /**
     * Method to observe data changes from server
     */
    private void observedInvitationsData() {
        if (isNetworkAvailable()) {
            clearList();
            showErrorDataInfo(View.GONE, "");
            setRetryView(View.GONE,"");
            showProgressInfo(View.VISIBLE,getString(R.string.str_loading_application));
            invitationsViewModel.getResponse(10).observe(getViewLifecycleOwner(), new Observer<BaseApiResponse.InvitationEvent>() {
                @Override
                public void onChanged(BaseApiResponse.InvitationEvent invitationEvent) {
                    showProgressInfo(View.GONE,"");
                    if (invitationEvent.isSuccess() && invitationEvent.getResponseView()!=null) {
                        List<ResponseView.InvitationsDataModel> modelList = invitationEvent.getResponseView().invitationDataModelList;
                        if (modelList != null && !modelList.isEmpty()) {
                            List<InvitationsModel> invitationsList = InvitationsModel.getInvitationsModelList(modelList);
                            invitationsModelList.clear();
                            invitationsModelList.addAll(invitationsList);
                            invitationsAdapter.notifyDataSetChanged();
                            addData(invitationsModelList);
                        }
                        else {
                            showErrorDataInfo(View.VISIBLE, getString(R.string.sr_no_invitation_found));
                        }
                    } else {
                        showShortToast(invitationEvent.getStatusDescription());
                        showErrorDataInfo(View.VISIBLE, invitationEvent.getStatusDescription());
                        setRetryView(View.VISIBLE,getString(R.string.str_retry));
                    }
                }
            });
        }
        else {
            showProgressInfo(View.GONE,"");
            if(invitationsModelList.isEmpty()) {
                showErrorDataInfo(View.VISIBLE,getString(R.string.no_internet_available_text));
                setRetryView(View.VISIBLE,getString(R.string.str_retry));
            }
        }
    }


    /**
     * Method to observe data changes from room database
     */
    private void observeOfflineInvitationsData() {
        invitationsViewModel.getOfflineResponse().observe(getViewLifecycleOwner(), new Observer<List<InvitationsModel>>() {
            @Override
            public void onChanged(List<InvitationsModel> dataList) {
                if(dataList!=null && !dataList.isEmpty()) {
                    invitationsModelList.clear();
                    invitationsModelList.addAll(dataList);
                    invitationsAdapter.notifyDataSetChanged();
                }
                else {
                    observedInvitationsData();
                }
            }
        });
    }

    private void clearList() {
        invitationsModelList.clear();
        invitationsAdapter.notifyDataSetChanged();
    }


    private void showProgressInfo(int visibility,String text) {
        progressBar.setVisibility(visibility);
        textViewProgress.setVisibility(visibility);
        textViewProgress.setText(text);
    }

    private void setRetryView(int visibilty,String text) {
        textViewRetry.setVisibility(visibilty);
        textViewRetry.setText(text);
    }

    private void showErrorDataInfo(int visibility,String text) {
        textViewNoDataFound.setVisibility(visibility);
        textViewNoDataFound.setText(text);
    }

    @OnClick(R.id.text_view_retry)
    public void onRetryClick(View view) {
        observedInvitationsData();
    }

    /**
     * Method to add data into database
     * @param invitationsModelList InvitationList
     */
    private void addData(List<InvitationsModel> invitationsModelList) {
        invitationsViewModel.addData(invitationsModelList);
    }

    /**
     * Method to update data into database
     * @param invitationsModel Invitation model
     */
    private void updateData(InvitationsModel invitationsModel) {
        invitationsViewModel.updateData(invitationsModel);
    }

    @Override
    public void onAcceptClick(InvitationsModel invitationsModel) {
        invitationsModel.setInvitationStatus(InvitationsModel.INVITATION_STATUS.ACCEPTED.getStatus());
        updateData(invitationsModel);
    }

    @Override
    public void onDeclineClick(InvitationsModel invitationsModel) {
        invitationsModel.setInvitationStatus(InvitationsModel.INVITATION_STATUS.REJECTED.getStatus());
        updateData(invitationsModel);
    }
}
