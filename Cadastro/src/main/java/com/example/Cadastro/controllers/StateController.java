package com.example.Cadastro.controllers;

import com.example.Cadastro.models.State;
import com.example.Cadastro.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/state")
public class StateController {

    @Autowired
    private StateRepository repository;

    @GetMapping
    public ResponseEntity<List<State>> findAllStates(){
        List<State> allStates = repository.findAll();
        return new ResponseEntity<>(allStates, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<State> postState(@RequestBody State state){

        State newState = repository.save(state);
        return new ResponseEntity<>(newState, HttpStatus.CREATED);
    }

    @GetMapping(value = "/findbyid/{id}")
    public ResponseEntity<Optional<State>> findStateById(@PathVariable UUID id){
        Optional<State> stateById = repository.findById(id);
        return stateById.isPresent() ?
                new ResponseEntity<>(stateById, HttpStatus.FOUND)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/findbyname/{name}")
    public ResponseEntity<Optional<State>> findStateByName(@PathVariable String name){
        Optional<State> stateByName = repository.findByName(name);
        return stateByName.isPresent() ?
                new ResponseEntity<>(stateByName, HttpStatus.FOUND)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/findbyuf/{abbreviation}")
    public ResponseEntity<Optional<State>> findStateByAbbreviaiton(@PathVariable String abbreviation){
        Optional<State> statesByUf = repository.findByAbbreviation(abbreviation);
        return statesByUf.isPresent() ?
                new ResponseEntity<>(statesByUf, HttpStatus.FOUND)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<State> deleteById(@PathVariable UUID id){
        Optional<State> stateDelete = repository.findById(id);
        if(stateDelete.isPresent()){
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<State> updateState(@PathVariable UUID id, @RequestBody State state){
        Optional<State> stateToUpdate = repository.findById(id);
        if(stateToUpdate.isPresent()){
            State stateUpdate = stateToUpdate.get();

            stateUpdate.setName(state.getName());
            stateUpdate.setAbbreviation(state.getAbbreviation());

            State stateUpdated = repository.save(stateUpdate);
            return new ResponseEntity<>(stateUpdated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
