package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task1 {
    private Task1() {

    }

    private static final DateTimeFormatter MY_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

    public static String meanDuration(String entries) {
        Duration total = Duration.ZERO;
        String[] lines = entries.split("\n");
        for (String entry : lines) {
            String[] dates = entry.split(" - ");
            if (dates.length != 2) {
                throw new IllegalArgumentException("Sessions format was invalid!");
            }
            LocalDateTime t1;
            LocalDateTime t2;
            try {
                t1 = LocalDateTime.parse(dates[0], MY_FMT);
                t2 = LocalDateTime.parse(dates[1], MY_FMT);
            } catch (DateTimeParseException ex) {
                throw new IllegalArgumentException("DateTime format was invalid!");
            }
            if (t1.isAfter(t2)) {
                throw new IllegalArgumentException("Times order was invalid!");
            }
            Duration diff = Duration.between(t1, t2);
            total = total.plus(diff);
        }
        Duration res = total.dividedBy(lines.length);
        return res.toHoursPart() + "ч " + res.toMinutesPart() + "м";
    }
}
