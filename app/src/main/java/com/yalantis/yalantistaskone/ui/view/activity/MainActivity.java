package com.yalantis.yalantistaskone.ui.view.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.yalantis.yalantistaskone.R;
import com.yalantis.yalantistaskone.ui.App;
import com.yalantis.yalantistaskone.ui.model.UserProfile;
import com.yalantis.yalantistaskone.ui.util.Helpers;
import com.yalantis.yalantistaskone.ui.view.fragment.MainFragment;

import org.json.JSONObject;

import java.util.Collections;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @Bind(R.id.main_toolbar)
    Toolbar mToolbar;
    @Bind(R.id.drawer)
    DrawerLayout mDrawer;
    private CallbackManager mCallbackManager;
    private static final int IMAGE_SIZE = 250;
    private static final String PROFILE_TYPE = "public_profile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mCallbackManager = CallbackManager.Factory.create();
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.all_tasks);
        }
        switchFragment(MainFragment.newInstance());
        initDrawer();
    }

    private void initDrawer() {
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawer, R.string.drawer_open, R.string.drawer_close);
        drawerToggle.syncState();
        mDrawer.addDrawerListener(drawerToggle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.filter_menu, menu);
        return true;
    }


    @Override
    public int getFragmentContainer() {
        return R.id.container_main;
    }

    /**
     * if user is'nt logged in with facebook we will do it
     */
    public void onLoginClick(MenuItem item) {
        if (!Helpers.isLoggedIn()) {
            signUp();
        } else {
            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
        }

    }

    private void signUp() {
        LoginManager.getInstance().logInWithReadPermissions(this
                , Collections.singletonList(PROFILE_TYPE));
        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        final String token = loginResult.getAccessToken().getToken();
                            GraphRequest.newMeRequest(
                                    AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                                        @Override
                                        public void onCompleted(JSONObject json, GraphResponse response) {
                                            {
                                                setUserProfile(Profile.getCurrentProfile()
                                                        , token);
                                                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                                            }
                                        }
                                    }).executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        Log.d("Err", "cancel");
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(MainActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void setUserProfile(Profile profile, String token) {
        UserProfile userProfile = new UserProfile();
        userProfile.setAccessToken(token);
        userProfile.setFirstName(profile.getFirstName());
        userProfile.setLastName(profile.getLastName());
        userProfile.setImageUrl(profile.getProfilePictureUri(IMAGE_SIZE, IMAGE_SIZE).toString());
        userProfile.setId(profile.getId());
        App.getDataManager().saveUserToDb(userProfile);
    }
}
