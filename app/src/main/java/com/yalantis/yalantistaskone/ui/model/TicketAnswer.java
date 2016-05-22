package com.yalantis.yalantistaskone.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Антон on 22.05.2016.
 */
public class TicketAnswer extends RealmObject implements Parcelable {
    public static final String ID = "id";
    public static final String FILE_NAME = "filename";
    public static final String ORIGIN_NAME = "origin_name";

    public TicketAnswer() {
    }


    @SerializedName(ID)
    private long id;

    @SerializedName(FILE_NAME)
    private String filename;

    @SerializedName(ORIGIN_NAME)
    private String originName;

    protected TicketAnswer(Parcel in) {
        id = in.readLong();
        filename = in.readString();
        originName = in.readString();
    }

    public static final Creator<TicketAnswer> CREATOR = new Creator<TicketAnswer>() {
        @Override
        public TicketAnswer createFromParcel(Parcel in) {
            return new TicketAnswer(in);
        }

        @Override
        public TicketAnswer[] newArray(int size) {
            return new TicketAnswer[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(filename);
        dest.writeString(originName);
    }
}
