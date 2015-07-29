<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    Date date = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd hh:mm:ss");
    String lastLoginTime = simpleDateFormat.format(date);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <title>Subscribe-管理员后台</title>
    <%@ include file="../../common/common.jsp"%>

    <script type="text/javascript">
        $(function() {
            initMenu();
        });

    </script>

</head>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="">
    <div class="header">
        <div class="hgroup">
            <b style="font-size: 30px">Subscribe-管理员后台</b>
        </div>
        <div class="clear"></div>
        <div class="secondary_bar">
            <div class="user user_noshadow">
                <p><s:property value="#session.superAdmin_realname"/> (上次登录:<%=lastLoginTime%>)</p>
            </div>
            <div style="float: right;padding-top: 3px; margin-right: 30px;">
                <a id="btn" href="javascript:void(0)"  class="easyui-linkbutton">更新主页</a>
            </div>
        </div>
    </div>
    <div class="clear"></div>
</div>
<div data-options="region:'west', split:'false'" style="width:240px;">
    <div class="easyui-accordion" data-options="fit:false,border:false">
        <div title="开发者管理">
            <ul class="wg_menu">
                <li><a href="system/admin/user-manage.jsp">开发者列表</a></li>
            </ul>
        </div>
        <div title="服务管理">
            <ul class="wg_menu">
                <li><a href="system/admin/not-audit-server-manage.jsp">未审核服务</a></li>
                <li><a href="system/admin/audit-server-manage.jsp">已经核服务</a></li>
                <li><a href="system/admin/server-manage.jsp">所有服务</a></li>
            </ul>
        </div>
        <div title="设备管理">
            <ul class="wg_menu">
                <li><a href="system/admin/device-manage.jsp">设备列表</a></li>
            </ul>
        </div>
        <div title="推送历史">
            <ul class="wg_menu">
                <li><a href="system/admin/article-manage.jsp">推送列表</a></li>
            </ul>
        </div>

        <div title="个人管理">
            <ul class="wg_menu">
                <li><a href="admin/system/super_admin/password-update.jsp">修改密码</a></li>
                <li><a href="superadmin/superadmin_loginOut">安全退出</a></li>
            </ul>
        </div>

    </div>
</div>
<div data-options="region:'center'">
    <iframe src="system/admin/main.jsp" id="wg_main" frameborder="0" style="width: 100%; height: 100%">
    </iframe>
</div>
</body>
</html>