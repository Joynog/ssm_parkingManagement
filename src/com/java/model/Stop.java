package com.java.model;

import java.util.Date;

public class Stop {
    private Integer id=0;
    private Integer carstopId;
    private CarStop carstop;
    private Date starttime;
    private Date endtime;
    private String mid;
    private Integer money;
    private int mins;
    private Integer back;
    private String clientmid;
    private Pages page;
    
    
	public Pages getPage() {
		return page;
	}
	public void setPage(Pages page) {
		this.page = page;
	}
	public String getClientmid() {
		return clientmid;
	}
	public void setClientmid(String clientmid) {
		this.clientmid = clientmid;
	}
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
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	
	public int getMins() {
		return mins;
	}
	public void setMins(int mins) {
		this.mins = mins;
	}
	public Integer getBack() {
		return back;
	}
	public void setBack(Integer back) {
		this.back = back;
	}
    
    
}