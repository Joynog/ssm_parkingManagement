package com.java.service;



import java.util.List;

import com.java.model.*;

public interface CarService {

	public int Add(Car r);
	public int Edit(Car r);
	public Car GetByID(int id);
	public int Del(int id);
	public List<Car> Get(Car s);

	public int GetCount(Car s);

}
