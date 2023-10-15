package edu.hw1;

public class Task7 {
    public static int rotateLeft(int n, int shift) {
        if (n <= 0) {
            throw new IllegalArgumentException("Only positive numbers can be rotated!");
        }
        if (shift < 0) {
            throw new IllegalArgumentException("Shift amount should be non-negative!");
        }
        int bits = bitsLength(n);
        shift = shift % bits;
        int mask = (1 << bits) - 1;
        int lesser_len = bits - shift;
        int lesser_mask = (1 << lesser_len) - 1;
        int upper_mask = mask - lesser_mask;
        int lesser = n & lesser_mask;
        int upper = (n & upper_mask) >> lesser_len;
        return (lesser << shift) | upper;
    }

    public static int rotateRight(int n, int shift) {
        if (n <= 0) {
            throw new IllegalArgumentException("Only positive numbers can be rotated!");
        }
        if (shift < 0) {
            throw new IllegalArgumentException("Shift amount should be non-negative!");
        }
        int bits = bitsLength(n);
        shift = shift % bits;
        int mask = (1 << bits) - 1;
        int upper_len = bits - shift;
        int lesser_mask = (1 << shift) - 1;
        int upper_mask = mask - lesser_mask;
        int lesser = n & lesser_mask;
        int upper = (n & upper_mask) >> shift;
        return (lesser << upper_len) | upper;
    }

    private static int bitsLength(int x) {
        int cnt = 0;
        while (x > 0) {
            ++cnt;
            x >>= 1;
        }
        return cnt;
    }
}
