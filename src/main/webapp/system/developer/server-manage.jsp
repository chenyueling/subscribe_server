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
        服务申请
    </div>
    <form id="addForm" method="post">
        <table width="100%">
            <tr>
                <td class="flabel">
                    服务名称:
                </td>
                <td class="finpur">
                    <input type="text" name="name" style="width: 200px;"
                           class="easyui-validatebox" validType="length[2, 15]"
                           required="true" />
                </td>
            </tr>
            <tr>
                <td class="flabel">
                    说明
                </td>
                <td class="finpur">
                    <input type="radio" name="status" value="PUBLIC"
                           class="easyui-validatebox"
                           required="true" />公开
                    <input type="radio" name="status" value="PRIVATE"
                           class="easyui-validatebox"
                           required="true" />非公开
                </td>
            </tr>

            <tr>
                <td class="flabel">
                    是否公开
                </td>
                <td class="finpur">
                    <textarea rows="10" cols="30" name="tip" ></textarea>
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
    var dgTitle = "服务管理";
    var dgDeleteMsg = "确定要删除该服务?";
    var dgBaseUrl = "rest/users/servers";
    var dgDateUrl = dgBaseUrl;
    var dgAddUrl = dgBaseUrl + "/apply"
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
        { title: '服务名称', field: 'name',sortable: false, resizable: true, width:125},
        { title: '创建时间', field: 'createTime',sortable: false, resizable: true, width:125},
        { title: 'token', field: 'token',sortable: true, resizable: true, width:300},
        { title: '状态', field: 'audit',sortable: true, resizable: true, width:125},
        { title: '是否公有', field: 'status',sortable: true, resizable: true, width:125},
        { title: '其他操作', field: 'operate', sortable: false, resizable: true, width: 100,
            formatter: function (value, rowData, rowIndex) {
                var html;
                    var html = "<a href='javascript:void(0)' onclick='resetToken(&quot;" + rowData.id + "&quot;)'>重置Token</a>";
                return html;
            }
        }
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


    function resetToken(id) {

        var url = dgBaseUrl +'/'+id +  '/token';
        doConfirmPost(url, {id:id}, '确定要重置该服务Token?');
        doReload();
    }




</script>

</html>
