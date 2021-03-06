package com.freitas.lucas.carregistration.api;

import com.freitas.lucas.carregistration.domain.Car;
import com.freitas.lucas.carregistration.dto.CarDTO;
import com.freitas.lucas.carregistration.services.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/cars")
@Api(tags = "Cars")
public class CarRestController {

    @Autowired
    private CarService carService;

    @GetMapping
    @ApiOperation(value = "Lista todos os carros do usuário logado")
    public ResponseEntity<List<CarDTO>> findAll () {
        List<CarDTO> listDTO = this.carService.findAll()
                .stream()
                .map(CarDTO::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Cadastrar um novo carro para o usuário logado")
    public ResponseEntity<CarDTO> create (@RequestBody @Valid CarDTO car) {
        Car newCar = this.carService.save(car.toEntity());
        return new ResponseEntity<>(new CarDTO(newCar), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Buscar um carro do usuário logado pelo id")
    public ResponseEntity<CarDTO> findById(@PathVariable Long id) {
        Car car = this.carService.findById(id);
        return new ResponseEntity<>(new CarDTO(car), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Remover um carro do usuário logado pelo id")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        this.carService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar um carro do usuário logado pelo id")
    public ResponseEntity <CarDTO> updateById(@PathVariable Long id, @Valid @RequestBody CarDTO carDTO) {
        carDTO.setId(id);
        Car car = this.carService.update(carDTO.toEntity());
        return new ResponseEntity<>(new CarDTO(car), HttpStatus.OK);
    }
}
