package com.yalantis.yalantistaskone.ui.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yalantis.yalantistaskone.R;
import com.yalantis.yalantistaskone.ui.contract.ProfileContract;
import com.yalantis.yalantistaskone.ui.model.UserProfile;
import com.yalantis.yalantistaskone.ui.presenter.ProfilePresenter;
import com.yalantis.yalantistaskone.ui.util.RoundBitmapTransformation;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProfileActivity extends BaseActivity implements ProfileContract.View {
    @Bind(R.id.profile_picture)
    ImageView mProfilePicture;
    @Bind(R.id.text_view_first_name)
    TextView mFirstName;
    @Bind(R.id.text_view_last_name)
    TextView mLastName;
    private ProfileContract.Presenter mPresenter;

    public ProfileActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        mPresenter = new ProfilePresenter();
        mPresenter.attachView(this);
        mPresenter.getProfile();

    }


    @Override
    public int getFragmentContainer() {
        return 0;
    }

    @Override
    public void showProfile(UserProfile profile) {
        mFirstName.setText(profile.getFirstName());
        mLastName.setText(profile.getLastName());
        Picasso.with(ProfileActivity.this).load(profile.getImageUrl())
                .transform(new RoundBitmapTransformation()).into(mProfilePicture);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.attachView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.attachView(this);
    }
}
