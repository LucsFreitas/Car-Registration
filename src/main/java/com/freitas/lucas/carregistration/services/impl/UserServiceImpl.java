package com.freitas.lucas.carregistration.services.impl;

import com.freitas.lucas.carregistration.domain.User;
import com.freitas.lucas.carregistration.dto.UserDTO;
import com.freitas.lucas.carregistration.error.exceptions.ObjectAlreadyExistsException;
import com.freitas.lucas.carregistration.error.exceptions.ObjectNotFoundException;
import com.freitas.lucas.carregistration.error.exceptions.UnauthorizedException;
import com.freitas.lucas.carregistration.repositories.UserRepository;
import com.freitas.lucas.carregistration.security.AuthenticationService;
import com.freitas.lucas.carregistration.security.UserSS;
import com.freitas.lucas.carregistration.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User save(User user) {

        if (userRepository.findByLogin(user.getLogin())
                .filter(element -> !element.getId().equals(user.getId()))
                .isPresent()){
            throw new ObjectAlreadyExistsException("Login already exists.");
        }
        if (userRepository.findByEmail(user.getEmail())
                .filter(element -> !element.getId().equals(user.getId()))
                .isPresent()){
            throw new ObjectAlreadyExistsException("Email already exists.");
        }
        user.setCreatedAt(new Date(System.currentTimeMillis()));
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

    @Override
    public User update(User user) {
        User old = findById(user.getId());
        user.setCars(old.getCars());
        return this.userRepository.save(user);
    }

    @Override
    public User fromDTO(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setBirthday(userDTO.getBirthday());
        user.setEmail(userDTO.getEmail());
        user.setLogin(userDTO.getLogin());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user.setPhone(userDTO.getPhone());
        if (userDTO.getCars() != null) {
            user.setCars(userDTO.getCars().stream()
                    .map(car -> car.toEntityWithOwner(user))
                    .collect(Collectors.toList()));
        }
        return user;
    }

    @Override
    public User getCurrentUser() {
        UserSS authenticated = AuthenticationService.authenticated();
        if (authenticated == null) {
            throw new UnauthorizedException("Unauthorized.");
        }
        return this.findById(authenticated.getId());
    }

    @Override
    public void registerLogin(String username) {
        Optional<User> optionalUser = this.userRepository.findByLogin(username);

        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setLastLogin(new Date(System.currentTimeMillis()));
            save(user);
        }
    }
}
