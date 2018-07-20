package com.example.demo.role;

import javax.persistence.*;

@Entity
@Table(name = "Role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;

    public Role() {}

    public Role(String role) {

        this.role = role;
    }

    public Long getId() {

        return id;
    }

    public String getRole() {

        return role;
    }

    @Override
    public String toString() {
        return String.format("%s",role);
    }
}
