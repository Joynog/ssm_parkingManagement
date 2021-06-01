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
	  		            <td class="left">车辆型号：</td>
	  		            <td class="right">
							<input type="text" name="type" lay-verify="required" autocomplete="off" class="layui-input"  value="${add.type }">
						</td>
	  	            </tr>
	  	            <tr>
	  		            <td class="left">车牌号码：</td>
	  		            <td class="right">
							<input type="text" name="mid" lay-verify="required" autocomplete="off" class="layui-input"  value="${add.mid }">
						</td>
	  	            </tr>
	  	            
	  	            <tr>
	  		            <td class="left">&nbsp;</td>
	  		            <td class="right"><input class="layui-btn layui-btn-normal" lay-submit  lay-filter="demo1"  type="submit" value="提交"></input></td>
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
     
	   ///监听提交
	  form.on('submit(demo1)', function(data){
	    $.post("${basePath}car/add.do?id=${add.id}",data.field,function(result){
	    	if (result.success) {
				layer.alert(result.mgf, function () {
					location.href='${basePath}car/client_list.do';
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
