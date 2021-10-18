package com.example.springmicrometertest;

import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Component
class SampleMetrics {

    public static final String COUNTER_NAME = "sample.counter";
    private Counter counter;

    public SampleMetrics(MeterRegistry meterRegistry) {
        this.counter = Counter.builder(COUNTER_NAME).register(meterRegistry);
    }

    public void increment() {
        counter.increment();
    }
}
