package edu.hw5;

public class Task6 {
    private Task6() {

    }

    public static boolean isSubstring(String s1, String s2) {
        return s2.matches(".*" + s1 + ".*");
    }
}
