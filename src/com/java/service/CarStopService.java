package com.java.service;



import java.util.List;

import com.java.model.*;

public interface CarStopService {

	public int Add(CarStop r);
	public int Edit(CarStop r);
	public CarStop GetByID(int id);
	public int Del(int id);
	public List<CarStop> Get(CarStop s);
	public List<CarStop> GetEmpty(CarStop s);
	public int GetCount(CarStop s);

}
