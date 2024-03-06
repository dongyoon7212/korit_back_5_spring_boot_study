package com.study.mvc.repository;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CarRepository {
    public List<String> getCarNames();
    public int insertCar(String carName);
}
