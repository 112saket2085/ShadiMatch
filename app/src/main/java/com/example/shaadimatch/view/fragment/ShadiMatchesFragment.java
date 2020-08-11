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
import com.example.shaadimatch.app.Constants;
import com.example.shaadimatch.rest.response.ResponseView;
import com.example.shaadimatch.room.entity.ShadiMatchesModel;
import com.example.shaadimatch.view.adapter.ShadiMatchesAdapter;
import com.example.shaadimatch.viewmodel.ShadiMatchesViewModel;
import com.example.shaadimatch.viewmodel.model.BaseApiResponse;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by SAKET on 11/08/2020
 */
public class ShadiMatchesFragment extends BaseFragment implements ShadiMatchesAdapter.OnItemClickListener {

    @BindView(R.id.recyclerView_invitations) RecyclerView recyclerView;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.textview_no_data_found) TextView textViewNoDataFound;
    @BindView(R.id.text_view_progress) TextView textViewProgress;
    private ShadiMatchesViewModel shadiMatchesViewModel;
    private List<ShadiMatchesModel> shadiMatchesModelList = new ArrayList<>();
    private ShadiMatchesAdapter shadiMatchesAdapter;

    @Override
    int getLayoutId() {
        return R.layout.fragment_shadi_matches;
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
        observeOfflineShadiMatchData();
    }

    private void initViewModel() {
        shadiMatchesViewModel = new ViewModelProvider(getParentActivity()).get(ShadiMatchesViewModel.class);
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getParentActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        shadiMatchesAdapter = new ShadiMatchesAdapter(shadiMatchesModelList);
        shadiMatchesAdapter.setListener(this);
        recyclerView.setAdapter(shadiMatchesAdapter);
    }

    private void observedInvitationsData(boolean showProgress) {
        if (isNetworkAvailable()) {
            if (showProgress) {
                clearList();
            }
            showErrorDataInfo(View.GONE, "");
            showProgressInfo(showProgress ? View.VISIBLE : View.GONE,getString(R.string.str_loading_application));
            shadiMatchesViewModel.getResponse(10).observe(getViewLifecycleOwner(), new Observer<BaseApiResponse.InvitationEvent>() {
                @Override
                public void onChanged(BaseApiResponse.InvitationEvent invitationEvent) {
                    showProgressInfo(View.GONE,"");
                    if (invitationEvent.isSuccess()) {
                        List<ResponseView.ShadiMatchesDataModel> modelList = invitationEvent.getResponseView().invitationDataModelList;
                        if (modelList != null && !modelList.isEmpty()) {
                            List<ShadiMatchesModel> shadiMatchesList = ShadiMatchesModel.getShadiMatchesList(modelList);
                            shadiMatchesModelList.clear();
                            shadiMatchesModelList.addAll(shadiMatchesList);
                            shadiMatchesAdapter.notifyDataSetChanged();
                            addData(shadiMatchesModelList);
                        }
                        else {
                            showErrorDataInfo(View.VISIBLE, getString(R.string.sr_no_invitation_found));
                        }
                    } else {
                        showShortToast(invitationEvent.getStatusDescription());
                        showErrorDataInfo(View.VISIBLE, invitationEvent.getStatusDescription());
                        setRetryView(getString(R.string.str_retry));
                    }
                }
            });
        }
        else {
            showProgressInfo(View.GONE,"");
            if(shadiMatchesModelList.isEmpty()) {
                showErrorDataInfo(View.VISIBLE,getString(R.string.no_internet_available_text));
                setRetryView(getString(R.string.str_retry));
            }
        }
    }

    private void observeOfflineShadiMatchData() {
        shadiMatchesViewModel.getOfflineResponse().observe(getViewLifecycleOwner(), new Observer<List<ShadiMatchesModel>>() {
            @Override
            public void onChanged(List<ShadiMatchesModel> dataList) {
                if(dataList!=null && !dataList.isEmpty()) {
                    shadiMatchesModelList.clear();
                    shadiMatchesModelList.addAll(dataList);
                    shadiMatchesAdapter.notifyDataSetChanged();
                }
                else {
                    observedInvitationsData(true);
                }
            }
        });
    }

    private void clearList() {
        shadiMatchesModelList.clear();
        shadiMatchesAdapter.notifyDataSetChanged();
    }


    private void showProgressInfo(int visibility,String text) {
        progressBar.setVisibility(visibility);
        textViewProgress.setVisibility(visibility);
        textViewProgress.setText(text);
    }

    private void setRetryView(String text) {
        textViewProgress.setVisibility(View.VISIBLE);
        textViewProgress.setText(text);
    }

    private void showErrorDataInfo(int visibility,String text) {
        textViewNoDataFound.setVisibility(visibility);
        textViewNoDataFound.setText(text);
    }

//    @OnClick(R.id.text_view_progress)
//    public void onRetryClick(View view) {
//        observedInvitationsData(true);
//    }

    /**
     * Method to add data into database
     * @param shadiMatchesModelList ShadiModelList
     */
    private void addData(List<ShadiMatchesModel> shadiMatchesModelList) {
        shadiMatchesViewModel.addData(shadiMatchesModelList);
    }

    /**
     * Method to update data into database
     * @param shadiMatchesModel shadi match model
     */
    private void updateData(ShadiMatchesModel shadiMatchesModel) {
        shadiMatchesViewModel.updateData(shadiMatchesModel);
    }

    @Override
    public void onAcceptClick(ShadiMatchesModel shadiMatchesModel) {
        int position = shadiMatchesModelList.indexOf(shadiMatchesModel);
        shadiMatchesModel.setStatus(Constants.ACCEPTED);
//        shadiMatchesAdapter.notifyItemChanged(position);
        updateData(shadiMatchesModel);
    }

    @Override
    public void onDeclineClick(ShadiMatchesModel shadiMatchesModel) {
        int position = shadiMatchesModelList.indexOf(shadiMatchesModel);
        shadiMatchesModel.setStatus(Constants.REJECTED);
        shadiMatchesAdapter.notifyItemChanged(position);
        updateData(shadiMatchesModel);
    }
}
