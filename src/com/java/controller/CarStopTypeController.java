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
import org.springframework.web.bind.annotation.RequestParam;

import com.java.model.*;
import com.java.service.*;
import com.java.utils.ResponseUtil;;

@Controller
@RequestMapping("/carstoptype")
public class CarStopTypeController {
	private CarStopTypeService carstoptypeService;

	public CarStopTypeService getCarStopTypeService() {
		return carstoptypeService;
	}

	@Autowired
	public void setCarStopTypeService(CarStopTypeService carstoptypeService) {
		this.carstoptypeService = carstoptypeService;
	}

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;

	@RequestMapping("/addpage")
	public String add() {
		return "admin/addcarstoptype";
	}
	
	@RequestMapping("/add")
	public String add(CarStopType q) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			System.out.println(q);
			String id=request.getParameter("id").trim().length()==0?"0":request.getParameter("id");
			q.setId(Integer.parseInt(id));
			int count=0;
			if(q.getId()==0)
				count = carstoptypeService.Add(q);
			else
				count=carstoptypeService.Edit(q);
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
	public String Get() {
		try {
			List<CarStopType> list = carstoptypeService.Get();
			System.out.println(list);
			request.setAttribute("list", list);
			return "admin/carstoptype";
		} catch (Exception e) {
			return null;
		}
		
	}
	
	@RequestMapping("/web_list")
	public String Get2() {
		try {
			List<CarStopType> list = carstoptypeService.Get();
			System.out.println(list);
			request.setAttribute("list", list);
			return "carstoptype";
		} catch (Exception e) {
			return null;
		}
		
	}
	
	@RequestMapping("/editpage")
	public String GetByID() {
		try {
			int id=Integer.parseInt(request.getParameter("id"));
			CarStopType q = carstoptypeService.GetByID(id);
			request.setAttribute("carstoptype", q);
			return "admin/addcarstoptype";
		} catch (Exception e) {
			return null;
		}
	}
	

	@RequestMapping("/del")
	public String Del(@RequestParam(value = "id") int id) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			System.out.println("================================");
			System.out.println(id);
			//int id=Integer.parseInt(request.getParameter("id"));
			
			int r = carstoptypeService.Del(id);
			
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