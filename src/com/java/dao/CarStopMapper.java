package com.java.dao;

import java.util.List;

import com.java.model.CarStop;

public interface CarStopMapper {
    int Del(Integer id);

    int Add(CarStop record);


    CarStop GetByID(Integer id);

    List<CarStop> Get(CarStop record);
    List<CarStop> GetEmpty(CarStop record);
    int GetCount(CarStop record);

    int Edit(CarStop record);
}