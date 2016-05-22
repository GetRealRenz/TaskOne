package com.yalantis.yalantistaskone.ui.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Антон on 22.05.2016.
 */
public class District extends RealmObject {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DISTRICT = "district";
    @PrimaryKey
    @SerializedName(ID)
    private long id;

    @SerializedName(NAME)
    private String title;

    @SerializedName(DISTRICT)
    private DistrictFake district;

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

    public DistrictFake getDistrict() {
        return district;
    }

    public void setDistrict(DistrictFake district) {
        this.district = district;
    }
}
