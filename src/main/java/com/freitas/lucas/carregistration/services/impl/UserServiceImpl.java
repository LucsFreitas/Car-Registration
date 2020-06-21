package com.freitas.lucas.carregistration.services.impl;

import com.freitas.lucas.carregistration.domain.User;
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
}
