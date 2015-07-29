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
    <div style="padding: 3px;">
        <input id="ss" class="easyui-searchbox" searcher="doSearch"
               prompt="请输入关键字" menu="#xsearchSelect" style="width: 300px"/>
        <div id="xsearchSelect" style="width: 120px">
            <div name="realname">
                管理员姓名
            </div>
            <div name="username">
                登录名
            </div>
        </div>
    </div>
    <div style="padding: 3px; padding-top: 0px;">
        <div style="float: left;">
        </div>
        <div style="float: right; text-align: right;">
            <a href="javascript:void(0)" class="easyui-linkbutton"
               iconCls="icon-add" plain="true" onclick="doAdd()">新增</a>
            <a href="javascript:void(0)" class="easyui-linkbutton"
               iconCls="icon-edit" plain="true" onclick="doEdit()">修改</a>
        </div>
        <div class="clear"></div>
    </div>
</div>
<!-- 右击菜单 -->
<div id="xClickMenu" class="easyui-menu" style="width: 120px;">
    <div data-options="iconCls:'icon-edit'" onclick="clickEdit()">
        修改
    </div>
    <div class="menu-sep"></div>
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
        添加Banner
    </div>
    <form id="addForm" method="post">
        <table width="100%">
            <tr>
                <td class="flabel">
                    管理员姓名:
                </td>
                <td class="finpur">
                    <input type="text" name="realname" style="width: 200px;"
                           class="easyui-validatebox" validType="length[2, 15]"
                           required="true" />
                </td>
            </tr>
            <tr>
                <td class="flabel">
                    密码:
                </td>
                <td class="finpur">
                    <input type="text" name="password" style="width: 200px;"
                           class="easyui-validatebox" validType="length[6, 20]"
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

<!-- 编辑表单 -->
<div id="editDlg" class="easyui-dialog" style="padding: 10px 20px">
    <div class="ftitle">
        添加Banner
    </div>
    <form id="editForm" method="post">
        <input type="hidden" name="id" />
        <table width="100%">
            <tr>
                <td class="flabel">
                    管理员姓名:
                </td>
                <td class="finpur">
                    <input type="text" name="realname" style="width: 200px;"
                           class="easyui-validatebox" validType="length[2, 15]"
                           required="true" />
                </td>
            </tr>
            <tr>
                <td class="flabel">
                    登录名:
                </td>
                <td class="finpur">
                    <input type="text" name="username" style="width: 200px;"
                           class="easyui-validatebox" validType="length[2, 15]"
                           required="true" />
                </td>
            </tr>
            <tr>
                <td class="flabel">
                    密码:
                    <span style="font-size: 12px; color: red;">(如不修改请勿填)</span>
                </td>
                <td class="finpur">
                    <input type="text" name="password" style="width: 200px;"
                           class="easyui-validatebox" validType="length[0, 20]" />

                </td>
            </tr>
        </table>
    </form>
    <div id="editButtons">
        <a href="javascript:void(0)" class="easyui-linkbutton"
           iconCls="icon-ok" onclick="javascript:$('#editForm').submit()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton"
           iconCls="icon-cancel"
           onclick="javascript:$('#editDlg').dialog('close')">关闭</a>
    </div>
</div>



</body>
<script type="text/javascript">
    var dgTitle = "开发者管理";
    var dgDeleteMsg = "确定要删除该开发者?";
    var dgBaseUrl = "rest/admins/users";
    var dgDateUrl = dgBaseUrl;
    var dgAddUrl = dgBaseUrl + "save"
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
        { title: '姓名', field: 'name',sortable: false, resizable: true, width:200},
        { title: '登录名', field: 'username',sortable: false, resizable: true, width:200},
        { title: '创建时间', field: 'createTime',sortable: true, resizable: true, width:125},
        { title: '最后登录时间', field: 'lastLoginTime',sortable: true, resizable: true, width:125},
        { title: '邮箱', field: 'email',sortable: true, resizable: true, width:300}
    ]];

    //往edit表单load数据
    function loadDialog(row) {
        $('#editForm').form('load', {
            id:row.id,
            realname:row.realname,
            username:row.username
        });
        $("#editDlg").dialog('open');
    }


    $(function() {

    });


    function activeItem(id) {
        var url = dgBaseUrl + 'active';
        doConfirmPost(url, {id:id}, '确定要激活该管理员?');
    }


    function cancelItem(id) {
        var url = dgBaseUrl + 'cancel';
        doConfirmPost(url, {id:id}, '确定要注销该管理员?');
    }

</script>

</html>
