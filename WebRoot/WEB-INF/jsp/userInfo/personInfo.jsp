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

  </head>
  
  <body class="page-header-fixed page-sidebar-closed-hide-logo page-container-bg-solid page-content-white">
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
             	 <div class="page-content" id="personInfoId">
             	 	<div class="page-bar">
                        <ul class="page-breadcrumb">
                            <li>
                                <a href="index.html">首页</a>
                                <i class="fa fa-circle"></i>
                            </li>
                        </ul>
                    </div>
                    <h1 class="page-title">个人资料
                        <small></small>
                    </h1>
                    <div class="row">
                    	<div class="col-md-12">
                    		<div class="profile-content">
                    			<div class="row">
                    				<div class="col-md-12">
										<div class="portlet light ">
											<div class="portlet-title tabbable-line">
												<div class="caption caption-md">
													<i class="icon-globe theme-font hide"></i>
													<span class="caption-subject font-blue-madison bold uppercase">账号</span>
												</div>
												<ul class="nav nav-tabs">
													<li class="active">
														<a href="#tab_1_1" data-toggle="tab" aria-expanded="true">个人信息</a>
													</li>
													<li class="">
														<a href="#tab_1_2" data-toggle="tab" aria-expanded="false">修改密码</a>
													</li>
												</ul>
											</div>
											<div class="portlet-body">
												<div class="tab-content">
													<div class="tab-pane active" id="tab_1_1">
														<form action="#"  class="form-horizontal form-bordered" id="person-info-form-id">
															<input type="hidden" name="id" value="${userInfo.id }">
															<div class="row">
																<div class="col-md-4">
																	<div class="row">
											                			<div class="col-md-1"></div>
											                			<div class="col-md-10" style="text-align: center;margin-top: 50px;">
											                				<c:if test="${empty userInfo.picIcon }">
											                					<img class="herder-image" src="<%=basePath %>theme/assets/pages/media/profile/profile_user.jpg" style="float: none;width: 50%; height: 50%; margin: 0px auto; border-radius: 50% !important;"/>
											                				</c:if>
											                				<c:if test="${not empty userInfo.picIcon }">
											                					<img class="herder-image" src="${userInfo.picIcon  }" style="float: none;width: 50%; height: 50%; margin: 0px auto; border-radius: 50% !important;"/>
											                				</c:if>
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
																<div class="col-md-3">
																	<div class="form-group">
																		<label class="control-label">姓名</label>
																		<input class="form-control" name="name" type="text" value="${userInfo.name }" placeholder="请输入姓名" >
																	</div>
																	<div class="form-group">
																		<label class="control-label">性别</label>
																		<select class="form-control" name="sex">
									                            			<option value="男" <c:if test="${userInfo.sex == '男' }">selected</c:if> >男</option>
									                            			<option value="女" <c:if test="${userInfo.sex == '女' }">selected</c:if> >女</option>
									                            			<option value="其他" <c:if test="${userInfo.sex == '其他' }">selected</c:if> >其他</option>
									                            		</select>
																	</div>
																	<div class="form-group">
																		<label class="control-label">年龄</label>
																		<input class="form-control form-control-inline" name="age" type="text" maxlength="3" value="${userInfo.age }" placeholder="请输入年龄" >
																	</div>
																	<div class="form-group">
																		<label class="control-label">身份证号</label>
																		<input class="form-control form-control-inline" name="idCar" maxlength="18" type="text" value="${userInfo.idCar }" placeholder="请输入身份证号" >
																	</div>
																	<div class="form-group">
																		<label class="control-label">生日</label>
																		<div class="input-group date date-picker" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
																		    <input class="form-control form-control-inline" name="birthday" type="text" value="${userInfo.birthday }" placeholder="请输入生日" >
																		    <span class="input-group-btn">
																		        <button class="btn default" type="button">
																		            <i class="fa fa-calendar"></i>
																		        </button>
																		    </span>
																		</div>
																	</div>
																	<div class="form-group">
																		<label class="control-label">手机号</label>
																		<input class="form-control form-control-inline" name="tel" maxlength="11" type="tel" value="${userInfo.tel }" placeholder="请输入手机号" >
																	</div>
																	<div class="form-group">
																		<label class="control-label">民族</label>
																		<input class="form-control form-control-inline" name="nations" type="text" value="${userInfo.nations }" placeholder="请输入民族" >
																	</div>
																</div>
																<div class="col-md-1">
																</div>
																<div class="col-md-3">
																	<div class="form-group">
																		<label class="control-label">身高cm</label>
																		<input class="form-control form-control-inline" name="height" maxlength="3" type="text" value="${userInfo.height }" placeholder="请输入身高" >
																	</div>
																	<div class="form-group">
																		<label class="control-label">体重kg</label>
																		<input class="form-control form-control-inline" name="weight" type="text" maxlength="3" value="${userInfo.weight }" placeholder="请输入体重" >
																	</div>
																	<div class="form-group">
																		<label class="control-label">最高学历</label>
																		<select class="form-control" name="education">
									                                  		<option value="博士" <c:if test="${userInfo.education == '博士' }">selected</c:if> >博士</option>
									                                  		<option value="硕士" <c:if test="${userInfo.education == '硕士' }">selected</c:if> >硕士</option>
									                                  		<option value="本科" <c:if test="${userInfo.education == '本科' }">selected</c:if> >本科</option>
									                                  		<option value="专科" <c:if test="${userInfo.education == '专科' }">selected</c:if> >专科</option>
									                                  		<option value="高中" <c:if test="${userInfo.education == '高中' }">selected</c:if> >高中</option>
									                                  		<option value="中专" <c:if test="${userInfo.education == '中专' }">selected</c:if> >中专</option>
									                                  		<option value="初中" <c:if test="${userInfo.education == '初中' }">selected</c:if> >初中</option>
									                                  		<option value="小学" <c:if test="${userInfo.education == '小学' }">selected</c:if> >小学</option>
									                                  	</select>
																	</div>
																	<div class="form-group">
																		<label class="control-label">毕业院校</label>
																		<input class="form-control form-control-inline" name="graduate" type="text" value="${userInfo.graduate }" placeholder="请输入毕业院校" >
																	</div>
																	<div class="form-group">
																		<label class="control-label">专业</label>
																		<input class="form-control form-control-inline" name="specialty" type="text" value="${userInfo.specialty }" placeholder="请输入专业" >
																	</div>
																	<div class="form-group">
																		<label class="control-label">毕业时间</label>
																		<div class="input-group date date-picker" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
																		    <input class="form-control form-control-inline" name="graduation" type="text" value="${userInfo.graduation }" placeholder="请输入毕业时间" >
																		    <span class="input-group-btn">
																		        <button class="btn default" type="button">
																		            <i class="fa fa-calendar"></i>
																		        </button>
																		    </span>
																		</div>
																	</div>
																</div>
															</div>
															<div class="row">
																<div class="margin-top-10">
																	<button type="submit" class="btn green save-person-info-class">确认修改个人信息</button>
																	<a href="javascript:;" class="btn default cancel-person-info-class"> 取消 </a>
																</div>
															</div>
														</form>
													</div>
													<div class="tab-pane" id="tab_1_2">
														<form action="#" id="person-pass-form-id">
															<div class="form-group">
																<label class="control-label">原密码</label>
																<input type="password" name="oldPass" class="form-control">
															</div>
															<div class="form-group">
																<label class="control-label">新密码</label>
																<input type="password" name="newPass" id="newPass" class="form-control">
															</div>
															<div class="form-group">
																<label class="control-label">再次输入新密码</label>
																<input type="password" name="againNewPass" class="form-control">
															</div>
															<div class="margin-top-10">
																<button type="submit" class="btn green save-person-pass-class">确认修改密码</button>
																<a href="javascript:;" class="btn default cancel-person-pass-class"> 取消 </a>
															</div>
														</form>
													</div>
												</div>
											</div>
										</div>
                                    </div>
                    			</div>
                    		</div>
                    	</div>
                    </div>
             	 </div>
             </div>
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
    <script src="<%=basePath %>theme/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
    <script src="<%=basePath %>theme/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js" type="text/javascript"></script>
    <!-- END PAGE LEVEL PLUGINS -->
    <!-- BEGIN THEME GLOBAL SCRIPTS -->
    <script src="<%=basePath %>theme/assets/global/scripts/app.js" type="text/javascript"></script>
    <!-- END THEME GLOBAL SCRIPTS -->
    <!-- BEGIN PAGE LEVEL SCRIPTS -->
    <script src="<%=basePath %>theme/assets/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
    <script src="<%=basePath %>theme/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
    <script src="<%=basePath %>theme/script/formvalidateadd.js" type="text/javascript"></script>
    <script src="<%=basePath %>theme/script/userInfo/form-validate-person-info.js?a=5" type="text/javascript"></script>
    <!-- END PAGE LEVEL SCRIPTS -->
    <!-- BEGIN THEME LAYOUT SCRIPTS -->
    <script src="<%=basePath %>theme/assets/layouts/layout/scripts/layout.js" type="text/javascript"></script>
    <script src="<%=basePath %>theme/assets/layouts/layout/scripts/menu.js?a=22" type="text/javascript"></script>
    <script src="<%=basePath %>theme/assets/layouts/layout/scripts/demo.js" type="text/javascript"></script>
    <script src="<%=basePath %>theme/script/fileUploadLy.js" type="text/javascript"></script>
    <script src="<%=basePath %>theme/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
    <script src="<%=basePath %>theme/assets/layouts/global/scripts/quick-nav.min.js" type="text/javascript"></script>
   
   <script type="text/javascript">
   jQuery(document).ready(function(){
	   
	   if (jQuery().datepicker) {
           $('.date-picker').datepicker({
               rtl: App.isRTL(),
				"language":"zh-CN",
               "orientation": "left",
               "autoclose": true
           });
           //$('body').removeClass("modal-open"); // fix bug when inline picker is used in modal
       }
	   FileUploadLy.upLoade({"id":"uploasd","selectType":"image/gif,image/jpeg,image/jpg,image/png,image/svg","aout":false,"btnText":"更改头像","changeFn":function(files){
			console.info(files[0].name);
			var strc = FileUploadLy.getObjectURL(files[0]);
			console.info(strc);
			$(".herder-image").attr("src",strc);
		}});
	   
	   $(".cancel-person-info-class").click(function(){
		   location.href = location.href ;
	   });
	   $(".cancel-person-pass-class").click(function(){
		   $("#person-pass-form-id")[0].reset();
	   });
   })
   </script>
  </body>
</html>
