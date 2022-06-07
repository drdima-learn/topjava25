package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.util.List;

public interface ProxyMealRepository extends JpaRepository<Meal, Integer> {

    @Transactional
    @Modifying
    int delete(int id);

    @Override
    @Transactional
    Meal save(Meal meal);

    @Override

    Meal findOne(Integer id);

    @Override
    List<Meal> findAll(Sort sort);

}
