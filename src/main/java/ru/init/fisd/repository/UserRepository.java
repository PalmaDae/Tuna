package ru.init.fisd.repository;

import ru.init.fisd.dao.UserDao;
import ru.init.fisd.entity.UserEntity;

public class UserRepository {
    private final UserDao userDao = new UserDao();

    public UserEntity save(UserEntity user) {
        return userDao.save(user);
    }

    public UserEntity findByUsername(String name) {
        return userDao.findByUsername(name);
    }
}
