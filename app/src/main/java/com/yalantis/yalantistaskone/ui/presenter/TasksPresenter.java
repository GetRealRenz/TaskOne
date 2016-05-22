package com.yalantis.yalantistaskone.ui.presenter;

import com.yalantis.yalantistaskone.ui.App;
import com.yalantis.yalantistaskone.ui.api.ApiSettings;
import com.yalantis.yalantistaskone.ui.contract.TaskContract;
import com.yalantis.yalantistaskone.ui.model.Ticket;

import java.util.List;

import io.realm.RealmResults;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Антон on 20.04.2016.
 */
public class TasksPresenter implements TaskContract.Presenter {
    private TaskContract.View mView;

    @Override
    public void getTasks(int[] status, int offset) {
        App.getApiManager().getTasks(status, ApiSettings.DISPLAY_AMOUNT, offset)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .retry(5)
                .subscribe(new Subscriber<List<Ticket>>() {
                    @Override
                    public void onCompleted() {
                        mView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<Ticket> dataModels) {
                        App.getDataManager().saveTickesToDb(dataModels);
                        mView.addModel(dataModels);
                    }
                });
    }

    @Override
    public void loadFromDb(int[] status) {
        App.getDataManager().getTicketsByState(status)
                .subscribe(new Action1<RealmResults<Ticket>>() {
                    @Override
                    public void call(RealmResults<Ticket> tickets) {
                        mView.addModel(tickets);
                    }
                });
    }

    @Override
    public void attachView(TaskContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
