package com.kolibree.android.codingtest;

import org.junit.Test;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CounterSyncTest {
    private final static int NUM_THREADS = 2;
    private final static int NUM_ITERATIONS = 10;

    @Test
    public void testSynchronized() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        final Counter sync = new Counter();

        for (int i = 0; i < NUM_THREADS; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < NUM_ITERATIONS; i++) {
                        sync.increment();
                    }
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
        assertThat(sync.getValue(), is(NUM_THREADS * NUM_ITERATIONS));
    }

    private static class Counter {
        final private AtomicInteger value = new AtomicInteger();

        public int getValue() {
            return value.get();
        }

        public int increment() {
            return value.incrementAndGet();
        }
    }
}