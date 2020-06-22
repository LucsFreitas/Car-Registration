package com.freitas.lucas.carregistration.services.impl;

import com.freitas.lucas.carregistration.domain.Car;
import com.freitas.lucas.carregistration.error.exceptions.ObjectAlreadyExistsException;
import com.freitas.lucas.carregistration.error.exceptions.ObjectNotFoundException;
import com.freitas.lucas.carregistration.repositories.CarRepository;
import com.freitas.lucas.carregistration.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
            throw new ObjectAlreadyExistsException("License plate already exists.");
        }

        return this.carRepository.save(car);
    }

    @Override
    public Car findById(Long id) {
        Optional<Car> car = this.carRepository.findById(id);
        return car.orElseThrow(() -> new ObjectNotFoundException("Car not found for id " + id));
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
