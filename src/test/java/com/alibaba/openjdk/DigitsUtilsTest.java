package com.alibaba.openjdk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DigitsUtilsTest {
    @Test
    public void test() {
        for (long i = 1; i < Integer.MAX_VALUE; i *= 10) {
            int v = (int) i;
            assertEquals(Integer.toString(v), DigitsUtils.toString(v));
            assertEquals(Integer.toString(-v), DigitsUtils.toString(-v));
        }
    }

    @Test
    public void test1() {
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
                Integer.MAX_VALUE
        };
        for (int i = 0; i < values.length; i++) {
            int v = values[i];
            assertEquals(Integer.toString(v), DigitsUtils.toString(v));
            assertEquals(Integer.toString(-v), DigitsUtils.toString(-v));
        }
    }
}
