package edu.hw2.task2;

public class Rectangle {
    private int width;
    private int height;

    public Rectangle setWidth(int width) {
        Rectangle ret = new Rectangle();
        ret.height = this.height;
        ret.width = width;
        return ret;
    }

    public Rectangle setHeight(int height) {
        Rectangle ret = new Rectangle();
        ret.height = height;
        ret.width = this.width;
        return ret;
    }

    public double area() {
        return width * height;
    }
}
