package com.example.shaadimatch.viewmodel.model;

import com.example.shaadimatch.rest.response.ResponseView;

/**
 * Created by SAKET on 11/08/2020
 * Model class for Mutable Livedata object
 */
public class BaseApiResponse {

    public static class InvitationEvent {
        private boolean isSuccess;
        private ResponseView.InvitationResponseData responseView;

        public InvitationEvent(boolean isSuccess,ResponseView.InvitationResponseData responseView) {
            this.isSuccess = isSuccess;
            this.responseView = responseView;
        }

        public boolean isSuccess() {
            return isSuccess;
        }

        public ResponseView.InvitationResponseData getResponseView() {
            return responseView;
        }
    }

}
