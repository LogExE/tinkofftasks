package edu.hw1;

public class Task4 {
    public static String fixString(String toFix) {
        String res = "";
        int add = 1;
        int len = toFix.length();
        for (int i = 0; i < len; ++i) {
            res += toFix.charAt(Math.min(i + add, len - 1));
            add *= -1;
        }
        return res;
    }
}