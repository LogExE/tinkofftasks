package edu.hw2.task4;

public class Task4 {

    private Task4() {

    }

    public static CallingInfo callingInfo() {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        return new CallingInfo(stack[2].getClassName(), stack[2].getMethodName());
    }
}
