package com.yalantis.yalantistaskone.ui.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yalantis.yalantistaskone.R;
import com.yalantis.yalantistaskone.ui.view.activity.DetailsActivity;
import com.yalantis.yalantistaskone.ui.view.adapters.BlankFragmentAdapter;
import com.yalantis.yalantistaskone.ui.contract.TaskContract;
import com.yalantis.yalantistaskone.ui.model.DataModel;
import com.yalantis.yalantistaskone.ui.presenter.TasksPresenter;

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


    public BlankFragment() {
        // Required empty public constructor
    }


    @Bind(R.id.blank_recycler)
    RecyclerView mRecyclerView;


    public static BlankFragment newInstance(int status) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putInt(STATUS, status);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mPresenter = new TasksPresenter();
        mPresenter.attachView(this);
        mPresenter.loadModel(getArguments().getInt(STATUS));
        // Inflate the layout for this fragment
        return view;
    }

    @Nullable
    @Override
    public int getLayout() {
        return R.layout.fragment_blank;
    }


    @Override
    public void addModel(List<DataModel> data) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new BlankFragmentAdapter(data, this));
    }

    @Override
    public void onItemClick(DataModel model) {
        startActivity(DetailsActivity.newIntent(getContext(), model));
    }

    @Override
    public void onDestroyView() {
        mPresenter.detachView();
        super.onDestroyView();

    }
}
