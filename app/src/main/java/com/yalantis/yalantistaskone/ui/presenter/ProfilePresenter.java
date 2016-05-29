package com.yalantis.yalantistaskone.ui.presenter;

import com.yalantis.yalantistaskone.ui.App;
import com.yalantis.yalantistaskone.ui.contract.ProfileContract;
import com.yalantis.yalantistaskone.ui.model.UserProfile;

import rx.functions.Action1;

/**
 * Created by Антон on 27.05.2016.
 */
public class ProfilePresenter implements ProfileContract.Presenter {
    private ProfileContract.View mView;

    /**
     * Loads logged user's profile into view
     */


    @Override
    public void getProfile() {
        App.getDataManager().getProfile().subscribe(new Action1<UserProfile>() {
            @Override
            public void call(UserProfile profile) {
                mView.showProfile(profile);
            }
        });
    }

    @Override
    public void attachView(ProfileContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
