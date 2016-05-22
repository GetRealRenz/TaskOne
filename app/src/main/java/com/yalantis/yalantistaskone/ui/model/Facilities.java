package com.yalantis.yalantistaskone.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Антон on 22.05.2016.
 */
public class Facilities extends RealmObject implements Parcelable {
    public static final String ID = "id";
    public static final String NAME = "name";
    @PrimaryKey
    @SerializedName(ID)
    private long id;

    @SerializedName(NAME)
    private String title;

    public Facilities() {

    }

    protected Facilities(Parcel in) {
        id = in.readLong();
        title = in.readString();
    }

    public static final Creator<Facilities> CREATOR = new Creator<Facilities>() {
        @Override
        public Facilities createFromParcel(Parcel in) {
            return new Facilities(in);
        }

        @Override
        public Facilities[] newArray(int size) {
            return new Facilities[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
    }
}
