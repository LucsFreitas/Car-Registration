package com.freitas.lucas.carregistration.services.impl;

import com.freitas.lucas.carregistration.domain.Car;
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
}
