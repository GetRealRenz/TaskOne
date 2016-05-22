package com.yalantis.yalantistaskone.ui.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Антон on 22.05.2016.
 */
public class City extends RealmObject {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String NAME_RU = "ru_name";
    public static final String DISTRICT_ID = "districtId";

    @PrimaryKey
    @SerializedName(ID)
    private long id;

    @SerializedName(NAME)
    private String title;
    @SerializedName(NAME_RU)
    private String nameRu;
    @SerializedName(DISTRICT_ID)
    private long districtId;

    public long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(long districtId) {
        this.districtId = districtId;
    }

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

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }
}
