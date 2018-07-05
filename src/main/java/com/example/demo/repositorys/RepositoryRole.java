package com.example.demo.repositorys;

import com.example.demo.role.Role;
import org.springframework.data.repository.CrudRepository;

public interface RepositoryRole extends CrudRepository<Role, Long> {
}
