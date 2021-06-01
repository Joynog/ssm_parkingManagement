package com.java.dao;

import java.util.List;

import com.java.model.Car;

public interface CarMapper {
    int Del(Integer id);

    int Add(Car record);


    Car GetByID(Integer id);

    List<Car> Get(Car record);


    int GetCount(Car record);

    int Edit(Car record);
}