package com.alibaba.openjdk;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;

public class ByteArray {
    private static final VarHandle CHAR = create(char[].class);
    private static final VarHandle INT = create(int[].class);
    private static final VarHandle LONG = create(long[].class);

    public static void setChar(byte[] array, int offset, char value) {
        CHAR.set(array, offset, value);
    }

    public static void setInt(byte[] array, int offset, int value) {
        INT.set(array, offset, value);
    }

    public static void setLong(byte[] array, int offset, long value) {
        LONG.set(array, offset, value);
    }

    private static VarHandle create(Class<?> viewArrayClass) {
        return MethodHandles.byteArrayViewVarHandle(viewArrayClass, ByteOrder.BIG_ENDIAN);
    }
}
