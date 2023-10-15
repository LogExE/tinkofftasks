package edu.hw1;

import java.util.Arrays;

public class Task6 {
    private static final int KAPREKAR_NUMBER = 6174;
    private static final int DIGITS_COUNT = 4;

    private Task6() {

    }

    @SuppressWarnings("MagicNumber")
    public static int countK(int x) {
        if (x == KAPREKAR_NUMBER) {
            return 0;
        }
        if (x <= 1000 || x >= 10000) {
            throw new IllegalArgumentException("Number must consist of 4 digits and be greater than 1000!");
        }
        int[] arr = new int[DIGITS_COUNT];
        for (int i = 0; i < DIGITS_COUNT; ++i) {
            arr[i] = x % 10;
            x /= 10;
        }
        Arrays.sort(arr);
        if (arr[0] == arr[DIGITS_COUNT - 1]) {
            throw new IllegalArgumentException("Number must contain at least two distinct digits!");
        }
        int descending = 0;
        for (int i = 0; i < DIGITS_COUNT; ++i) {
            descending = descending * 10 + arr[i];
        }
        int ascending = 0;
        for (int i = DIGITS_COUNT - 1; i >= 0; --i) {
            ascending = ascending * 10 + arr[i];
        }
        return 1 + countK(ascending - descending);
    }
}
