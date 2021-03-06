package com.example.shaadimatch.rest;

import com.example.shaadimatch.rest.response.ResponseView;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by SAKET on 11/08/2020
 */
public interface ShadiApi {

    @GET("api/")
    Call<ResponseView.InvitationsResponseData> getInvitationList(@Query("results") int count);
}
