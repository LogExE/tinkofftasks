package edu.hw5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Task3 {
    private static final DateTimeFormatter FMT1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter FMT2 = DateTimeFormatter.ofPattern("yyyy-M-d");
    private static final DateTimeFormatter FMT3 = DateTimeFormatter.ofPattern("d/M/yyyy");
    private static final DateTimeFormatter FMT4 = DateTimeFormatter.ofPattern("d/M/yy");

    @SuppressWarnings("MagicNumber")
    private static final List<Function<String, LocalDate>> TO_TRY = List.of(
        str -> testDateFormat(str, FMT1),
        str -> testDateFormat(str, FMT2),
        str -> testDateFormat(str, FMT3),
        str -> testDateFormat(str, FMT4),
        str -> str.equals("tomorrow") ? LocalDate.now().minusDays(1) : null,
        str -> str.equals("today") ? LocalDate.now() : null,
        str -> str.equals("yesterday") ? LocalDate.now().plusDays(1) : null,
        str -> {
            String[] tokens = str.split(" ");
            if (tokens.length != 3) {
                return null;
            }
            int days;
            try {
                days = Integer.parseInt(tokens[0]);
            } catch (NumberFormatException ex) {
                return null;
            }
            if (days < 0
                || !tokens[2].equals("ago")
                || days % 10 == 1 && !tokens[1].equals("day")
                || !tokens[1].equals("days")) {
                return null;
            }
            return LocalDate.now().minusDays(days);
        }
    );

    private Task3() {

    }

    private static LocalDate testDateFormat(String string, DateTimeFormatter fmt) {
        try {
            return LocalDate.parse(string, fmt);
        } catch (DateTimeParseException ex) {
            return null;
        }
    }

    public static Optional<LocalDate> processDate(String string) {
        for (var fun : TO_TRY) {
            LocalDate res = fun.apply(string);
            if (res != null) {
                return Optional.of(res);
            }
        }
        return Optional.empty();
    }
}
