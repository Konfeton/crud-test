package com.onkonfeton.crudtest.mapper;

import com.onkonfeton.crudtest.domain.Person;
import com.onkonfeton.crudtest.entity.PersonEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class PersonMapperTest {

    private PersonMapper personMapper = Mappers.getMapper(PersonMapper.class);

    @Test
    void shouldMapToDomain() {
        UUID id = UUID.randomUUID();
        PersonEntity personEntity = new PersonEntity();
        personEntity.setId(id);
        personEntity.setName("John");
        personEntity.setAge(30);

        Person person = personMapper.toDomain(personEntity);

        assertNotNull(person);
        assertEquals(personEntity.getId(), person.getId());
        assertEquals(personEntity.getName(), person.getName());
        assertEquals(personEntity.getAge(), person.getAge());
    }

    @Test
    void shouldMapToEntity() {
        UUID id = UUID.randomUUID();
        Person person = new Person();
        person.setId(id);
        person.setName("John");
        person.setAge(30);

        PersonEntity personEntity = personMapper.toEntity(person);

        assertNotNull(personEntity);
        assertEquals(person.getId(), personEntity.getId());
        assertEquals(person.getName(), personEntity.getName());
        assertEquals(person.getAge(), personEntity.getAge());
    }

    @Test
    void toDomain_shouldReturnNull() {
        assertNull(personMapper.toDomain(null));
    }

    @Test
    void toEntity_ShouldReturnNull() {
        assertNull(personMapper.toEntity(null));
    }
}
