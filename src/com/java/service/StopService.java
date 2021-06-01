package com.java.service;



import java.util.List;

import com.java.model.*;

public interface StopService {

	public int Add(Stop r);
	public int Edit(Stop r);
	public int EditEndTime(Stop r);
	public Stop GetByID(int id);
	public int Del(int id);
	public List<Stop> Get(Stop s);
	public int GetCount(Stop s);
	public int GetSum(Stop s);
}
