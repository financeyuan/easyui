<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="${pageContext.request.contextPath}/jquery-easyui-1.5.1/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.5.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.5.1/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-easyui-1.5.1/themes/default/easyui.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-easyui-1.5.1/themes/icon.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-easyui-1.5.1/themes/bootstrap/easyui.css" >

<title>人员列表</title>

<script type="text/javascript">
	$(function() {
		//将增加框和修改框进行隐藏
		$('#updateStu').dialog('close');
		$('#addStu').dialog('close');
		$('#importExcel').dialog('close');
		//配置学生信息表格
		$('#user')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/user/showAllUser',
							striped:true, //隔行换色
							fitColumns:true,//自适应
							rownumbers : true, //显示行数
							loadMsg:'数据正在加载中，请稍后......',
							columns : [ [ {
								field : 'checked',
								checkbox : true,
								width : 100
							}, {
								field : 'id',
								title : 'ID',
								width : fixWidth(0.2),
							}, {
								field : 'userName',
								title : '用户名',
								width : fixWidth(0.2),
							}, {
								field : 'password',
								title : '密码',
								width : fixWidth(0.2),
								align : 'right'
							},{
								field : 'age',
								title : '年龄',
								width : fixWidth(0.2),
								align : 'right'
							} ] ],
							pagination : true, //显示分页
							pagePosition : 'bottom',//分页显示在底部
							toolbar : [
									{
										iconCls : 'icon-add',
										text : '添加',
										handler : function() {
											$('#addStu').dialog('open');//点击添加按钮显示添加框

										}
									},
									'-',
									{
										iconCls : 'icon-remove',
										text : '删除',
										handler : function() {

											var sids = '';
											var ss = $("#user").datagrid(
													'getSelections');//接收选中的对象结果集
											if (ss.length == 0) {//对选中的大小判断
												$.messager.alert('提示信息',
														'请至少选择一条数据进行删除!');
											} else {
												//对选中数据的id以逗号进行拼接,结果会多一个逗号,后台再进行处理:截取字符串
												$.messager.confirm(
																'提示',
																'确认删除？',
																function(r) {
																	$.each(ss,function(n,v) {sids = sids + v.id + ','});
																	$.ajax({
																				type : 'post',
																				url : '${pageContext.request.contextPath}/user/deleteUser',
																				data : {
																					'ids' : sids
																				},
																				dataType : "json",
																				success : function(result) {
																					if (result.success) {
																						$.messager.alert('提示','删除成功！');
																						$("#user").datagrid('reload');
																					} else {
																						$.messager.confirm('删除失败!',"删除数据失败!");
																					}
																				}
																			})
																});
											}
										}
									},
									'-',
									{
										iconCls : 'icon-edit',
										text : '修改',
										handler : function() {
											var stus =  $("#user").datagrid(
											'getSelections');
											if (stus.length != 1) {
												$.messager.alert('提示信息','请选择一条数据');
											} else {
												//显示修改框
												$('#updateStu').dialog('open');
												//将选中的数据加载到修改面板上
												var stu = stus[0];
												$('#upStu').form('load', stu);
											}
										}
									},
									'-',
									{
										iconCls : 'icon-print',
										text : '导出',
										handler : function() {
											var grid = $('#user');  
											var options = grid.datagrid('getPager').data("pagination").options;  
											var page = options.pageNumber;//当前页数  
											var rows = options.pageSize;//每页的记录数（行数） 
											var url = '${pageContext.request.contextPath}/LeadToExcel/LeadToUser?rows='+rows+'&page='+page
											window.location.href=url;
										}
									},'-',
									{
										iconCls : 'icon-filter',
										text : '导入',
										handler : function() {
												//显示修改框
												$('#importExcel').dialog('open');
										}
									}]

						});

		/* 配置添加框 */
		$("#addStuf").form(
				{
					type : 'post',
					url : '${pageContext.request.contextPath}/user/addUser',
					dataType : "json",
					success : function(result) {
						var result = eval('(' + result + ')');
						if (result.success) {
							$('#addStu').dialog('close');
							$('#addStuf').form('clear');
							$.messager.alert('我的消息', '添加人员信息成功', 'info',
									function() {
										$('#user').datagrid('reload');
									});
						} else {
							$.messager.alert('我的消息', '添加人员信息失败，重新添加', 'info',
									function() {
										$("#addStuf").form('clear');
									});
						}
					}
				});

		/* 配置修改框 */
		$("#upStu").form({
			type : 'post',
			url : '${pageContext.request.contextPath}/user/updateUser',
			dataType : "json",
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success) {
					$("#user").datagrid('reload');
					$.messager.alert('提示!', '修改成功','info',
						function() {
							$('#updateStu').dialog('close');
							$('#user').datagrid('reload');
					    });
				}else {
					$.messager.alert('我的消息', '修改失败!', '修改失败!', function() {
						$('#upStu').form('clear');
					});
				}
			}
		});

		/* 配置导入框 */
		$("#uploadExcel").form({
			type : 'post',
			url : '${pageContext.request.contextPath}/LeadToExcel/LeadInUser',
			dataType : "json",
			onSubmit: function() {
				var fileName= $('#excel').filebox('getValue'); 
				  //对文件格式进行校验  
                 var d1=/\.[^\.]+$/.exec(fileName);
				if (fileName == "") {  
				      $.messager.alert('Excel批量用户导入', '请选择将要上传的文件!'); 
				      return false;  
				 }else if(d1!=".xls"){
					 $.messager.alert('提示','请选择xls格式文件！','info');  
					 return false; 
				 }
				 $("#booten").linkbutton('disable');
                return true;  
            }, 
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success) {
					$.messager.alert('提示!', '导入成功','info',
							function() {
								$("#booten").linkbutton('enable');
								$('#importExcel').dialog('close');
								$('#user').datagrid('reload');
						    });
				} else {
					$.messager.confirm('提示',"导入失败!");
					$("#booten").linkbutton('enable');
				}
			}
		});
		
		<!--配置搜索框, 该功能尚未实现-->
		$('#searchStu').searchbox({
			searcher : function(value, name) {
				var sname = value;
				$('#goods').datagrid('reload', {
					searchname : sname,
				});
			}
		});

	});
	//配置百分比
	function fixWidth(percent)  
	{  
	    return document.body.clientWidth * percent ; //这里你可以自己做调整  
	}  
	//配置修改学生信息表单提交
	function upForm() {
		$("#upStu").form('submit');
	}

	function addForm() {
		$("#addStuf").form('submit');
	}
	function uploadExcel() {
		$("#uploadExcel").form('submit');
	}
