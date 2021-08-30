package com.kaveski.yonathan.personal_api.service;

import com.kaveski.yonathan.personal_api.dto.response.MessageResponseDTO;
import com.kaveski.yonathan.personal_api.dto.request.PersonDTO;
import com.kaveski.yonathan.personal_api.entity.Person;
import com.kaveski.yonathan.personal_api.dto.mapper.PersonMapper;
import com.kaveski.yonathan.personal_api.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PersonService {

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public MessageResponseDTO create(PersonDTO personDTO) {
        Person person = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(person);

        return MessageResponseDTO
                .builder()
                .message("Created person with ID: "+savedPerson.getId())
                .build();
    }
}