package com.yalantis.yalantistaskone.ui.contract;

/**
 * Created by Антон on 20.04.2016.
 */
public interface BaseMvpPresenter<V extends BaseMvpView> {

    void attachView(V view);

    void detachView();
}
