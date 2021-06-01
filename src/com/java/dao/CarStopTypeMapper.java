package com.java.dao;

import java.util.List;

import com.java.model.CarStopType;

public interface CarStopTypeMapper {
	int Del(Integer id);

    int Add(CarStopType record);

    CarStopType GetByID(Integer id);

    int Edit(CarStopType record);

    List<CarStopType> Get();
}