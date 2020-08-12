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
 * Model class
 */
@Entity
public class InvitationsModel {

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
    private String invitationStatus;

    public InvitationsModel() {
    }

    /**
     * Method to fill model from response data
     *
     * @param invitationDataModelList List of data coming from server
     * @return Filtered Model Data
     */
    public static List<InvitationsModel> getInvitationsModelList(List<ResponseView.InvitationsDataModel> invitationDataModelList) {
        List<InvitationsModel> invitationsModelList = new ArrayList<>();
        for (ResponseView.InvitationsDataModel invitationDataModel : invitationDataModelList) {
            InvitationsModel invitationsModel = new InvitationsModel();
            invitationsModel.setTitle(invitationDataModel.nameData.title);
            invitationsModel.setFirstName(invitationDataModel.nameData.firstName);
            invitationsModel.setLastName(invitationDataModel.nameData.lastName);
            invitationsModel.setAge(invitationDataModel.dateOfBirthData.age);
            invitationsModel.setCity(invitationDataModel.locationData.city);
            invitationsModel.setState(invitationDataModel.locationData.state);
            invitationsModel.setCountry(invitationDataModel.locationData.country);
            invitationsModel.setProfilePicUrl(invitationDataModel.profilePictureData.large);
            invitationsModel.setInvitationStatus(INVITATION_STATUS.NONE.getStatus());
            invitationsModelList.add(invitationsModel);
        }
        return invitationsModelList;
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

    public String getInvitationStatus() {
        return invitationStatus;
    }

    public void setInvitationStatus(String invitationStatus) {
        this.invitationStatus = invitationStatus;
    }

    /**
     * Method to get placeholder icon for profile picture.
     * @return res Id for ImageView placeholder
     */
    public int getProfilePlaceholderIcon() {
        if (!TextUtils.isEmpty(title)) {
            if (title.equalsIgnoreCase(Constants.TITLE_MALE)) {
                return R.drawable.ic_man;
            } else if (title.equalsIgnoreCase(Constants.TITLE_MISS) || title.equalsIgnoreCase(Constants.TITLE_MS) || title.equalsIgnoreCase(Constants.TITLE_MRS)) {
                return R.drawable.ic_woman;
            } else {
                return R.drawable.ic_user_profile;
            }
        } else {
            return R.drawable.ic_user_profile;
        }
    }

    public enum INVITATION_STATUS {
        ACCEPTED("ACCEPTED"),
        REJECTED("REJECTED"),
        NONE("NONE");

        private String status;

        INVITATION_STATUS(String status) {
            this.status=status;
        }

        public String getStatus() {
            return status;
        }
    }
}
