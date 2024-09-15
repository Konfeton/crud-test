package com.onkonfeton.crudtest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onkonfeton.crudtest.domain.Person;
import com.onkonfeton.crudtest.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PersonService personService;

    private UUID testUUID;
    private Person person;

    @BeforeEach
    void setUp() {
        testUUID = UUID.randomUUID();
        person = new Person(testUUID, "John", 30);
    }

    @Test
    void testGetAll() throws Exception {
        List<Person> persons = Arrays.asList(person);

        when(personService.getAll()).thenReturn(persons);

        mockMvc.perform(get("/api/v1/persons")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("John")));

        verify(personService, times(1)).getAll();
    }

    @Test
    void testGetById() throws Exception {
        when(personService.getById(testUUID)).thenReturn(person);

        mockMvc.perform(get("/api/v1/persons/{id}", testUUID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("John")));

        verify(personService, times(1)).getById(testUUID);
    }

    @Test
    void testCreatePerson() throws Exception {
        when(personService.create(person)).thenReturn(person);

        mockMvc.perform(post("/api/v1/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("John")))
                .andExpect(jsonPath("$.age", is(30)));

    }

    @Test
    void testUpdatePerson() throws Exception {
        when(personService.update(testUUID, person)).thenReturn(person);

        mockMvc.perform(put("/api/v1/persons/{id}", testUUID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("John")))
                .andExpect(jsonPath("$.age", is(30)));

    }

    @Test
    void testDeletePerson() throws Exception {
        doNothing().when(personService).delete(testUUID);

        mockMvc.perform(delete("/api/v1/persons/{id}", testUUID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(personService, times(1)).delete(testUUID);
    }
}
