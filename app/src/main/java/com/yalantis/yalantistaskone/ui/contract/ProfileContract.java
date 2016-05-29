package com.yalantis.yalantistaskone.ui.contract;

import com.yalantis.yalantistaskone.ui.model.UserProfile;

/**
 * Created by Антон on 27.05.2016.
 */
public class ProfileContract {
    public interface Presenter extends BaseMvpPresenter<View> {
        void getProfile();
    }

    public interface View extends BaseMvpView {
        void showProfile(UserProfile profile);
    }
}
