package com.yalantis.yalantistaskone.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Антон on 22.05.2016.
 */
public class Ticket extends RealmObject implements Parcelable {
    public static final String ID = "id";

    public Ticket() {
    }

    @PrimaryKey
    @SerializedName(ID)
    private long id;
    @SerializedName("user")
    private User user;

    @SerializedName("title")
    private String title;

    @SerializedName("body")
    private String body;

    @SerializedName("created_date")
    private long created;

    @SerializedName("completed_date")
    private long completed;

    @SerializedName("start_date")
    private long startDate;

    @SerializedName("state")
    private State state;

    @SerializedName("manager")
    private Manager manager;

    @SerializedName("ticket_id")
    private String ticketId;

    @SerializedName("address")
    private Address address;

    @SerializedName("comment")
    private String comment;

    private Category category;

    private Type type;

    @SerializedName("geo_address")
    private GeoAddress geoAddress;

    @SerializedName("likes_counter")
    private int likesCounter;

    @SerializedName("answers")
    private RealmList<TicketAnswer> answers;

    @SerializedName("files")
    private RealmList<TicketFiles> files;

    @SerializedName("performers")
    private RealmList<Performer> performers;

    @SerializedName("deadline")
    private long deadline;

    protected Ticket(Parcel in) {
        id = in.readLong();
        title = in.readString();
        body = in.readString();
        created = in.readLong();
        completed = in.readLong();
        startDate = in.readLong();
        ticketId = in.readString();
        comment = in.readString();
        likesCounter = in.readInt();
        deadline = in.readLong();
    }

    public static final Creator<Ticket> CREATOR = new Creator<Ticket>() {
        @Override
        public Ticket createFromParcel(Parcel in) {
            return new Ticket(in);
        }

        @Override
        public Ticket[] newArray(int size) {
            return new Ticket[size];
        }
    };

    public int getLikesCounter() {
        return likesCounter;
    }

    public void setLikesCounter(int likesCounter) {
        this.likesCounter = likesCounter;
    }

    public GeoAddress getGeoAddress() {
        return geoAddress;
    }

    public void setGeoAddress(GeoAddress geoAddress) {
        this.geoAddress = geoAddress;
    }


    public RealmList<TicketFiles> getFiles() {
        return files;
    }

    public void setFiles(RealmList<TicketFiles> files) {
        this.files = files;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getCompleted() {
        return completed;
    }

    public void setCompleted(long completed) {
        this.completed = completed;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public RealmList<TicketAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(RealmList<TicketAnswer> answers) {
        this.answers = answers;
    }

    public RealmList<Performer> getPerformers() {
        return performers;
    }

    public void setPerformers(RealmList<Performer> performers) {
        this.performers = performers;
    }

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(body);
        dest.writeLong(created);
        dest.writeLong(completed);
        dest.writeLong(startDate);
        dest.writeString(ticketId);
        dest.writeString(comment);
        dest.writeInt(likesCounter);
        dest.writeLong(deadline);
    }
}
