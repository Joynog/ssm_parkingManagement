<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
  		<form class="layui-form" id="sqlform" method="post">
  		<table cellpadding="0" cellspacing="0" id="addtable"> 
  					<tr>
	  		            <td class="left">登陆名：</td>
	  		            <td class="right">${client.login }</td>
	  	            </tr>
	  	            <tr>
	  		            <td class="left">姓名：</td>
	  		            <td class="right">${client.name }</td>
	  	            </tr>
	  	            <tr>
	  		            <td class="left">性别：</td>
	  		            <td class="right">${client.sex }</td>
	  	            </tr>
	  	            <tr>
	  		            <td class="left">电话：</td>
	  		            <td class="right">${client.tel }</td>
	  	            </tr>
	  	            <tr>
	  		            <td class="left">地址：</td>
	  		            <td class="right">${client.address }</td>
	  	            </tr>
	  	            <tr>
	  		            <td class="left">邮箱：</td>
	  		            <td class="right">${client.mail }</td>
	  	            </tr>
	  	           
	  	            <tr>
	  		            <td class="left">&nbsp;</td>
	  		            <td class="right"><input class="layui-btn layui-btn-normal"  type="button" onclick="location.href='${basePath}client/editpage.do'" value="修改资料"></input></td>
	  	            </tr>
	  	        </table>
	  	  </form>
  	</td>
            <td class="mainrightborder"></td>
        </tr>
    </table>
    <%@ include file="foot.jsp"%>

    
  </body>
</html>
