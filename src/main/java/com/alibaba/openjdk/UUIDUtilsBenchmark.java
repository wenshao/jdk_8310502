package com.alibaba.openjdk;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class UUIDUtilsBenchmark {
    public static UUID uuid = UUID.randomUUID();

    @Benchmark
    public void jdk(Blackhole bh) {
        bh.consume(uuid.toString());
    }

    @Benchmark
    public void fast(Blackhole bh) {
        bh.consume(UUIDUtils.fastUUID(uuid));
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(UUIDUtilsBenchmark.class.getName())
                .mode(Mode.Throughput)
                .timeUnit(TimeUnit.MILLISECONDS)
                .warmupIterations(3)
                .forks(1)
                .threads(1)
                .build();
        new Runner(options).run();
    }
}
