package com.yalantis.yalantistaskone.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Антон on 22.05.2016.
 */
public class TicketFiles extends RealmObject implements Parcelable {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String FILE_NAME = "filename";

    public TicketFiles() {

    }

    @PrimaryKey
    @SerializedName(ID)
    private long id;

    @SerializedName(NAME)
    private String name;

    @SerializedName(FILE_NAME)
    private String filename;

    protected TicketFiles(Parcel in) {
        id = in.readLong();
        name = in.readString();
        filename = in.readString();
    }

    public static final Creator<TicketFiles> CREATOR = new Creator<TicketFiles>() {
        @Override
        public TicketFiles createFromParcel(Parcel in) {
            return new TicketFiles(in);
        }

        @Override
        public TicketFiles[] newArray(int size) {
            return new TicketFiles[size];
        }
    };

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

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
        dest.writeString(filename);
    }
}
