package com.onkonfeton.crudtest.controller;


import com.onkonfeton.crudtest.domain.Person;
import com.onkonfeton.crudtest.service.PersonService;
import lombok.RequiredArgsConstructor;
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

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public List<Person> getAll() {
        return personService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable UUID id) {
        Person person = personService.getById(id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping
    public Person create(@RequestBody Person person) {
        return personService.create(person);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable UUID id, @RequestBody Person person) {
        Person updatedPerson = personService.update(id, person);

        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable UUID id) {
        personService.delete(id);
    }
}
