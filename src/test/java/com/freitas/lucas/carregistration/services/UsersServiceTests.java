package com.freitas.lucas.carregistration.services;

import com.freitas.lucas.carregistration.domain.User;
import com.freitas.lucas.carregistration.error.exceptions.ObjectAlreadyExistsException;
import com.freitas.lucas.carregistration.error.exceptions.ObjectNotFoundException;
import com.freitas.lucas.carregistration.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersServiceTests {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void saveUser() {
        User user = new User(null, "User1", "Last", "test@test.com", new Date(System.currentTimeMillis()), "user", "123",
                "81999999999", null, new Date(System.currentTimeMillis()), null);

        User expectedUser = new User(1L, "User1", "Last", "test@test.com", new Date(System.currentTimeMillis()), "user", "123",
                "81999999999", null, new Date(System.currentTimeMillis()), null);

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(expectedUser);

        User result = userService.save(user);

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
        Assert.assertEquals(expectedUser, result);
    }

    @Test
    public void saveUserWithDuplicatedLogin() {
        User user = new User(null, "User1", "Last", "test@test.com", new Date(System.currentTimeMillis()), "user", "123",
                "81999999999", null, new Date(System.currentTimeMillis()), null);

        User duplicatedLogin = new User(1L, "User1", "Last", "other_email@test.com", new Date(System.currentTimeMillis()), "user", "123",
                "81999999999", null, new Date(System.currentTimeMillis()), null);

        Mockito.when(userRepository.findByLogin(Mockito.anyString())).thenReturn(Optional.of(duplicatedLogin));

        try {
            userService.save(user);
            Assert.fail();
        }
        catch (ObjectAlreadyExistsException e) {
            Assert.assertEquals("Login already exists.", e.getMessage());
        }
    }

    @Test
    public void findUnknownUser() {
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Long id = 5L;
        try {
            userService.findById(id);
            Assert.fail();
        }
        catch (ObjectNotFoundException e) {
            Assert.assertEquals("User not found for id " + id, e.getMessage());
        }
    }


}
