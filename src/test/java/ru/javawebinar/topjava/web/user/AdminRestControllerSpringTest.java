package ru.javawebinar.topjava.web.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

@ContextConfiguration("classpath:spring/spring-app.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class AdminRestControllerSpringTest {

    @Autowired
    private AdminRestController controller;

    @Test
    public void testCreate(){
        controller.create(new User(null,"Name", "email@ya.ru", "password", Role.USER));
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound(){
        controller.delete(0);
    }
}
