package edu.hw1;

public class Task2 {
    public static int countDigits(int x) {
        x = Math.abs(x);
        int ans = 1;
        while (x > 9) {
            x /= 10;
            ++ans;
        }
        return ans;
    }
}
