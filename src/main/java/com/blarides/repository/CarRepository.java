package com.blarides.repository;

import org.springframework.data.repository.CrudRepository;

import com.blarides.domain.entity.Car;

public interface CarRepository extends CrudRepository<Car,Long> {

}
