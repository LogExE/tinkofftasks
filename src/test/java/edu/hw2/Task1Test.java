package edu.hw2;

import edu.hw2.task1.Expr;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {
    static Arguments[] exprs() {
        return new Arguments[] {
            Arguments.of(new Expr.Constant(42), 42),
            Arguments.of(
                new Expr.Addition(
                    new Expr.Constant(3.2),
                    new Expr.Constant(4)
                ),
                7.2
            ),
            Arguments.of(
                new Expr.Multiplication(
                    new Expr.Constant(6.5),
                    new Expr.Constant(2.2)
                ),
                14.3
            ),
            Arguments.of(
                new Expr.Exponent(
                    new Expr.Addition(
                        new Expr.Constant(53),
                        new Expr.Constant(256)
                    ),
                    3
                ),
                29503629
            )
        };
    }

    @ParameterizedTest
    @MethodSource("exprs")
    @DisplayName("Тесты на разных выражениях")
    void variousExprs(Expr expr, double res) {
        assertEquals(res, expr.evaluate());
    }

    @Test
    @DisplayName("Тест выражения из условия")
    void testExample() {
        var two = new Expr.Constant(2);
        var four = new Expr.Constant(4);
        var negOne = new Expr.Negate(new Expr.Constant(1));
        var sumTwoFour = new Expr.Addition(two, four);
        var mult = new Expr.Multiplication(sumTwoFour, negOne);
        var exp = new Expr.Exponent(mult, 2);
        var res = new Expr.Addition(exp, new Expr.Constant(1));

        assertEquals(37, res.evaluate());
    }
}
