package com.yalantis.yalantistaskone.ui.presenter;

import com.yalantis.yalantistaskone.ui.contract.TaskDetailContract;

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
}
