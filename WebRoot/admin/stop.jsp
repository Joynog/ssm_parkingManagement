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
			               <td>车牌号码</td>
			               <td>所停车位</td>
			               <td>停车时间</td>
			               <td>停留时长</td>
			               <td>停车费用</td>
		                </tr>
                		<c:forEach var="list"  items="${list}">
		                <tr class="rows">
			                <td>${list.mid}</td>
			                <td>${list.carstop.name }</td>
			                <td><fmt:formatDate value="${list.starttime }" pattern="MM-dd HH:mm"/></td>
			                <td>${Math.floor(list.mins/60) }小时${list.mins%60 }分钟</td>
			                <td>${list.money }元</td>

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
    		$.post("${basePath}car/del.do",{id:id},function(result){
    	    	if (result.success) {
    				layer.alert(result.mgf, function () {
    					location.reload();
    		        }); 
    			} else {
    				layer.alert(result.mgf);
    			}
    	    },"json");
    	});
    	
    	
    })
    </script>
  </body>
</html>
