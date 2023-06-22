package com.alibaba.openjdk;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UUIDUtilsTest {
    @Test
    public void test() {
        for (int i = 0; i < 10000; i++) {
            UUID uuid = UUID.randomUUID();
            assertEquals(uuid.toString(), UUIDUtils.fastUUID(uuid));
            assertEquals(new String(uuid.toString().getBytes()), new String(UUIDUtils.fastUUID2(uuid).getBytes()));
        }
    }

    @Test
    public void testx() {
        UUID uuid = UUID.randomUUID();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000_000_000; i++) {
            JMH.BH.consume(UUIDUtils.fastUUID(uuid));
        }
        long millis = System.currentTimeMillis() - start;
        System.out.println("millis " + millis);
    }

    @Test
    public void printHex256() {
        final char[] hex256 = UUIDUtils.DigitCache.HEX256;
        for (int i = 0; i < UUIDUtils.DigitCache.HEX256.length; i++) {
            if (i != 0) {
                System.out.print(", ");
            }
            if (i != 0 && i % 10 == 0) {
                System.out.println();
            }
            int ch = hex256[i];
            System.out.print("0x" + Integer.toHexString(ch));
        }
    }
}
