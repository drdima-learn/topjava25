package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaMealRepository implements MealRepository {

    @Autowired
    ProxyMealRepository proxyMealRepository;

    //@Autowired
    //ProxyUserRepository proxyUserRepository;

    @Override
    public Meal save(Meal meal, int userId) {
        meal.setUser(new User( userId));
        if (meal.isNew()) {
            proxyMealRepository.save(meal);
            return meal;
        } else if (get(meal.id(), userId) == null) {
            return null;
        }
        return proxyMealRepository.save(meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        return proxyMealRepository.deleteByIdAndUserId(id, userId) !=0;
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal = proxyMealRepository.findOne(id);
        return meal != null && meal.getUser().getId() == userId ? meal : null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return proxyMealRepository.findAllByUserIdOrderByDateTimeDesc(userId);
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return proxyMealRepository.findAllByUserIdAndDateTimeGreaterThanEqualAndDateTimeLessThanOrderByDateTimeDesc(userId,startDateTime, endDateTime);
    }
}
