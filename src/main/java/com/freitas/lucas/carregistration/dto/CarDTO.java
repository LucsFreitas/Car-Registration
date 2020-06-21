package com.freitas.lucas.carregistration.dto;

import com.freitas.lucas.carregistration.domain.Car;
import com.freitas.lucas.carregistration.domain.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarDTO {

    private Long id;

    @NotNull(message = "{year.not.null}")
    private Integer year;

    @NotBlank(message = "{licensePlate.not.blank}")
    private String licensePlate;

    @NotBlank(message = "{model.not.blank}")
    private String model;

    @NotBlank(message = "{color.not.blank}")
    private String color;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public CarDTO() {
    }

    public CarDTO(Car car) {
        this.id = car.getId();
        this.color = car.getColor();
        this.licensePlate = car.getLicensePlate();
        this.model = car.getModel();
        this.year = car.getYear();
    }

    public Car toEntity(){
        Car car = new Car();
        car.setId(this.id);
        car.setColor(this.color);
        car.setLicensePlate(this.licensePlate);
        car.setModel(this.model);
        car.setYear(this.year);
        return car;
    }

    public Car toEntityWithOwner(User owner){
        Car car = toEntity();
        car.setOwner(owner);
        return car;
    }
}
