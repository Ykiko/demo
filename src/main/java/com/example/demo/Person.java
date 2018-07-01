package com.example.demo;


public class Person {

    private Long id;
    private String firstName;
    private String lastName;

    public Person(){

    }

    public Person(String firstName, String lastName, Long id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
