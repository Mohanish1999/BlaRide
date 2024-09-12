package com.blarides.repository;

import org.springframework.data.repository.CrudRepository;

import com.blarides.domain.entity.DriverAccount;

public interface DriverAccountRepository extends CrudRepository<DriverAccount,Long> {
    DriverAccount findByDriverAccountId(Long driverAccountId);
}
