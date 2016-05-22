package com.yalantis.yalantistaskone.ui.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.yalantis.yalantistaskone.R;
import com.yalantis.yalantistaskone.ui.api.ApiSettings;
import com.yalantis.yalantistaskone.ui.contract.TaskContract;
import com.yalantis.yalantistaskone.ui.model.Ticket;
import com.yalantis.yalantistaskone.ui.presenter.TasksPresenter;
import com.yalantis.yalantistaskone.ui.util.EndlessScrollListener;
import com.yalantis.yalantistaskone.ui.view.activity.DetailsActivity;
import com.yalantis.yalantistaskone.ui.view.adapters.BlankFragmentAdapter;

import java.util.List;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends BaseFragment implements TaskContract.View, BlankFragmentAdapter.ItemClickListener {
    private static final String STATUS = "status";
    private TaskContract.Presenter mPresenter;
    private BlankFragmentAdapter mAdapter;
    @Bind(R.id.is_loading)
    ProgressBar loading;

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.attachView(this);
    }

    public BlankFragment() {
        // Required empty public constructor
    }


    @Bind(R.id.blank_recycler)
    RecyclerView mRecyclerView;


    public synchronized static BlankFragment newInstance(final int[] status) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putIntArray(STATUS, status);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mPresenter = new TasksPresenter();
        mPresenter.attachView(this);
        iniRecycler();
        showProgress();
        int[] arr = getArguments().getIntArray(STATUS);
        mPresenter.loadFromDb(getArguments().getIntArray(STATUS));
        if (mAdapter.isViewEmpty()) {
            mPresenter.getTasks(getArguments().getIntArray(STATUS), ApiSettings.START_OFFSET);
        }
        // Inflate the layout for this fragment
        return view;
    }

    @Nullable
    @Override
    public int getLayout() {
        return R.layout.fragment_blank;
    }


    @Override
    public void addModel(List<Ticket> data) {
        mAdapter.addData(data);
    }

    @Override
    public void showProgress() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        loading.setVisibility(View.GONE);
    }

    private void iniRecycler() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mAdapter = new BlankFragmentAdapter(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new EndlessScrollListener(manager) {
            @Override
            public void onLoadMore(int page) {
                showProgress();
                mPresenter.getTasks(getArguments().getIntArray(STATUS), page);
            }
        });
    }

    @Override
    public void onItemClick(Ticket model) {
        long id = model.getId();
        startActivity(DetailsActivity.newIntent(getContext(), model.getId()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();

    }

}
