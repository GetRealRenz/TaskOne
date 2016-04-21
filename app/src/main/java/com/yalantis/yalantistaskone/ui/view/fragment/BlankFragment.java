package com.yalantis.yalantistaskone.ui.view.fragment;


import android.os.Bundle;
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
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends BaseFragment implements TaskContract.View, BlankFragmentAdapter.ItemClickListener {
    private static final String STATUS = "status";

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
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        ButterKnife.bind(this, view);
        TaskContract.Presenter Presenter = new TasksPresenter();
        Presenter.attachView(this);
        Presenter.loadModel(getArguments().getInt(STATUS));
        // Inflate the layout for this fragment
        return view;
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
}
