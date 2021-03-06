<%@page import="com.java.service.IntroduceService"%>
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
    <title>My JSP 'index.jsp' starting page</title>
    <link href="<%=basePath %>admin/css/css.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath %>js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="<%=basePath %>js/layer/layer.js" type="text/javascript"></script>
<script src="<%=basePath %>js/layui/layui.js"  type="text/javascript"></script>
<link rel="stylesheet" href="<%=basePath %>js/layui/css/layui.css" />
<script type="text/javascript" src="<%=basePath %>js/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
  </head>
  
  <body>
   
                
                <form id="reg" class="layui-form"  method="post" style="padding-top:20px; padding-right:20px">
           <div class="layui-form-item">
    <label class="layui-form-label">姓名</label>
    <div class="layui-input-block">
      <input name="name" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input" type="text">
    </div>
  </div>     
  <div class="layui-form-item">
    <label class="layui-form-label">用户名</label>
    <div class="layui-input-block">
      <input name="login" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input" type="text">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">密码</label>
    <div class="layui-input-block">
      <input name="pwd" id="pwd" lay-verify="pwd"  placeholder="请输入" autocomplete="off" class="layui-input" type="password">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">确认密码</label>
    <div class="layui-input-block">
      <input name="twopwd" id="twopwd" lay-verify="twopwd" placeholder="请输入" autocomplete="off" class="layui-input" type="password">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">性别</label>
    <div class="layui-input-block">
      <input name="sex" value="男" title="男" checked="" type="radio">
      <input name="sex" value="女" title="女" type="radio">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">联系电话</label>
    <div class="layui-input-block">
      <input name="tel"  lay-verify="required|phone" placeholder="请输入" autocomplete="off" class="layui-input" type="text">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">地址</label>
    <div class="layui-input-block">
      <input name="address" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input" type="text">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">邮箱</label>
    <div class="layui-input-block">
      <input name="mail" lay-verify="email" placeholder="请输入" autocomplete="off" class="layui-input" type="text">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">&nbsp;</label>
    <div class="layui-input-block">
      <input class="layui-btn layui-btn-normal" id="sub" type="submit" lay-filter="demo1"  lay-submit value="注册"></input>
    </div>
  </div>
  </form>
                

    
        	<script type="text/javascript">
//Demo
layui.use(['form', 'layedit', 'laydate','layer'], function(){
     var form = layui.form
     ,layedit = layui.layedit
     ,laydate = layui.laydate
     ,layer=layui.layer;
     
   //自定义验证规则
     form.verify({
       pwd: [/(.+){6,12}$/, '密码必须6到12位']
       ,twopwd: function(value){
         if($("#pwd").val()!=value)
           return "两次输入的密码不一至";
       }
     });
   
   //监听提交
     form.on('submit(demo1)', function(data){
	       $("#reg").form("submit",{
				url : "${basePath}client/client_add.do",
				onSubmit : function() { },
				success : function(result) {
					var result = eval('(' + result + ')');
				    if (result.success) {
				    	layer.alert(result.mgf, function () {
				    		parent.layer.closeAll();
				        }); 
						
					} else {
						layer.alert(result.mgf);
						return;
					}  
				}
			});  
    		 return false;
    	}) 
});

 
</script>

    
  </body>
</html>
