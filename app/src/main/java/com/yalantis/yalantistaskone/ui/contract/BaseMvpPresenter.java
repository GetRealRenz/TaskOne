package com.yalantis.yalantistaskone.ui.contract;

/**
 * Created by Антон on 20.04.2016.
 */
@SuppressWarnings("unused")
public interface BaseMvpPresenter<V extends BaseMvpView> {

    void attachView(V view);

    void detachView();
}
