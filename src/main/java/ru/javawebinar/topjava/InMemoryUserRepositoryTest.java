package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.inmemory.InMemoryUserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static ru.javawebinar.topjava.model.Role.USER;

public class InMemoryUserRepositoryTest {
    public static void main(String[] args) {

        InMemoryUserRepository inMemoryUserRepository = new InMemoryUserRepository();
        List<User> userList = inMemoryUserRepository.getAll();
        System.out.println(userList);

        User user = inMemoryUserRepository.get(1);
        System.out.println(user);

        User newUser = inMemoryUserRepository.save(new User("User3", "user3@gmail.com", "7412", 2, false, new HashSet<>(Arrays.asList(USER))));
        System.out.println(newUser);

        User emailUser = inMemoryUserRepository.getByEmail("user2@gmail.com");
        System.out.println(emailUser);
    }
}
