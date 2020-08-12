package com.example.shaadimatch.rest;

import com.example.shaadimatch.rest.response.ResponseView;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by SAKET on 11/08/2020
 * Retrofit Api Interface
 */
public interface ShadiApi {

    @GET("?results=10")
    Call<ResponseView.InvitationsResponseData> getInvitationList();
}
