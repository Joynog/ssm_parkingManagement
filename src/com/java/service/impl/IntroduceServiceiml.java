package com.java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.*;
import com.java.model.*;
import com.java.service.IntroduceService;

@Service("IntroduceService")
public class IntroduceServiceiml implements IntroduceService {

	private IntroduceMapper introduceMapper;

	public IntroduceMapper getAddMapper() {
		return introduceMapper;
	}
	@Autowired
	public void setAddMapper(IntroduceMapper introduceMapper) {
		this.introduceMapper = introduceMapper;
	}
	  
	public int Edit(Introduce introduce) {
		return introduceMapper.Edit(introduce);
	}
	
	public Introduce GetByID(int id) {
		return introduceMapper.GetByID(id);
	}

	public List<Introduce> Get() {
		return introduceMapper.Get();
	}

}
