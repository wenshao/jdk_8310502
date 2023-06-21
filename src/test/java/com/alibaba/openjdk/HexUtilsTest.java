package com.alibaba.openjdk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HexUtilsTest {
    @Test
    public void test() {
        for (long i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; i += 64) {
            int v = (int) i;
            assertEquals(Integer.toHexString(v), HexUtils.toHexString(v));
        }
    }


}
