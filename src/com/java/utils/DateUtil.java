package com.java.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DateUtil {
	
	/**
	 * 日期格式化
	 * @param date
	 * @param format
	 * @return
	 */
	public static String DateToString(Date date, String format) {
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if (date != null) {
			result = sdf.format(date);
		}
		return result;
	}
	
	/**
	 * 格式化字符串
	 * @param str
	 * @param format
	 * @return
	 * @throws ParseException 
	 * @throws Exception
	 */
	public static Date StringToDate(String str, String format) throws ParseException{
		if (StringUtil.isEmpty(str)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(str);
	}
	
	/**
	 * 获取当前格式化日期yyyy-MM-dd hh:mm:ss
	 * @return
	 */
	public static String GetNowString(String format){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	
	 
	 /**
	  * 时间前推或后推分钟,其中JJ表示分钟.
	  */
	 public static String AddMins(String sj1, int jj) {
	  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  String mydate1 = "";
	  try {
	   Date date1 = format.parse(sj1);
	   long Time = (date1.getTime() / 1000) + jj * 60;
	   date1.setTime(Time * 1000);
	   mydate1 = format.format(date1);
	  } catch (Exception e) {
	  }
	  return mydate1;
	 }
	 
	 /**
	  * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
	  */
	 public static String AddDay(String nowdate, String delay) {
	  try{
	  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	  String mdate = "";
	  Date d = StringToDate(nowdate,"yyyy-MM-dd");
	  long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24 * 60 * 60;
	  d.setTime(myTime * 1000);
	  mdate = format.format(d);
	  return mdate;
	  }catch(Exception e){
	   return "";
	  }
	 }
	 
	 /**
	  * 两个时间之间的天数
	  * 
	  * @param date1
	  * @param date2
	  * @return
	  */
	 public static long getDays(String date1, String date2) {
	  if (date1 == null || date1.equals(""))
	   return 0;
	  if (date2 == null || date2.equals(""))
	   return 0;
	  // 转换为标准时间
	  SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
	  java.util.Date date = null;
	  java.util.Date mydate = null;
	  try {
	   date = myFormatter.parse(date1);
	   mydate = myFormatter.parse(date2);
	  } catch (Exception e) {
	  }
	  long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
	  return day;
	 }
	 
	 /**
	  * 两个时间之间的小时
	  * 
	  * @param date1
	  * @param date2
	  * @return
	  */
	 public static long getHours(String date1, String date2) {
	  if (date1 == null || date1.equals(""))
	   return 0;
	  if (date2 == null || date2.equals(""))
	   return 0;
	  // 转换为标准时间
	  SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  java.util.Date date = null;
	  java.util.Date mydate = null;
	  try {
	   date = myFormatter.parse(date1);
	   mydate = myFormatter.parse(date2);
	  } catch (Exception e) {
	  }
	  long hours = (date.getTime() - mydate.getTime()) / (60 * 60 * 1000);
	  return hours;
	 }
	 
	 /**
	  * 两个时间之间的分钟
	  * 
	  * @param date1
	  * @param date2
	  * @return
	  */
	 public static long getMinutes(String date1, String date2) {
	  if (date1 == null || date1.equals(""))
	   return 0;
	  if (date2 == null || date2.equals(""))
	   return 0;
	  // 转换为标准时间
	  SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  java.util.Date date = null;
	  java.util.Date mydate = null;
	  try {
	   date = myFormatter.parse(date1);
	   mydate = myFormatter.parse(date2);
	  } catch (Exception e) {
	  }
	  long mins = (date.getTime() - mydate.getTime()) / (60 * 1000);
	  return mins;
	 }
	 
	 /**
	  * 取得数据库主键 生成格式为yyyymmddhhmmss+k位随机数
	  * 
	  * @param k
	  *            表示是取几位随机数，可以自己定
	  */

	 public static String getNo(int k) {

	  return GetNowString("yyyyMMddhhmmss") + getRandom(k);
	 }

	 /**
	  * 返回一个随机数
	  * 
	  * @param i
	  * @return
	  */
	 public static String getRandom(int i) {
	  Random jjj = new Random();
	  // int suiJiShu = jjj.nextInt(9);
	  if (i == 0)
	   return "";
	  String jj = "";
	  for (int k = 0; k < i; k++) {
	   jj = jj + jjj.nextInt(9);
	  }
	  return jj;
	 }
	
}
