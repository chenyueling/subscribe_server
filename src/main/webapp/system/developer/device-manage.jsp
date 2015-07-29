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
<!-- 工具栏 -->
<div id="xtoolbar">

  <div style="padding: 3px; padding-top: 0px;">
    <div style="float: left;">
    </div>
    <div style="float: right; text-align: right;">
      <a href="javascript:void(0)" class="easyui-linkbutton"
         iconCls="icon-add" plain="true" onclick="doAdd()">新增</a>
      <a href="javascript:void(0)" class="easyui-linkbutton"
         iconCls="icon-edit" plain="true" onclick="doDelete()">删除</a>

    </div>
    <div class="clear"></div>
  </div>
</div>
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
<!-- 新增表单 -->
<div id="addDlg" class="easyui-dialog" style="padding: 10px 20px">
  <div class="ftitle">
    添加设备
  </div>
  <form id="addForm" method="post">
    <table width="100%">
      <tr>
        <td class="flabel">
          设备标识码:
        </td>
        <td class="finpur">
          <input type="text" name="deviceCode" style="width: 200px;"
                 class="easyui-validatebox" validType="length[2, 15]"
                 required="true" />
        </td>
      </tr>
    </table>
  </form>
  <div id="addButtons">
    <a href="javascript:void(0)" class="easyui-linkbutton"
       iconCls="icon-ok" onclick="javascript:$('#addForm').submit()">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton"
       iconCls="icon-cancel"
       onclick="javascript:$('#addDlg').dialog('close')">关闭</a>
  </div>
</div>


</body>
<script type="text/javascript">
  var dgTitle = "设备管理";
  var dgDeleteMsg = "确定要删除该设备吗?";
  var dgBaseUrl = "rest/users/devices";
  var dgDateUrl = dgBaseUrl;
  var dgAddUrl = dgBaseUrl + "/add_user";
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
    { title: '设备码', field: 'imei',sortable: false, resizable: true, width:200},
    { title: '设备标识码', field: 'code',sortable: false, resizable: true, width:125},
    { title: '最后登录时间', field: 'lastLoginTime',sortable: true, resizable: true, width:125},
    { title: '推送条数', field: 'push_setting',sortable: true, resizable: true, width:125},
    { title: '创建时间', field: 'createTime',sortable: true, resizable: true, width:125},
      { title: '其他操作', field: 'operate', sortable: false, resizable: true, width: 100,
          formatter: function (value, rowData, rowIndex) {
              var html;
              var html = "<a href='javascript:void(0)' onclick='removeDevice(&quot;" + rowData.id + "&quot;)'>删除</a>";
              return html;
          }
      }
  ]];



  $(function() {

  });

  function removeDevice(id) {

      var url = dgBaseUrl +'/'+id +  '/remove_user';
      doConfirmPost(url, {id:id}, '确定要删除该设备?');
      doReload();
  }


</script>

</html>
