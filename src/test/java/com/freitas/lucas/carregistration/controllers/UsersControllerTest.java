package com.freitas.lucas.carregistration.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freitas.lucas.carregistration.domain.User;
import com.freitas.lucas.carregistration.dto.UserDTO;
import com.freitas.lucas.carregistration.error.exceptions.ObjectNotFoundException;
import com.freitas.lucas.carregistration.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void listUsers() throws Exception {
        Mockito.when(userService.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void newUserWithoutLoginAndPassword() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("User1");
        userDTO.setLastName("Last Name");
        userDTO.setEmail("test@test.com");
        userDTO.setPhone("81999999999");
        userDTO.setBirthday(new Date(System.currentTimeMillis()));

        Mockito.when(userService.save(Mockito.any(User.class))).thenReturn(new User());

        mockMvc.perform(MockMvcRequestBuilders
                .post("/users")
                .content(objectMapper.writeValueAsString(userDTO))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void newUser() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("User1");
        userDTO.setLastName("Last Name");
        userDTO.setEmail("test@test.com");
        userDTO.setPhone("81999999999");
        userDTO.setLogin("test");
        userDTO.setPassword("123");
        userDTO.setBirthday(new Date(System.currentTimeMillis()));

        User user = new User();
        user.setId(1L);
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        user.setBirthday(userDTO.getBirthday());
        user.setCreatedAt(new Date(System.currentTimeMillis()));

        Mockito.when(userService.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(userService.fromDTO(Mockito.any(UserDTO.class))).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/users")
                .content(objectMapper.writeValueAsString(userDTO))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }


    @Test
    public void findUnknownUser() throws Exception {
        Mockito.when(userService.findById(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(""));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/users/99")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
