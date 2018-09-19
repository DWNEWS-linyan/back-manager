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
		<link href="<%=basePath %>theme/assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=basePath %>theme/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" />
        <link href="<%=basePath %>theme/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />
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
					<!-- 	                    <i class="icon-settings font-dark"></i> -->
						                    <span class="caption-subject font-dark sbold uppercase">公告管理</span>
						                </div>
						                <div class="actions">
						                    <div class="btn-group btn-group-devided" data-toggle="buttons">
						                        <label class="btn btn-transparent grey-salsa btn-outline btn-circle btn-sm add-btn-class">
					                            	添加公告
					                            </label>
						                    </div>
						                </div>
						            </div>
                                    <div class="portlet-body">
                                        <div class="table-container">
                                        <table class="table table-striped table-bordered table-hover table-checkable" id="datatable_ajax_notice">
                                                <thead>
                                                    <tr role="row" class="heading">
                                                        <th style="width:5%;"> ID </th>
                                                        <th style="width:20%;"> 标题 </th>
                                                        <th style="width:20%;"> 目的 </th>
                                                        <th style="width:30%;"> 内容 </th>
                                                        <th style="width:15%;"> 发布日期 </th>
                                                        <th style="width:10%;"> 操作 </th>
                                                    </tr>
                                                    <tr role="row" class="filter">
                                                        <td>
                                                        	<input type="text" class="form-control form-filter input-sm" name="noticeId">
                                                        </td>
                                                        <td>
                                                        	<input type="text" class="form-control form-filter input-sm" name="noticeTitle">
                                                        </td>
                                                        <td>
                                                        	<input type="text" class="form-control form-filter input-sm" name="noticeTarget">
                                                        </td>
                                                        <td>
                                                        	<input type="text" class="form-control form-filter input-sm" name="noticeContent">
                                                        </td>
                                                        <td>
                                                        	<div class="input-group date date-picker margin-bottom-5" data-date-format="yyyy-mm-dd">
                                                                <input type="text" class="form-control form-filter input-sm" readonly="" name="publish_date_start" placeholder="From">
                                                                <span class="input-group-btn">
                                                                    <button class="btn btn-sm default" type="button">
                                                                        <i class="fa fa-calendar"></i>
                                                                    </button>
                                                                </span>
                                                            </div>
                                                            <div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
                                                                <input type="text" class="form-control form-filter input-sm" readonly="" name="publish_date_end" placeholder="To">
                                                                <span class="input-group-btn">
                                                                    <button class="btn btn-sm default" type="button">
                                                                        <i class="fa fa-calendar"></i>
                                                                    </button>
                                                                </span>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <div class="margin-bottom-5">
                                                                <button class="btn btn-sm green btn-outline filter-submit margin-bottom">
                                                                    <i class="fa fa-search"></i> 搜索</button> <button class="btn btn-sm red btn-outline filter-cancel">
                                                                <i class="fa fa-times"></i> 重置</button>
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
    <script src="<%=basePath %>theme/assets/global/scripts/datatable.js" type="text/javascript"></script>
    <script src="<%=basePath %>theme/assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
    <script src="<%=basePath %>theme/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
    <script src="<%=basePath %>theme/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
    <script src="<%=basePath %>theme/script/notice/table-datatables-ajax-notice.js" type="text/javascript"></script>
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
    <script src="<%=basePath %>theme/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
    <script src="<%=basePath %>theme/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js" type="text/javascript"></script>
   <script src="<%=basePath %>theme/script/notice/form-validate-notice.js" type="text/javascript"></script>
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
   })
   </script>
  </body>
</html>
