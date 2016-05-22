package com.yalantis.yalantistaskone.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Антон on 22.05.2016.
 */
public class User extends RealmObject implements Parcelable {
    public static final String ID = "id";
    public static final String EMAIL = "email";
    public static final String BIRTHDAY = "birthday";
    public static final String FIRST_NAME = "first_name";
    public static final String MIDDLE_NAME = "middle_name";

    public static final String LAST_NAME = "last_name";
    public static final String ADDRESS = "address";
    public static final String PHONE = "phone";
    public static final String FB_REGISTER = "fb_registered";

    public static final String IMAGE = "image";
    public static final String SOCIAL_CONDITION = "social_condition";
    public static final String FACILITIES = "facilities";

    public User() {
    }


    @PrimaryKey
    @SerializedName(ID)
    private long id;
    @SerializedName(SOCIAL_CONDITION)
    private SocialCondition socialCondition;
    @SerializedName(FACILITIES)
    private Facilities facilities;


    @SerializedName(EMAIL)
    private String email;

    @SerializedName(BIRTHDAY)
    private long birthdayMillis;

    @SerializedName(FIRST_NAME)
    private String firstName;

    @SerializedName(LAST_NAME)
    private String lastName;
    @SerializedName(MIDDLE_NAME)
    private String middleName;
    @SerializedName(ADDRESS)
    private Address address;

    @SerializedName(IMAGE)
    private String image;

    @SerializedName(PHONE)
    private String phone;
    @SerializedName(FB_REGISTER)
    private int fbRegistered = 0;


    protected User(Parcel in) {
        id = in.readLong();
        socialCondition = in.readParcelable(SocialCondition.class.getClassLoader());
        facilities = in.readParcelable(Facilities.class.getClassLoader());
        email = in.readString();
        birthdayMillis = in.readLong();
        firstName = in.readString();
        lastName = in.readString();
        middleName = in.readString();
        image = in.readString();
        phone = in.readString();
        fbRegistered = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getFbRegistered() {
        return fbRegistered;
    }

    public void setFbRegistered(int fbRegistered) {
        this.fbRegistered = fbRegistered;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBirthdayMillis() {
        return birthdayMillis;
    }

    public void setBirthdayMillis(long birthdayMillis) {
        this.birthdayMillis = birthdayMillis;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String user) {
        this.email = user;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public SocialCondition getSocialCondition() {
        return socialCondition;
    }

    public void setSocialCondition(SocialCondition socialCondition) {
        this.socialCondition = socialCondition;
    }

    public Facilities getFacilities() {
        return facilities;
    }

    public void setFacilities(Facilities facilities) {
        this.facilities = facilities;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeParcelable(socialCondition, flags);
        dest.writeParcelable(facilities, flags);
        dest.writeString(email);
        dest.writeLong(birthdayMillis);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(middleName);
        dest.writeString(image);
        dest.writeString(phone);
        dest.writeInt(fbRegistered);
    }
}
