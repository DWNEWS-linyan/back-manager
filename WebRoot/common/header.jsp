<%@page import="com.ly.po.UserInfo"%>
<%@ page language="java" import="java.util.*,java.awt.*,javax.swing.*,java.awt.event.*,javax.swing.border.*"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String handerImageDuf = basePath+"theme/assets/layouts/layout/img/avatar3_small.jpg";

String picIcon = (String)request.getSession().getAttribute("picIcon");
if(picIcon==null||picIcon.equals("")){
	picIcon = handerImageDuf;
}
String loginName = (String)request.getSession().getAttribute("loginName");
String infoName = (String)request.getSession().getAttribute("infoName");

%>

<div class="page-header navbar navbar-fixed-top">
<input type="hidden" value="<%=basePath %>" id="doMainURL"/>
<input type="hidden" value="<%=loginName %>" id="loginName"/>
     <!-- BEGIN HEADER INNER -->
     <div class="page-header-inner ">
         <!-- BEGIN LOGO -->
         <div class="page-logo">
             <a href="index.html">
                 <img src="<%=basePath %>theme/assets/layouts/layout/img/logo.png" alt="logo" class="logo-default" /> </a>
             <div class="menu-toggler sidebar-toggler">
                 <span></span>
             </div>
         </div>
         <!-- END LOGO -->
         <!-- BEGIN RESPONSIVE MENU TOGGLER -->
         <a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse">
             <span></span>
         </a>
         <!-- END RESPONSIVE MENU TOGGLER -->
         <!-- BEGIN TOP NAVIGATION MENU -->
         <div class="top-menu">
             <ul class="nav navbar-nav pull-right">
                 <!-- BEGIN NOTIFICATION DROPDOWN -->
                 <!-- DOC: Apply "dropdown-dark" class after "dropdown-extended" to change the dropdown styte -->
                 <!-- DOC: Apply "dropdown-hoverable" class after below "dropdown" and remove data-toggle="dropdown" data-hover="dropdown" data-close-others="true" attributes to enable hover dropdown mode -->
                 <!-- DOC: Remove "dropdown-hoverable" and add data-toggle="dropdown" data-hover="dropdown" data-close-others="true" attributes to the below A element with dropdown-toggle class -->
                 <li class="dropdown dropdown-extended dropdown-notification" id="header_notification_bar">
                     <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                         <i class="icon-bell"></i>
                         <span class="badge badge-default"> 7 </span>
                     </a>
                     <ul class="dropdown-menu">
                         <li class="external">
                             <h3>
                                 <span class="bold">12 pending</span> notifications</h3>
                             <a href="page_user_profile_1.html">view all</a>
                         </li>
                         <li>
                             <ul class="dropdown-menu-list scroller" style="height: 250px;" data-handle-color="#637283">
                                 <li>
                                     <a href="javascript:;">
                                         <span class="time">just now</span>
                                         <span class="details">
                                             <span class="label label-sm label-icon label-success">
                                                 <i class="fa fa-plus"></i>
                                             </span> New user registered. </span>
                                     </a>
                                 </li>
                                 <li>
                                     <a href="javascript:;">
                                         <span class="time">3 mins</span>
                                         <span class="details">
                                             <span class="label label-sm label-icon label-danger">
                                                 <i class="fa fa-bolt"></i>
                                             </span> Server #12 overloaded. </span>
                                     </a>
                                 </li>
                                 <li>
                                     <a href="javascript:;">
                                         <span class="time">10 mins</span>
                                         <span class="details">
                                             <span class="label label-sm label-icon label-warning">
                                                 <i class="fa fa-bell-o"></i>
                                             </span> Server #2 not responding. </span>
                                     </a>
                                 </li>
                                 <li>
                                     <a href="javascript:;">
                                         <span class="time">14 hrs</span>
                                         <span class="details">
                                             <span class="label label-sm label-icon label-info">
                                                 <i class="fa fa-bullhorn"></i>
                                             </span> Application error. </span>
                                     </a>
                                 </li>
                                 <li>
                                     <a href="javascript:;">
                                         <span class="time">2 days</span>
                                         <span class="details">
                                             <span class="label label-sm label-icon label-danger">
                                                 <i class="fa fa-bolt"></i>
                                             </span> Database overloaded 68%. </span>
                                     </a>
                                 </li>
                                 <li>
                                     <a href="javascript:;">
                                         <span class="time">3 days</span>
                                         <span class="details">
                                             <span class="label label-sm label-icon label-danger">
                                                 <i class="fa fa-bolt"></i>
                                             </span> A user IP blocked. </span>
                                     </a>
                                 </li>
                                 <li>
                                     <a href="javascript:;">
                                         <span class="time">4 days</span>
                                         <span class="details">
                                             <span class="label label-sm label-icon label-warning">
                                                 <i class="fa fa-bell-o"></i>
                                             </span> Storage Server #4 not responding dfdfdfd. </span>
                                     </a>
                                 </li>
                                 <li>
                                     <a href="javascript:;">
                                         <span class="time">5 days</span>
                                         <span class="details">
                                             <span class="label label-sm label-icon label-info">
                                                 <i class="fa fa-bullhorn"></i>
                                             </span> System Error. </span>
                                     </a>
                                 </li>
                                 <li>
                                     <a href="javascript:;">
                                         <span class="time">9 days</span>
                                         <span class="details">
                                             <span class="label label-sm label-icon label-danger">
                                                 <i class="fa fa-bolt"></i>
                                             </span> Storage server failed. </span>
                                     </a>
                                 </li>
                             </ul>
                         </li>
                     </ul>
                 </li>
                 <!-- END NOTIFICATION DROPDOWN -->
                 <!-- BEGIN INBOX DROPDOWN -->
                 <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
                 <li class="dropdown dropdown-extended dropdown-inbox" id="header_inbox_bar">
                     <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                         <i class="icon-envelope-open"></i>
                         <span class="badge badge-default"> 4 </span>
                     </a>
                     <ul class="dropdown-menu">
                         <li class="external">
                             <h3>You have
                                 <span class="bold">7 New</span> Messages</h3>
                             <a href="app_inbox.html">view all</a>
                         </li>
                         <li>
                             <ul class="dropdown-menu-list scroller" style="height: 275px;" data-handle-color="#637283">
                                 <li>
                                     <a href="#">
                                         <span class="photo">
                                             <img src="<%=basePath %>theme/assets/layouts/layout3/img/avatar2.jpg" class="img-circle" alt=""> </span>
                                         <span class="subject">
                                             <span class="from"> Lisa Wong </span>
                                             <span class="time">Just Now </span>
                                         </span>
                                         <span class="message"> Vivamus sed auctor nibh congue nibh. auctor nibh auctor nibh... </span>
                                     </a>
                                 </li>
                                 <li>
                                     <a href="#">
                                         <span class="photo">
                                             <img src="<%=basePath %>theme/assets/layouts/layout3/img/avatar3.jpg" class="img-circle" alt=""> </span>
                                         <span class="subject">
                                             <span class="from"> Richard Doe </span>
                                             <span class="time">16 mins </span>
                                         </span>
                                         <span class="message"> Vivamus sed congue nibh auctor nibh congue nibh. auctor nibh auctor nibh... </span>
                                     </a>
                                 </li>
                                 <li>
                                     <a href="#">
                                         <span class="photo">
                                             <img src="<%=basePath %>theme/assets/layouts/layout3/img/avatar1.jpg" class="img-circle" alt=""> </span>
                                         <span class="subject">
                                             <span class="from"> Bob Nilson </span>
                                             <span class="time">2 hrs </span>
                                         </span>
                                         <span class="message"> Vivamus sed nibh auctor nibh congue nibh. auctor nibh auctor nibh... </span>
                                     </a>
                                 </li>
                                 <li>
                                     <a href="#">
                                         <span class="photo">
                                             <img src="<%=basePath %>theme/assets/layouts/layout3/img/avatar2.jpg" class="img-circle" alt=""> </span>
                                         <span class="subject">
                                             <span class="from"> Lisa Wong </span>
                                             <span class="time">40 mins </span>
                                         </span>
                                         <span class="message"> Vivamus sed auctor 40% nibh congue nibh... </span>
                                     </a>
                                 </li>
                                 <li>
                                     <a href="#">
                                         <span class="photo">
                                             <img src="<%=basePath %>theme/assets/layouts/layout3/img/avatar3.jpg" class="img-circle" alt=""> </span>
                                         <span class="subject">
                                             <span class="from"> Richard Doe </span>
                                             <span class="time">46 mins </span>
                                         </span>
                                         <span class="message"> Vivamus sed congue nibh auctor nibh congue nibh. auctor nibh auctor nibh... </span>
                                     </a>
                                 </li>
                             </ul>
                         </li>
                     </ul>
                 </li>
                 <!-- END INBOX DROPDOWN -->
                 <!-- BEGIN TODO DROPDOWN -->
                 <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
                 <li class="dropdown dropdown-extended dropdown-tasks" id="header_task_bar">
                     <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                         <i class="icon-rocket"></i>
                         <span class="badge badge-default"> 255 </span>
                     </a>
                     <ul class="dropdown-menu extended tasks">
                         <li class="external">
                             <h3>您有
                                 <span class="bold">12 个待定</span> 任务</h3>
                             <a href="app_todo.html">查看所有</a>
                         </li>
                         <li>
                             <ul class="dropdown-menu-list scroller" style="height: 275px;" data-handle-color="#637283">
                                 <li>
                                     <a href="javascript:;">
                                         <span class="task">
                                             <span class="desc">项目开发</span>
                                             <span class="percent">65%</span>
                                         </span>
                                         <span class="progress">
                                             <span style="width: 65%;" class="progress-bar progress-bar-danger" aria-valuenow="65" aria-valuemin="0" aria-valuemax="100">
                                                 <span class="sr-only">65% Complete</span>
                                             </span>
                                         </span>
                                     </a>
                                 </li>
                                 <li>
                                     <a href="javascript:;">
                                         <span class="task">
                                             <span class="desc">数据迁移</span>
                                             <span class="percent">10%</span>
                                         </span>
                                         <span class="progress">
                                             <span style="width: 10%;" class="progress-bar progress-bar-warning" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100">
                                                 <span class="sr-only">10% Complete</span>
                                             </span>
                                         </span>
                                     </a>
                                 </li>
                             </ul>
                         </li>
                     </ul>
                 </li>
                 <!-- END TODO DROPDOWN -->
                 
                 <li class="dropdown dropdown-extended dropdown-calendar" id="header_calendar_bar">
                 	<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                 		<i class="icon-calendar"></i>
                 		<span class="badge badge-default"> 5 </span>
                 	</a>
                 	<ul class="dropdown-menu extended calendar">
                         <li class="external">
                             <h3>您有
                                 <span class="bold">12 个待定</span> 任务</h3>
                             <a href="app_todo.html">查看所有</a>
                         </li>
                         <li>
                             <ul class="dropdown-menu-list scroller" style="height: 275px;" data-handle-color="#637283">
                                 <li>
                                     <a href="javascript:;">
                                         <span class="task">
                                             <span class="desc">项目开发</span>
                                             <span class="percent">65%</span>
                                         </span>
                                         <span class="progress">
                                             <span style="width: 65%;" class="progress-bar progress-bar-danger" aria-valuenow="65" aria-valuemin="0" aria-valuemax="100">
                                                 <span class="sr-only">65% Complete</span>
                                             </span>
                                         </span>
                                     </a>
                                 </li>
                                 <li>
                                     <a href="javascript:;">
                                         <span class="task">
                                             <span class="desc">数据迁移</span>
                                             <span class="percent">10%</span>
                                         </span>
                                         <span class="progress">
                                             <span style="width: 10%;" class="progress-bar progress-bar-warning" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100">
                                                 <span class="sr-only">10% Complete</span>
                                             </span>
                                         </span>
                                     </a>
                                 </li>
                             </ul>
                         </li>
                     </ul>
                 </li>
                 
                 <!-- BEGIN USER LOGIN DROPDOWN -->
                 <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
                 <li class="dropdown dropdown-user">
                     <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                         <img alt="" class="img-circle" src="<%=picIcon %>" />
                         <span class="username username-hide-on-mobile"> <%=infoName %> </span>
                         <i class="fa fa-angle-down"></i>
                     </a>
                     <ul class="dropdown-menu dropdown-menu-default">
                         <li>
                             <a href="<%=basePath %>userInfo/personInfo">
                                 <i class="icon-user"></i> 个人资料 </a>
                         </li>
                         <li>
                             <a href="app_calendar.html">
                                 <i class="icon-calendar"></i> 记事本 </a>
                         </li>
<!--                          <li> -->
<!--                              <a href="app_inbox.html"> -->
<!--                                  <i class="icon-envelope-open"></i> 收件箱 -->
<!--                                  <span class="badge badge-danger"> 3 </span> -->
<!--                              </a> -->
<!--                          </li> -->
                         <li class="divider"> </li>
                         <li>
                             <a href="<%=basePath %>sys/logout">
                                 <i class="icon-key"></i> 退出 </a>
                         </li>
                     </ul>
                 </li>
                 <!-- END USER LOGIN DROPDOWN -->
                 <!-- BEGIN QUICK SIDEBAR TOGGLER -->
                 <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
                 <li class="dropdown dropdown-quick-sidebar-toggler hide">
                     <a href="javascript:;" class="dropdown-toggle">
                         <i class="icon-logout"></i>
                     </a>
                 </li>
                 <!-- END QUICK SIDEBAR TOGGLER -->
             </ul>
         </div>
         <!-- END TOP NAVIGATION MENU -->
     </div>
     <!-- END HEADER INNER -->
 </div>