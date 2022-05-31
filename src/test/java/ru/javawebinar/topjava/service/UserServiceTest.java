package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.topjava.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceTest {

    @Autowired
    private UserService service;

    @Test
    public void create() {
        User newUser = new User(
                null,"New User", "new@gmail.com", "newpass", 1555, false, Arrays.asList(Role.USER)
        );
        User created = service.create(newUser);
        newUser.setId(created.getId());
        assertMatch(created, newUser);
    }


    @Test(expected = DataAccessException.class)
    public void duplicate(){
        User newUser = new User(
                null,"Duplicate", "user@yandex.ru", "newpass", 1555, false, Arrays.asList(Role.USER)
        );
        User created = service.create(newUser);
        newUser.setId(created.getId());
    }

    @Test
    public void delete() {
        service.delete(USER_ID);
        assertMatch(service.getAll(), ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotExistUser(){
        service.delete(999);
    }

    @Test
    public void get() {
        User user = service.get(USER_ID);
        assertMatch(user, USER);
    }

    @Test(expected = NotFoundException.class)
    public void getNotExistUser() {
        service.get(888);
    }

    @Test
    public void getByEmail() {
        User user = service.getByEmail("user@yandex.ru");
        assertMatch(user, USER);
    }

    @Test(expected = NotFoundException.class)
    public void getByWrongEmail() {
        service.getByEmail("user2@yandex.ru");

    }

    @Test
    public void getAll() {
        List<User> all = service.getAll();
        assertMatch(all, ADMIN,USER);

    }

    @Test
    public void update() {
        User updated = new User(USER);
        updated.setName("Updated Name");
        updated.setCaloriesPerDay(330);
        service.update(updated);
        assertMatch(service.get(USER_ID), updated);
    }
}