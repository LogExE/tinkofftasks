package edu.hw5;

public class Task7 {
    private Task7() {

    }

    public static boolean atLeast3CharsAnd0On3(String str) {
        return str.matches("[01]{2}0[01]*");
    }

    public static boolean endStartSame(String str) {
        return str.matches("0|1|0[01]*0|1[01]*1");
    }

    public static boolean lenBetween1And3(String str) {
        return str.matches("[01]{1,3}");
    }
}
