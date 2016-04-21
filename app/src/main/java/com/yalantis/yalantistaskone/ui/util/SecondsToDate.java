package com.yalantis.yalantistaskone.ui.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Created by Антон on 20.04.2016.
 */
public class SecondsToDate {

    public String toDate(String millis) {
        DateFormat formatter = SimpleDateFormat.getDateInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(TimeUnit.SECONDS.toMillis(Long.parseLong(millis)));

        return formatter.format(calendar.getTime());
    }
}
