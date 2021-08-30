package com.kaveski.yonathan.personal_api.service;

import com.kaveski.yonathan.personal_api.dto.response.MessageResponseDTO;
import com.kaveski.yonathan.personal_api.dto.request.PersonDTO;
import com.kaveski.yonathan.personal_api.entity.Person;
import com.kaveski.yonathan.personal_api.dto.mapper.PersonMapper;
import com.kaveski.yonathan.personal_api.exception.PersonNotFoundException;
import com.kaveski.yonathan.personal_api.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


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

    public List<PersonDTO> listAll() {
        List<Person> person = personRepository.findAll();
        return person.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = personRepository.findById(id).orElseThrow(()->
                    new PersonNotFoundException(id)
                );
        return personMapper.toDTO(person);
    }
}