<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("basePath", basePath);
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
</head>

  </head>
  
  <body>
  	<%@ include file="top.jsp"%>
  	<table cellpadding="0" cellspacing="0" id="maintable">
        <tr>
            <td class="lefttd"><%@ include file="left.jsp"%></td>
        <td>
  		<table id="sqltable">
		                <tr class="title">
			               <td>车位名称</td>
			               <td>预约日期</td>
			               <td>预约时间段</td>
			               <td>车辆</td>
			               <td>车牌号</td>
			               <td>操作</td>
		                </tr>
                		<c:forEach var="list"  items="${list}">
		                <tr class="rows">
			                <td>${list.carstop.name}</td>
			                <td><fmt:formatDate value="${list.starttime }" pattern="MM-dd"/></td>
			                <td>${list.timetype}</td>
			                <td>${list.car.type}</td>
			                <td>${list.car.mid}</td>
			                <td>
				                <a href="#" class="del" id="${list.id }">取消预约</a>
			                </td>
		                </tr>
		                </c:forEach>
	                </table>
  	</td>
            <td class="mainrightborder"></td>
        </tr>
    </table>
    <%@ include file="foot.jsp"%>
  	
      	 <script>
    $(function(){ 
    	
    	$(".del").click(function(){
    		var id = $(this).attr("id");
    		layer.confirm("确定取消本次预约？",{btn:["确定","取消"]},function(){
	    		$.post("${basePath}yue/del.do",{id:id},function(result){
	    			if (result.success) {
	    				layer.alert(result.mgf, function () {
	    					location.reload();
	    				}); 
	    			} else {
	    				layer.alert(result.mgf);
	    			}
	    		 },"json");
    		})
    	});
    	
    })
    </script>
  </body>
</html>
