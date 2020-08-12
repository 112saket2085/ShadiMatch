package com.example.shaadimatch.rest.response;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by SAKET on 11/08/2020
 * Class Containing response data
 */
public class ResponseView {

    public static class InvitationsResponseData {
        @SerializedName("results")
        public List<InvitationsDataModel> invitationDataModelList;
    }

    public static class InvitationsDataModel {
        @SerializedName("gender")
        public String gender;
        @SerializedName("email")
        public String email;
        @SerializedName("name")
        public NameData nameData;
        @SerializedName("location")
        public LocationData locationData;
        @SerializedName("dob")
        public DateOfBirthData dateOfBirthData;
        @SerializedName("picture")
        public ProfilePictureData profilePictureData;

        public static class NameData {
            @SerializedName("title")
            public String title;
            @SerializedName("first")
            public String firstName;
            @SerializedName("last")
            public String lastName;
        }

        public static class LocationData {
            @SerializedName("city")
            public String city;
            @SerializedName("state")
            public String state;
            @SerializedName("country")
            public String country;
        }

        public static class DateOfBirthData {
            @SerializedName("date")
            public String date;
            @SerializedName("age")
            public String age;
        }

        public static class ProfilePictureData {
            @SerializedName("large")
            public String large;
            @SerializedName("medium")
            public String medium;
            @SerializedName("thumbnail")
            public String thumbnail;
        }

    }
}
