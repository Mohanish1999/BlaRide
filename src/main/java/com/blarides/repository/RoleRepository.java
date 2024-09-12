package com.blarides.repository;

import org.springframework.data.repository.CrudRepository;

import com.blarides.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}
