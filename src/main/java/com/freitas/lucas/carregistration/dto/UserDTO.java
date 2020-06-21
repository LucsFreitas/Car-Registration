package com.freitas.lucas.carregistration.dto;

import com.freitas.lucas.carregistration.domain.Car;
import com.freitas.lucas.carregistration.domain.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class UserDTO {

    private Long id;

    @NotBlank(message = "{firstName.not.blank}")
    private String firstName;

    @NotBlank(message = "{lastName.not.blank}")
    private String lastName;

    @NotBlank(message = "{email.not.blank}")
    @Email(message = "{email.not.valid}")
    private String email;

    @NotNull(message = "{birthday.not.null}")
    private Date birthday;

    @NotBlank(message = "{login.not.blank}")
    private String login;

    @NotBlank(message = "{password.not.blank}")
    private String password;

    @NotBlank(message = "{phone.not.blank}")
    private String phone;

    private List<Car> cars;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public UserDTO() {
    }

    public UserDTO (User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.birthday = user.getBirthday();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.cars = user.getCars();
    }

    public User toEntity() {
        User user = new User();
        user.setId(this.id);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setBirthday(this.birthday);
        user.setEmail(this.email);
        user.setLogin(this.login);
        user.setPassword(this.password);
        user.setPhone(this.phone);
        user.setCars(this.cars);
        return user;
    }
}
