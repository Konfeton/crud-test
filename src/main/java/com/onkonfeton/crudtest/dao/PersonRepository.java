package com.onkonfeton.crudtest.dao;

import com.onkonfeton.crudtest.domain.Person;
import com.onkonfeton.crudtest.entity.PersonEntity;
import com.onkonfeton.crudtest.exception.PersonNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PersonRepository {

    private final List<PersonEntity> persons = new ArrayList<>();

    public List<PersonEntity> findAll() {
        return persons;
    }

    public Optional<PersonEntity> findById(UUID id) {
        return persons.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    public PersonEntity save(PersonEntity person) {
        persons.add(person);
        return person;
    }

    public void deleteById(UUID id) {
        persons.removeIf(person -> person.getId().equals(id));
    }

    public PersonEntity update(UUID id, Person updatedPerson) {
        Optional<PersonEntity> optionalPerson = findById(id);
        if (optionalPerson.isPresent()) {
            PersonEntity person = optionalPerson.get();
            person.setName(updatedPerson.getName());
            person.setAge(updatedPerson.getAge());
            return person;
        }
        throw PersonNotFoundException.byId(id);
    }
}
