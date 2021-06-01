<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	  		            <td class="left">车辆：</td>
	  		            <td class="right">
							<select name="carId" lay-filter="aihao">
							<c:forEach var="list"  items="${car}">
						        <option value="${list.id }">${list.type }(${list.mid})</option>
						        </c:forEach>
						      </select>
						</td>
	  	            </tr>
	  	            <tr>
	  		            <td class="left">车位：</td>
	  		            <td class="right">
							<select name="carstopId" lay-filter="aihao">
							<c:forEach var="list"  items="${carstop}">
						        <option value="${list.id }">${list.name }</option>
						        </c:forEach>
						      </select>
						</td>
	  	            </tr>
	  	            <tr>
	  		            <td class="left">预定日期：</td>
	  		            <td class="right">
							<input type="text" name="starttime" id="starttime" lay-verify="required" autocomplete="off" class="layui-input"  value="<fmt:formatDate value="${add.starttime }" pattern="yyyy-MM-dd"/>">
						</td>
	  	            </tr>
	  	            <tr>
	  		            <td class="left">停留时段：</td>
	  		            <td class="right">
							<select name="timetype" lay-filter="aihao">
						        <option value="上午">上午</option>
						        <option value="下午">下午</option>
						        <option value="晚上">晚上</option>
						      </select>
						</td>
	  	            </tr>
	  	           
	  	            <tr>
	  		            <td class="left">&nbsp;</td>
	  		            <td class="right"><input class="layui-btn layui-btn-normal" lay-submit  lay-filter="demo1"  type="submit" value="预约车位"></input></td>
	  	            </tr>
	  	        </table>
	  	  </form>
  	</td>
            <td class="mainrightborder"></td>
        </tr>
    </table>
    <%@ include file="foot.jsp"%>
<script type="text/javascript">
//Demo
layui.use(['form', 'layedit', 'laydate','layer'], function(){
     var form = layui.form
     ,layedit = layui.layedit
     ,laydate = layui.laydate
     ,layer=layui.layer;
     
   //常规用法
     laydate.render({
       elem: '#starttime',
       min:1
     });
   
     ///监听提交
	  form.on('submit(demo1)', function(data){
	    $.post("${basePath}yue/add.do?id=${add.id}",data.field,function(result){
	    	if (result.success) {
				layer.alert(result.mgf, function () {
					location.href='${basePath}yue/client_list.do';
		        }); 
			} else {
				layer.alert(result.mgf);
			}
	    },"json");
	    return false;
	  });
});

 
</script>

    
  </body>
</html>
