package scoproject.com.contactsappgojek.networking.updatecontact;

import com.google.gson.annotations.SerializedName;

import scoproject.com.contactsappgojek.data.People;

/**
 * Created by ibnumuzzakkir on 5/20/17.
 */

public class UpdateContactAPIResponse extends People {
    @SerializedName("error")
    public String error;
}
