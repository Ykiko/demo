package com.example.demo.role;

import javax.persistence.*;

@Entity
@Table(name = "Role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rol;

    public Role() {}

    public Role(String rol) {

        this.rol = rol;
    }

    public Long getId() {

        return id;
    }

    public String getRol() {

        return rol;
    }

    @Override
    public String toString() {
        return String.format("%s",rol);
    }
}
