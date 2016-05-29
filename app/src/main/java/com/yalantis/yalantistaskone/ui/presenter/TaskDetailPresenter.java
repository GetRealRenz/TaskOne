package com.yalantis.yalantistaskone.ui.presenter;

import com.yalantis.yalantistaskone.ui.App;
import com.yalantis.yalantistaskone.ui.contract.TaskDetailContract;
import com.yalantis.yalantistaskone.ui.model.Ticket;

import rx.functions.Action1;

/**
 * Created by Антон on 20.04.2016.
 */
public class TaskDetailPresenter implements TaskDetailContract.Presenter {
    private TaskDetailContract.View mView;

    @Override
    public void attachView(TaskDetailContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    /**
     *  loads ticket from realm by id
     * @param id of ticket
     */
    @Override
    public void getTicket(long id) {
        App.getDataManager().getTicketById(id)
                .subscribe(new Action1<Ticket>() {
                    @Override
                    public void call(Ticket ticket) {
                        if (mView != null) {
                            mView.initRecycler(ticket.getFiles());
                            mView.bindData(ticket);
                        }
                    }
                });
    }
}
