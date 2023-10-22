package edu.hw2.task4;

public class Task4 {

    private Task4() {

    }

    public static CallingInfo callingInfo() {
        try {
            throw new Exception("( ͡o ͜ʖ ͡o)");
        } catch (Exception ex) {
            StackTraceElement[] stack = ex.getStackTrace();
            return new CallingInfo(stack[1].getClassName(), stack[1].getMethodName());
        }
    }
}
