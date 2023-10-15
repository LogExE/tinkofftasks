package edu.hw1;

public class Task5 {
    private Task5() {

    }

    public static boolean isPalindromeDescendant(int x) {
        if (x < 0) {
            throw new IllegalArgumentException("Number should be non-negative!");
        }
        if (flipInt(x) == x) {
            return true;
        }
        int cnt = intDigitsCount(x);
        if (cnt % 2 == 1) {
            // не известно, какое число считать потомком в таком случае - пар не хватает
            return false;
        }
        int child = getIntChild(x);
        if (intDigitsCount(child) < 2) {
            return false;
        }
        return isPalindromeDescendant(child);
    }

    @SuppressWarnings({"MagicNumber", "ParameterAssignment"})
    private static int intDigitsCount(int x) {
        int cnt = 0;
        while (x > 0) {
            x /= 10;
            ++cnt;
        }
        return cnt;
    }

    @SuppressWarnings({"MagicNumber", "ParameterAssignment"})
    private static int getIntChild(int x) {
        // в этот метод должны передаваться только числа с четным кол-вом цифр
        int res = 0;
        while (x >= 10) {
            int lastDig = x % 10;
            int preLastDig = (x / 10) % 10;
            int sum = lastDig + preLastDig;
            if (sum <= 9) {
                res *= 10;
            } else {
                res *= 100;
            }
            res += sum;
            x /= 100;
        }
        return flipInt(res);
    }

    @SuppressWarnings({"MagicNumber", "ParameterAssignment"})
    private static int flipInt(int x) {
        int res = 0;
        while (x > 0) {
            res = res * 10 + x % 10;
            x /= 10;
        }
        return res;
    }
}
