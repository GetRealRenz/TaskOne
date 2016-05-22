package com.yalantis.yalantistaskone.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Антон on 22.05.2016.
 */
public class SocialCondition extends RealmObject implements Parcelable {
    public SocialCondition() {

    }

    public static final String ID = "id";
    public static final String NAME = "name";
    @PrimaryKey
    @SerializedName(ID)
    private long id;

    @SerializedName(NAME)
    private String title;

    protected SocialCondition(Parcel in) {
        id = in.readLong();
        title = in.readString();
    }

    public static final Creator<SocialCondition> CREATOR = new Creator<SocialCondition>() {
        @Override
        public SocialCondition createFromParcel(Parcel in) {
            return new SocialCondition(in);
        }

        @Override
        public SocialCondition[] newArray(int size) {
            return new SocialCondition[size];
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
