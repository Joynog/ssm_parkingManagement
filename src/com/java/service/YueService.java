package com.java.service;



import java.util.List;

import com.java.model.*;

public interface YueService {

	public int Add(Yue r);
	public int Edit(Yue r);
	public int EditEndTime(Yue r);
	public Yue GetByID(int id);
	public int Del(int id);
	public List<Yue> Get(Yue s);
	public int GetCount(Yue s);
}
