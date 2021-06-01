package com.java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.*;
import com.java.model.*;
import com.java.service.CarStopTypeService;

@Service("CarStopTypeService")
public class CarStopTypeServiceiml implements CarStopTypeService {

	private CarStopTypeMapper carstoptypeMapper;

	public CarStopTypeMapper getAddMapper() {
		return carstoptypeMapper;
	}
	@Autowired
	public void setAddMapper(CarStopTypeMapper carstoptypeMapper) {
		this.carstoptypeMapper = carstoptypeMapper;
	}
	   
	public int Edit(CarStopType carstoptype) {
		return carstoptypeMapper.Edit(carstoptype);
	}
	
	public CarStopType GetByID(int id) {
		return carstoptypeMapper.GetByID(id);
	}
	
	public int Add(CarStopType carstoptype) {
		return carstoptypeMapper.Add(carstoptype);
	}
	
	public int Del(int id) {
		int result = carstoptypeMapper.Del(id);
		System.out.println("no:"+result);
		return result;
	}

	public List<CarStopType> Get() {
		return carstoptypeMapper.Get();
	}
	

}
