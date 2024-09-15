package com.onkonfeton.crudtest.exception;

import java.util.UUID;

public class PersonNotFoundException extends RuntimeException {
    private PersonNotFoundException(String message) {
        super(message);
    }

    public static PersonNotFoundException byId(UUID id){
        return new PersonNotFoundException(
                String.format("Person with id %s not found", id)
        );
    }
}
