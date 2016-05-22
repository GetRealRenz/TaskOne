package com.yalantis.yalantistaskone.ui.api.service;

import com.yalantis.yalantistaskone.ui.model.Ticket;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Антон on 20.05.2016.
 */
public interface TasksService {
    @GET("tickets")
    Observable<List<Ticket>> getTasks(@Query("state") int[] state,
                                      @Query("amount") int amount,
                                      @Query("offset") int offset);
}
