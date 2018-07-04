package com.example.demo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

@Entity
@Table(name = "Person")
public class Person implements Serializable {

    private Set<Role> repositoryRole = new HashSet<Role>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;

    public Person() {}

    public Person(Long id, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Person[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

    @ManyToMany
    @JoinTable(name = "Person_Role",
            joinColumns = @JoinColumn(name = "PERSON_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    public Set<Role> getRepositoryRole() {
        return this.repositoryRole;
    }

}