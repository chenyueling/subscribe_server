<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<%@ include file="../../common/common.jsp" %>
  </head>
  
  <body style="padding-left: 100px;padding-top: 150px;">
    <h2>Subscribe-开发者后台</h2> <br>
  </body>
</html>
