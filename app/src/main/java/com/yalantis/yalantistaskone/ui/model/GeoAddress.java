package com.yalantis.yalantistaskone.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Антон on 22.05.2016.
 */
public class GeoAddress extends RealmObject implements Parcelable {
    public static final String ID = "id";
    public static final String ADDRESS = "address";

    public static final String LONGITUDE = "longitude";

    public static final String LATITUDE = "latitude";

    public GeoAddress() {

    }

    @PrimaryKey
    @SerializedName(ID)
    private long id;

    @SerializedName(ADDRESS)
    private String address;

    @SerializedName(LATITUDE)
    private String latitude;

    @SerializedName(LONGITUDE)
    private String longitude;

    protected GeoAddress(Parcel in) {
        id = in.readLong();
        address = in.readString();
        latitude = in.readString();
        longitude = in.readString();
    }

    public static final Creator<GeoAddress> CREATOR = new Creator<GeoAddress>() {
        @Override
        public GeoAddress createFromParcel(Parcel in) {
            return new GeoAddress(in);
        }

        @Override
        public GeoAddress[] newArray(int size) {
            return new GeoAddress[size];
        }
    };

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(address);
        dest.writeString(latitude);
        dest.writeString(longitude);
    }
}
