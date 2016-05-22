package com.yalantis.yalantistaskone.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Антон on 22.05.2016.
 */
public class Performer extends RealmObject implements Parcelable {
    public static final String ORGANIZATION = "organization";
    public static final String PERSON = "person";
    public static final String ID = "id";
    @PrimaryKey
    @SerializedName(ID)
    private long id;

    @SerializedName(PERSON)
    private String person;

    @SerializedName(ORGANIZATION)
    private String organization;

    public Performer() {

    }

    protected Performer(Parcel in) {
        id = in.readLong();
        person = in.readString();
        organization = in.readString();
    }

    public static final Creator<Performer> CREATOR = new Creator<Performer>() {
        @Override
        public Performer createFromParcel(Parcel in) {
            return new Performer(in);
        }

        @Override
        public Performer[] newArray(int size) {
            return new Performer[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(person);
        dest.writeString(organization);
    }
}
