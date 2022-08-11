package ru.titov.persist;

import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository {

    List<User> findAll();

    User findById(long id);

    void insert(User user);

    void update(User user);

    void delete(long id);

    long getCount();

}
