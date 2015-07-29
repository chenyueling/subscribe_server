<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <base href="<%=basePath%>">
  <%@ include file="../../common/common.jsp"%>

  <script src="js/adminUI.js" type="text/javascript"></script>

</head>

<body>

<form id="updatePasswordForm" action="/rest/users/update_password" method="post">
  <table width="100%">
    <tr>
      <td class="flabel">
        旧密码:
      </td>
      <td class="finpur">
        <input type="password" name="password" style="width: 200px;"
               class="easyui-validatebox" validType="length[2, 15]"
               required="true" />
      </td>
    </tr>
    <tr>
      <td class="flabel">
        新密码:
      </td>
      <td class="finpur">
        <input type="newpassword" name="" style="width: 200px;"
               class="easyui-validatebox" validType="length[6, 20]"
               required="true" />
      </td>
    </tr>
    <tr>
      <td class="flabel">
        确认密码:
      </td>
      <td class="finpur">
        <input type="repassword" name="" style="width: 200px;"
               class="easyui-validatebox" validType="length[6, 20]"
               required="true" />
      </td>
    </tr>
    <tr>
      <td>
        <a href="javascript:void(0)" class="easyui-linkbutton"
           iconCls="icon-ok" onclick="javascript:$('#updatePasswordForm').submit()">保存</a>
      </td>
    </tr>
  </table>
</form>

<style type="text/css">
  .ftitle {
    font-size: 14px;
    font-weight: bold;
    padding: 5px 0;
    margin-bottom: 10px;
    border-bottom: 1px solid #ccc;
  }

  .flabel {
    font-size: 12px;
    text-align: right;;
  }

  .finput {

  }
</style>

</body>
</html>
