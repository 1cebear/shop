package ru.shop.service;

import ru.shop.model.User;

import java.util.List;

public interface UserService {
    User save(User user);

    void delete(int id);

    User get(int id);

    User getByEmail(String email);

    List<User> getAll();

    void update(User user);

    void evictCache();

    void enable(int id, boolean enable);
}
