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
<%-- 		<link href="<%=basePath %>theme/assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" /> --%>
<%--         <link href="<%=basePath %>theme/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" /> --%>
<%--         <link href="<%=basePath %>theme/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" /> --%>
		<!-- BEGIN THEME GLOBAL STYLES -->
        <link href="<%=basePath %>theme/assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
        <link href="<%=basePath %>theme/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
        <!-- END THEME GLOBAL STYLES -->
        <!-- BEGIN THEME LAYOUT STYLES -->
        <link href="<%=basePath %>theme/assets/layouts/layout/css/layout.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=basePath %>theme/assets/layouts/layout/css/themes/darkblue.min.css" rel="stylesheet" type="text/css" id="style_color" />
        <link href="<%=basePath %>theme/assets/layouts/layout/css/custom.min.css" rel="stylesheet" type="text/css" />
        <!-- END THEME LAYOUT STYLES -->
        <link rel="icon" type="image/png" href="<%=basePath %>theme/assets/global/img/favicon32-32.ico" sizes="32x32" />
		<link rel="icon" type="image/png" href="<%=basePath %>theme/assets/global/img/favicon16-16.ico" sizes="16x16" />

  </head>
  
  <body class="page-header-fixed page-sidebar-closed-hide-logo page-container-bg-solid page-content-white">
    <div class="page-wrapper">
         <c:import url="/common/header.jsp"></c:import> 
         <div class="clearfix"> </div>
         <div class="page-container">
             <div class="page-sidebar-wrapper">
                 <div class="page-sidebar navbar-collapse collapse">
                     <c:import url="/common/menu.jsp"></c:import>
                 </div>
             </div>
             <div class="page-content-wrapper" >
             	 <!-- BEGIN CONTENT BODY -->
             	 <div class="page-content" id="noticeId">
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
                    		<div class="profile-content">
                    			<div class="row">
                    			<div class="portlet light portlet-fit portlet-datatable bordered">
                        			<div class="portlet-title">
						                <div class="caption">
						                    <span class="caption-subject font-dark sbold uppercase">额额 额 额</span>
						                </div>
						            </div>
                                    <div class="portlet-body">
                                    	<form action="#" class="form-horizontal form-bordered" id="cmb-form-id">
											<div class="form-group">
												<label class="control-label col-md-2">现汇买入价</label>
												<div class="col-md-8">
													<input type="text" name="mn" value="${mn }" class="form-control">
												</div>
											</div>
											<div class="form-group">
												<label class="control-label col-md-2">通知手机号</label>
												<div class="col-md-8">
													<input type="text" name="mobles" value="${moble }" class="form-control">
												</div>
												<div class="control-label btn btn-info btn-sm add-moble"><span aria-hidden="true" class="icon-plus"></span></div>
											</div>
											<div class="form-group">
												<label class="control-label col-md-2">通知次数</label>
												<div class="col-md-8">
													<input type="text" name="falge" value="${falge }" class="form-control">
												</div>
											</div>
											<div class="margin-top-10">
												<button type="submit" class="btn green save-person-pass-class">确认</button>
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
         <c:import url="/common/bottom.jsp"></c:import>
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
<%--     <script src="<%=basePath %>theme/assets/global/scripts/datatable.js" type="text/javascript"></script> --%>
<%--     <script src="<%=basePath %>theme/assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script> --%>
<%--     <script src="<%=basePath %>theme/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script> --%>
    <script src="<%=basePath %>theme/assets/global/plugins/jquery-validation/js/jquery.validate.js" type="text/javascript"></script>
    <script src="<%=basePath %>theme/assets/global/plugins/jquery-validation/js/jquery.metadata.js" type="text/javascript"></script>
    <!-- END PAGE LEVEL PLUGINS -->
    <!-- BEGIN THEME GLOBAL SCRIPTS -->
    <script src="<%=basePath %>theme/assets/global/scripts/app.js" type="text/javascript"></script>
    <!-- END THEME GLOBAL SCRIPTS -->
    <!-- BEGIN PAGE LEVEL SCRIPTS -->
    <script src="<%=basePath %>theme/assets/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
    <!-- END PAGE LEVEL SCRIPTS -->
    <!-- BEGIN THEME LAYOUT SCRIPTS -->
    <script src="<%=basePath %>theme/assets/layouts/layout/scripts/layout.js" type="text/javascript"></script>
    <script src="<%=basePath %>theme/assets/layouts/layout/scripts/menu.js?a=22" type="text/javascript"></script>
    <script src="<%=basePath %>theme/assets/layouts/layout/scripts/demo.js" type="text/javascript"></script>
<%--     <script src="<%=basePath %>theme/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script> --%>
<%--     <script src="<%=basePath %>theme/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js" type="text/javascript"></script> --%>
   <script src="<%=basePath %>theme/script/formvalidateadd.js" type="text/javascript"></script>
   <script src="<%=basePath %>theme/script/cmbv/form-validate-cmbv.js" type="text/javascript"></script>
   <script type="text/javascript">
   jQuery(document).ready(function(){
	   
	   
	   
   })
   </script>
  </body>
</html>
