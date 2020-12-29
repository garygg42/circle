package org.example;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.stackdriver.StackdriverConfig;
import io.micrometer.stackdriver.StackdriverMeterRegistry;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) throws Exception {
        StackdriverConfig stackdriverConfig = new MyStackdriverConfig();
        MeterRegistry registry = StackdriverMeterRegistry.builder(stackdriverConfig).build();
        Counter counter = Counter
                .builder("mycounter")
                .register(registry);
        App app = new App();
        app.run(counter);
    }

    public void run(Counter counter) throws InterruptedException {
        for (int j = 0; j < 100000; j++) {
            counter.increment(1);
            System.out.println("Iteration: " + counter.count());
            TimeUnit.SECONDS.sleep(1);
        }
    }

    public static class MyStackdriverConfig implements StackdriverConfig {
        @Override
        public String projectId() {
            return "jovial-idiom-298108";
        }

        @Override
        public String get(String key) {
            String result = null;
            try {
                result = Files.readString(Paths.get("/Users/igor/circle-experiments/keys/newkey.json"));
            } catch (IOException e) {
                e.printStackTrace();
            }
//            return result;
            return null;
        }
    }
}