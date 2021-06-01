package com.java.model;

public class CarStop {
    private Integer id;
    private String name;
    private Integer carstoptypeId;
    private CarStopType carstoptype;
    private Pages page;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Pages getPage() {
		return page;
	}
	public void setPage(Pages page) {
		this.page = page;
	}
	public Integer getCarstoptypeId() {
		return carstoptypeId;
	}
	public void setCarstoptypeId(Integer carstoptypeId) {
		this.carstoptypeId = carstoptypeId;
	}
	public CarStopType getCarstoptype() {
		return carstoptype;
	}
	public void setCarstoptype(CarStopType carstoptype) {
		this.carstoptype = carstoptype;
	}
    
}