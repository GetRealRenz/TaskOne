package com.yalantis.yalantistaskone.ui.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Антон on 22.05.2016.
 */
public class Address extends RealmObject {
    public static final String ID = "id";
    public static final String DISTRICT = "district";
    public static final String CITY = "city";
    public static final String STREET = "street";
    public static final String HOUSE = "house";
    public static final String FLAT = "flat";


    @PrimaryKey
    @SerializedName(ID)
    private long id;

    @SerializedName(DISTRICT)
    private District district;

    @SerializedName(CITY)
    private City city;

    @SerializedName(STREET)
    private Street street;

    @SerializedName(HOUSE)
    private House house;

    @SerializedName(FLAT)
    private String flat;

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }
}
