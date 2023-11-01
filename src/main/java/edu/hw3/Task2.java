package edu.hw3;

import java.util.ArrayList;

public class Task2 {
    private Task2() {

    }

    public static ArrayList<String> clusterize(String str) {
        int balance = 0;
        ArrayList<String> res = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        for (char x : str.toCharArray()) {
            int preBalance = balance;
            cur.append(x);
            if (x == '(') {
                --balance;
            } else if (x == ')') {
                if (balance == 0) {
                    throw new IllegalArgumentException("The string has too many closing parentheses!");
                }
                ++balance;
            }
            if (balance == 0 && preBalance != 0) {
                res.add(cur.toString());
                cur.setLength(0);
            }
        }
        if (balance < 0) {
            throw new IllegalArgumentException("The string has too many opening parentheses!");
        }
        if (cur.length() != 0) {
            res.add(cur.toString());
        }
        return res;
    }
}
