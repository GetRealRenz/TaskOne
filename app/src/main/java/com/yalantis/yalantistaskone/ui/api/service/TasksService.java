package com.yalantis.yalantistaskone.ui.api.service;

import com.yalantis.yalantistaskone.ui.api.ApiSettings;
import com.yalantis.yalantistaskone.ui.model.Ticket;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Антон on 20.05.2016.
 */
public interface TasksService {
    @GET(ApiSettings.TICKETS)
    Observable<List<Ticket>> getTasks(@Query(ApiSettings.QUERY_STATE) int[] state,
                                      @Query(ApiSettings.QUERY_AMOUNT) int amount,
                                      @Query(ApiSettings.QUERY_OFFSET) int offset);
}
