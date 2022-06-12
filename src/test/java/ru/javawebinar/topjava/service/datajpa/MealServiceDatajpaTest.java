package ru.javawebinar.topjava.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.MealServiceAbstract;


@ActiveProfiles({"postgres","datajpa"})
public class MealServiceDatajpaTest extends MealServiceAbstract {
}
