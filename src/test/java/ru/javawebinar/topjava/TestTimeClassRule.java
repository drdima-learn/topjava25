package ru.javawebinar.topjava;

import org.junit.rules.ExternalResource;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

public class TestTimeClassRule extends ExternalResource {
    private static final Logger log = getLogger(TestTimeClassRule.class);

    public static Map<String, Long> map = new HashMap<>();

    @Override
    protected void after() {
        log.info("All test finished:");
        map.forEach((k, v) -> log.info("Test name: {} , {} milliseconds", k, v));
    }
}
