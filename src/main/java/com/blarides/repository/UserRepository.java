package com.blarides.repository;

import org.springframework.data.repository.CrudRepository;

import com.blarides.domain.entity.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAll();
}
