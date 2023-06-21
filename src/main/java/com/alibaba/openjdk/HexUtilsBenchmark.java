package com.alibaba.openjdk;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

public class HexUtilsBenchmark {
    @Benchmark
    public void jdk(Blackhole bh) {
        for (long i = 1; i < Integer.MAX_VALUE; i <<= 1) {
            int v = (int) i;
            bh.consume(Integer.toHexString(v));
            bh.consume(Integer.toHexString(-v));
        }
    }

    @Benchmark
    public void fast(Blackhole bh) {
        for (long i = 1; i < Integer.MAX_VALUE; i <<= 1) {
            int v = (int) i;
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
                .threads(1)
                .build();
        new Runner(options).run();
    }
}
