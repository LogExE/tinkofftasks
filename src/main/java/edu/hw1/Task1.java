package edu.hw1;

public class Task1 {
    private static final int SECONDS_MAX = 60;
    private static final int RESULT_ERROR = -1;

    private Task1() {

    }

    public static long getVideoDurationSeconds(String duration) {
        String[] sep = duration.split(":");
        if (sep.length != 2) {
            return RESULT_ERROR;
        }
        long minutes;
        long seconds;
        try {
            minutes = Long.parseLong(sep[0]);
            seconds = Long.parseLong(sep[1]);
        } catch (NumberFormatException e) {
            return RESULT_ERROR;
        }
        if (minutes < 0 || seconds < 0 || seconds >= SECONDS_MAX) {
            return RESULT_ERROR;
        }
        return minutes * SECONDS_MAX + seconds;
    }
}
