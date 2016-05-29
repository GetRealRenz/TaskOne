package com.yalantis.yalantistaskone.ui.manager;

import android.content.Context;

import com.yalantis.yalantistaskone.ui.api.ApiSettings;
import com.yalantis.yalantistaskone.ui.api.service.TasksService;
import com.yalantis.yalantistaskone.ui.interfaces.Manager;
import com.yalantis.yalantistaskone.ui.model.Ticket;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Антон on 20.05.2016.
 */
public class ApiManager implements Manager {

    private Retrofit mRetrofit;

    private TasksService mTasksService;

    @Override
    public void init(Context context) {
        initRetrofit();
        initService();
    }

    @Override
    public void clear() {

    }

    private void initService() {
        mTasksService = mRetrofit.create(TasksService.class);
    }

    private void initRetrofit() {

        mRetrofit = new Retrofit.Builder()
                .baseUrl(ApiSettings.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


    public Observable<List<Ticket>> getTasks(int[] status, int amount, int ofset) {
        return mTasksService.getTasks(status, amount, ofset);
    }
}