</script>
</head>
<body>
	<input id="searchStu" class="easyui-searchbox"
		data-options="prompt:'输入学生查询信息',width:150">
		
	<table  style="width:84%"  id="user" ></table>

	<!-- 配置修改框面板 -->
	<div id="updateStu" class="easyui-dialog" title="更新人员信息"
		style="width: 400px; height: 450px;" data-options="modal:true">
		<form id="upStu" method="post">
			<div style="margin-bottom: 20px; display:none;">
				<span>id</span> <input class="easyui-textbox" name="id"
					style="width: 200px">
			</div>
			<div style="margin-bottom: 20px">
				<span>学生姓名</span> <input class="easyui-textbox" name="userName"
					style="width: 200px">
			</div>
			<div style="margin-bottom: 20px">
				<span>学生年龄</span> <input class="easyui-textbox" name="age"
					style="width: 200px">
			</div>
			<div style="margin-bottom: 20px">
				<span>密码</span> <input class="easyui-textbox" name="password"
					style="width: 200px">
			</div>
		</form>
		<div style="text-align: center; padding: 5px 0;">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="upForm()" style="width: 80px" id="is">提交</a>
		</div>
	</div>



	<!-- 配置增加框 -->
	<div id="addStu" class="easyui-dialog" title="添加人员信息"
		style="width: 400px; height: 300px;" data-options="modal:true">

		<form id="addStuf" method="post">
			<div style="margin-bottom: 20px">
				<span>用户名&nbsp; </span> <input class="easyui-textbox" name="userName"
					style="width: 200px">
			</div>

			<div style="margin-bottom: 20px">
				<span>用户年龄</span> <input
					class="easyui-textbox" name="age" style="width: 200px">
			</div>
			
			<div style="margin-bottom: 20px">
				<span>用户密码</span> <input class="easyui-textbox" name="password"
					style="width: 200px">
			</div>
		</form>

		<div style="text-align: center; padding: 5px 0;">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="addForm()" style="width: 80px" id="tt">提交</a>
		</div>
	</div>
	
	<!-- 配置导入框 -->
	<div id="importExcel" class="easyui-dialog" title="导入excel文件"
		style="width: 400px; height: 300px;" data-options="modal:true">
		<form id="uploadExcel"  method="post" enctype="multipart/form-data">  
   			选择文件：　<input id = "excel" name = "excel" class="easyui-filebox" style="width:200px" data-options="prompt:'请选择文件...'">  
		</form>  
		<div style="text-align: center; padding: 5px 0;">
			<a id = "booten" href="javascript:void(0)" class="easyui-linkbutton"
				onclick="uploadExcel()" style="width: 80px" id="tt">导入</a>
		</div>
	</div>
</body>
</html>
