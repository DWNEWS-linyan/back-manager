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
        <meta content="width=device-width, initial-scale=1" name="viewport" />
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
        <link href="<%=basePath %>theme/assets/global/plugins/bootstrap-select/css/bootstrap-select.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=basePath %>theme/assets/global/plugins/jquery-multi-select/css/multi-select.css" rel="stylesheet" type="text/css" />
        <link href="<%=basePath %>theme/assets/global/plugins/bootstrap-modal/css/bootstrap-modal.css" rel="stylesheet" type="text/css" />
        <link href="<%=basePath %>theme/assets/global/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=basePath %>theme/assets/global/plugins/select2/css/select2-bootstrap.min.css" rel="stylesheet" type="text/css" />
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
        <link rel="icon" type="image/png" href="<%=basePath %>theme/assets/global/img/favicon32-32.ico" sizes="32x32" />
		<link rel="icon" type="image/png" href="<%=basePath %>theme/assets/global/img/favicon16-16.ico" sizes="16x16" />
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
        .ms-container{
        	width:100%;
        }
        </style>
        
  </head>

  <body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
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
                    <div class="page-content" id="mainBodyId">
                        <div class="page-bar">
                            <ul class="page-breadcrumb">
                                <li>
                                    <a href="${basePath }sys/main">首页</a>
                                    <i class="fa fa-circle"></i>
                                </li>
                            </ul>
                        </div>
                        <div class="row">
                        	<div class="portlet light portlet-fit portlet-datatable bordered">
                               <div class="portlet-title">
				                 <div class="caption">
			<!-- 	                    <i class="icon-settings font-dark"></i> -->
				                    <span class="caption-subject font-dark sbold uppercase">系统用户管理</span>
				                 </div>
				                 <div class="actions">
				                    <div class="btn-group btn-group-devided" data-toggle="buttons">
				                        <label class="btn btn-transparent grey-salsa btn-outline btn-circle btn-sm add-btn-class">
			                            	添加系统用户
			                            </label>
				                    </div>
				                 </div>
				               </div>
                               <div class="portlet-body">
                                   <div class="table-container">
                                       <table class="table table-striped table-bordered table-hover table-checkable" id="datatable_ajax_sysuser">
                                           <thead>
                                               <tr role="row" class="heading">
                                                   <th style="width:10%;"> id </th>
                                                   <th style="width:10%;"> 用户名 </th>
                                                   <th style="width:10%;"> 用户详情id </th>
                                                   <th style="width:30%;"> 操作 </th>
                                               </tr>
                                               <tr role="row" class="filter">
                                                   <td><input type="text" class="form-control form-filter input-sm" name="userid"> </td> 
                                                   <td>
                                                       <input type="text" class="form-control form-filter input-sm" name="username"> </td>
                                                   <td>
                                                       <input type="text" class="form-control form-filter input-sm" name="userinfoid"> </td>
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
            <c:import url="/common/bottom.jsp"></c:import>
        </div>
        <div class="quick-nav-overlay"></div>
        
        <div class="modal fade" id="fenpeiquanxian" tabindex="-1" role="dialog" style="top:0px;">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title">分配权限</h4>
		      </div>
		      <div class="modal-body">
		      <div class="row">
		      	<div class="col-md-12">
		      		<input type="hidden" name="sysuserid" id = "editsysuserid"/>
		        	<select multiple="multiple" class="multi-select" id="my_multi_select1" name="my_multi_select1[]">
	                </select>
                </div>
		      </div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        <button type="button" class="btn btn-primary save-sys-user-role">保存</button>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		
        <div class="modal fade" id="add-sys-user" role="dialog" style="top:0px;">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title">添加系统用户</h4>
		      </div>
		      <div class="modal-body">
		      <div class="row">
		      	<div class="col-md-12">
		      		<form action="#" class="form-horizontal form-bordered add-sys-user-form">
		      			<div class="form-body" id="formbodyid">
		      				 <div class="form-group">
                            	<label class="control-label col-md-3">用户名</label>
                            	<div class="col-md-7">
                            		<input class="form-control" name="name" type="text" value="" placeholder="请输入用户名" >
                            	</div>
                            </div>
                            <div class="form-group">
                            	<label class="control-label col-md-3">密码</label>
                            	<div class="col-md-7">
                            		<input class="form-control" name="pass" type="text" value="" placeholder="请输入密码" >
                            	</div>
                            </div>
                            <div class="form-group">
                            	<label class="control-label col-md-3">用户</label>
                            	<div class="col-md-7">
	                            	<select class="form-control select-ajax-class" name="userinfoid">
	                            		<option>请选择用户</option>
	                            	</select>
                            	</div>
                            </div>
		      			</div>
		      			<div class="modal-footer">
					        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					        <button type="submit" class="btn btn-primary save-sys-user-add">保存</button>
					      </div>
		      		</form>
                </div>
		      </div>
		      </div>
		      
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
        
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
        <script src="<%=basePath %>theme/assets/global/plugins/bootstrap-select/js/bootstrap-select.min.js" type="text/javascript"></script>
        <script src="<%=basePath %>theme/assets/global/plugins/jquery-multi-select/js/jquery.multi-select.js" type="text/javascript"></script>
        <script src="<%=basePath %>theme/assets/global/plugins/select2/js/select2.full.min.js" type="text/javascript"></script>
        <script src="<%=basePath %>theme/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL SCRIPTS -->
        <script src="<%=basePath %>theme/assets/global/scripts/app.js" type="text/javascript"></script>
        <script src="<%=basePath %>theme/assets/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
        <!-- END THEME GLOBAL SCRIPTS -->
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
         <script src="<%=basePath %>theme/script/sys/table-datatables-ajax-sysUser.js" type="text/javascript"></script>
         <script src="<%=basePath %>theme/script/sys/form-validate-sys-user.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL SCRIPTS -->
        <!-- BEGIN THEME LAYOUT SCRIPTS -->
        <script src="<%=basePath %>theme/assets/layouts/layout/scripts/layout.js" type="text/javascript"></script>
        <script src="<%=basePath %>theme/assets/layouts/layout/scripts/menu.js" type="text/javascript"></script>
        <script src="<%=basePath %>theme/assets/layouts/layout/scripts/demo.js" type="text/javascript"></script>
        <script src="<%=basePath %>theme/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
        <script src="<%=basePath %>theme/assets/layouts/global/scripts/quick-nav.min.js" type="text/javascript"></script>
       
     <!-- END THEME LAYOUT SCRIPTS -->
     <script>
     	jQuery(document).ready(function() {
     		$(".add-btn-class").click(function(){
     			$("#add-sys-user").modal("show");
     			$(".add-sys-user-form")[0].reset();
     			$(".add-sys-user-form").find(".has-error").removeClass('has-error');
     			$(".add-sys-user-form").find(".help-block").remove();
     			$(".select-ajax-class").val("");
     			$(".select2-selection__rendered").html("请选择用户");
     		});
     		function initSelect2(){
     			function formatRepo(repo) {
     	            if (repo.loading) return repo.text;

     	            var markup = "<div class='select2-result-repository clearfix'>" +
     	                "<div class='select2-result-repository__avatar'><img src='" + repo.picIcon + "' /></div>" +
     	                "<div class='select2-result-repository__meta'>" +
     	                "<div class='select2-result-repository__title'>" + repo.name + "</div>";

     	            if (repo.description) {
     	                markup += "<div class='select2-result-repository__description'>" + repo.description + "</div>";
     	            }

     	            markup += "<div class='select2-result-repository__statistics'>" +
     	                "<div class='select2-result-repository__forks'><span class='glyphicon glyphicon-flash'></span> " + repo.sex + " Forks</div>" +
     	                "<div class='select2-result-repository__stargazers'><span class='glyphicon glyphicon-star'></span> " + repo.age + " Stars</div>" +
     	                "<div class='select2-result-repository__watchers'><span class='glyphicon glyphicon-eye-open'></span> " + repo.tel + " Watchers</div>" +
     	                "</div>" +
     	                "</div></div>";

     	            return markup;
     	        }

     	        function formatRepoSelection(repo) {
     	            return repo.name || repo.text;
     	        }

     	        $(".select-ajax-class").select2({
     	            width: "off",
     	            ajax: {
     	                url: App.domain()+"/system/select2JSONData",
     	                dataType: 'json',
     	                delay: 250,
     	                data: function(params) {
     	                    return {
     	                        p: params.term, // search term
     	                        page: params.page
     	                    };
     	                },
     	                processResults: function(data, page) {
     	                    // parse the results into the format expected by Select2.
     	                    // since we are using custom formatting functions we do not need to
     	                    // alter the remote JSON data
     	                    return {
     	                        results: data.data
     	                    };
     	                },
     	                cache: true
     	            },
     	            escapeMarkup: function(markup) {
     	                return markup;
     	            }, // let our custom formatter work
     	            minimumInputLength: 1,
     	            templateResult: formatRepo,
     	            templateSelection: formatRepoSelection
     	        });
     		}
     		initSelect2();
			$("#datatable_ajax_sysuser").on("click",".resetpassbtn",function(){
				var sysuserid = $(this).data("id");
				bootbox.confirm("你确定要重置密码吗?", function(result) {
                   if(result){
                   		App.blockUI({zIndex:11111});
                   	   $.ajax({
							url:App.domain()+"/system/resetpass",
							data:{id:sysuserid},
							success: function(da){
								if(da.code==0){
									bootbox.alert("已经重置了"+sysuserid);
                       				$('#datatable_ajax_sysuser').DataTable().ajax.reload(); 
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
                   }
                }); 
			});
			$(".save-sys-user-role").on("click",function(){
				App.blockUI({zIndex:11111});
				var sele = $('#my_multi_select1').val();
				var sysuserid = $("#editsysuserid").val();
				$.ajax({
					url:App.domain()+"/system/addSysUserRole",
					data:{sysuserid:sysuserid,roleIds:sele},
					success: function(da){
						if(da.code==0){
							$("#fenpeiquanxian").modal("hide");
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
			$("#datatable_ajax_sysuser").on("click",".eidtrolebtn",function(){
				App.blockUI({zIndex:11111});
				var sysuserid = $(this).data("id");
				$.ajax({
					url:App.domain()+"/selectRoleBySysUser",
					data:{id:sysuserid},
					success: function(da){
						if(da.code==0){
							var list = da.reData;
							$('#my_multi_select1').html("");
							$('#my_multi_select1').multiSelect();
							for(var i in list){
								var map = list[i];
								var selected = "";
								if(map.selected){
									selected = "selected";
								}
								var htmlOption = "<option value='"+map.value+"' "+selected+">"+map.name+"</option>";
								$('#my_multi_select1').append(htmlOption);
							}
							$("#editsysuserid").val(sysuserid);
							$('#my_multi_select1').multiSelect("refresh");
							$("#fenpeiquanxian").modal("show");
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
			$("#datatable_ajax_sysuser").on("click",".delesysuserbtn",function(){
				var sysuserid = $(this).data("id");
				bootbox.confirm("你确定要删除吗?", function(result) {
                   if(result){
						App.blockUI({zIndex:11111});
                       $.ajax({
							url:App.domain()+"/system/deletesysuser",
							data:{id:sysuserid},
							success: function(da){
								if(da.code==0){
									bootbox.alert("已经删除了");
									$('#datatable_ajax_sysuser').DataTable().ajax.reload();
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
                       
                   }
                }); 
			});
		});
		
	</script>
 </body>
</html>
