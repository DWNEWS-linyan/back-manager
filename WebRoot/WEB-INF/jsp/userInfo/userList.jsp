<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>

<!DOCTYPE HTML>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
  <head>
        <title>后台管理</title>
        <meta content="#1 selling multi-purpose bootstrap admin theme sold in themeforest marketplace packed with angularjs, material design, rtl support with over thausands of templates and ui elements and plugins to power any type of web applications including saas and admin dashboards. Preview page of Theme #1 for basic datatable samples"
            name="description" />
        <meta content="" name="author" />
        <!-- BEGIN GLOBAL MANDATORY STYLES -->
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" />
        <link href="<%=basePath %>theme/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=basePath %>theme/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=basePath %>theme/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=basePath %>theme/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
        <!-- END GLOBAL MANDATORY STYLES -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <link href="<%=basePath %>theme/assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=basePath %>theme/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" />
        <link href="<%=basePath %>theme/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL STYLES -->
        <link href="<%=basePath %>theme/assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
        <link href="<%=basePath %>theme/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
        <!-- END THEME GLOBAL STYLES -->
        <!-- BEGIN THEME LAYOUT STYLES -->
        <link href="<%=basePath %>theme/assets/layouts/layout/css/layout.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=basePath %>theme/assets/layouts/layout/css/themes/darkblue.min.css" rel="stylesheet" type="text/css" id="style_color" />
        <link href="<%=basePath %>theme/assets/layouts/layout/css/custom.min.css" rel="stylesheet" type="text/css" />
        <!-- END THEME LAYOUT STYLES -->
        <link rel="shortcut icon" href="favicon.ico" /> 
        <style type="text/css">
        .page-header.navbar .menu-toggler.sidebar-toggler{
        	margin:30px 0 0
        }
        .selectMenuClass{
        	background-color:#7f868e !important
        }
        .page-container-bg-solid .page-bar .page-breadcrumb>li>i.fa-circle, .page-content-white .page-bar .page-breadcrumb>li>i.fa-circle{
        	top:0px;
        }
        .zijisanjiclass{ 
			width: 220px;
			border: 1px solid rgba(0,0,0,.15);
			background-color: #fff;
			padding: 10px;
			top :0px;
			position: absolute;
			display: none;
			z-index:20005;
		}
		.datepicker-select-dropdown.datepicker-select-orient-top:before {
		    bottom: -7px;
		    left: 6px;
		    border-bottom: 0;
		    border-top: 7px solid rgba(0,0,0,.15);
		}
		.datepicker-select-dropdown:before {
		    border-left: 7px solid transparent;
		    border-right: 7px solid transparent;
		    border-bottom: 7px solid rgba(0,0,0,.15);
		    border-bottom-color: rgba(0,0,0,.2);
		}
		.datepicker-select-dropdown:after, .datepicker-select-dropdown:before {
		    content: '';
		    display: inline-block;
		    border-top: 0;
		    position: absolute;
		}
		.datepicker-select-dropdown.datepicker-select-orient-top:after {
		    bottom: -6px;
		    left: 7px;
		    border-bottom: 0;
		    border-top: 6px solid #fff;
		}	
		.datepicker-select-dropdown:after {
		    border-left: 6px solid transparent;
		    border-right: 6px solid transparent;
		    border-bottom: 6px solid #fff;
		}
		.datepicker-select-dropdown.datepicker-select-orient-bottom:after {
		    top: -6px;
		}
		.datepicker-select-dropdown.datepicker-select-orient-bottom:before {
		    top: -7px;
		}
        </style>
        
  </head>

  <body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
        <div class="page-wrapper">
            <c:import url="${basePath }common/header.jsp"></c:import> 
            <div class="clearfix"> </div>
            <div class="page-container">
                <div class="page-sidebar-wrapper">
                    <div class="page-sidebar navbar-collapse collapse">
                        <c:import url="${basePath }common/menu.jsp"></c:import>
                    </div>
                </div>
                <div class="page-content-wrapper" >
                    <!-- BEGIN CONTENT BODY -->
                    <div class="page-content" id="mainBodyId">
                        <div class="page-bar">
                            <ul class="page-breadcrumb">
                                <li>
                                    <a href="index.html">首页</a>
                                    <i class="fa fa-circle"></i>
                                </li>
                            </ul>
                        </div>
                        <div class="row">
                        	<div class="col-md-12">
                        		<div class="portlet light portlet-fit portlet-datatable bordered">
                        			<div class="portlet-title">
						                <div class="caption">
					<!-- 	                    <i class="icon-settings font-dark"></i> -->
						                    <span class="caption-subject font-dark sbold uppercase">用户管理</span>
						                </div>
						                <div class="actions">
						                    <div class="btn-group btn-group-devided" data-toggle="buttons">
						                        <label class="btn btn-transparent grey-salsa btn-outline btn-circle btn-sm add-btn-class">
					                            	添加用户信息
					                            </label>
						                    </div>
						                </div>
						            </div>
                                    <div class="portlet-body">
                                        <div class="table-container">
                                            <table class="table table-striped table-bordered table-hover table-checkable" id="datatable_ajax_user_info">
                                                <thead>
                                                    <tr role="row" class="heading">
                                                        <th style="width:5%;"> ID </th>
                                                        <th style="width:5%;"> 姓名 </th>
                                                        <th style="width:5%;"> 年龄 </th>
                                                        <th style="width:5%;"> 性别 </th>
                                                        <th style="width:5%;"> 出生日期 </th>
                                                        <th style="width:5%;"> 民族 </th>
                                                        <th style="width:5%;"> 身高(cm) </th>
                                                        <th style="width:5%;"> 体重(Kg) </th>
                                                        <th style="width:5%;"> 学历 </th>
                                                        <th style="width:15%;"> 操作 </th>
                                                    </tr>
                                                    <tr role="row" class="filter">
                                                        <td>
                                                        	<input type="text" class="form-control form-filter input-sm" name="userInfoId">
                                                        </td>
                                                        <td>
                                                        	<input type="text" class="form-control form-filter input-sm" name="userName">
                                                        </td>
                                                        <td>
                                                        	<input type="text" class="form-control form-filter input-sm" name="userAge">
                                                        </td>
                                                        <td>
                                                        	<select class="form-control form-filter" style="height: 30px;padding: 4px 12px;">
                                                        		<option value="男">男</option>
                                                        		<option value="女">女</option>
                                                        		<option value="其他">其他</option>
                                                        	</select>
                                                        </td>
                                                        <td>
                                                        	<input type="text" class="form-control form-filter input-sm" id="userBirthdayid" name="userBirthday">
                                                        </td>
                                                        <td>
															<input type="text" class="form-control form-filter input-sm" name="userNations">
														</td>
                                                        <td> 
                                                        	<input type="text" class="form-control form-filter input-sm" name="userHight">
                                                        </td>
                                                        <td>
                                                            <input type="text" class="form-control form-filter input-sm" name="userWeight">
                                                        </td>
                                                        <td>
                                                            <select class="form-control form-filter" style="height: 30px;padding: 4px 12px;">
                                                        		<option value="博士">博士</option>
                                                        		<option value="硕士">硕士</option>
                                                        		<option value="本科">本科</option>
                                                        		<option value="专科">专科</option>
                                                        		<option value="高中">高中</option>
                                                        		<option value="中专">中专</option>
                                                        		<option value="初中">初中</option>
                                                        		<option value="小学">小学</option>
                                                        	</select>
                                                        </td>
                                                        <td>
                                                            <div class="margin-bottom-5">
                                                                <button class="btn btn-sm green btn-outline filter-submit margin-bottom">
                                                                    <i class="fa fa-search"></i> Search</button> <button class="btn btn-sm red btn-outline filter-cancel">
                                                                <i class="fa fa-times"></i> Reset</button>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </thead>
                                                <tbody> </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                        	</div>
                        </div>
                    </div>
                </div>
            </div>
            <c:import url="${basePath }common/bottom.jsp"></c:import>
        </div>
        <div class="quick-nav-overlay"></div>
        <div class="modal fade add-or-edit-modal-class" id="addOrEditUserInfo" tabindex="-1" role="dialog" style="top:0px;">
		  <div class="modal-dialog modal-lg" style="width: 80%;" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title">添加/修改用户信息</h4>
		      </div>
		      <div class="modal-body">
		      <form action="#" class="form-horizontal form-bordered" id="user-info-form-id">
		      <div class="row">
		      		<input type="hidden" name="id" id="editUserId"/>
		      		<div class="col-md-4" >
                		<div class="row">
                			<div class="col-md-1"></div>
                			<div class="col-md-10" style="text-align: center;">
                				<img class="herder-image" src="<%=basePath %>theme/assets/pages/media/profile/profile_user.jpg" style="float: none;width: 50%; height: 50%; margin: 0px auto; border-radius: 50% !important;"/>
                			</div>
                			<div class="col-md-1"></div>
                		</div>
                		<div class="row">
                			<div class="col-md-1"></div>
                			<div class="col-md-10" style="text-align: center;margin-top: 15px;">
                				<input type="file" id="uploasd" name="file"/>
                			</div>
                			<div class="col-md-1"></div>
                		</div>
                	</div>
		      		<div class="col-md-4 form" style=" border-left: 1px #e4e2e2 solid;">
		      			<div class="form-body" id="formbodyid">
                            <div class="form-group">
                            	<label class="control-label col-md-3">姓名</label>
                            	<div class="col-md-7">
                            		<input class="form-control" name="name" type="text" value="" placeholder="请输入姓名" >
                            	</div>
                            </div>
                            <div class="form-group">
                            	<label class="control-label col-md-3">性别</label>
                            	<div class="col-md-7">
                            		<select class="form-control" name="sex">
                            			<option value="男">男</option>
                            			<option value="女">女</option>
                            			<option value="其他">其他</option>
                            		</select>
                            	</div>
                            </div>
                            <div class="form-group">
                            	<label class="control-label col-md-3">年龄</label>
                            	<div class="col-md-7">
                            		<input class="form-control form-control-inline" name="age" type="text" maxlength="3" value="" placeholder="请输入年龄" >
                            	</div>
                            </div>
                            <div class="form-group">
                            	<label class="control-label col-md-3">身份证号</label>
                            	<div class="col-md-7">
                            		<input class="form-control form-control-inline" name="idCar" maxlength="18" type="text" value="" placeholder="请输入身份证号" >
                            	</div>
                            </div>
                            <div class="form-group">
                            	<label class="control-label col-md-3">生日</label>
                            	<div class="col-md-7">
                            		<div class="input-group date date-picker" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
									    <input class="form-control form-control-inline" name="birthday" type="text" value="" placeholder="请输入生日" >
									    <span class="input-group-btn">
									        <button class="btn default" type="button">
									            <i class="fa fa-calendar"></i>
									        </button>
									    </span>
									</div>
                            	</div>
                            </div>
                            <div class="form-group">
                            	<label class="control-label col-md-3">手机号</label>
                            	<div class="col-md-7">
                            		<input class="form-control form-control-inline" name="tel" maxlength="11" type="tel" value="" placeholder="请输入手机号" >
                            	</div>
                            </div>
                            <div class="form-group">
                            	<label class="control-label col-md-3">民族</label>
                            	<div class="col-md-7">
                            		<input class="form-control form-control-inline" name="nations" type="text" value="" placeholder="请输入民族" >
                            	</div>
                            </div>
                        </div>
                	</div>
                	<div class="col-md-4 form" style=" border-left: 1px #e4e2e2 solid;">
		      			<div class="form-body" id="formbodyid">
                            <div class="form-group">
                            	<label class="control-label col-md-3">身高cm</label>
                            	<div class="col-md-7">
                            		<input class="form-control form-control-inline" name="height" maxlength="3" type="text" value="" placeholder="请输入身高" >
                            	</div>
                            </div>
                            <div class="form-group">
                            	<label class="control-label col-md-3">体重kg</label>
                            	<div class="col-md-7">
                            		<input class="form-control form-control-inline" name="weight" type="text" maxlength="3" value="" placeholder="请输入体重" >
                            	</div>
                            </div>
                            <div class="form-group">
                            	<label class="control-label col-md-3">最高学历</label>
                            	<div class="col-md-7">
