package com.yalantis.yalantistaskone.ui.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Антон on 21.04.2016.
 */
public abstract class BaseActivity extends AppCompatActivity {

    public void switchFragment(Fragment fragment) {
        switchFragment(fragment, false);
    }

    public void switchFragment(Fragment fragment, boolean isBackstack) {
        if (isBackstack) {
            initFragment(fragment).addToBackStack(fragment.getClass().getSimpleName()).commit();
        } else {
            initFragment(fragment).commit();
        }
    }

    private FragmentTransaction initFragment(Fragment fragment) {
        return getSupportFragmentManager().beginTransaction()
                .replace(getFragmentContainer(), fragment, fragment.getClass().getSimpleName());
    }

    abstract public int getFragmentContainer();
}
