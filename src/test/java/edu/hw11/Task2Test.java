package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
    @Test
    void testReloadMethod() {
        //ByteBuddyAgent.install(); // не распознается класс, нельзя подключить
        DynamicType.Loaded<?> dyn = new ByteBuddy()
            .redefine(ArithmeticUtils.class)
            .method(named("sum")).intercept(MethodDelegation.to(new ArithIntercept()))
            .make()
            .load(ClassLoader.getSystemClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
        assertEquals(42, new ArithmeticUtils().sum(14, 3));
    }

    class ArithmeticUtils {
        public int sum(int a, int b) {
            return a + b;
        }
    }

    class ArithIntercept {
        public int mul(int a, int b) {
            return a * b;
        }
    }
}
