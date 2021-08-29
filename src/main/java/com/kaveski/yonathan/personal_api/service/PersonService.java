package com.kaveski.yonathan.personal_api.service;

import com.kaveski.yonathan.personal_api.dto.MessageResponseDTO;
import com.kaveski.yonathan.personal_api.entity.Person;
import com.kaveski.yonathan.personal_api.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonService {
    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO create(Person person){
        Person savedPerson = personRepository.save(person);
        return MessageResponseDTO
                .builder()
                .message("Saved person with ID "+savedPerson.getId())
                .build();
    }

}
