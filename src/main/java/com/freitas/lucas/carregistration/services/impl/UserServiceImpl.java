package com.freitas.lucas.carregistration.services.impl;

import com.freitas.lucas.carregistration.domain.User;
import com.freitas.lucas.carregistration.error.exceptions.ObjectAlreadyExists;
import com.freitas.lucas.carregistration.repositories.UserRepository;
import com.freitas.lucas.carregistration.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User create(User user) {

        if (userRepository.findByLogin(user.getLogin()).isPresent()){
            throw new ObjectAlreadyExists("Login already exists.");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new ObjectAlreadyExists("Email already exists.");
        }

        return this.userRepository.save(user);
    }
}
