//对EasyUI的各种封装

//右击表格取得的对象
var clickData = null;

$(function() {
	//初始化表格
	var dgHeight = $(window.parent.document).find('#wg_main').height();
	var dgWidth = $(window.parent.document).find('#wg_main').width();
	dgHeight = dgHeight - 10;
	dgWidth = dgWidth - 10;
	
	$('#xdatagrid').datagrid({
		height:dgHeight,
		width:dgWidth,
		title:dgTitle,
		columns:dgColumns,
		url:dgDateUrl,
		idField:dgIdField,
		queryParams:dgParams,
		sortName:dgSortName,
		sortOrder:dgSortOrder,
		fitColumns:false,
		remoteSort:true,
		nowrap:false,//false自动换行
        autoRowHeight:true, //自动行高
		remoteSort:true,//是否从服务器给数据排序
		striped: true,
		singleSelect: true,
		rownumbers:true,
		pagination:true,//分页控件 
	    pageSize: 15,//每页显示的记录条数，默认为10 
	    pageList: [10,15,20],//可以设置每页记录条数的列表 
	    toolbar:"#xtoolbar",
	    onLoadSuccess:function(data) {//读取成功后调用
			//单选框特殊处理
			$(".xid").bind('click', function(event) {
				 event.stopPropagation();
			});
	 	},
	 	onLoadError:function() {
	 		$.messager.alert(dgTitle, '未知异常,请联系管理员');
	 	},
	 	onRowContextMenu: function (e, rowIndex, rowData) {
            e.preventDefault();
            clickData = rowData;
            $('#xClickMenu').menu('show', {
                left: e.pageX - 5,
                top: e.pageY - 5
            });
        },
        onSelect: function(rowIndex, rowData) {
        	var obj = "input[value='" + rowData.id + "']";
        	if ($(obj).attr('checked') == 'checked') {
        		$(obj).removeAttr('checked');
        	} else {
        		$(obj).attr('checked', 'checked');
        	}
        	
        }
	 	
	 });
	
	$(window).resize(function() {
		var dgHeight = $(window.parent.document).find('#wg_main').height();
		var dgWidth = $(window.parent.document).find('#wg_main').width();
		dgHeight = dgHeight - 10;
		dgWidth = dgWidth - 10;
		$("#xdatagrid").datagrid("resize", {height:dgHeight, width:dgWidth});
	});
	
	//初始化新增框
	$("#addDlg").dialog({
		   title:dgTitle,
		   width:400,
		   height:400,
		   close:true,
		   buttons:"#addButtons",
		   iconCls:'icon-save',
		   resizable:false,
		   modal:true			   
	}).dialog('close');
	
	//初始化新增表达
	$('#addForm').ajaxForm({  
	    type : "post",  
	    url : dgAddUrl,  
	    dataType : 'json',
	    beforeSubmit : function() {
			$.messager.progress({
				title:dgTitle,
				msg:"提交数据中"
			});
	    },  
	    success : function(json) {
	    	$.messager.progress('close');
	        if (json.result != "success") {
	        	$.messager.alert(dgTitle, json.data); 
	            return;  
	        } else {
	        	$("#addDlg").dialog('close');
	        	$('#xdatagrid').datagrid('reload');
	        }
	    },
	    error : function(xmlHttp) {
	    	$.messager.progress('close');
	    	$.messager.alert(dgTitle, '登录超时,请重新登录');
	    	document.write(xmlHttp.responseText);
		}  
	});
	
	//初始化编辑栏
	$("#editDlg").dialog({
		   title:dgTitle,
		   width:400,
		   height:400,
		   close:true,
		   buttons:"#editButtons",
		   iconCls:'icon-save',
		   resizable:false,
		   modal:true			   
	}).dialog('close');

	//初始化编辑表单
	$('#editForm').ajaxForm({  
	    type : "post",  
	    url : dgUpdateUrl,  
	    dataType : 'json',
	    beforeSubmit : function() {
			$.messager.progress({
				title:dgTitle,
				msg:"提交数据中"
			});
	    },  
	    success : function(json) {
	    	$.messager.progress('close');
	        if (json.result != "success") {
	        	$.messager.alert(dgTitle, json.data); 
	            return;  
	        } else {
	        	$("#editDlg").dialog('close');
	        	$('#xdatagrid').datagrid('reload');
	        }
	    },
	    error : function(xmlHttp) {
	    	$.messager.progress('close');
	    	$.messager.alert(dgTitle, '登录超时,请重新登录');
	    	document.write(xmlHttp.responseText);
		}  
	});
	

});

