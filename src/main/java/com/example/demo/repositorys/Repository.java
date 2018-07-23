package com.example.demo.repositorys;

import com.example.demo.person.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface Repository extends CrudRepository<Person, Long> {
    Person findByFirstNameAndLastName(String firstName, String lastName);
    Person findByUsername(String username);
    Person getById(Long id);
    Optional<Person> findById(Long id);
}
