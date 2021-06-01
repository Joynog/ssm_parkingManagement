<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	pageContext.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html>
<head>

<link href="css/login.css" rel="stylesheet" type="text/css" />

<script src="<%=basePath %>js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="<%=basePath %>js/layer/layer.js" type="text/javascript"></script>
<script src="<%=basePath %>js/layui/layui.js"  type="text/javascript"></script>
<link rel="stylesheet" href="<%=basePath %>js/layui/css/layui.css" />

<title>登录页面</title>
</head>
<body>
	<form class="layui-form" action="">
	    <div id="loginborder">
        <table cellpadding="0" cellspacing="0">
            <tr>
                <td><input type="text" name="login" class="val" lay-verify="required" /></td>
            </tr>
            <tr>
                <td><input type="password" name="pwd" class="val" lay-verify="required" /></td>
            </tr>
            <tr>
                <td>
					<input name="author" value="${basePath}admin/login.do" checked="" type="radio"  title="管理员" />
      				<input name="author" value="${basePath}client/login.do" type="radio"  title="用户" />
				</td>
            </tr>
        </table>
        <input type="submit" class="btn" lay-submit="" lay-filter="demo1" value="立即登陆" />
        <input type="button" id="regbtn" class="btn2" value="立即注册" />
        <input type="button" id="stopbtn" class="btn3" value="现实模拟" />
    </div>
	</form>

<script>
layui.use(['form', 'layedit', 'laydate'], function(){
  var form = layui.form
  ,layer = layui.layer
  ,layedit = layui.layedit
  ,laydate = layui.laydate;
   
   //监听提交
     form.on('submit(demo1)', function(data){
       $.post($("input[name='author']:checked").val(),data.field,function(result){
    	   if (result.success) {
				if("${basePath}admin/login.do"==$("input[name='author']:checked").val())
					location.href='${basePath}client/admin_list.do';
				else
					location.href='${basePath}introduce/show.do?id=1';
			} else {
				layer.alert(result.mgf);
			}
    	   
       },"json");
       return false;
     });
   
   $("#stopbtn").click(function(){
	 //prompt层
	   layer.prompt({title: '请输入车牌号', formType: 0}, function(mid, index){
	     layer.close(index);
	     $.post("${basePath}stop/add.do",{mid:mid},function(result){
		    	if (result.success) {
		    		if(result.code==0)
		    			layer.alert(result.mgf);
		    		else
						layer.alert(result.mgf, function () {
							////
				        }); 
				} else {
					layer.alert(result.mgf);
				}
		    },"json");
	     return false;
	   });
   })
     
});

 
</script>

  	
      	 <script>
    $(function(){ 
    	$("#regbtn").click(function(){
    		layer.open({
    		  type: 2,
    		  title: '用户注册',
    		  shadeClose: true,
    		  shade: 0.8,
    		  area: ['480px', '550px'],
    		  content: '${basePath}reg.jsp' //iframe的url
    		}); 
    	});
    })
    </script>
</body>
</html>