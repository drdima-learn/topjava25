package ru.javawebinar.topjava.repository.inmemory;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;

import static ru.javawebinar.topjava.util.MealsUtil.*;

public class MealRepositoryImpl implements MealRepository {

    @Override
    public void delete(Integer id) {
        int index = meals.indexOf(new Meal(id));
        meals.remove(index);
    }
}
