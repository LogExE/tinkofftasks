package edu.hw3;

public class Task1 {
    private Task1() {

    }

    public static String atbash(String str) {
        StringBuilder sb = new StringBuilder();
        for (char x : str.toCharArray()) {
            char modified;
            if (x >= 'a' && x <= 'z') {
                modified = (char) ('z' - (x - 'a'));
            } else if (x >= 'A' && x <= 'Z') {
                modified = (char) ('Z' - (x - 'A'));
            } else {
                modified = x;
            }
            sb.append(modified);
        }
        return sb.toString();
    }
}
