package com.freitas.lucas.carregistration.services.impl;

import com.freitas.lucas.carregistration.domain.Car;
import com.freitas.lucas.carregistration.domain.User;
import com.freitas.lucas.carregistration.error.exceptions.ForbiddenException;
import com.freitas.lucas.carregistration.error.exceptions.ObjectAlreadyExistsException;
import com.freitas.lucas.carregistration.error.exceptions.ObjectNotFoundException;
import com.freitas.lucas.carregistration.repositories.CarRepository;
import com.freitas.lucas.carregistration.security.AuthenticationService;
import com.freitas.lucas.carregistration.security.UserSS;
import com.freitas.lucas.carregistration.services.CarService;
import com.freitas.lucas.carregistration.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<Car> findAll() {
        UserSS authenticated = AuthenticationService.authenticated();
        if (authenticated == null) {
            throw new ForbiddenException("Access denied.");
        }
        User user = this.userService.findById(authenticated.getId());
        return this.carRepository.findByOwner(user).orElse(new ArrayList<>());
    }

    @Override
    public Car save(Car car) {
        if (carRepository.findByLicensePlate(car.getLicensePlate())
                .filter(element -> !element.getId().equals(car.getId()))
                .isPresent()){
            throw new ObjectAlreadyExistsException("License plate already exists.");
        }

        UserSS authenticated = AuthenticationService.authenticated();
        if (authenticated == null) {
            throw new ForbiddenException("Access denied.");
        }
        User user = this.userService.findById(authenticated.getId());
        car.setOwner(user);

        return this.carRepository.save(car);
    }

    @Override
    public Car findById(Long id) {
        UserSS authenticated = AuthenticationService.authenticated();
        if (authenticated == null) {
            throw new ForbiddenException("Access denied.");
        }
        User user = this.userService.findById(authenticated.getId());

        return this.carRepository.findByIdAndOwner(id, user)
                .orElseThrow(() -> new ObjectNotFoundException("Car not found for id " + id));
    }

    @Override
    public void delete(Long id) {
        Car car = findById(id);
        this.carRepository.delete(car);
    }


    @Override
    public Car update(Car car) {
        Car old = findById(car.getId());
        car.setOwner(old.getOwner());
        return this.carRepository.save(car);
    }
}
