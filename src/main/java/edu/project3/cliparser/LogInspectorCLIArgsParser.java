package edu.project3.cliparser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public class LogInspectorCLIArgsParser {
    private LogInspectorCLIArgsParser() {

    }

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE;

    enum CLIArgsState {
        NONE,
        PATHS,
        FROM,
        TO,
        FORMAT
    }

    private static final Map<String, CLIArgsState> CLI_FLAGS = Map.of(
        "--path", CLIArgsState.PATHS,
        "--from", CLIArgsState.FROM,
        "--to", CLIArgsState.TO,
        "--format", CLIArgsState.FORMAT
    );

    private static final Map<String, LogInspectorOutputFormat> FMTS = Map.of(
        "markdown", LogInspectorOutputFormat.MARKDOWN,
        "adoc", LogInspectorOutputFormat.ADOC
    );

    @SuppressWarnings("InnerAssignment")
    public static Optional<LogInspectorCLIArgs> parse(String[] args) {
        ArrayList<String> paths = new ArrayList<>();
        String dateFrom = null;
        String dateTo = null;
        String format = null;

        CLIArgsState state = CLIArgsState.NONE;
        boolean failed = false;
        for (String arg : args) {
            if (CLI_FLAGS.containsKey(arg)) {
                state = CLI_FLAGS.get(arg);
                continue;
            }
            switch (state) {
                case PATHS -> paths.add(arg);
                case FROM -> dateFrom = arg;
                case TO -> dateTo = arg;
                case FORMAT -> format = arg;
                default -> failed = true;
            }
        }

        LocalDate parsedFrom = null;
        if (dateFrom == null) {
            parsedFrom = LocalDate.MIN;
        } else {
            try {
                parsedFrom = LocalDate.parse(dateFrom, FORMATTER);
            } catch (DateTimeParseException ex) {
                failed = true;
            }
        }

        LocalDate parsedTo = null;
        if (dateTo == null) {
            parsedTo = LocalDate.MAX;
        } else {
            try {
                parsedTo = LocalDate.parse(dateTo, FORMATTER);
            } catch (DateTimeParseException ex) {
                failed = true;
            }
        }

        LogInspectorOutputFormat parsedFormat = null;
        if (format == null) {
            parsedFormat = LogInspectorOutputFormat.MARKDOWN;
        } else if (FMTS.containsKey(format)) {
            parsedFormat = FMTS.get(format);
        } else {
            failed = true;
        }

        if (failed) {
            return Optional.empty();
        }
        return Optional.of(new LogInspectorCLIArgs(paths, parsedFrom, parsedTo, parsedFormat));
    }
}
