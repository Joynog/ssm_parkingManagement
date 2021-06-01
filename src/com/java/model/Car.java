package com.java.model;

public class Car {
    private Integer id;
    private String mid;
    private String type;
    private Client client;
    private Integer clientId;
    private Pages page;
    
    
	public Pages getPage() {
		return page;
	}
	public void setPage(Pages page) {
		this.page = page;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
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
    
    
}