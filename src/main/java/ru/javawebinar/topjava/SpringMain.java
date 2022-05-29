package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.web.SecurityUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management (ARM)
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ADMIN));

            MealRestController mealRestController = appCtx.getBean(MealRestController.class);
            Meal meal = mealRestController.get(8);
            System.out.println(meal);

            List<MealTo> mealToList = mealRestController.getAllWithExcess();
            System.out.println(mealToList);

            System.out.println("==================");

            List<MealTo> mealToDateTime = mealRestController.getByDateTime(
                    LocalDateTime.of(2020, Month.JANUARY, 31,10,0),
                    LocalDateTime.of(2020,Month.JANUARY,31,20,01));
            System.out.println(mealToDateTime);

            System.out.println("==================");

            mealRestController.delete(7);
            System.out.println(mealRestController.getAll());

            System.out.println("==================");
            mealRestController.create(new Meal(
                    null,
                    LocalDateTime.of(2022,05,29,11,15),
                    "breakfast",50
            ));
            System.out.println(mealRestController.getAll());

            System.out.println("==================");
            mealRestController.update(new Meal(
                    10,
                    LocalDateTime.of(2022,05,30,11,15),
                    "breakfast - new",60
                    ),10);

            System.out.println(mealRestController.getAll());


        }
    }
}
