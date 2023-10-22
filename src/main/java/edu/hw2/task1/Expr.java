package edu.hw2.task1;

public sealed interface Expr {
    double evaluate();

    public record Constant(double value) implements Expr {
        @Override
        public double evaluate() {
            return value;
        }
    }

    public record Negate(Expr value) implements Expr {
        @Override
        public double evaluate() {
            return -value.evaluate();
        }
    }

    public record Exponent(Expr value, double deg) implements Expr {
        @Override
        public double evaluate() {
            return Math.pow(value.evaluate(), deg);
        }
    }

    public record Addition(Expr v1, Expr v2) implements Expr {
        @Override
        public double evaluate() {
            return v1.evaluate() + v2.evaluate();
        }
    }

    public record Multiplication(Expr v1, Expr v2) implements Expr {
        @Override
        public double evaluate() {
            return v1.evaluate() * v2.evaluate();
        }
    }
}
