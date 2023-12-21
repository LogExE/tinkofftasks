package edu.hw11.task3;

import edu.hw11.FibASM;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.jar.asm.Opcodes;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {
    @Test
    void testFib()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> dyn = new ByteBuddy()
            .subclass(Object.class)
            .visit(new StackFramesPls())
            .defineMethod("calc", int.class, Opcodes.ACC_PUBLIC)
            .withParameter(int.class, "n")
            .intercept(new Implementation.Simple(new FibASM()))
            .make()
            .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION)
            .getLoaded();
        assertEquals(2, dyn.getMethod("calc", int.class).invoke(dyn.getDeclaredConstructor().newInstance(), 3));
        assertEquals(8, dyn.getMethod("calc", int.class).invoke(dyn.getDeclaredConstructor().newInstance(), 6));
    }
}

