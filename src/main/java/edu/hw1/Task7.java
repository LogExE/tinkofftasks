package edu.hw1;

public class Task7 {
    private Task7() {

    }

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
        int lesserLen = bits - shift;
        int lesserMask = (1 << lesserLen) - 1;
        int upperMask = mask - lesserMask;
        int lesser = n & lesserMask;
        int upper = (n & upperMask) >> lesserLen;
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
        int upperLen = bits - shift;
        int lesserMask = (1 << shift) - 1;
        int upperMask = mask - lesserMask;
        int lesser = n & lesserMask;
        int upper = (n & upperMask) >> shift;
        return (lesser << upperLen) | upper;
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
