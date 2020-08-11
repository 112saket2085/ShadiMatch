package com.example.shaadimatch.rest;

import com.example.shaadimatch.rest.response.ResponseView;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by SAKET on 11/08/2020
 */
public interface ShadiApi {

    @GET("?results={count}")
    Call<ResponseView.InvitationResponseData> getInvitationList(int count);


}
