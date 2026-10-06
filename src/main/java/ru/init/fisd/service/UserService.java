package ru.init.fisd.service;

import ru.init.fisd.entity.UserEntity;
import ru.init.fisd.repository.UserRepository;
import ru.init.fisd.util.HashUtil;

public class UserService {
    private UserRepository userRepository = new UserRepository();

    public boolean registerUser(String username, String pass) {
        if (userRepository.findByUsername(username) != null) {
            return false;
        }

        String hashPass = HashUtil.hashPassword(pass);
        UserEntity user = new UserEntity(username, hashPass, "USER");

        userRepository.save(user);

        return true;
    }

    public UserEntity authUser(String username, String pass) {
        UserEntity user = userRepository.findByUsername(username);

        if (HashUtil.verify(pass, user.getHashPass())) {
            return user;
        }
        return null;
    }
}
