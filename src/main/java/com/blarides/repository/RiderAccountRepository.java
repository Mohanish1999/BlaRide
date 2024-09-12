package com.blarides.repository;

import org.springframework.data.repository.CrudRepository;

import com.blarides.domain.entity.RiderAccount;

public interface RiderAccountRepository extends CrudRepository<RiderAccount,Long> {
    RiderAccount findByRiderAccountId(Long riderAccountId);
}
