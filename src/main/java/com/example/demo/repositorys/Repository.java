package com.example.demo.repositorys;

import com.example.demo.Person;
import org.springframework.data.repository.CrudRepository;

public interface Repository extends CrudRepository<Person, Long> {
    Person findByFirstNameAndLastName(String firstName, String lastName);
}
