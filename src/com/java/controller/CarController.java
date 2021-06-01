package com.java.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.model.*;
import com.java.service.*;
import com.java.utils.PageList;


@Controller
@RequestMapping("/car")
public class CarController {
	private CarService carService;

	public CarService getCarService() {
		return carService;
	}

	@Autowired
	public void setCarService(CarService carService) {
		this.carService = carService;
	}
	@Autowired
	private ClientService clientService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;

	@RequestMapping("/addpage")
	public String addpage(Integer id) {
		Car c=id==null?new Car():carService.GetByID(id);
		request.setAttribute("add",c);
		return "client/addcar";
	}
	

	@RequestMapping("/add")
	public String add(Car s) {
		Client c=(Client)request.getSession().getAttribute("client");
		s.setClientId(c.getId());
		try{
			int count=0;
			if(s.getId()==null)
				count = carService.Add(s);
			else
				count=carService.Edit(s);
			if(count>0)
				return Util.SetMap("操作成功", true, response);
			else
				return Util.SetMap("操作失败", false, response);
		} catch (Exception e) {
			return Util.SetMap(e.getMessage(), false, response);
		} 
	}

	@RequestMapping("/admin_list")
	public String Get(Car h) {
		//分页参数设置
		Pages p=new Pages();
		p.setPagesize(10);//每页显示数量 
		int startindex=request.getParameter("startindex")==null?0:Integer.parseInt(request.getParameter("startindex"));//起始页，默认从第1页开始读
		p.setStartindex(startindex);
		h.setPage(p);
		try {
			List<Car> list = carService.Get(h);
			for(int i=0;i<list.size();i++)
			{
				Client c=clientService.GetByID(list.get(i).getClientId());
				list.get(i).setClient(c);
			}
			request.setAttribute("list", list);
			//分页
			request.setAttribute("pages", PageList.Page(request, carService.GetCount(h), 
					p.getPagesize(), p.getStartindex(),request.getQueryString()));
			return "admin/car";
		} catch (Exception e) {
			return null;
		}
		
	}
	
	@RequestMapping("/client_list")
	public String web_list(Car h) {
		Client c=(Client)request.getSession().getAttribute("client");
		h.setClientId(c.getId());
		//分页参数设置
		Pages p=new Pages();
		p.setPagesize(10);//每页显示数量 
		int startindex=request.getParameter("startindex")==null?0:Integer.parseInt(request.getParameter("startindex"));//起始页，默认从第1页开始读
		p.setStartindex(startindex);
		h.setPage(p);
		try {
			List<Car> list = carService.Get(h);
			request.setAttribute("list", list);
			//分页
			request.setAttribute("pages", PageList.Page(request, carService.GetCount(h), 
					p.getPagesize(), p.getStartindex(),request.getQueryString()));
			return "client/car";
		} catch (Exception e) {
			return null;
		}
		
	}
	
	@RequestMapping("/del")
	public String Del(int id) {
		if(carService.Del(id)>0)
			return Util.SetMap("删除成功", true, response);
		else
			return Util.SetMap("删除失败", false, response);
	}
	
	
}