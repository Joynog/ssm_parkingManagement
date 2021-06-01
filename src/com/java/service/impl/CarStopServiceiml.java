package com.java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.*;
import com.java.model.*;
import com.java.service.CarStopService;

@Service("CarStopService")
public class CarStopServiceiml implements CarStopService {

	private CarStopMapper carstopMapper;

	public CarStopMapper getAddMapper() {
		return carstopMapper;
	}
	@Autowired
	public void setAddMapper(CarStopMapper carstopMapper) {
		this.carstopMapper = carstopMapper;
	}
	   
	public int Edit(CarStop carstop) {
		return carstopMapper.Edit(carstop);
	}
	
	public CarStop GetByID(int id) {
		return carstopMapper.GetByID(id);
	}
	
	public int Add(CarStop carstop) {
		return carstopMapper.Add(carstop);
	}
	
	public int Del(int id) {
		int result = carstopMapper.Del(id);
		return result;
	}

	public List<CarStop> Get(CarStop carstop) {
		return carstopMapper.Get(carstop);
	}
	
	public List<CarStop> GetEmpty(CarStop carstop) {
		return carstopMapper.GetEmpty(carstop);
	}
	
	public int GetCount(CarStop carstop) {
		return carstopMapper.GetCount(carstop);
	}

}
