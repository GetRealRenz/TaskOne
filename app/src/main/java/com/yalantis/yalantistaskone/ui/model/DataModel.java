package com.yalantis.yalantistaskone.ui.model;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Антон on 10.04.2016.
 */
public class DataModel implements Parcelable {
    private String title;
    private String likes;

    private String address;
    private String date;
    private String daysleft;
    private String registred;
    private String responsible;
    private String status;

    private String description;
    private Drawable category;
    private List<String> images;

    public DataModel() {

    }


    protected DataModel(Parcel in) {
        title = in.readString();
        likes = in.readString();
        address = in.readString();
        date = in.readString();
        daysleft = in.readString();
        registred = in.readString();
        responsible = in.readString();
        description = in.readString();
        status = in.readString();
        images = in.createStringArrayList();

    }

    public static final Creator<DataModel> CREATOR = new Creator<DataModel>() {
        @Override
        public DataModel createFromParcel(Parcel in) {
            return new DataModel(in);
        }

        @Override
        public DataModel[] newArray(int size) {
            return new DataModel[size];
        }
    };

    public void setRegistred(String registred) {
        this.registred = registred;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getRegistred() {
        return registred;
    }

    public String getResponsible() {
        return responsible;
    }

    public List<String> getImages() {
        return images;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {

        return status;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDaysleft(String daysleft) {
        this.daysleft = daysleft;
    }

    public void setCategory(Drawable category) {
        this.category = category;
    }

    public String getLikes() {
        return likes;
    }

    public String getAddress() {
        return address;
    }

    public String getDate() {
        return date;
    }

    public String getDaysleft() {
        return daysleft;
    }

    public Drawable getCategory() {
        return category;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(likes);
        dest.writeString(address);
        dest.writeString(date);
        dest.writeString(daysleft);
        dest.writeString(registred);
        dest.writeString(responsible);
        dest.writeString(description);
        dest.writeString(status);
        dest.writeList(images);
    }
}
