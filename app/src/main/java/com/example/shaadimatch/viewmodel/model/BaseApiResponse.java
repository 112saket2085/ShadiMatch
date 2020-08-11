package com.example.shaadimatch.viewmodel.model;

import com.example.shaadimatch.rest.response.ResponseView;

/**
 * Created by SAKET on 11/08/2020
 * Model class for Mutable Livedata object
 */
public class BaseApiResponse {

    public static class InvitationEvent {
        private boolean isSuccess;
        private String statusDescription;
        private ResponseView.ShadiMatchesResponseData responseView;

        public InvitationEvent(boolean isSuccess, String statusDescription, ResponseView.ShadiMatchesResponseData responseView) {
            this.isSuccess = isSuccess;
            this.statusDescription = statusDescription;
            this.responseView = responseView;
        }

        public boolean isSuccess() {
            return isSuccess;
        }

        public String getStatusDescription() {
            return statusDescription;
        }

        public ResponseView.ShadiMatchesResponseData getResponseView() {
            return responseView;
        }
    }

}
