package com.alibaba.openjdk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HexUtilsTest {
    @Test
    public void test() {
        for (int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; i += 1) {
            assertEquals(Integer.toHexString(i), HexUtils.toHexString(i));
        }
    }
}
