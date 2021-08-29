package com.kaveski.yonathan.personal_api.controller;

import com.kaveski.yonathan.personal_api.dto.MessageResponseDTO;
import com.kaveski.yonathan.personal_api.entity.Person;
import com.kaveski.yonathan.personal_api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {
    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody Person person){
        return personService.create(person);
    }
}
