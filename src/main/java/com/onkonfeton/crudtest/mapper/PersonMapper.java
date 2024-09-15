package com.onkonfeton.crudtest.mapper;

import com.onkonfeton.crudtest.domain.Person;
import com.onkonfeton.crudtest.entity.PersonEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PersonMapper {

    Person toDomain(PersonEntity personEntity);
    PersonEntity toEntity(Person personEntity);
}
