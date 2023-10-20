package edu.hw1;

public class Task7 {
    private static final String POS_NUMBER_ROTATE_STRING = "Only positive numbers can be rotated!";
    private static final String NON_NEG_SHIFT_STRING = "Shift amount should be non-negative!";

    private Task7() {

    }

    @SuppressWarnings("ParameterAssignment")
    public static int rotateLeft(int n, int shift) {
        if (n <= 0) {
            throw new IllegalArgumentException(POS_NUMBER_ROTATE_STRING);
        }
        if (shift < 0) {
            throw new IllegalArgumentException(NON_NEG_SHIFT_STRING);
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

    @SuppressWarnings("ParameterAssignment")
    public static int rotateRight(int n, int shift) {
        if (n <= 0) {
            throw new IllegalArgumentException(POS_NUMBER_ROTATE_STRING);
        }
        if (shift < 0) {
            throw new IllegalArgumentException(NON_NEG_SHIFT_STRING);
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

    @SuppressWarnings("ParameterAssignment")
    private static int bitsLength(int x) {
        int cnt = 0;
        while (x > 0) {
            ++cnt;
            x >>= 1;
        }
        return cnt;
    }
}
