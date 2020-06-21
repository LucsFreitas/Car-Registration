package com.freitas.lucas.carregistration.services;

import com.freitas.lucas.carregistration.domain.Car;

import java.util.List;

public interface CarService {

    List<Car> findAll();

    Car save(Car car);

    Car findById(Long id);

    void delete(Long id);
}
