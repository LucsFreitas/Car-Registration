package com.freitas.lucas.carregistration.repositories;

import com.freitas.lucas.carregistration.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
