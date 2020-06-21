package com.freitas.lucas.carregistration.services;

import com.freitas.lucas.carregistration.domain.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User save(User user);

    User findById(Long id);

    void delete(Long id);

    User update(User user);
}