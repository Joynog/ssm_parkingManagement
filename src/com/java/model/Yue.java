package com.java.model;

import java.util.Date;

public class Yue {
    private Integer id;
    private Integer carstopId;
    private CarStop carstop;
    private Date starttime;
    private String timetype;
    private Car car;
    private Integer carId;
    private Client client;
    private Integer clientId;
    
    
    public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	private Pages page;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCarstopId() {
		return carstopId;
	}

	public void setCarstopId(Integer carstopId) {
		this.carstopId = carstopId;
	}

	public CarStop getCarstop() {
		return carstop;
	}

	public void setCarstop(CarStop carstop) {
		this.carstop = carstop;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public String getTimetype() {
		return timetype;
	}

	public void setTimetype(String timetype) {
		this.timetype = timetype;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public Pages getPage() {
		return page;
	}

	public void setPage(Pages page) {
		this.page = page;
	}
    
    
	
    
}