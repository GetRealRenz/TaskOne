package com.yalantis.yalantistaskone.ui.contract;

import com.yalantis.yalantistaskone.ui.model.Ticket;
import com.yalantis.yalantistaskone.ui.model.TicketFiles;

import java.util.List;

/**
 * Created by Антон on 20.04.2016.
 */
public class TaskDetailContract {
    /**
     * This contract is made for future tasks
     */
    public interface Presenter extends BaseMvpPresenter<View> {
        void getTicket(long id);

    }

    public interface View extends BaseMvpView {
        void bindData(Ticket ticket);

        void initRecycler(List<TicketFiles> images);
    }
}
