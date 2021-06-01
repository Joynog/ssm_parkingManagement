<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String left_top = request.getContextPath();
String basePath_left = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+left_top+"/";
%>
<div class="menumain">
     <div><b>车位管理</b></div>
     <ul>
        <li><a href="<%=basePath_left %>carstop/addpage.do">添加车位</a></li>
        <li><a href="<%=basePath_left %>carstop/admin_list.do">车位管理</a></li>
    </ul>
</div>

<div class="menumain">
     <div><b>用户管理</b></div>
     <ul>
        <li><a href="<%=basePath_left %>client/admin_list.do">用户查看</a></li>
    </ul>
</div>

<div class="menumain">
     <div><b>车位分类管理</b></div>
     <ul>
        <li><a href="<%=basePath_left %>carstoptype/addpage.do">添加车位分类</a></li>
        <li><a href="<%=basePath_left %>carstoptype/admin_list.do">车位分类管理</a></li>
    </ul>
</div>

<div class="menumain">
     <div><b>停车收费管理</b></div>
     <ul>
        <li><a href="<%=basePath_left %>stop/admin_list.do">停车收费记录</a></li>
        <li><a href="<%=basePath_left %>stop/sum.do">停车统计</a></li>
    </ul>
</div>

<div class="menumain">
     <div><b>预约管理</b></div>
     <ul>
        <li><a href="<%=basePath_left %>yue/admin_list.do">预约管理</a></li>
    </ul>
</div>

<div class="menumain">
     <div><b>系统中心</b></div>
     <ul>
        <li><a href="<%=basePath_left %>introduce/introduce_list.do">网站信息</a></li>
            <li><a href="<%=basePath_left %>admin/exit.do">退出</a></li>
    </ul>
</div>

