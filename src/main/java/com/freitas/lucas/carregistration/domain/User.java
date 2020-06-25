package com.freitas.lucas.carregistration.domain;

import com.freitas.lucas.carregistration.domain.enums.Role;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class User {
    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    /**
     * Nome do usuário
     */
    private String firstName;

    /**
     * Sobrenome do usuário
     */
    private String lastName;

    /**
     * Email do usuário
     */
    private String email;

    /**
     * Data de nascimento do usuário
     */
    private Date birthday;

    /**
     * login do usuário
     */
    private String login;

    /**
     * Senha do usuário
     */
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="profiles")
    private List<Integer> profiles = new ArrayList<>();

    /**
     * Telefone do usuário
     */
    private String phone;

    /**
     * Lista de carros do usuário
     */
    @OneToMany(mappedBy = "owner", cascade = { CascadeType.PERSIST }, orphanRemoval = true)
    private List<Car> cars;

    /**
     * Data de criação do usuário
     */
    private Date createdAt;

    /**
     * Data do último login
     */
    private Date lastLogin;

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

    public List<Role> getProfiles() {
        return profiles.stream()
                .map(Role::toEnum)
                .collect(Collectors.toList());
    }

    public void addProfile(Role profile) {
        this.profiles.add(profile.getCode());
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
        this.cars.clear();
        this.cars.addAll(cars);
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public User() {
        this.cars = new ArrayList<>();
        addProfile(Role.DEFAULT);
    }

    public User(Long id, String firstName, String lastName, String email, Date birthday, String login, String password,
                String phone, List<Car> cars, Date createdAt, Date lastLogin) {
        this();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.createdAt = createdAt;
        this.lastLogin = lastLogin;
        if (cars != null) {
            this.cars.addAll(cars);
        }
    }
}
