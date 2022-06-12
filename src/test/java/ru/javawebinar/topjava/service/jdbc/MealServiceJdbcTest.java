package ru.javawebinar.topjava.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.MealServiceAbstract;


@ActiveProfiles({"postgres","jdbc"})
public class MealServiceJdbcTest extends MealServiceAbstract {
}
