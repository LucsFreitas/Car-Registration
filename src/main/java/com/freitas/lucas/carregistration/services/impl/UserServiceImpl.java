package com.freitas.lucas.carregistration.services.impl;

import com.freitas.lucas.carregistration.repositories.UserRepository;
import com.freitas.lucas.carregistration.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
}
