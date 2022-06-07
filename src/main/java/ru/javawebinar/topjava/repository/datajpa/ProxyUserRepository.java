package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;

import java.util.List;

public interface ProxyUserRepository extends JpaRepository<User, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=?1")
    int delete(int id);

    @Override
    @Transactional
    User save(User user);


    @Override
    User findOne(Integer id);

    //User findById(Integer id);

    @Override
    List<User> findAll(Sort sort);

    User getByEmail(String email);
}