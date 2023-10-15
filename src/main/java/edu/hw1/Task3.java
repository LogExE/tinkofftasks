package edu.hw1;

public class Task3 {
    private Task3() {

    }

    public static boolean isNestable(int[] arr1, int[] arr2) {
        if (arr1.length == 0 || arr2.length == 0) {
            throw new IllegalArgumentException("Arrays should non-empty!");
        }
        int min1, max1;
        min1 = max1 = arr1[0];
        for (int j : arr1) {
            if (j < min1) {
                min1 = j;
            }
            if (j > max1) {
                max1 = j;
            }
        }
        int min2, max2;
        min2 = max2 = arr2[0];
        for (int j : arr2) {
            if (j < min2) {
                min2 = j;
            }
            if (j > max2) {
                max2 = j;
            }
        }
        return min1 > min2 && max1 < max2;
    }
}
