package com.freitas.lucas.carregistration.api;

import com.freitas.lucas.carregistration.dto.CarDTO;
import com.freitas.lucas.carregistration.services.CarService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/cars")
public class CarRestController {

    @Autowired
    private CarService carService;

    @GetMapping
    @ApiOperation(value = "Lista todos os carros")
    public ResponseEntity<List<CarDTO>> findAll () {
        List<CarDTO> listDTO = this.carService.findAll()
                .stream()
                .map(CarDTO::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }
}
