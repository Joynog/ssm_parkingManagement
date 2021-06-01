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
  <style>
  	#datatable {
	border: 1px solid #ccc;
	border-collapse: collapse;
	border-spacing: 0;
	font-size: 12px;
}
td,th {
	border: 1px solid #ccc;
	padding: 4px 20px;
}
  </style>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>

    <script src="${basePath }js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="${basePath }js/highcharts/code/highcharts.js"></script>

<script src="${basePath }js/highcharts/code/modules/exporting.js"></script>
<script src="${basePath }js/highcharts/code/modules/data.js"></script>

  		
  </head>
  
  <body>
  <%@ include file="top.jsp" %>
    <table cellpadding="0" cellspacing="0" id="maintable">
        <tr>
            <td class="lefttd">
                <%@ include file="left.jsp" %> 
            </td>
            <td>
  		

		

  		

<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

<table id="datatable" class="layui-hide">
<thead>
		                <tr>
		                <th>月份</th>
		                <th>收费总额<th>
		                </tr>
		                </thead>
		                <tbody>
                		<c:forEach var="list"  items="${sum}">
		                <tr class="rows">
		                <td>${list.month}</td>
		                <th>${list.money}</th>
		                </tr>
		                
		                </c:forEach>
		                </tbody>
	                </table>
  		
  		
  	</td>
            <td class="mainrightborder"></td>
        </tr>
    </table>
    
		<script>
		var chart = Highcharts.chart('container', {
			data: {
				table: 'datatable'
			},
			chart: {
				type: 'column'
			},
			title: {
				text: '停车场收费统计'
				// 该功能依赖 data.js 模块，详见https://www.hcharts.cn/docs/data-modules
			},
			yAxis: {
				allowDecimals: false,
				title: {
					text: '元',
					rotation: 0
				}
			},
			tooltip: {
				formatter: function () {
					return '<b>' + this.series.name + '</b><br/>' +
					this.point.name.toLowerCase()+"月收费："+this.point.y + ' 元';
				}
			}
		});
		</script>
    <%@ include file="foot.jsp"%>

  </body>
</html>
