package com.example.demo.repositorys;

import com.example.demo.user.User;
import org.springframework.data.repository.CrudRepository;

public interface RepositoryUser extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
