package com.yalantis.yalantistaskone.ui.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Антон on 22.05.2016.
 */
public class Street extends RealmObject {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String CITY_ID = "cityId";
    public static final String NAME_RU = "ru_name";

    @PrimaryKey
    @SerializedName(ID)
    private long id;

    @SerializedName(NAME)
    private String name;
    @SerializedName(NAME_RU)
    private String nameRu;
    @SerializedName(CITY_ID)
    private long cityId;
    @SerializedName("city_district")
    private CityDistrict cityDistrict;

    public CityDistrict getCityDistrict() {
        return cityDistrict;
    }

    @SerializedName("street_type")
    private StreetType streetType;

    public StreetType getStreetType() {
        return streetType;
    }

    public void setStreetType(StreetType streetType) {
        this.streetType = streetType;
    }

    public void setCityDistrict(CityDistrict cityDistrict) {
        this.cityDistrict = cityDistrict;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
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

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }
}
