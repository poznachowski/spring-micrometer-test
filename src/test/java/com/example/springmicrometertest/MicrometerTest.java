package com.example.springmicrometertest;

import static com.example.springmicrometertest.SampleMetrics.COUNTER_NAME;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@SpringBootTest
class MicrometerTest {

    @Autowired
    private MeterRegistry meterRegistry;

    @Autowired
    private SampleMetrics metrics;

    @AfterEach
    public void cleanUp() {
        // Clear won't work as the counter would be removed from the registry.
        // meterRegistry.clear();
    }

    @Test
    void testA() {
        metrics.increment();
        assertThat(meterRegistry.find(COUNTER_NAME).counter())
                .isNotNull()
                .extracting(Counter::count)
                .isEqualTo(1d);
    }

    @Test
    // Won't pass - counter will be at 2 after the increment
    void testB() {
        metrics.increment();
        assertThat(meterRegistry.find(COUNTER_NAME).counter())
                .isNotNull()
                .extracting(Counter::count)
                .isEqualTo(1d);
    }

}
