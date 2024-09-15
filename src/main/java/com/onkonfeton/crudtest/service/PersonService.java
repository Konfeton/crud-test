package com.onkonfeton.crudtest.service;


import com.onkonfeton.crudtest.dao.PersonRepository;
import com.onkonfeton.crudtest.domain.Person;
import com.onkonfeton.crudtest.entity.PersonEntity;
import com.onkonfeton.crudtest.exception.PersonNotFoundException;
import com.onkonfeton.crudtest.mapper.PersonMapper;
import com.onkonfeton.crudtest.mapper.PersonMapperImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper = new PersonMapperImpl();

    public List<Person> getAll() {
        List<PersonEntity> persons = personRepository.findAll();

        return persons.stream()
                .map(personEntity -> personMapper.toDomain(personEntity))
                .toList();
    }

    public Person getById(UUID id) {
        Optional<PersonEntity> byId = personRepository.findById(id);
        if (byId.isPresent()){
            return personMapper.toDomain(byId.get());
        }
        throw PersonNotFoundException.byId(id);
    }

    public Person create(Person person) {
        PersonEntity entity = personMapper.toEntity(person);
        personRepository.save(entity);
        return person;
    }

    public Person update(UUID id, Person person) {
        PersonEntity updated = personRepository.update(id, person);
        return personMapper.toDomain(updated);
    }

    public void delete(UUID id) {
        personRepository.deleteById(id);
    }
}
