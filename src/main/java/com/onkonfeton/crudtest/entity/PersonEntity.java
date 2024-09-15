package com.onkonfeton.crudtest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity {
    private UUID id;
    private String name;
    private int age;

}