package com.yalantis.yalantistaskone.ui.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yalantis.yalantistaskone.R;
import com.yalantis.yalantistaskone.ui.api.ApiSettings;
import com.yalantis.yalantistaskone.ui.model.Ticket;
import com.yalantis.yalantistaskone.ui.view.adapters.PagerAdapter;

import butterknife.Bind;
import io.realm.Realm;

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
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.where(Ticket.class).findAll().deleteAllFromRealm();
        realm.commitTransaction();
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
        adapter.addFragment(BlankFragment.newInstance(ApiSettings.IN_PROGRESS), getString(R.string.in_work));
        adapter.addFragment(BlankFragment.newInstance(ApiSettings.DONE), getString(R.string.is_done));
        adapter.addFragment(BlankFragment.newInstance(ApiSettings.UNDONE), getString(R.string.undone));
        pager.setAdapter(adapter);
    }

    private void initView() {
        setupPager(mPager);
        mTabs.setupWithViewPager(mPager);
    }

}
