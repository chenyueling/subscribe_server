<%--
  Created by IntelliJ IDEA.
  User: chenyueling
  Date: 2015/5/22
  Time: 8:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:jsp="http://www.w3.org/1999/html">
<head>
    <title>Subscribe-开发者后台</title>
    <base href="<%=basePath%>">
    <%@ include file="../common/common.jsp" %>

</head>
<body id="login">
<div id="login-wrapper" class="png_bg">
    <div id="login-top">
        <h1>
            Subscribe-开发者后台
        </h1>
        <!-- Logo (221px width) -->
    </div>
    <!-- End #logn-top -->
    <div class="wg_input" id="login-content">
        <form id="login_form" action="student/student_login" method="post">
            <p>
                <label>用户名</label>
                <input class="text-input" type="text" name="username" id="username"/>
            </p>

            <div class="clear"></div>
            <p>
                <label>密码</label>
                <input class="text-input" type="password" id="password" name="password"/>
            </p>

            <div class="clear"></div>
            <p>
                <input id="login_submit" class="button" type="button" style="height:30px;width:50px;margin-top: 0px;"
                       value="登 录"/>
            </p>
        </form>
    </div>
    <!-- End #login-content -->
</div>
<!-- End #login-wrapper -->
</body>
</html>

<script type="text/javascript">
    $(function () {
//        var url = top.location.href;
//        if (url.indexOf("login") < 0) {
//            top.location.href = "admin/system/super_login.jsp";
//        }
//        initLoginForm('#login_form', '/jersey-jetty/rest/admins/login',  function () {
//            window.location.href = "admin/system/super_admin/index.jsp";
//        });

        $('#login_submit').click(function(){
            var url = top.location.href;
            var data = {
                username:  $("input[name='username']").val(),
                password:  $("input[name='password']").val()
            };
            console.log(data);
            var options = {
                type: 'POST',
                url: '/jersey-jetty/rest/users/login',
                contentType: 'application/json',
                data: JSON.stringify(data) ,
                beforeSubmit: function () {

                    $.messager.progress({
                        title: '用户登录',
                        msg: '登录中...'
                    });
                },
                success: function (json){
                    $.messager.progress('close');
                    console.log("success function")
                    if (!json) {
                        $.messager.alert('用户登录', '未知异常,请联系管理员');
                        return;
                    }
                    if (json.result != "success") {
                        $("input[name='password']").val('')
                        if(json.status == 'success') {
                            window.location.href = '/jersey-jetty/system/developer/index.jsp';
                        }
                        else {
                            $.messager.alert('用户登录', json.data);
                            refreshCode();
                        }
                        return;
                    } else {
                        if (callback) {
                            callback();
                        }
                    }
                },
                error: function (data) {
                    $.messager.progress('close');
                    $("input[name='password']").val('');
                    $("input[name='validateCode']").val('');
                    $.messager.alert('用户登录', '未知异常,请联系开发者');
                },
                dataType: 'json'
            }

//            $.post( '/jersey-jetty/rest/admins/login', 'application/json',data, function (data){
//
//                console.log(data);
//            },'json');

            $.ajax(options)

        })

    });


</script>
    