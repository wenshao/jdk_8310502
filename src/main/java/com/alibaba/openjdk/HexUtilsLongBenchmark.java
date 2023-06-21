package com.alibaba.openjdk;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class HexUtilsLongBenchmark {
    static long i8;
    static long i6;
    static long i4;
    static long i2;
    static long i1;

    static {
        Random r = new Random();
        i1 = r.nextInt(127);
        i2 = (1 << 15) / 2 + i1;
        i4 = (1L << 31) + r.nextInt(Integer.MAX_VALUE);
        i6 = (1L << 47) + r.nextInt(Integer.MAX_VALUE);
        i8 = (1L << 63) + r.nextInt(Integer.MAX_VALUE);
    }

    @Benchmark
    public void i8_jdk(Blackhole bh) {
        for (int i = 0; i < 100; i++) {
            long v = i8;
            bh.consume(Long.toHexString(v));
            bh.consume(Long.toHexString(-v));
        }
    }

    @Benchmark
    public void i8_fast(Blackhole bh) {
        for (int i = 0; i < 100; i++) {
            long v = i8;
            bh.consume(HexUtils.toHexString(v));
            bh.consume(HexUtils.toHexString(-v));
        }
    }

    @Benchmark
    public void i6_jdk(Blackhole bh) {
        for (int i = 0; i < 100; i++) {
            long v = i6;
            bh.consume(Long.toHexString(v));
            bh.consume(Long.toHexString(-v));
        }
    }

    @Benchmark
    public void i6_fast(Blackhole bh) {
        for (int i = 0; i < 100; i++) {
            long v = i6;
            bh.consume(HexUtils.toHexString(v));
            bh.consume(HexUtils.toHexString(-v));
        }
    }

    @Benchmark
    public void i4_jdk(Blackhole bh) {
        for (int i = 0; i < 100; i++) {
            long v = i4;
            bh.consume(Long.toHexString(v));
            bh.consume(Long.toHexString(-v));
        }
    }

    @Benchmark
    public void i4_fast(Blackhole bh) {
        for (int i = 0; i < 100; i++) {
            long v = i4;
            bh.consume(HexUtils.toHexString(v));
            bh.consume(HexUtils.toHexString(-v));
        }
    }

    @Benchmark
    public void i2_jdk(Blackhole bh) {
        for (int i = 0; i < 100; i++) {
            long v = i2;
            bh.consume(Long.toHexString(v));
            bh.consume(Long.toHexString(-v));
        }
    }

    @Benchmark
    public void i2_fast(Blackhole bh) {
        for (int i = 0; i < 100; i++) {
            long v = i2;
            bh.consume(HexUtils.toHexString(v));
            bh.consume(HexUtils.toHexString(-v));
        }
    }

    @Benchmark
    public void i1_jdk(Blackhole bh) {
        for (int i = 0; i < 100; i++) {
            long v = i1;
            bh.consume(Long.toHexString(v));
            bh.consume(Long.toHexString(-v));
        }
    }

    @Benchmark
    public void i1_fast(Blackhole bh) {
        for (int i = 0; i < 100; i++) {
            long v = i1;
            bh.consume(HexUtils.toHexString(v));
            bh.consume(HexUtils.toHexString(-v));
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(HexUtilsLongBenchmark.class.getName())
                .mode(Mode.Throughput)
                .timeUnit(TimeUnit.MILLISECONDS)
                .warmupIterations(3)
                .forks(1)
                .threads(16)
                .build();
        new Runner(options).run();
    }
}
