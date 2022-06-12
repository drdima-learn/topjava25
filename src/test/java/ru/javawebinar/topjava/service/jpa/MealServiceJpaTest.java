package ru.javawebinar.topjava.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.MealServiceAbstract;


@ActiveProfiles({"postgres","jpa"})
public class MealServiceJpaTest extends MealServiceAbstract {
}
