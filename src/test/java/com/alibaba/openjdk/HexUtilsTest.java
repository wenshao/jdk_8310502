package com.alibaba.openjdk;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HexUtilsTest {
    @Test
    public void test() {
        for (int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; i += 1) {
            assertEquals(Integer.toHexString(i), HexUtils.toHexString(i));
        }
    }

    @Test
    public void testInt() {
        int[] values = new int[] {
                0,
                1,
                10,
                100,
                1000,
                10000,
                100000,
                1000000,
                10000000,
                100000000,
                1000000000,
                Integer.MAX_VALUE,
        };

        for (int i = 0; i < values.length; i++) {
            int v = values[i];
            assertEquals(Integer.toHexString(v), HexUtils.toHexString(v));
            assertEquals(Integer.toHexString(-v), HexUtils.toHexString(-v));
        }
    }

    @Test
    public void testLong() {
        long[] values = new long[] {
                0,
                1,
                10,
                100,
                1000,
                10000,
                100000,
                1000000,
                10000000,
                100000000,
                1000000000,
                10000000000L,
                100000000000L,
                1000000000000L,
                10000000000000L,
                100000000000000L,
                1000000000000000L,
                10000000000000000L,
                100000000000000000L,
                1000000000000000000L,
                Integer.MAX_VALUE,
                Long.MAX_VALUE,
        };

        for (int i = 0; i < values.length; i++) {
            long v = values[i];
            assertEquals(Long.toHexString(v), HexUtils.toHexString(v));
            assertEquals(Long.toHexString(-v), HexUtils.toHexString(-v));
        }
    }

    @Test
    public void test1() {
        Random r = new Random();
        long i1 = r.nextInt(127);
        long i2 = (1 << 15) / 2 + i1;
        long i4 = (1L << 31) + r.nextInt(Integer.MAX_VALUE);
        long i6 = (1L << 47) + r.nextInt(Integer.MAX_VALUE);
        long i8 = (1L << 63) + r.nextInt(Integer.MAX_VALUE);

        System.out.println("i1 " + Long.toHexString(i1));
        System.out.println("i2 " + Long.toHexString(i2));
        System.out.println("i4 " + Long.toHexString(i4));
        System.out.println("i6 " + Long.toHexString(i6));
        System.out.println("i8 " + Long.toHexString(i8));
    }
}
