package com.example.demo.role;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String rol;

    public Role() {}



    public Long getId() {

        return id;
    }
    public Role(String rol) {

        this.rol = rol;
    }

    public String getRol() {

        return rol;
    }

    @Override
    public String toString() {
        return String.format("%s",rol);
    }
}
