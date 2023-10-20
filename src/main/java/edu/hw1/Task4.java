package edu.hw1;

public class Task4 {
    private Task4() {

    }

    public static String fixString(String toFix) {
        StringBuilder res = new StringBuilder();
        int add = 1;
        int len = toFix.length();
        for (int i = 0; i < len; ++i) {
            res.append(toFix.charAt(Math.min(i + add, len - 1)));
            add *= -1;
        }
        return res.toString();
    }
}
