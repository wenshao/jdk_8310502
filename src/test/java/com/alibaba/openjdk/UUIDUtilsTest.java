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
}
