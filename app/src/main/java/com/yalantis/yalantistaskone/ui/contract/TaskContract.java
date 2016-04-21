package com.yalantis.yalantistaskone.ui.contract;

import com.yalantis.yalantistaskone.ui.model.DataModel;

import java.util.List;

/**
 * Created by Антон on 20.04.2016.
 */
public class TaskContract {
    public interface Presenter extends BaseMvpPresenter<View> {
        void loadModel(int status);
    }

    public interface View extends BaseMvpView {
        void addModel(List<DataModel> data);
    }
}
