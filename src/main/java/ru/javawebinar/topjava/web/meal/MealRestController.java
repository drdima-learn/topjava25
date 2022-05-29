package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.Util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserCaloriesPerDay;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private MealService service;

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(id, authUserId());
    }

    public List<MealTo> getByDateTime(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        log.info("getByDateTime");
        //List<Meal> meals = service.getByDateTime(startDateTime, endDateTime, SecurityUtil.authUserId());
        List<Meal> meals = service.getAll(authUserId());

//        LocalDate finalStartDate = startDate!=null ? startDate : LocalDate.MIN;
//        LocalTime finalStartTime = startTime!=null ? startTime : LocalTime.MIN;
//        LocalDate finalEndDate = endDate!=null ? endDate : LocalDate.MAX;
//        LocalTime finalEndTime = endTime!=null ? endTime : LocalTime.MAX;


        //LocalDateTime startDateTime = LocalDateTime.of(startDate!=null ? startDate : LocalDate.MIN, startTime!=null ? startTime : LocalTime.MIN);
        //LocalDateTime endDateTime = LocalDateTime.of(endDate!=null ? endDate : LocalDate.MAX, endTime!=null ? endTime : LocalTime.MAX);

        return MealsUtil.filterByPredicate(meals, authUserCaloriesPerDay(),
                (m) -> {

                    if (startDate != null && endDate != null && startTime != null && endTime != null) {
                        LocalDateTime ldtStart = LocalDateTime.of(startDate, startTime);
                        LocalDateTime ldtEnd = LocalDateTime.of(endDate, endTime);
                        return Util.isBetweenHalfOpen(m.getDateTime(), ldtStart, ldtEnd);
                    }

                    if (startDate != null && endDate != null && startTime != null) {
                        LocalDateTime ldtStart = LocalDateTime.of(startDate, startTime);
                        LocalDateTime ldtEnd = LocalDateTime.of(endDate, startTime);
                        return Util.isBetweenHalfOpen(m.getDateTime(), ldtStart, ldtEnd);
                    }
                    return false;
                }

        );
    }

    public List<Meal> getAll() {
        log.info("getAll");
        return service.getAll(authUserId());
    }

    public List<MealTo> getAllWithExcess() {
        log.info("getAllWithExcess");
        return getTos(getAll());
    }


    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id, authUserId());
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        return service.create(meal, authUserId());
    }

    public void update(Meal meal, int id) {
        log.info("update {} with id={}", meal, id);
        assureIdConsistent(meal, id);
        service.update(meal, authUserId());
    }


    private List<MealTo> getTos(Collection<Meal> meals) {
        return MealsUtil.getTos(meals, authUserCaloriesPerDay());
    }


}