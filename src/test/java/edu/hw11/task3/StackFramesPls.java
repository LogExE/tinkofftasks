package edu.hw11.task3;

import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.ClassWriter;
import net.bytebuddy.pool.TypePool;
import org.jetbrains.annotations.NotNull;

public class StackFramesPls implements AsmVisitorWrapper {
    @Override
    public final int mergeWriter(int flags) {
        return flags | ClassWriter.COMPUTE_FRAMES;
    }

    @Override
    public final int mergeReader(int flags) {
        return flags | ClassWriter.COMPUTE_FRAMES;
    }

    @Override
    public final @NotNull ClassVisitor wrap(@NotNull TypeDescription td, @NotNull ClassVisitor cv, Implementation.@NotNull Context ctx, @NotNull TypePool tp, @NotNull FieldList<FieldDescription.InDefinedShape> fields, @NotNull MethodList<?> methods, int wflags, int rflags) {
        return cv;
    }
}
