package ru.javawebinar.topjava;

import org.junit.rules.ExternalResource;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

public class TestTimeRule extends ExternalResource {
    private static final Logger log = getLogger(TestTimeRule.class);


    private LocalDateTime start;

    private String testName;



    @Override
    public Statement apply(Statement base, Description description) {
        // Store the test name
        testName = description.getMethodName();
        return super.apply(base, description);
    }

    @Override
    protected void before() throws Throwable {
        start = LocalDateTime.now();
    }

    @Override
    protected void after() {
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        long millis = duration.toMillis();
        log.info("test name: {} finished in {} milliseconds", testName, millis);
        TestTimeClassRule.map.put(testName,millis);
    }
}
