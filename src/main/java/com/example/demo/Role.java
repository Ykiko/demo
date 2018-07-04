package com.example.demo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Role")
public class Role implements Serializable {

    private Set<Person> repository = new HashSet<Person>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String rol;

    public Role() {}

    public Role(String rol) {
        this.rol = rol;
    }

    public String getRol() {

        return rol;
    }

    @Override
    public String toString() {

        return String.format("rol='%s'",rol);
    }

    @ManyToMany
    @JoinTable(name = "Person_Role",
            joinColumns = @JoinColumn(name = "ROLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "PERSON_ID"))
    public Set<Person> getRepository() {
        return this.repository;
    }
}
