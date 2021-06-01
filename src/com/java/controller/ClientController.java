package com.java.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.java.model.*;
import com.java.service.*;
import com.java.utils.PageList;
import com.java.utils.ResponseUtil;

@Controller
@RequestMapping("/client")
public class ClientController {
	private ClientService clientService;

	public ClientService getClientService() {
		return clientService;
	}

	@Autowired
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	
	@RequestMapping("/reg")
	public String add() {
		return "reg";
	}
	
	@RequestMapping("/pwdpage")
	public String pwd() {
		
		Client c=(Client)request.getSession().getAttribute("client");
		request.setAttribute("client", c);
		return "client/pwd";
	}
	
	
	
	@RequestMapping("/editpage")
	public String EditForm() {
		Client c=(Client)request.getSession().getAttribute("client");
		request.setAttribute("client", c);
		return "client/edit";
	}
	
	@RequestMapping("/messhow")
	public String messhow() {
		
		Client c=(Client)request.getSession().getAttribute("client");
		c=clientService.GetByID(c.getId());
		request.setAttribute("client", c);
		return "client/messhow";
	}
	
	@RequestMapping("/client_add")
	public String Add(Client c) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			System.out.println(request.getParameter("login"));
			int r = clientService.Add(c);
			if(r>0)
			{
				map.put("mgf", "注册成功");
				map.put("success", true);
			}
			else
			{
				map.put("mgf", "注册失败");
				map.put("success", false);
			}
		} catch (Exception e) {
			map.put("mgf", "错误："+e.getMessage());
			map.put("success", false);
		} 
		String result = new JSONObject(map).toString();
		ResponseUtil.write(response, result);
		return null;
	}
	
	@RequestMapping("/edit")
	public String Edit(Client client) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Client c=(Client)request.getSession().getAttribute("client");
			client.setId(c.getId());
			int r=clientService.Edit(client);
			if(r>0)
			{
				map.put("mgf", "更新成功");
				map.put("success", true);
			}
			else
			{
				map.put("mgf", "更新失败");
				map.put("success", false);
			}
		} catch (Exception e) {
			map.put("mgf", "错误："+e.getMessage());
			map.put("success", false);
		} 
		String result = new JSONObject(map).toString();
		ResponseUtil.write(response, result);
		return null;
	}

	@RequestMapping("/editpass")
	public String EditPass() {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Client c=(Client)request.getSession().getAttribute("client");
			c.setPwd(request.getParameter("pwd"));
			int r=clientService.EditPass(c);
			if(r>0)
			{
				map.put("mgf", "密码修改成功，下次请用新密码登陆");
				map.put("success", true);
				request.getSession().setAttribute("client", c);
			}
			else
			{
				map.put("mgf", "密码修改失败");
				map.put("success", false);
			}
		} catch (Exception e) {
			map.put("mgf", "错误："+e.getMessage());
			map.put("success", false);
		} 
		String result = new JSONObject(map).toString();
		ResponseUtil.write(response, result);
		return null;
	}
	

	
	@RequestMapping("/admin_list")
	public String admin_list(Client c) {
		//分页参数设置
		Pages p=new Pages();
		p.setPagesize(10);//每页显示数量 
		int startindex=request.getParameter("startindex")==null?0:Integer.parseInt(request.getParameter("startindex"));//起始页，默认从第1页开始读
		p.setStartindex(startindex);
		c.setPage(p);
		try {
			List<Client> list = clientService.Get(c);
			System.out.println(list);
			request.setAttribute("list", list);
			//分页
			request.setAttribute("pages", PageList.Page(request, clientService.GetCount(c), 
					p.getPagesize(), p.getStartindex(),request.getQueryString()));
			return "admin/client";
		} catch (Exception e) {
			return null;
		}
		
	}
	
	
	@RequestMapping(value="/del", method = RequestMethod.POST)
	public String Del(@RequestParam(value = "id") int id) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			System.out.println("================================");
			System.out.println(id);
			int r = clientService.Del(id);
			
			if(r>0)
			{
				map.put("mgf", "删除成功");
				map.put("success", true);
			}
			else
			{
				map.put("mgf", "删除失败");
				map.put("success", false);
			}
		} catch (Exception e) {
			map.put("mgf", "错误："+e.getMessage());
			map.put("success", false);
		} 
		String result = new JSONObject(map).toString();
		ResponseUtil.write(response, result);
		return null;
	}
	
	@RequestMapping("/login")
	public String Login(Client client) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Client a = clientService.Login(client.getLogin());
			
			if(a==null)
			{
				map.put("mgf", "用户不存在");
				map.put("success", false);
			}
			else if(!a.getPwd().equals(client.getPwd()))
			{
				map.put("mgf", "密码错误");
				map.put("success", false);
			}
			else
			{
				request.getSession().setAttribute("client", a);
				map.put("mgf", "验证通过!");
				map.put("success", true);
			}
		} catch (Exception e) {
			map.put("mgf", "错误："+e.getMessage());
			map.put("success", false);
		} 
		
		String result = new JSONObject(map).toString();
		ResponseUtil.write(response, result);
		return null;
	}
	
	@RequestMapping("/exit")
	public String Exit() {
		//Map<String,Object> map = new HashMap<String,Object>();
		request.getSession().removeAttribute("client");
		//map.put("mgf", "已安全退出!");
		//map.put("success", true);
		//String result = new JSONObject(map).toString();
		//ResponseUtil.write(response, result);
		return "login";
	}
	
}