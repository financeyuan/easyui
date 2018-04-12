<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="../jquery-easyui-1.5.1/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript" src="../jquery-easyui-1.5.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.5.1/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="../jquery-easyui-1.5.1/themes/default/easyui.css" type="text/css" />
<link rel="stylesheet" href="../jquery-easyui-1.5.1/themes/icon.css" type="text/css" />
<link rel="stylesheet" href="../jquery-easyui-1.5.1/themes/bootstrap/easyui.css" >

<title>人员列表</title>

<script type="text/javascript">
	$(function() {
		//将增加框和修改框进行隐藏
		$('#updateStu').dialog('close');
		$('#addStu').dialog('close');
		//配置学生信息表格
		$('#user')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/user/showAllUser',
							type:"get",
				            dataType:"json",
							columns : [ [ {
								field : 'checked',
								checkbox : true,
								width : 100
							}, {
								field : 'id',
								title : 'ID',
								width : 100
							}, {
								field : 'userName',
								title : '用户名',
								width : 100
							}, {
								field : 'password',
								title : '密码',
								width : 100,
								align : 'right'
							},{
								field : 'age',
								title : '年龄',
								width : 100,
								align : 'right'
							} ] ],
							pagination : true, //显示分页
							pagePosition : 'bottom',//分页显示在底部
							toolbar : [
									{
										iconCls : 'icon-edit',
										text : '添加',
										handler : function() {
											$('#addStu').dialog('open');//点击添加按钮显示添加框

										}
									},
									'-',
									{
										iconCls : 'icon-delete',
										text : '删除',
										handler : function() {

											var sids = '';
											var ss = $("#students").datagrid(
													'getSelections');//接收选中的对象结果集

											if (ss.length == 0) {//对选中的大小判断
												$.messager.alert('这是一个提示信息！',
														'请至少选择一条数据进行删除!');
											} else {
												//对选中数据的id以逗号进行拼接,结果会多一个逗号,后台再进行处理:截取字符串
												$.messager
														.confirm(
																'提示',
																'确认删除？',
																function(r) {
																	$
																			.each(
																					ss,
																					function(
																							n,
																							v) {
																						sids = sids
																								+ v.sid
																								+ ','
																					});
																	$
																			.ajax({
																				type : 'post',
																				url : '${pageContext.request.contextPath}/deleteStus',
																				data : {
																					'sids' : sids
																				},
																				dataType : "text",
																				success : function(
																						data) {
																					if (data == "1") {
																						$.messager
																								.alert(
																										'提示',
																										'删除成功！');
																						$(
																								"#students")
																								.datagrid(
																										'reload');

																					} else {
																						$.messager
																								.confirm(
																										'删除失败!',
																										"删除数据失败!");
																					}
																				}
																			})
																});
											}
										}
									},
									'-',
									{
										iconCls : 'icon-update',
										text : '修改',
										handler : function() {
											var stus = $("#students").datagrid(
													'getSelections');

											if (stus.length != 1) {
												$.messager
														.confirm(
																'提示',
																'请选择一条数据',
																function(r) {

																	$(
																			"#students")
																			.datagrid(
																					'unselectAll');

																})
											} else {
												//显示修改框
												$('#updateStu').dialog('open');
												//将选中的数据加载到修改面板上
												var stu = stus[0];
												$('#upStu').form('load', stu);

											}

										}
									} ]

						});

		/* 配置添加框 */
		$("#addStuf").form(
				{
					type : 'post',
					url : '${pageContext.request.contextPath}/AddStu',
					dataType : "text",
					success : function(data) {
						if (data == "1") {
							$('#addStu').dialog('close');
							$('#addStuf').form('clear');
							$.messager.alert('我的消息', '添加商品信息成功', 'info',
									function() {
										$('#students').datagrid('reload');
									});
						} else {
							$.messager.alert('我的消息', '添加商品信息失败，重新添加', 'info',
									function() {
										$("#addStuf").form('clear');
									});
						}
					}
				});

		/* 配置修改框 */
		$("#upStu").form({
			type : 'post',
			url : '${pageContext.request.contextPath}/updataStu',
			dataType : "text",
			success : function(data) {

				if (data == "1") {

					$("#students").datagrid('reload');
					$.messager.alert('提示!', '修改成功');
					$('#updateStu').dialog('close');

				} else {
					$.messager.alert('我的消息', '修改学生信息失败!', '修改失败!', function() {
						$('#upStu').form('clear');
					});

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

	//配置修改学生信息表单提交
	function updataForm() {
		$("#upStu").form('submit');
	}

	function addForm() {
		$("#addStuf").form('submit');

	}
</script>
</head>
<body>
	<input id="searchStu" class="easyui-searchbox"
		data-options="prompt:'输入学生查询信息',width:150">

	<table id="user"></table>

	<!-- 配置修改框面板 -->
	<div id="updateStu" class="easyui-dialog" title="更新学生信息信息"
		style="width: 400px; height: 450px;" data-options="modal:true">

		<form id="upStu" method="post">
			//id默认隐藏,这样就不允许修改
			<div style="margin-bottom: 20px;">
				<span>id</span> <input class="easyui-textbox" name="id" id="sid"
					style="width: 200px; display: none;">
			</div>
			<div style="margin-bottom: 20px">
				<span>学生姓名</span> <input class="easyui-textbox" name="userName"
					style="width: 200px">
			</div>
			<div style="margin-bottom: 20px">
				<span>学生年龄</span> <input class="easyui-textbox" name="age"
					style="width: 200px">
			</div>
		</form>
		<div style="text-align: center; padding: 5px 0;">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="updataForm()" style="width: 80px" id="tt">提交</a>
		</div>
	</div>



	<!-- 配置增加框 -->
	<div id="addStu" class="easyui-dialog" title="添加学生信息信息"
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
</body>
</html>
