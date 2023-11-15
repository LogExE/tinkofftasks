package edu.hw5;

public class Task8 {
    private Task8() {

    }

    public static boolean binaryOddLen(String str) {
        return str.matches("([01]{2})*[01]");
    }

    public static boolean odd0even1(String str) {
        return str.matches("(0|1[01])([01]{2})*");
    }

    public static boolean nullsDivs3(String str) {
        return str.matches("1*|(1*01*01*01*)*");
    }

    public static boolean noRepOnes(String str) {
        return str.matches("0*(10+)*1?");
    }
}