<!--                             		<input class="form-control form-control-inline" name="education" type="text" value="" placeholder="请输入" > -->
									<select class="form-control" name="education">
                                  		<option value="博士">博士</option>
                                  		<option value="硕士">硕士</option>
                                  		<option value="本科">本科</option>
                                  		<option value="专科">专科</option>
                                  		<option value="高中">高中</option>
                                  		<option value="中专">中专</option>
                                  		<option value="初中">初中</option>
                                  		<option value="小学">小学</option>
                                  	</select>
                            	</div>
                            </div>
                            <div class="form-group">
                            	<label class="control-label col-md-3">毕业院校</label>
                            	<div class="col-md-7">
                            		<input class="form-control form-control-inline" name="graduate" type="text" value="" placeholder="请输入毕业院校" >
                            	</div>
                            </div>
                            <div class="form-group">
                            	<label class="control-label col-md-3">专业</label>
                            	<div class="col-md-7">
                            		<input class="form-control form-control-inline" name="specialty" type="text" value="" placeholder="请输入专业" >
                            	</div>
                            </div>
                            <div class="form-group">
                            	<label class="control-label col-md-3">毕业时间</label>
                            	<div class="col-md-7">
                            		<div class="input-group date date-picker" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
									    <input class="form-control form-control-inline" name="graduation" type="text" value="" placeholder="请输入毕业时间" >
									    <span class="input-group-btn">
									        <button class="btn default" type="button">
									            <i class="fa fa-calendar"></i>
									        </button>
									    </span>
									</div>
                            	</div>
                            </div>
                        </div>
                	</div>
		      </div>
		      <div class="row" style="border-top: 1px #c1b8b8 solid; margin-top: 15px;padding-top: 15px;">
		      	<div class="form-actions center" style="text-align: center; background-color: #fff;">
                        <button type="button" class="btn btn-default default" data-dismiss="modal">取消</button>
                        <button type="submit" class="btn green save-user-info-class">提交</button>
