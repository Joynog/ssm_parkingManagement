package com.java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.*;
import com.java.model.*;
import com.java.service.YueService;

@Service("YueService")
public class YueServiceiml implements YueService {

	private YueMapper yueMapper;

	public YueMapper getAddMapper() {
		return yueMapper;
	}
	@Autowired
	public void setAddMapper(YueMapper yueMapper) {
		this.yueMapper = yueMapper;
	}
	   
	public int Edit(Yue yue) {
		return yueMapper.Edit(yue);
	}
	  
	public int EditEndTime(Yue yue) {
		return yueMapper.EditEndTime(yue);
	}
	
	public Yue GetByID(int id) {
		return yueMapper.GetByID(id);
	}
	
	public int Add(Yue yue) {
		return yueMapper.Add(yue);
	}
	
	public int Del(int id) {
		int result = yueMapper.Del(id);
		return result;
	}

	public List<Yue> Get(Yue yue) {
		return yueMapper.Get(yue);
	}
	
	public int GetCount(Yue yue) {
		return yueMapper.GetCount(yue);
	}

}
