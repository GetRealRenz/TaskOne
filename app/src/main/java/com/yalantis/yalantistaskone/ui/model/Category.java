package com.yalantis.yalantistaskone.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Антон on 22.05.2016.
 */
public class Category extends RealmObject implements Parcelable {

    private static final String ID = "id";
    private static final String NAME = "name";
    @PrimaryKey
    @SerializedName(ID)
    private long id;

    @SerializedName(NAME)
    private String name;

    public Category(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category() {
    }

    protected Category(Parcel in) {
        id = in.readLong();
        name = in.readString();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
    }
}