<!--                             <button type="submit" class="btn green">提交</button> -->
                    </div>
		      </div>
		      </form>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		
		<div id="zijixiede" class="zijisanjiclass datepicker-select-dropdown datepicker-select-orient-left">
        	<div class="form" style="border: 1px solid #e7ecf1!important;">
        	<form class="form-horizontal form-bordered">
        	<div class="form-group">
        		<label class="control-label col-md-3">年</label>
        		<div class="col-md-9">
        			<select class="form-control form-filter" name="searchYear" id="searchYear">
	        			<option value="">请选择</option>
	        			<option value="2000">2000</option>
	        			<option value="2001">2001</option>
	        			<option value="2002">2002</option>
	        			<option value="2003">2003</option>
	        			<option value="2004">2004</option>
	        			<option value="2005">2005</option>
	        			<option value="2006">2006</option>
	        			<option value="2007">2007</option>
	        			<option value="2008">2008</option>
	        			<option value="2009">2009</option>
	        			<option value="2010">2010</option>
	        			<option value="2011">2011</option>
	        		</select>
        		</div>
        	</div>
        	<div class="form-group">
        		<label class="control-label col-md-3">月</label>
        		<div class="col-md-9">
        		<select class="form-control form-filter" name="searchMonth" id="searchMonth">
        			<option value="">请选择</option>
        		</select>
        		</div>
        	</div>
        	<div class="form-group">
	        	<label class="control-label col-md-3">日</label>
	        	<div class="col-md-9">
	        		<select class="form-control form-filter" name="searchDay" id="searchDay">
	        			<option value="">请选择</option>
	        		</select>
        		</div>
        	</div>
        	<div class="form-group">
        		<div class="col-md-11">
        			<div class="btn btn-default quedingbtn" >确定</div>
        		</div>
        	</div>
        	</form>
        	</div>
        </div>
		
     <!--[if lt IE 9]>
