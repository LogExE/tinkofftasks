package edu.hw1;

public class Task1 {
    private static final int SECONDS_MAX = 60;
    private static final int RESULT_ERROR = -1;

    private Task1() {

    }

    @SuppressWarnings("MultipleVariableDeclarations")
    public static long getVideoDurationSeconds(String duration) {
        String[] sep = duration.split(":");
        if (sep.length != 2) {
            return RESULT_ERROR;
        }
        long mins, secs;
        try {
            mins = Long.parseLong(sep[0]);
            secs = Long.parseLong(sep[1]);
        } catch (NumberFormatException e) {
            return RESULT_ERROR;
        }
        if (mins < 0 || secs < 0 || secs >= SECONDS_MAX) {
            return RESULT_ERROR;
        }
        return mins * SECONDS_MAX + secs;
    }
}
