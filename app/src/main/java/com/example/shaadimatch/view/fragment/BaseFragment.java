package com.example.shaadimatch.view.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
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
import com.example.shaadimatch.app.App;
import com.example.shaadimatch.view.activity.MainActivity;

import java.util.Objects;

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

    static boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT < 23) {
                final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null) {
                    return (networkInfo.isConnected() && (networkInfo.getType() == ConnectivityManager.TYPE_WIFI || networkInfo.getType() == ConnectivityManager.TYPE_MOBILE));
                }
            } else {
                final Network network = connectivityManager.getActiveNetwork();
                if (network != null) {
                    final NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
                    return (Objects.requireNonNull(networkCapabilities).hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI));
                }
            }
        }
        return false;
    }


}
