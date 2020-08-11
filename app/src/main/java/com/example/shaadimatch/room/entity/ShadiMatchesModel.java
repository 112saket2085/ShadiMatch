package com.example.shaadimatch.room.entity;

import android.text.TextUtils;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.shaadimatch.R;
import com.example.shaadimatch.app.Constants;
import com.example.shaadimatch.rest.response.ResponseView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SAKET on 11/08/2020
 */
@Entity
public class ShadiMatchesModel {

  @PrimaryKey(autoGenerate = true)
  private long id;
  private String firstName;
  private String lastName;
  private String title;
  private String age;
  private String city;
  private String state;
  private String country;
  private String profilePicUrl;
  private String status = "NONE";

//    public ShadiMatchesModel(String firstName, String lastName, String age, String city, String state, String country, String profilePicUrl) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.age = age;
//        this.city = city;
//        this.state = state;
//        this.country = country;
//        this.profilePicUrl = profilePicUrl;
//    }

    public ShadiMatchesModel() {
    }

    /**
     * Method to fill model from response data
     * @param invitationDataModelList List of data coming from server
     * @return Filtered Model Data
     */
    public static List<ShadiMatchesModel> getShadiMatchesList(List<ResponseView.ShadiMatchesDataModel> invitationDataModelList) {
        List<ShadiMatchesModel> shadiMatchesModelList = new ArrayList<>();
        for (ResponseView.ShadiMatchesDataModel invitationDataModel : invitationDataModelList) {
            ShadiMatchesModel shadiMatchesModel = new ShadiMatchesModel();
            shadiMatchesModel.setTitle(invitationDataModel.nameData.title);
            shadiMatchesModel.setFirstName(invitationDataModel.nameData.firstName);
            shadiMatchesModel.setLastName(invitationDataModel.nameData.lastName);
            shadiMatchesModel.setAge(invitationDataModel.dateOfBirthData.age);
            shadiMatchesModel.setCity(invitationDataModel.locationData.city);
            shadiMatchesModel.setState(invitationDataModel.locationData.state);
            shadiMatchesModel.setCountry(invitationDataModel.locationData.country);
            shadiMatchesModel.setProfilePicUrl(invitationDataModel.profilePictureData.large);
            shadiMatchesModelList.add(shadiMatchesModel);
        }
        return shadiMatchesModelList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getProfilePlaceholderIcon() {
        if(!TextUtils.isEmpty(title)) {
            if(title.equalsIgnoreCase(Constants.TITLE_MALE)) {
                return R.drawable.ic_man;
            }
            else if(title.equalsIgnoreCase(Constants.TITLE_FEMALE)) {
                return R.drawable.ic_woman;
            }
            else {
                return R.drawable.ic_man;
            }
        }
        else {
            return R.drawable.ic_man;
        }
    }
}
