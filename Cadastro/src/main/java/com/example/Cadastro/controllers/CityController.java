package com.example.Cadastro.controllers;

import com.example.Cadastro.models.City;
import com.example.Cadastro.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/city")
public class CityController {

    @Autowired
    private CityRepository repository;

    @GetMapping
    public ResponseEntity<List<City>> findAllCities(){
        List<City> allCities = repository.findAll();
        return new ResponseEntity<>(allCities, HttpStatus.OK);
    }

    @GetMapping(value = "/findbyid/{id}")
    public ResponseEntity<Optional<City>> findCityById(@PathVariable UUID id){
        Optional<City> cityById = repository.findById(id);
        return new ResponseEntity<>(cityById, HttpStatus.FOUND);
    }

    @GetMapping(value = "/findbyname/{name}")
    public ResponseEntity<Optional<City>> findCityByName(@PathVariable String name){
        Optional<City> cityByName = repository.findByName(name);
        return new ResponseEntity<>(cityByName, HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<City> postCity(@RequestBody City city){
        city.setId(UUID.randomUUID());
        City newCity = repository.save(city);
        return new ResponseEntity<>(newCity, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<City> updateCity(@RequestBody City city, @PathVariable UUID id){
        Optional<City> cityToUpdate = repository.findById(id);
        if(cityToUpdate.isPresent()){
            City cityUpdate = cityToUpdate.get();

            cityUpdate.setState(city.getState());
            cityUpdate.setName(city.getName());
            cityUpdate.setState(city.getState());

            City cityUpdated = repository.save(cityUpdate);
            return new ResponseEntity<>(cityUpdated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<City> deleteCity(@PathVariable UUID id){
        Optional<City> cityToDelete = repository.findById(id);
        if(cityToDelete.isPresent()){
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
