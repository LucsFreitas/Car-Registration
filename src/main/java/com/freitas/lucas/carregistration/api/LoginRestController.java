package com.freitas.lucas.carregistration.api;

import com.freitas.lucas.carregistration.dto.CredentialsDTO;
import com.freitas.lucas.carregistration.dto.UserDTO;
import com.freitas.lucas.carregistration.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    @ApiOperation(value = "Retorna as informações do usuário logado")
    public ResponseEntity<UserDTO> getMe () {
        return new ResponseEntity<>(new UserDTO(this.userService.getCurrentUser()), HttpStatus.OK);
    }

    @PostMapping("/login")
    @ApiOperation(value = "Realiza o login do usuário")
    public void fakeLogin(@RequestBody CredentialsDTO credentialsDTO) {
        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
    }
}
