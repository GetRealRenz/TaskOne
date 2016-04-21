package com.yalantis.yalantistaskone.ui.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yalantis.yalantistaskone.R;
import com.yalantis.yalantistaskone.ui.App;
import com.yalantis.yalantistaskone.ui.util.SecondsToDate;
import com.yalantis.yalantistaskone.ui.view.adapters.ImageRecyclerAdapter;
import com.yalantis.yalantistaskone.ui.contract.TaskDetailContract;
import com.yalantis.yalantistaskone.ui.model.DataModel;
import com.yalantis.yalantistaskone.ui.presenter.TaskDetailPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements TaskDetailContract.View {

    public static final String KEY_MODEL = "model";

    @Bind(R.id.image_recycler_view)
    public RecyclerView mRecyclerView;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.dt_created)
    TextView created;
    @Bind(R.id.dt_left)
    TextView daysLeft;
    @Bind(R.id.some_text)
    TextView description;
    @Bind(R.id.status)
    TextView status;
    @Bind(R.id.dt_registered)
    TextView registred;
    @Bind(R.id.tx_responsible)
    TextView responsible;

    public static Intent newIntent(@NonNull Context context, @NonNull DataModel model) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(KEY_MODEL, model);
        return intent;
    }

    /*Dummy image Urls*/
    private final static String[] mImageUrls = {
            "http://i.imgur.com/rFLNqWI.jpg",
            "http://i.imgur.com/C9pBVt7.jpg",
            "http://i.imgur.com/rT5vXE1.jpg",
            "http://i.imgur.com/aIy5R2k.jpg",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        TaskDetailContract.Presenter mPresenter = new TaskDetailPresenter();
        mPresenter.attachView(this);
        DataModel model = getIntent().getParcelableExtra(KEY_MODEL);
        ButterKnife.bind(this);
        bindData(model);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initRecycler();
    }


    /**
     * Closing app then user presses home button
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Showing a toast every time user presses a control
     */
    public void onViewClick(View view) {
        Toast.makeText(this, view.getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
    }

    private void bindData(DataModel data) {
        SecondsToDate date = new SecondsToDate();
        title.setText(data.getTitle());
        created.setText(date.toDate(data.getDate()));
        daysLeft.setText(date.toDate(data.getDaysleft()));
        description.setText(data.getDescription());
        registred.setText(date.toDate(data.getRegistred()));
        responsible.setText(data.getResponsible());
        final String inwork = getString(R.string.in_work);
        final String done = getString(R.string.done);
        final String undone = getString(R.string.undone);
        switch (data.getStatus()) {
            case "В роботі":
                status.setText(inwork);
                status.setBackground(ContextCompat.getDrawable(this, R.drawable.textview_inwork));
                break;
            case "Виконано":
                status.setText(done);
                status.setBackground(ContextCompat.getDrawable(this, R.drawable.textview_done));
                break;
            case "Не виконано":
                status.setText(undone);
                status.setBackground(ContextCompat.getDrawable(this, R.drawable.textview_undone));
                break;
        }

    }

    /**
     * Init our Recycler View
     */
    private void initRecycler() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(new ImageRecyclerAdapter(mImageUrls, DetailsActivity.this));

    }

    @Override
    public Context getContext() {
        return this;
    }
}
