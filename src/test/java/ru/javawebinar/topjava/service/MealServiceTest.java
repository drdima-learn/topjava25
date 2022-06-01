package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.UtilTestData.assertMatch;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(MEAL_ID, USER_ID);
        assertMatch(meal, MEAL02);
    }

    @Test
    public void delete() {
        service.delete(MEAL_ID, USER_ID);
        List<Meal> meals = service.getAll(USER_ID);
        List<Meal> clonedMeals = cloneUserMeals(USER_MEALS);

    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> meals = service.getBetweenInclusive(MEAL02.getDate(),MEAL08.getDate(),USER_ID);
        assertMatch(meals, USER_MEALS);
    }

    @Test
    public void getAll() {
        List<Meal> meals = service.getAll(USER_ID);
        assertMatch(meals, USER_MEALS);
    }

    @Test
    public void update() {
        Meal updated = new Meal(MEAL08);
        updated.setCalories(456);
        service.update(new Meal(updated),USER_ID);
        Meal dbUpdate = service.get(updated.getId(), USER_ID);
        assertMatch(dbUpdate, updated);
    }

    @Test
    public void create() {
        Meal created = service.create(new Meal(MEAL_WO_ID), USER_ID);
        Meal expected = new Meal(MEAL_WO_ID);
        expected.setId(created.getId());
        assertMatch(created, expected);
    }

    @Test(expected = DuplicateKeyException.class)
    public void createSameDateTime() {
        service.create(new Meal(MEAL_WO_ID), USER_ID);
        service.create(new Meal(MEAL_WO_ID), USER_ID);

    }
}