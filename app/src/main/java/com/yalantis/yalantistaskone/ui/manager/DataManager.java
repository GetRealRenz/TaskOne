package com.yalantis.yalantistaskone.ui.manager;

import android.content.Context;

import com.yalantis.yalantistaskone.ui.interfaces.Manager;
import com.yalantis.yalantistaskone.ui.model.Ticket;
import com.yalantis.yalantistaskone.ui.model.UserProfile;
import com.yalantis.yalantistaskone.ui.util.Constants;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.exceptions.RealmMigrationNeededException;
import rx.Observable;

/**
 * Created by Антон on 20.05.2016.
 */
public class DataManager implements Manager {
    private Realm mRealm;
    private static final String STATE_ID = "state.id";
    private static final String ID = "id";

    @Override
    public void init(Context context) {
        mRealm = getRealmInstance(context);
    }

    @Override
    public void clear() {
        if (mRealm != null && !mRealm.isClosed()) {
            mRealm.close();
        }
    }

    private Realm getRealmInstance(Context context) {
        try {
            return Realm.getDefaultInstance();
        } catch (RealmMigrationNeededException exception) {
            Realm.deleteRealm(new RealmConfiguration.Builder(context)
                    .name(Constants.STORAGE_MAIN).build());
            return Realm.getDefaultInstance();
        }
    }

    public void saveTickesToDb(List<Ticket> tickets) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(tickets);
        mRealm.commitTransaction();
    }

    public Observable<RealmResults<Ticket>> getTicketsByState(int[] states) {
        Observable<RealmResults<Ticket>> results = null;
        for (int state : states) {
            results = mRealm.where(Ticket.class)
                    .equalTo(STATE_ID, state)
                    .findAll().asObservable();
        }
        RealmList<Ticket> tickets=new RealmList<>();
        return results;
    }

    public Observable<Ticket> getTicketById(long id) {
        return mRealm.where(Ticket.class)
                .equalTo(ID, id)
                .findFirst().asObservable();
    }

    public void saveUserToDb(UserProfile user) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(user);
        mRealm.commitTransaction();
    }

    public Observable<UserProfile> getProfile() {
        return mRealm.where(UserProfile.class)
                .findFirst().asObservable();
    }

    public void clearTickets(int[] states) {
        for (int state : states) {
            mRealm.beginTransaction();
            mRealm.where(Ticket.class)
                    .equalTo(STATE_ID, state)
                    .findAll().deleteAllFromRealm();
            mRealm.commitTransaction();
        }

    }

}
