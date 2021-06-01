package com.java.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.java.model.*;
import com.java.service.*;

import com.java.utils.PageList;
import com.java.utils.ResponseUtil;;

@Controller
@RequestMapping("/carstop")
public class CarStopController {
	private CarStopService carstopService;

	public CarStopService getCarStopService() {
		return carstopService;
	}

	@Autowired
	public void setCarStopService(CarStopService carstopService) {
		this.carstopService = carstopService;
	}
	@Autowired
	private CarStopTypeService carstoptypeService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;

	@RequestMapping("/addpage")
	public String add(Integer id) {
		CarStop cs=id==null?new CarStop():carstopService.GetByID(id);
		request.setAttribute("carstoptype",carstoptypeService.Get());
		request.setAttribute("add", cs);
		return "admin/addcarstop";
	}
	
	@RequestMapping("/editpage")
	public String Edit(Model m) {
		try {
			int id=Integer.parseInt(request.getParameter("id"));
			CarStop r = carstopService.GetByID(id);
			m.addAttribute("carstop",r);
			m.addAttribute("carstoptype",carstoptypeService.Get());
			return "admin/addcarstop";
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping("/add")
	public String add(CarStop s) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			System.out.println(s);
			int count=0;
			if(s.getId()==null)
				count = carstopService.Add(s);
			else
				count=carstopService.Edit(s);
			if(count>0)
			{
				map.put("mgf", "操作成功");
				map.put("success", true);
			}
			else
			{
				map.put("mgf", "操作失败");
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
	public String Get(CarStop h) {
		//分页参数设置
		Pages p=new Pages();
		p.setPagesize(10);//每页显示数量 
		int startindex=request.getParameter("startindex")==null?0:Integer.parseInt(request.getParameter("startindex"));//起始页，默认从第1页开始读
		p.setStartindex(startindex);
		h.setPage(p);
		try {
			List<CarStop> list = carstopService.Get(h);
			for(int i=0;i<list.size();i++)
			{
				CarStopType tt=carstoptypeService.GetByID(list.get(i).getCarstoptypeId());
				list.get(i).setCarstoptype(tt);
			}
			System.out.println(list);
			request.setAttribute("list", list);
			//分页
			request.setAttribute("pages", PageList.Page(request, carstopService.GetCount(h), 
					p.getPagesize(), p.getStartindex(),request.getQueryString()));
			return "admin/carstop";
		} catch (Exception e) {
			return null;
		}
		
	}
	
	@RequestMapping("/shop_list")
	public String web_list(CarStop h) {
		//分页参数设置
		Pages p=new Pages();
		p.setPagesize(10);//每页显示数量 
		int startindex=request.getParameter("startindex")==null?0:Integer.parseInt(request.getParameter("startindex"));//起始页，默认从第1页开始读
		p.setStartindex(startindex);
		h.setPage(p);
		try {
			List<CarStop> list = carstopService.Get(h);
			for(int i=0;i<list.size();i++)
			{
				CarStopType tt=carstoptypeService.GetByID(list.get(i).getCarstoptypeId());
				list.get(i).setCarstoptype(tt);
			}
			System.out.println(list);
			request.setAttribute("list", list);
			//分页
			request.setAttribute("pages", PageList.Page(request, carstopService.GetCount(h), 
					p.getPagesize(), p.getStartindex(),request.getQueryString()));
			return "shop/carstop";
		} catch (Exception e) {
			return null;
		}
		
	}
	
	@RequestMapping("/del")
	public String Del(int id) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			System.out.println("================================");
			System.out.println(id);
			//int id=Integer.parseInt(request.getParameter("id"));
			
			int r = carstopService.Del(id);
			
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
	
	
}