//搜索框
function doSearch(value, name){  
	var params2 = {search:value, searchTitle:name};
	dgParams.search = value;
	dgParams.searchTitle = name;
	$("#xdatagrid").datagrid("reload", dgParams);
}

//重新刷新数据
function doReload() {
	$("#xdatagrid").datagrid("reload");	
}



//增加事件
function doAdd() {
	$('#addForm').form('reset');
	$('#addDlg').dialog('open');
}

//编辑事件
function doEdit() {
	var row = $("#xdatagrid").datagrid('getSelected');
	if (row) {
		loadDialog(row);
	}
}

//右键编辑事件
function clickEdit() {
	loadDialog(clickData);
}


//点击删除事件
function doDelete() {
	var deleteIds = $('.xid');
	var idstr = "";
	$(".xid").each(function() {
		if ($(this).attr("checked") == "checked") {
			idstr = idstr + $(this).val();
			idstr = idstr + ";";
		}
	});
	var row = $("#xdatagrid").datagrid('getSelected');
	if (row) {
		idstr = idstr + row.id;
	}
	doConfirmPost(dgDeleteUrl, {id:idstr}, dgDeleteMsg);
}

//右击删除事件
function clickDelete() {
	doConfirmPost(dgDeleteUrl, {id:clickData.id}, dgDeleteMsg);
}

//Post提交
function doPost(url, params, callback) {
	$.ajax({
		url: url,
		data: params,
		dataType: 'json',
		type: 'post',
		beforeSend:function(json) {
			$.messager.progress({
				title:dgTitle,
				msg:"提交数据中"
			});
		},
		success:function(json) {
			$.messager.progress('close');
			if (!json) {
				$.messager.alert(dgTitle, '未知异常,请联系管理员');
				return;
			}
			if (json.result != "success") {
				$.messager.alert(dgTitle, json.data);

                doReload();
				return;
			} else {
				$('#xdatagrid').datagrid('reload');
				if (callback) {
					callback();
				}

   			}
		},
		error: function(xmlHttp) {
			$.messager.progress('close');
			$.messager.alert(dgTitle, '未知异常,请联系管理员');
			document.write(xmlHttp.responseText);
		}

	});
}

//Post提交
function doPut(url, params, callback) {
    $.ajax({
        url: url,
        data: params,
        dataType: 'json',
        type: 'PUT ',
        beforeSend:function(json) {
            $.messager.progress({
                title:dgTitle,
                msg:"提交数据中"
            });
        },
        success:function(json) {
            $.messager.progress('close');
            if (!json) {
                $.messager.alert(dgTitle, '未知异常,请联系管理员');
                return;
            }
            if (json.result != "success") {
                $.messager.alert(dgTitle, json.data);
                return;
            } else {
                $('#xdatagrid').datagrid('reload');
                if (callback) {
                    callback();
                }

            }
        },
        error: function(xmlHttp) {
            $.messager.progress('close');
            $.messager.alert(dgTitle, '未知异常,请联系管理员');
            document.write(xmlHttp.responseText);
        }

    });
}

//Post提交，提交前会确认信息
function doConfirmPost(url, params, msg, callback) {
	$.messager.confirm(dgTitle, msg, function(r) {
		if (r) {
			doPost(url, params, callback);
		}
	});

}

function doConfirmPut(url, params, msg, callback) {
    $.messager.confirm(dgTitle, msg, function(r) {
        if (r) {
            doPut(url, params, callback);
        }
    });

}

//Get提交
function doGet(url, params, callback) {
	$.ajax({
		url: url,
		data: params,
		dataType: 'json',
		type: 'get',
		beforeSend:function(json) {
		$.messager.progress({
			title:dgTitle,
			msg:"提交数据中"
		});
		},
		success:function(json) {
			$.messager.progress('close');
			if (json.result != "success") {
				$.messager.alert(dgTitle, json.data);
				return;
			} else {
				callback();
   			}
		},
		error: function(xmlHttp) {
			$.messager.progress('close');
			document.write(xmlHttp.responseText);
		}

	});
}



