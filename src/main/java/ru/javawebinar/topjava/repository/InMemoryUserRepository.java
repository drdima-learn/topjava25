package ru.javawebinar.topjava.repository;

import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private static final Logger log = getLogger(InMemoryUserRepository.class);


    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}

