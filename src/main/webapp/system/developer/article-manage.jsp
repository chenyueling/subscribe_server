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
<!-- 表格 -->
<table id="xdatagrid">
</table>

<!-- 右击菜单 -->
<div id="xClickMenu" class="easyui-menu" style="width: 120px;">
  <div data-options="iconCls:'icon-reload'" onclick="doReload()">
    刷新
  </div>
</div>
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
<script type="text/javascript">
  var dgTitle = "推送管理";
  var dgDeleteMsg = "确定要删除该推送?";
  var dgBaseUrl = "rest/users/articles";
  var dgDateUrl = dgBaseUrl;
  var dgAddUrl = dgBaseUrl + "save";
  var dgUpdateUrl = dgBaseUrl + "update";
  var dgDeleteUrl = dgBaseUrl + "delete";
  var dgIdField = "id";
  var dgParams = {};
  var dgSortName = "createTime";
  var dgSortOrder = "desc";
  var dgColumns = [[
    { field: 'id',
      formatter:function(value, rowData, rowIndex){
        return " <input class='xid' type='checkbox' value='" + value +"'/>";
      }
    },
    { title: '标题', field: 'title',sortable: false, resizable: true, width:400},
    { title: '链接', field: 'url',sortable: false, resizable: true, width:400},
    { title: '创建时间', field: 'createTime',sortable: true, resizable: true, width:125},
    { title: '所属服务', field: 'serverName',sortable: true, resizable: true, width:125}
  ]];



</script>

</html>
