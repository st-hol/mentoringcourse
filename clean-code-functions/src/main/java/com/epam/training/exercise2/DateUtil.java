package com.epam.training.exercise2;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static void main(String[] args) {
        DateUtil dateUtil = new DateUtil();
        Date date = new Date();
        dateUtil.increment(date, false);
        System.out.println(date);

        System.out.println(dateUtil.create(2014, 10, 10));
    }

    private void increment(final Date date, final boolean up) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, up ? 1 : -1);
        setZeros(calendar);
        date.setTime(calendar.getTime().getTime());
    }

    private void setZeros(final Calendar calendar) {
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    private Date create(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

}

