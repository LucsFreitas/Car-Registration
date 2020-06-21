package com.freitas.lucas.carregistration.api;

import com.freitas.lucas.carregistration.domain.User;
import com.freitas.lucas.carregistration.dto.UserDTO;
import com.freitas.lucas.carregistration.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping
    @ApiOperation(value = "Lista todos os usuários")
    public ResponseEntity<List<UserDTO>> findAll () {
        List<UserDTO> listDTO = this.userService.findAll()
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Cadastrar um novo usuário")
    public ResponseEntity<UserDTO> create (@RequestBody @Valid UserDTO user) {
        User newUser = this.userService.create(user.toEntity());
        return new ResponseEntity<>(new UserDTO(newUser), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Buscar um usuário pelo id")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        User user = this.userService.findById(id);
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }
}
