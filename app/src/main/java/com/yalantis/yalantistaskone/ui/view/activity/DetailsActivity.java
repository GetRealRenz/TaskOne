package com.yalantis.yalantistaskone.ui.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yalantis.yalantistaskone.R;
import com.yalantis.yalantistaskone.ui.contract.TaskDetailContract;
import com.yalantis.yalantistaskone.ui.model.Performer;
import com.yalantis.yalantistaskone.ui.model.Ticket;
import com.yalantis.yalantistaskone.ui.model.TicketFiles;
import com.yalantis.yalantistaskone.ui.presenter.TaskDetailPresenter;
import com.yalantis.yalantistaskone.ui.util.Constants;
import com.yalantis.yalantistaskone.ui.util.DateHelper;
import com.yalantis.yalantistaskone.ui.view.adapters.ImageRecyclerAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailsActivity extends BaseActivity implements TaskDetailContract.View {

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
    /*Dummy image Urls*/
    private TaskDetailContract.Presenter mPresenter;

    public static Intent newIntent(@NonNull Context context, @NonNull long id) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(KEY_MODEL, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mPresenter = new TaskDetailPresenter();
        mPresenter.attachView(this);
        long id = getIntent().getLongExtra(KEY_MODEL, 0);

        ButterKnife.bind(this);
        mPresenter.getTicket(id);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
    @SuppressWarnings("unused")
    public void onViewClick(View view) {
        Toast.makeText(this, view.getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void bindData(Ticket data) {
        title.setText(data.getTitle());
        created.setText(DateHelper.getFormattedDate(data.getStartDate() * Constants.MILLIS_MULT));
        daysLeft.setText(DateHelper.getFormattedDate(data.getDeadline() * Constants.MILLIS_MULT));
        description.setText(data.getBody());
        registred.setText(DateHelper.getFormattedDate(data.getCreated() * Constants.MILLIS_MULT));
        if (!(data.getPerformers() == null)) {
            for (Performer performer : data.getPerformers()) {
                responsible.append(performer.getOrganization());
            }
        }
        /*switch (data.getType().getTitle()) {
            case Constants.IN_WORK:
                status.setText(Constants.IN_WORK);
                status.setBackground(ContextCompat.getDrawable(this, R.drawable.textview_inwork));
                break;
            case Constants.DONE:
                status.setText(Constants.DONE);
                status.setBackground(ContextCompat.getDrawable(this, R.drawable.textview_done));
                break;
            case Constants.UNDONE:
                status.setText(Constants.UNDONE);
                status.setBackground(ContextCompat.getDrawable(this, R.drawable.textview_undone));
                break;
        }*/

    }

    /**
     * Init our Recycler View
     */
    @Override
    public void initRecycler(List<TicketFiles> images) {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(new ImageRecyclerAdapter(images, DetailsActivity.this));

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
           mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public int getFragmentContainer() {
        return 0;
    }
}
