package com.yalantis.yalantistaskone.ui.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Антон on 22.05.2016.
 */
public class House extends RealmObject {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String STREET_ID = "streetId";

    @PrimaryKey
    @SerializedName(ID)
    private long id;

    @SerializedName(NAME)
    private String name;

    @SerializedName(STREET_ID)
    private long streetId;

    public long getStreetId() {
        return streetId;
    }

    public void setStreetId(long streetId) {
        this.streetId = streetId;
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
}
