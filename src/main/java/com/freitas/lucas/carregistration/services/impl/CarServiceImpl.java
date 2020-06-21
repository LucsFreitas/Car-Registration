package com.freitas.lucas.carregistration.services.impl;

import com.freitas.lucas.carregistration.domain.Car;
import com.freitas.lucas.carregistration.error.exceptions.ObjectAlreadyExists;
import com.freitas.lucas.carregistration.repositories.CarRepository;
import com.freitas.lucas.carregistration.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> findAll() {
        return this.carRepository.findAll();
    }

    @Override
    public Car save(Car car) {

        if (carRepository.findByLicensePlate(car.getLicensePlate())
                .filter(element -> !element.getId().equals(car.getId()))
                .isPresent()){
            throw new ObjectAlreadyExists("License plate already exists.");
        }

        return this.carRepository.save(car);
    }
}
