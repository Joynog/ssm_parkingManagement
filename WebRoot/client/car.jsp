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
			               <td>车辆名称</td>
			               <td>车牌号码</td>
			               <td>操作</td>
		                </tr>
                		<c:forEach var="list"  items="${list}">
		                <tr class="rows">
			                <td>${list.type}</td>
			                <td>${list.mid }</td>
			                
			                <td>
			                	<a href="${basePath }car/addpage.do?id=${list.id}" class="zu" title="${list.id }">编辑</a>
				                <a href="javascript:;;" class="del" id="${list.id }">删除</a>
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
