package com.blarides.service.ServiceImpl;

import com.blarides.domain.entity.Car;
import com.blarides.repository.CarRepository;
import com.blarides.service.CarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService{

    private CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car createCar(Car car) {
        return carRepository.save(car);
    }
}
