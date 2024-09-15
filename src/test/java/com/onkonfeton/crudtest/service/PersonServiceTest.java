package com.onkonfeton.crudtest.service;

import com.onkonfeton.crudtest.dao.PersonRepository;
import com.onkonfeton.crudtest.domain.Person;
import com.onkonfeton.crudtest.entity.PersonEntity;
import com.onkonfeton.crudtest.exception.PersonNotFoundException;
import com.onkonfeton.crudtest.mapper.PersonMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonMapper personMapper;

    @InjectMocks
    private PersonService personService;

    private UUID testUUID;
    private PersonEntity personEntity;
    private Person person;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testUUID = UUID.randomUUID();
        personEntity = new PersonEntity(testUUID, "John", 30);
        person = new Person(testUUID, "John", 30);
    }

    @Test
    void shouldReturnAllPeople() {
        when(personRepository.findAll()).thenReturn(Arrays.asList(personEntity));
        when(personMapper.toDomain(personEntity)).thenReturn(person);

        List<Person> result = personService.getAll();

        assertEquals(1, result.size());
    }

    @Test
    void shouldReturnPersonById_WhenPersonExists() {
        when(personRepository.findById(testUUID)).thenReturn(Optional.of(personEntity));
        when(personMapper.toDomain(personEntity)).thenReturn(person);

        Person result = personService.getById(testUUID);

        assertEquals("John", result.getName());
    }

    @Test
    void shouldThrowPersonNotFoundException_WhenPersonDoesNotExist() {
        when(personRepository.findById(testUUID)).thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () -> personService.getById(testUUID));
    }

    @Test
    void shouldReturnCreatedPerson() {
        when(personMapper.toEntity(person)).thenReturn(personEntity);
        when(personRepository.save(personEntity)).thenReturn(personEntity);

        Person result = personService.create(person);

        assertNotNull(result);
        assertEquals("John", result.getName());
    }

    @Test
    void shouldReturnUpdatedPerson() {
        when(personRepository.update(testUUID, person)).thenReturn(personEntity);
        when(personMapper.toDomain(personEntity)).thenReturn(person);

        Person result = personService.update(testUUID, person);

        assertNotNull(result);
        assertEquals("John", result.getName());
    }
    
}
