<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String left_top = request.getContextPath();
String basePath_left = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+left_top+"/";
%>

<div class="menumain">
     <div><b>停车记录</b></div>
     <ul>
        <li><a href="<%=basePath_left %>car/addpage.do">添加车辆</a></li>
            <li><a href="<%=basePath_left %>car/client_list.do">我的车辆</a></li>
    </ul>
</div>


<div class="menumain">
     <div><b>停车记录</b></div>
     <ul>
        <li><a href="<%=basePath_left %>stop/client_list.do">停车记录</a></li>
    </ul>
</div>

<div class="menumain">
     <div><b>车位预约</b></div>
     <ul>
        <li><a href="<%=basePath_left %>yue/addpage.do">我要预约</a></li>
         <li><a href="<%=basePath_left %>yue/client_list.do">预约记录</a></li>
    </ul>
</div>

<div class="menumain">
     <div><b>个人中心</b></div>
     <ul>
     <li><a href="<%=basePath_left %>introduce/show.do?id=1">系统公告</a></li>
        <li><a href="<%=basePath_left %>client/messhow.do">我的信息</a></li>
            <li><a href="<%=basePath_left %>client/editpage.do">更新信息</a></li>
            <li><a href="<%=basePath_left %>client/pwdpage.do">修改密码</a></li>
            <li><a href="<%=basePath_left %>client/exit.do">退出</a></li>
    </ul>
</div>




