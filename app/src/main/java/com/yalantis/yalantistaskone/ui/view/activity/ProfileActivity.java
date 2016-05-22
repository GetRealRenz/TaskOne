package com.yalantis.yalantistaskone.ui.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;
import com.yalantis.yalantistaskone.R;
import com.yalantis.yalantistaskone.ui.model.User;
import com.yalantis.yalantistaskone.ui.util.DateHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProfileActivity extends BaseActivity {
    @Bind(R.id.profile_picture)
    ProfilePictureView mProfilePicture;
    @Bind(R.id.text_view_first_name)
    TextView mFirstName;
    @Bind(R.id.text_view_last_name)
    TextView mLastName;
    @Bind(R.id.text_view_birthdate)
    TextView mBirthdate;
    @Bind(R.id.text_view_phone)
    TextView mPhone;
    @Bind(R.id.text_view_email)
    TextView mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        bindData();
    }

    private void bindData() {
        User user = new User();
        mFirstName.setText(user.getFirstName());
        mLastName.setText(user.getLastName());
        mEmail.setText(user.getEmail());
        mPhone.setText(user.getPhone());
        mBirthdate.setText(DateHelper.getFormattedDate(user.getBirthdayMillis()));
        mProfilePicture.setProfileId(String.valueOf(user.getId()));
    }

    @Override
    public int getFragmentContainer() {
        return 0;
    }
}
