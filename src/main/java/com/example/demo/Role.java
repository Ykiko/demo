package com.example.demo;

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
}
