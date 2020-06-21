package com.freitas.lucas.carregistration.services.impl;

import com.freitas.lucas.carregistration.domain.User;
import com.freitas.lucas.carregistration.error.exceptions.ObjectAlreadyExists;
import com.freitas.lucas.carregistration.error.exceptions.ObjectNotFoundException;
import com.freitas.lucas.carregistration.repositories.UserRepository;
import com.freitas.lucas.carregistration.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("User not found for id " + id));
    }

    @Override
    public void delete(Long id) {
        User user = findById(id);
        this.userRepository.delete(user);
    }
}
