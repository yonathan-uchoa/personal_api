package com.kaveski.yonathan.personal_api.repository;

import com.kaveski.yonathan.personal_api.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {


}
