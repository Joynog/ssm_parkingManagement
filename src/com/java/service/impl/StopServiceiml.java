package com.java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.*;
import com.java.model.*;
import com.java.service.StopService;

@Service("StopService")
public class StopServiceiml implements StopService {

	private StopMapper stopMapper;

	public StopMapper getAddMapper() {
		return stopMapper;
	}
	@Autowired
	public void setAddMapper(StopMapper stopMapper) {
		this.stopMapper = stopMapper;
	}
	  
	public int Edit(Stop stop) {
		return stopMapper.Edit(stop);
	}
	 
	public int EditEndTime(Stop stop) {
		return stopMapper.EditEndTime(stop);
	}
	
	public Stop GetByID(int id) {
		return stopMapper.GetByID(id);
	}
	
	public int Add(Stop stop) {
		return stopMapper.Add(stop);
	}
	
	public int Del(int id) {
		int result = stopMapper.Del(id);
		return result;
	}

	public List<Stop> Get(Stop stop) {
		return stopMapper.Get(stop);
	}
	
	public int GetCount(Stop stop) {
		return stopMapper.GetCount(stop);
	}

	public int GetSum(Stop stop) {
		return stopMapper.GetSum(stop);
	}
}