<script src="<%=basePath %>theme/assets/global/plugins/respond.min.js"></script>
<script src="<%=basePath %>theme/assets/global/plugins/excanvas.min.js"></script> 
<script src="<%=basePath %>theme/assets/global/plugins/ie8.fix.min.js"></script> 
<![endif]-->
     <!-- BEGIN CORE PLUGINS -->
	<script src="<%=basePath %>theme/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
        <script src="<%=basePath %>theme/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="<%=basePath %>theme/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
        <script src="<%=basePath %>theme/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="<%=basePath %>theme/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
        <script src="<%=basePath %>theme/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
        <!-- END CORE PLUGINS -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <script src="<%=basePath %>theme/assets/global/scripts/datatable.js" type="text/javascript"></script>
        <script src="<%=basePath %>theme/assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
        <script src="<%=basePath %>theme/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
        <script src="<%=basePath %>theme/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
        <script src="<%=basePath %>theme/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL SCRIPTS -->
        <script src="<%=basePath %>theme/assets/global/scripts/app.js" type="text/javascript"></script>
        <!-- END THEME GLOBAL SCRIPTS -->
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
        <script src="<%=basePath %>theme/assets/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
        <script src="<%=basePath %>theme/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
        <script src="<%=basePath %>theme/script/userInfo/table-datatables-ajax-user-info.js" type="text/javascript"></script>
        <script src="<%=basePath %>theme/script/formvalidateadd.js" type="text/javascript"></script>
        <script src="<%=basePath %>theme/script/userInfo/form-validate-user-info.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL SCRIPTS -->
        <!-- BEGIN THEME LAYOUT SCRIPTS -->
        <script src="<%=basePath %>theme/assets/layouts/layout/scripts/layout.js" type="text/javascript"></script>
        <script src="<%=basePath %>theme/assets/layouts/layout/scripts/menu.js?a=22" type="text/javascript"></script>
        <script src="<%=basePath %>theme/assets/layouts/layout/scripts/demo.js" type="text/javascript"></script>
        <script src="<%=basePath %>theme/script/fileUploadLy.js" type="text/javascript"></script>
        <script src="<%=basePath %>theme/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
        <script src="<%=basePath %>theme/assets/layouts/global/scripts/quick-nav.min.js" type="text/javascript"></script>
        
     <!-- END THEME LAYOUT SCRIPTS -->
     <script>
		jQuery(document).ready(function(){
			$(".add-btn-class").click(function(){
				if (jQuery().datepicker) {
		            $('.date-picker').datepicker({
		                rtl: App.isRTL(),
						"language":"zh-CN",
		                "orientation": "left",
		                "autoclose": true
		            });
		            //$('body').removeClass("modal-open"); // fix bug when inline picker is used in modal
		        }
				$("#editUserId").val("");
				$("#user-info-form-id")[0].reset();
				$(".herder-image").attr("src",App.domain()+"/theme/assets/pages/media/profile/profile_user.jpg");
				$("#user-info-form-id").find(".has-error").removeClass('has-error');
				$("#user-info-form-id").find(".help-block").remove();
				$(".add-or-edit-modal-class").modal("show");
			});
			
			
			$("#datatable_ajax_user_info").on("click",".edit-user-info-class",function(){
				var userInfoId = $(this).data("id");
				$.ajax({
					url:App.domain()+"/userInfo/editFind",
					data:{id:userInfoId},
	                type:"post",
					success: function(da){
						if(da.code==0){
							$("#editUserId").val(userInfoId);
							if (jQuery().datepicker) {
					            $('.date-picker').datepicker({
					                rtl: App.isRTL(),
									"language":"zh-CN",
					                "orientation": "left",
					                "autoclose": true
					            });
					        }
							$("#user-info-form-id")[0].reset();
							var d = da.reData;
							console.info(d);
							$("input[name='age']").val(d.age);
							$("input[name='birthday']").val(d.birthday);
							$("select[name='education']").val(d.education);
							$("input[name='graduate']").val(d.graduate);
							$("input[name='graduation']").val(d.graduation);
							$("input[name='height']").val(d.height);
							$("input[name='idCar']").val(d.idCar);
							$("input[name='name']").val(d.name);
							$("input[name='nations']").val(d.nations);
							$("input[name='picIcon']").val(d.picIcon);
							$("select[name='sex']").val(d.sex);
							$("input[name='specialty']").val(d.specialty);
							$("input[name='tel']").val(d.tel);
							$("input[name='id']").val(d.id);
							$("input[name='weight']").val(d.weight);
							if(d.picIcon!=null&&d.picIcon!=""&&d.picIcon!=undefined){
								$(".herder-image").attr("src",d.picIcon);
							}else{
								$(".herder-image").attr("src",App.domain()+"/theme/assets/pages/media/profile/profile_user.jpg");
							}
							$("#user-info-form-id").find(".has-error").removeClass('has-error');
							$("#user-info-form-id").find(".help-block").remove();
							$(".add-or-edit-modal-class").modal("show");
						}else{
							bootbox.alert(da.mes);
						}
						App.unblockUI();
					},
					error: function(a,b,c){
						var status = a.status;
						if(b=='timeout'){
							bootbox.alert("不好意思  超时了 ");
						}else{
							App.errorAlertStatus(status);
						}
						App.unblockUI();
					}
				});
			});
			
			$("#datatable_ajax_user_info").on("click",".delete-user-info-class",function(){
				var userInfoId = $(this).data("id");
				//
				$.ajax({
					url:App.domain()+"/userInfo/deleteUserInfo",
					data:{id:userInfoId},
	                type:"post",
					success: function(da){
						if(da.code==0){
							var oTable = $('#datatable_ajax_user_info').dataTable();
							oTable.fnDraw();
						}else{
							bootbox.alert(da.mes);
						}
						App.unblockUI();
					},
					error: function(a,b,c){
						var status = a.status;
						if(b=='timeout'){
							bootbox.alert("不好意思  超时了 ");
						}else{
							App.errorAlertStatus(status);
						}
						App.unblockUI();
					}
				});
			});
			
			
			FileUploadLy.upLoade({"id":"uploasd","selectType":"image/gif,image/jpeg,image/jpg,image/png,image/svg","aout":false,"btnText":"更改头像","changeFn":function(files){
				console.info(files[0].name);
				var strc = getObjectURL(files[0]);
				console.info(strc);
				$(".herder-image").attr("src",strc);
			}});
			//建立一個可存取到該file的url
			function getObjectURL(file) {
				var url = null ;
				if (window.createObjectURL!=undefined) { // basic
					url = window.createObjectURL(file) ;
				} else if (window.URL!=undefined) { // mozilla(firefox)
					url = window.URL.createObjectURL(file) ;
				} else if (window.webkitURL!=undefined) { // webkit or chrome
					url = window.webkitURL.createObjectURL(file) ;
				}
				return url ;
			}
			
			$("#userBirthdayid").focus(function(){ 
				//var offset = this.component ? this.component.parent().offset() : this.element.offset();
				var offset = $(this).offset();
				console.info(offset);

				var paddingTop = $("#zijixiede").css('padding-top');
				console.info(paddingTop);

				var height =  $(this).outerHeight(false);
				console.info(height);
				var width =  $(this).outerWidth(false);
				console.info(width);

				var scrollTop = $(document).scrollTop();
				console.info(scrollTop);

				var outerHeight = $("#zijixiede").outerHeight();
				console.info(outerHeight);
				var top_overflow = -scrollTop + offset.top - outerHeight;
				console.info(top_overflow);
				var yorient = top_overflow < 0 ? 'bottom' : 'top';
				$("#zijixiede").addClass('datepicker-select-orient-' + yorient);
				var top = offset.top;
				if (yorient === 'top')
					top -= outerHeight + parseInt(paddingTop);
				else
					top += height;
				var left = offset.left;
				console.info(left);
				$("#zijixiede").css({ 
					top: top,
					left: left
				});
				$("#zijixiede").show();
			});
			$(document).mousedown(function(e){
				if(!($("#zijixiede").find(e.target).length)&&!($("#userBirthdayid").is(e.target)))
					$("#zijixiede").hide();
			});
			$(".quedingbtn").click(function(){ 
				console.info("拉克建档立卡京东方拉克");
				if($("#searchYear").val()!=null&&$("#searchYear").val()!=""&&$("#searchYear").val()!=undefined){
					var searchDate ; 
					if($("#searchMonth").val()==null||$("#searchMonth").val()==""||$("#searchMonth").val()==undefined){
						searchDate = $("#searchYear").val();
					}else if($("#searchDay").val()==null||$("#searchDay").val()==""||$("#searchDay").val()==undefined){
						searchDate = $("#searchYear").val()+"-"+$("#searchMonth").val();
					}else{
						searchDate = $("#searchYear").val()+"-"+$("#searchMonth").val()+"-"+$("#searchDay").val();
					}
					$("#userBirthdayid").val(searchDate);
					$("#zijixiede").hide();
				}
			});
			
			var Months = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
			
			
			
			$("#searchYear").change(function(){
				var searchMoth = $("#searchMonth");
				var searchYearValue = $(this).val();
				var searchDay = $("#searchDay");
				if(searchYearValue==null||searchYearValue==""||searchYearValue==undefined){
					$(searchMoth).html('<option value="">请选择</option>');
					$(searchDay).html('<option value="">请选择</option>');
				}else{
					if(searchMoth.children().length<2){
						$(searchMoth).html('<option value="">请选择</option>');
						for(var i = 1 ; i < 13 ; i++){
							$(searchMoth).append('<option value="'+i+'">'+i+'</option>');
						}
					}
					var searchMothValue = $(searchMoth).val();
					var isLeapYear = IsPinYear(searchYearValue);
					$(searchDay).html('<option value="">请选择</option>');
					if(searchMothValue == 2 && isLeapYear){
						for(var i = 1 ; i < 30 ;i ++){
							$(searchDay).append('<option value="'+i+'">'+i+'</option>');
						}
					}else if(searchMothValue == "" || searchMothValue == null || searchMothValue == undefined){
					}else{
						var monthNum = new Number(searchMothValue);
						var monthLenth = Months[monthNum-1];
						for(var i = 1 ;i <= monthLenth ; i++){
							$(searchDay).append('<option value="'+i+'">'+i+'</option>');
						}
					}
				}
			});
			$("#searchMonth").change(function(){
				var searchYearValue = $("#searchYear").val();
				if(searchYearValue==null||searchYearValue==""||searchYearValue==undefined){
					$(this).html('<option value="">请选择</option>');
				}
				var searchDay = $("#searchDay");
				var searchMothValue = $(this).val();
				$(searchDay).html('<option value="">请选择</option>');
				if(searchMothValue != "" && searchMothValue != null && searchMothValue != undefined){
					var isLeapYear = IsPinYear(searchYearValue);
					if(searchMothValue == 2 && isLeapYear){
						for(var i = 1 ; i < 30 ;i ++){
							$(searchDay).append('<option value="'+i+'">'+i+'</option>');
						}
					}else{
						var monthNum = new Number(searchMothValue);
						var monthLenth = Months[monthNum-1];
						for(var i = 1 ;i <= monthLenth ; i++){
							$(searchDay).append('<option value="'+i+'">'+i+'</option>');
						}
					}
				}
			});
			
			//判断是否闰平年
			function IsPinYear(year)
			{
				return(0 == year%4 && (year%100 !=0 || year%400 == 0));
			}
			
		});
	</script>
 </body>
</html>
