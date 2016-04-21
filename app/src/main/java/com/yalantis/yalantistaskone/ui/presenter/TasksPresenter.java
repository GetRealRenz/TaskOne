package com.yalantis.yalantistaskone.ui.presenter;

import com.yalantis.yalantistaskone.ui.contract.TaskContract;
import com.yalantis.yalantistaskone.ui.util.InitMock;
import com.yalantis.yalantistaskone.ui.view.fragment.MainFragment;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Антон on 20.04.2016.
 */
public class TasksPresenter implements TaskContract.Presenter {
    private TaskContract.View mView;

    @Override
    public void loadModel(int status) {
        try {
            InitMock mock = new InitMock();
            InputStream in = mView.getContext().getAssets().open("mock.xml");
            mock.initModel(in);
            mView.addModel(mock.getModels(status));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
