package com.yalantis.yalantistaskone.ui.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yalantis.yalantistaskone.R;
import com.yalantis.yalantistaskone.ui.view.adapters.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Антон on 17.04.2016.
 */
public class MainFragment extends Fragment {

    @Bind(R.id.pager)
    ViewPager mPager;
    @Bind(R.id.tab)
    TabLayout mTabs;
    private PagerAdapter mAdapter;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void setupPager(ViewPager pager) {
        mAdapter = new PagerAdapter(getChildFragmentManager());
        mAdapter.addFragment(BlankFragment.newInstance(1), getString(R.string.in_work));
        mAdapter.addFragment(BlankFragment.newInstance(3), getString(R.string.is_done));
        mAdapter.addFragment(LastFragment.newInstance(2), getString(R.string.undone));
        pager.setAdapter(mAdapter);
    }

    private void initView() {
        setupPager(mPager);
        mTabs.setupWithViewPager(mPager);
    }

}
