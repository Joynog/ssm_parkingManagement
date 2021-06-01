package com.java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.*;
import com.java.model.*;
import com.java.service.ClientService;

@Service("ClientService")
public class ClientServiceiml implements ClientService {

	private ClientMapper clientMapper;

	public ClientMapper getAddMapper() {
		return clientMapper;
	}
	
	@Autowired
	public void setAddMapper(ClientMapper clientMapper) {
		this.clientMapper = clientMapper;
	}
	
	public int Edit(Client client) {
		return clientMapper.Edit(client);
	}
	
	public Client GetByID(int id) {
		return clientMapper.GetByID(id);
	}
	
	public Client Login(String login) {
		return clientMapper.Login(login);
	}

	public int Add(Client client) {
		return clientMapper.Add(client);
	}
	

	public int Del(int id) {
		int result = clientMapper.Del(id);
		System.out.println("no:"+result);
		return result;
	}

	public List<Client> Get(Client c) {
		return clientMapper.Get(c);
	}
	
	public int EditPass(Client client) {
		// TODO Auto-generated method stub
		return clientMapper.EditPass(client);
	}

	public int GetCount(Client c) {
		// TODO Auto-generated method stub
		return clientMapper.GetCount(c);
	}

}
