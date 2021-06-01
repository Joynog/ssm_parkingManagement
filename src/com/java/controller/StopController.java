package com.java.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.model.*;
import com.java.service.*;
import com.java.utils.DateUtil;
import com.java.utils.PageList;
import com.java.utils.ResponseUtil;;

@Controller
@RequestMapping("/stop")
public class StopController {
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
	}
		
	@Autowired
	private CarService carService;
	@Autowired
	private StopService stopService;
	@Autowired
	private CarStopService carstopService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;

	@RequestMapping("/addpage")
	public String addpage() {
		return "card";
	}
	
	@RequestMapping("/add")
	public String add(Stop s) {
		int price=2;//单价设置为2元一小时
		Stop ss=new Stop();
		ss.setBack(0);
		ss.setMid(s.getMid());
		List<Stop> stops=stopService.Get(ss);
		if(stops.size()>0)//如果是扫牌离开
		{
			try
			{
				Stop stop=stops.get(0);
				String starttime=DateUtil
						.DateToString(stop.getStarttime(), "yyyy-MM-dd HH:mm:ss");//进入时间
				String intime=DateUtil
						.GetNowString("yyyy-MM-dd HH:mm:ss");//现在实际时间
				int mins=(int) DateUtil
						.getMinutes(intime, starttime);//停留时差
				stop.setMins(mins);
				mins=mins<15?0:mins;//如果停留在15分钟以内免费不计时
				int hour=mins/60;
				if(stop.getMins()%60>0)
					hour+=1;
				stop.setMoney(hour*price);
				if(stopService.Edit(stop)>0)
					return Util.SetMap(0,String.format(
							"模拟车牌扫描成功，您一共停了%d小时%d分钟，共计%d元！",
							mins/60,
							stop.getMins()%60,
							stop.getMoney()),
							true,
							response);
				else
					return Util.SetMap("扫牌失败！", false, response);
			} catch (Exception e) {
				return Util.SetMap(e.getMessage(), false, response);
			} 
		}
		else
		{
			try{
				List<CarStop> c=carstopService.GetEmpty(null);
				if(c.size()==0)
					return Util.SetMap("车位已停满，请等待车位空闲再进！", false, response);
				s.setCarstopId(c.get(0).getId());
				
				if(stopService.Add(s)>0)
					return Util.SetMap(0,String.format("模拟车牌扫描成功，欢迎您尊敬的：%s车主，请把车停在%s车位上！",s.getMid(),c.get(0).getName()), true, response);
				else
					return Util.SetMap("扫牌失败！", false, response);
			} catch (Exception e) {
				return Util.SetMap(e.getMessage(), false, response);
			} 
		}
	}

	@RequestMapping("/client_list")
	public String Get() {
		Client client=(Client)request.getSession().getAttribute("client");
		Car c=new Car();
		c.setClientId(client.getId());
		String clientmid="";
		List<Car> cs=carService.Get(c);//获取用户拥有的所有车牌信息
		for(int i=0;i<cs.size();i++)
			clientmid+=String.format(" mid='%s' or", cs.get(i).getMid());
		clientmid=clientmid.length()>0?"("+clientmid.substring(0,clientmid.length()-2)+")":"";
		
		
		Pages page=new Pages();
		page.setPagesize(10);
		int startindex=request.getParameter("startindex")==null?0:Integer.parseInt(request.getParameter("startindex"));
		page.setStartindex(startindex);
		
		Stop s=new Stop();
		s.setPage(page);
		s.setClientmid(clientmid);
		try {
			List<Stop> list = stopService.Get(s);//根据车牌获取用户所有的停车记录信息
			for(int i=0;i<list.size();i++)
			{
				CarStop t=carstopService.GetByID(list.get(i).getCarstopId());
				list.get(i).setCarstop(t);
			}
			request.setAttribute("list", list);
			return "client/stop";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
	@RequestMapping("/admin_list")
	public String admin_list(Stop s) {
		//分页参数设置
		Pages p=new Pages();
		p.setPagesize(10);//每页显示数量 
		int startindex=request.getParameter("startindex")==null?0:Integer.parseInt(request.getParameter("startindex"));//起始页，默认从第1页开始读
		p.setStartindex(startindex);
		s.setPage(p);
		try {
			List<Stop> list = stopService.Get(s);
			for(int i=0;i<list.size();i++)
			{
				CarStop t=carstopService.GetByID(list.get(i).getCarstopId());
				list.get(i).setCarstop(t);
			}
			request.setAttribute("list", list);
			//分页
			request.setAttribute("pages", PageList.Page(request, stopService.GetCount(s), 
					p.getPagesize(), p.getStartindex(),request.getQueryString()));
			return "admin/stop";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
	@RequestMapping("/sum_bak")
	public String Sum_bak() throws ParseException {
		@SuppressWarnings("deprecation")
		int years=new Date().getYear()+1900;
		String months="";
		String money="";
		for(int i=1;i<=12;i++)
		{
			//Sum s=new Sum();
			Stop stop=new Stop();
			stop.setStarttime(DateUtil.StringToDate(String.format("%d-%d-1", years,i), "yyyy-MM-dd"));
			
			months+=String.format("'%d-%d',", years,i);
			money+=String.valueOf(stopService.GetSum(stop))+",";
		}
		money=money.length()>0?money.substring(0,money.length()-1):"";
		months=months.length()>0?months.substring(0,months.length()-1):"";
		Map<String,Object> jsonitem = new HashMap<String,Object>();
		jsonitem.put("money", money);
		jsonitem.put("months", months);
		ResponseUtil.write(response, new JSONObject(jsonitem).toString());
		return null;

		
	}
	
	@RequestMapping("/sum")
	public String sum() throws ParseException {
		@SuppressWarnings("deprecation")
		int years=new Date().getYear()+1900;
		List<Sum> sum=new ArrayList<Sum>();
		for(int i=1;i<=12;i++)
		{
			Sum s=new Sum();
			s.setMonth(i+"月");
			Stop stop=new Stop();
			stop.setStarttime(DateUtil.StringToDate(String.format("%d-%d-1", years,i), "yyyy-MM-dd"));
			s.setMoney(stopService.GetSum(stop));
			sum.add(s);
		}
		request.setAttribute("sum", sum);
		return "admin/sum";
	}
	
}