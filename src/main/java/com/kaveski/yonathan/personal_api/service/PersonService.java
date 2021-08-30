package com.kaveski.yonathan.personal_api.service;

import com.kaveski.yonathan.personal_api.dto.response.MessageResponseDTO;
import com.kaveski.yonathan.personal_api.dto.request.PersonDTO;
import com.kaveski.yonathan.personal_api.entity.Person;
import com.kaveski.yonathan.personal_api.dto.mapper.PersonMapper;
import com.kaveski.yonathan.personal_api.exception.PersonNotFoundException;
import com.kaveski.yonathan.personal_api.repository.PersonRepository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class PersonService {

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    private PersonRepository personRepository;



    public MessageResponseDTO create(PersonDTO personDTO) {
        Person person = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(person);

        return CreateMessageResponse(savedPerson.getId(), "Create person with ID: ");
    }

    public List<PersonDTO> listAll() {
        List<Person> person = personRepository.findAll();
        return person.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExist(id);
        return personMapper.toDTO(person);
    }

    public MessageResponseDTO update(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExist(id);
        Person person = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(person);

        return CreateMessageResponse(id, "Update person with ID: ");
    }

    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExist(id);
        personRepository.deleteById(id);
    }

    private MessageResponseDTO CreateMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }

    private Person verifyIfExist(Long id) throws PersonNotFoundException {
        return personRepository.findById(id).orElseThrow(()->
                new PersonNotFoundException(id)
        );
    }
}