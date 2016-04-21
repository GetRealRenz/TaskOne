package com.yalantis.yalantistaskone.ui.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yalantis.yalantistaskone.R;
import com.yalantis.yalantistaskone.ui.view.activity.DetailsActivity;
import com.yalantis.yalantistaskone.ui.view.adapters.ListAdapter;
import com.yalantis.yalantistaskone.ui.contract.TaskContract;
import com.yalantis.yalantistaskone.ui.model.DataModel;
import com.yalantis.yalantistaskone.ui.presenter.TasksPresenter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Антон on 10.04.2016.
 */
public class LastFragment extends BaseFragment implements TaskContract.View, ListAdapter.onItemClickListener {
    @Bind(R.id.list_container)
    ListView mListView;
    private static final String STATUS = "status";

    public static LastFragment newInstance(int status) {
        LastFragment fragment = new LastFragment();
        Bundle args = new Bundle();
        args.putInt(STATUS, status);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        TaskContract.Presenter presenter = new TasksPresenter();
        presenter.attachView(this);
        presenter.loadModel(getArguments().getInt(STATUS));
        return view;
    }

    @Nullable
    @Override
    public int getLayout() {
        return R.layout.fragment_list;
    }


    @Override
    public void addModel(List<DataModel> data) {
        mListView.setAdapter(new ListAdapter(data, this));
    }

    @Override
    public void onItemClick(DataModel model) {
        startActivity(DetailsActivity.newIntent(getContext(), model));
    }
}
