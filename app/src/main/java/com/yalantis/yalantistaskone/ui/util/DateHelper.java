package com.yalantis.yalantistaskone.ui.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Антон on 22.05.2016.
 */
public class DateHelper {
    private static final String LOW_LOC = "uk";
    private static final String BIG_LOC = "UA";
    public static final String DATE_FORMAT = "d MMMM yyyy";
    private static final SimpleDateFormat mDateFormatter = new SimpleDateFormat(DATE_FORMAT, new Locale(LOW_LOC, BIG_LOC));

    public static String getFormattedDate(long millisDate) {
        return mDateFormatter.format(new Date(millisDate)).toLowerCase();
    }
}
