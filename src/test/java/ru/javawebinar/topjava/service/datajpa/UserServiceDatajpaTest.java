package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserServiceAbstract;

import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles({"postgres", "datajpa"})
public class UserServiceDatajpaTest extends UserServiceAbstract {

    @Test
    public void getWithMeals() {
        User user = service.getWithMeals(USER_ID);
        USER_MATCHER_IGNORE_MEALS.assertMatch(user, UserTestData.userWithMeals);
    }


}
