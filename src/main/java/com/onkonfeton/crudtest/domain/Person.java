package com.onkonfeton.crudtest.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private UUID id;
    private String name;
    private int age;

}
