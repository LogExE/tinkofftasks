package edu.hw1;

public class Task1 {
    public static long getVideoDurationSeconds(String duration) {
        String[] sep = duration.split(":");
        if (sep.length != 2) {
            return -1;
        }
        long mins, secs;
        try {
            mins = Long.parseLong(sep[0]);
            secs = Long.parseLong(sep[1]);
        } catch (NumberFormatException e) {
            return -1;
        }
        if (mins < 0 || secs < 0) {
            return -1;
        }
        if (secs > 59) {
            return -1;
        }
        return mins * 60 + secs;
    }
}
