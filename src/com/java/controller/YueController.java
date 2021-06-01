package com.java.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.model.*;
import com.java.service.*;
import com.java.utils.PageList;

@Controller
@RequestMapping("/yue")
public class YueController {
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
	}
	


	@Autowired
	private YueService yueService;
	@Autowired
	private CarService carService;
	@Autowired
	private CarStopService carstopService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;

	@RequestMapping("/addpage")
	public String addpage(Integer id) {
		Yue y=id==null?new Yue():yueService.GetByID(id);
		Client client=(Client)request.getSession().getAttribute("client");
		Car c=new Car();
		c.setClientId(client.getId());
		request.setAttribute("car", carService.Get(c));
		request.setAttribute("carstop", carstopService.Get(null));
		request.setAttribute("add", y);
		return "client/addyue";
	}
	
	
	@RequestMapping("/add")
	public String add(Yue y) {
		Yue yue=new Yue();
		yue.setCarstopId(y.getCarstopId());
		yue.setTimetype(y.getTimetype());
		yue.setStarttime(y.getStarttime());
		if(yueService.GetCount(yue)>0)
			return Util.SetMap("该车位当天时间已被约！", false, response);
		yue.setCarstopId(null);
		yue.setCarId(y.getCarId());
		if(yueService.GetCount(yue)>0)
			return Util.SetMap("该车辆当天时间已有预约！", false, response);
		try{
			int count=y.getId()==null?yueService.Add(y):yueService.Edit(y);
			if(count>0)
				return Util.SetMap("预约成功！", true, response);
			else
				return Util.SetMap("预约失败！", false, response);
		} catch (Exception e) {
			return Util.SetMap(e.getMessage(), false, response);
		} 

	}

	@RequestMapping("/client_list")
	public String Get(Yue y) {
		Client client=(Client)request.getSession().getAttribute("client");
		y.setClientId(client.getId());
		
		Pages page=new Pages();
		page.setPagesize(10);
		int startindex=request.getParameter("startindex")==null?0:Integer.parseInt(request.getParameter("startindex"));
		page.setStartindex(startindex);

		y.setPage(page);
		try {
			List<Yue> list = yueService.Get(y);//根据车牌获取用户所有的停车记录信息
			for(int i=0;i<list.size();i++)
			{
				CarStop t=carstopService.GetByID(list.get(i).getCarstopId());
				list.get(i).setCarstop(t);
				Car car=carService.GetByID(list.get(i).getCarId());
				Client c=clientService.GetByID(car.getClientId());
				car.setClient(c);
				list.get(i).setCar(car);
			}
			request.setAttribute("list", list);
			return "client/yue";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
	@RequestMapping("/admin_list")
	public String admin_list(Yue s) {
		//分页参数设置
		Pages p=new Pages();
		p.setPagesize(10);//每页显示数量 
		int startindex=request.getParameter("startindex")==null?0:Integer.parseInt(request.getParameter("startindex"));//起始页，默认从第1页开始读
		p.setStartindex(startindex);
		s.setPage(p);
		try {
			List<Yue> list = yueService.Get(s);
			for(int i=0;i<list.size();i++)
			{
				CarStop t=carstopService.GetByID(list.get(i).getCarstopId());
				list.get(i).setCarstop(t);
				Car car=carService.GetByID(list.get(i).getCarId());
				Client c=clientService.GetByID(car.getClientId());
				car.setClient(c);
				list.get(i).setCar(car);
			}
			request.setAttribute("list", list);
			//分页
			request.setAttribute("pages", PageList.Page(request, yueService.GetCount(s), 
					p.getPagesize(), p.getStartindex(),request.getQueryString()));
			return "admin/yue";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
	@RequestMapping("/del")
	public String Del(int id) {
		if(yueService.Del(id)>0)
			return Util.SetMap("取消预约成功", true, response);
		else
			return Util.SetMap("取消预约失败", false, response);
	}
	
	
}