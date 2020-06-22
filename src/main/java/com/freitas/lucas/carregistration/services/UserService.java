package com.freitas.lucas.carregistration.services;

import com.freitas.lucas.carregistration.domain.User;
import com.freitas.lucas.carregistration.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User save(User user);

    User findById(Long id);

    void delete(Long id);

    User update(User user);

    User fromDTO (UserDTO userDTO);

    User getCurrentUser();

    void registerLogin (String username);
}
