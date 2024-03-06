package com.study.mvc.service;

import com.study.mvc.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service //component내장되어 있다.
public class CarServiceImpl implements CarService {

    final String componentName = "a";

    @Autowired
    @Qualifier(componentName) //변수명으로 넣을 수 있다.
    private CarRepository carRepository;

    @Override
    public String getCarNames() {

        return String.join(", ", carRepository.getCarNames());
        // 문자열 : k3, k5
    }

    @Override
    public int addCar(String carName) {
        return 0;
    }
}