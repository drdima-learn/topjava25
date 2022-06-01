package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID = START_SEQ+2;

    public static final Meal MEAL02 = new Meal(MEAL_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
    public static final Meal MEAL08 = new Meal(MEAL_ID+6, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410);
    public static final Meal MEAL_WO_ID = new Meal(null, LocalDateTime.of(2020, Month.FEBRUARY, 1, 05, 0), "Morning meal", 5000);

    public static final List<Meal> USER_MEALS = Arrays.asList(
            MEAL08,
            new Meal(100_007, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
            new Meal(100_006, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
            new Meal(100_005, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
            new Meal(100_004, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new Meal(100_003, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            MEAL02
    );

    public static List<Meal> cloneUserMeals(List<Meal> meals){
        return meals.stream().map(Meal::new).collect(Collectors.toList());
    }

}
