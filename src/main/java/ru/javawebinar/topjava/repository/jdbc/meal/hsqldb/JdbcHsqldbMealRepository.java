package ru.javawebinar.topjava.repository.jdbc.meal.hsqldb;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.jdbc.meal.JdbcMealRepositoryAbstract;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Profile("hsqldb")
@Repository
public class JdbcHsqldbMealRepository extends JdbcMealRepositoryAbstract {

    public JdbcHsqldbMealRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return jdbcTemplate.query(
                getBetweenHalfOpenSql(),
                ROW_MAPPER, userId, Timestamp.valueOf(startDateTime), Timestamp.valueOf(endDateTime));
    }


}
