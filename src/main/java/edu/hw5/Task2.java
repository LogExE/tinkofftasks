package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("MagicNumber")
public class Task2 {
    private Task2() {

    }

    public static List<LocalDate> fiendishFridays(int year) {
        List<LocalDate> fridays = new ArrayList<>();
        for (int i = 1; i <= 12; ++i) {
            LocalDate cur = LocalDate.of(year, i, 13);
            if (cur.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridays.add(cur);
            }
        }
        return fridays;
    }

    @SuppressWarnings("ParameterAssignment")
    public static LocalDate nearestFriday13th(LocalDate date) {
        do {
            date = date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }
        while (date.getDayOfMonth() != 13);
        return date;
    }
}
