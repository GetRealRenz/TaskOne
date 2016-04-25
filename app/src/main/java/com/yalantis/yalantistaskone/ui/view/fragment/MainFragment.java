package com.yalantis.yalantistaskone.ui.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yalantis.yalantistaskone.R;
import com.yalantis.yalantistaskone.ui.util.Constants;
import com.yalantis.yalantistaskone.ui.view.adapters.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Антон on 17.04.2016.
 */
public class MainFragment extends BaseFragment {

    @Bind(R.id.pager)
    ViewPager mPager;
    @Bind(R.id.tab)
    TabLayout mTabs;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        initView();
        return view;
    }

    @Nullable
    @Override
    public int getLayout() {
        return R.layout.fragment_main;
    }

    private void setupPager(ViewPager pager) {
        PagerAdapter adapter = new PagerAdapter(getChildFragmentManager());
        adapter.addFragment(BlankFragment.newInstance(Constants.STATUS_INWORK), getString(R.string.in_work));
        adapter.addFragment(BlankFragment.newInstance(Constants.STATUS_DONE), getString(R.string.is_done));
        adapter.addFragment(LastFragment.newInstance(Constants.STATUS_UNDONE), getString(R.string.undone));
        pager.setAdapter(adapter);
    }

    private void initView() {
        setupPager(mPager);
        mTabs.setupWithViewPager(mPager);
    }

}
