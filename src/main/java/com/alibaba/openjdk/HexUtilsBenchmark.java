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

public class HexUtilsBenchmark {
    static int i4;
    static int i2;
    static int i1;
    static {
        Random r = new Random();
        i1 = r.nextInt(127);
        i2 = Short.MAX_VALUE / 2 + i1;
        i4 = Integer.MAX_VALUE / 2 + r.nextInt(Short.MAX_VALUE);
    }
//
//    @Benchmark
//    public void full_jdk(Blackhole bh) {
//        for (long i = 1; i < Integer.MAX_VALUE; i <<= 1) {
//            int v = (int) i;
//            bh.consume(Integer.toHexString(v));
//            bh.consume(Integer.toHexString(-v));
//        }
//    }
//
//    @Benchmark
//    public void full_fast(Blackhole bh) {
//        for (long i = 1; i < Integer.MAX_VALUE; i <<= 1) {
//            int v = (int) i;
//            bh.consume(HexUtils.toHexString(v));
//            bh.consume(HexUtils.toHexString(-v));
//        }
//    }

    @Benchmark
    public void i4_jdk(Blackhole bh) {
        for (int i = 0; i < 100; i++) {
            int v = i4;
            bh.consume(Integer.toHexString(v));
            bh.consume(Integer.toHexString(-v));
        }
    }

    @Benchmark
    public void i4_fast(Blackhole bh) {
        for (int i = 0; i < 100; i++) {
            int v = i4;
            bh.consume(HexUtils.toHexString(v));
            bh.consume(HexUtils.toHexString(-v));
        }
    }

    @Benchmark
    public void i2_jdk(Blackhole bh) {
        for (int i = 0; i < 100; i++) {
            int v = i2;
            bh.consume(Integer.toHexString(v));
            bh.consume(Integer.toHexString(-v));
        }
    }

    @Benchmark
    public void i2_fast(Blackhole bh) {
        for (int i = 0; i < 100; i++) {
            int v = i2;
            bh.consume(HexUtils.toHexString(v));
            bh.consume(HexUtils.toHexString(-v));
        }
    }

    @Benchmark
    public void i1_jdk(Blackhole bh) {
        for (int i = 0; i < 100; i++) {
            int v = i1;
            bh.consume(Integer.toHexString(v));
            bh.consume(Integer.toHexString(-v));
        }
    }

    @Benchmark
    public void i1_fast(Blackhole bh) {
        for (int i = 0; i < 100; i++) {
            int v = i1;
            bh.consume(HexUtils.toHexString(v));
            bh.consume(HexUtils.toHexString(-v));
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(HexUtilsBenchmark.class.getName())
                .mode(Mode.Throughput)
                .timeUnit(TimeUnit.MILLISECONDS)
                .warmupIterations(3)
                .forks(1)
                .threads(16)
                .build();
        new Runner(options).run();
    }
}
