package com.freitas.lucas.carregistration.repositories;

import com.freitas.lucas.carregistration.domain.Car;
import com.freitas.lucas.carregistration.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByLicensePlate(String login);

    Optional<List<Car>> findByOwner(User owner);

    Optional<Car> findByIdAndOwner(Long id, User owner);
}
