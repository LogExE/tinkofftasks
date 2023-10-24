package edu.hw2;

import edu.hw2.task2.Rectangle;
import edu.hw2.task2.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
    static Arguments[] rectangles() {
        return new Arguments[] {
            Arguments.of(new Rectangle(5, 15)),
            Arguments.of(new Square(2))
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    @DisplayName("Тест из условия")
    void rectangleArea(Rectangle rect) {
        Rectangle newRect = rect.setWidth(20);
        newRect = newRect.setHeight(10);

        assertEquals(newRect.area(), 200.0);
    }
}
