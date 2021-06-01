package com.java.service;



import java.util.List;

import com.java.model.*;

public interface CarStopTypeService {

	public int Add(CarStopType j);
	public int Edit(CarStopType j);
	public CarStopType GetByID(int id);
	public int Del(int id);
	public List<CarStopType> Get();

}
