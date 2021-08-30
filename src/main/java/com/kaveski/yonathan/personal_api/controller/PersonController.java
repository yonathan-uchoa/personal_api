package com.kaveski.yonathan.personal_api.controller;

import com.kaveski.yonathan.personal_api.dto.response.MessageResponseDTO;
import com.kaveski.yonathan.personal_api.dto.request.PersonDTO;
import com.kaveski.yonathan.personal_api.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    private PersonService personService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO){
        return personService.create(personDTO);
    }
}
