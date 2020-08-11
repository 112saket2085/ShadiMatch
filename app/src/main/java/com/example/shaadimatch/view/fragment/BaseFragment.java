package com.example.shaadimatch.view.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.example.shaadimatch.MainActivity;
import butterknife.ButterKnife;

/**
 * Created by SAKET on 11/08/2020
 */
public abstract class BaseFragment extends Fragment {

    abstract int getLayoutId();
    abstract String  getTitle();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(getParentActivity()).inflate(getLayoutId(),(ViewGroup) null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getParentActivity().setActionBarTitle(getTitle());
    }

    private NavController getNavController() {
        return getParentActivity().getNavController();
    }

    MainActivity getParentActivity() {
        return (MainActivity) requireActivity();
    }

    void navigateTo(int resId) {
        getNavController().navigate(resId);
    }

    void navigateTo(int resId,Bundle bundle) {
        getNavController().navigate(resId,bundle);
    }

    void showShortToast(String msg) {
        Toast.makeText(getParentActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    void showLongToast(String msg) {
        Toast.makeText(getParentActivity(),msg,Toast.LENGTH_LONG).show();
    }

}
