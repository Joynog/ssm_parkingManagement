package com.java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.*;
import com.java.model.*;
import com.java.service.CarService;

@Service("CarService")
public class CarServiceiml implements CarService {

	private CarMapper carMapper;

	public CarMapper getAddMapper() {
		return carMapper;
	}
	@Autowired
	public void setAddMapper(CarMapper carMapper) {
		this.carMapper = carMapper;
	}
	 
	public int Edit(Car car) {
		return carMapper.Edit(car);
	}
	
	public Car GetByID(int id) {
		return carMapper.GetByID(id);
	}
	
	public int Add(Car car) {
		return carMapper.Add(car);
	}
	
	public int Del(int id) {
		int result = carMapper.Del(id);
		return result;
	}

	public List<Car> Get(Car car) {
		return carMapper.Get(car);
	}

	
	public int GetCount(Car car) {
		return carMapper.GetCount(car);
	}

}
