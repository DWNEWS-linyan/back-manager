<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>实时公交查询</title>
	<meta content="width=device-width, initial-scale=1.0, user-scalable=no" name="viewport">
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="format-detection" content="telephone=no">
	<meta name="viewport" id="viewport" content="width=device-width, initial-scale=1">
	<meta charset="utf-8">
    
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
body, div, ul, ol, li, dl, dt, dd, h1, h2, h3, h4, h5, h6, iframe, pre, code, fieldset, legend, form, input, select, textarea, button, p, blockquote, th, td, figure {
	margin: 0;
	padding: 0;
}

div {
	display: block;
}

li {
	list-style-type: none;
}

input, textarea, select, button, meter, progress {
	-webkit-writing-mode: horizontal-tb;
}

li {
	display: list-item;
	text-align: -webkit-match-parent;
}

input, textarea, select, button {
	text-rendering: auto;
	color: initial;
	letter-spacing: normal;
	word-spacing: normal;
	text-transform: none;
	text-indent: 0px;
	text-shadow: none;
	display: inline-block;
	text-align: start;
	margin: 0em;
	font: 400 13.3333px Arial;
}
.learble {
	display: inline-block;
	width: 100px;
}

.row {
	height: 30px;
}

.searchdiv {
	text-align: center;
    margin-left: 5px;
    background-color: darkgray;
    border-radius: 5px;
    border: 2px solid #d9d9ef;
}
.search{
	width: 44px;
	font-size: 20px;
}
.search:active{
	background-color: #bbbdca;
}
.important {
	font-size: 22px;
	color: #415536;
}

.info>div {
	padding-top: 3px;
	padding-left: 20px;
}

.tdm3 {
	width: 30%;
}

.tdm2 {
	padding-left:20px;
	width: 20%;
}

.tdm6 {
	width: 60%;
}
.selectw{
	width:100%;
}
.heard {
	padding: 10px;
}
td>select {
	height: 30px;
}
.info {
	
}

.buses {
	margin-top: 10px;
}

tr {
	height: 35px;
	width: 100%;
}

.nosite {
	
}

.sited {
	
}


.tableclass{
	width: 100%;
}
.tableclass tr{
	height: 25px;
}
</style>
</head>
  
  <body>
    <!--  选项  -->
<div class="heard">
	<table>
		<tr>
			<td style="width: 20%;">公交线路</td>
			<td style="width: 65%;">
				<select class="line selectw" >
					<option value="">请选择</option>
					<c:forEach var="line" items="${siteLine}" begin="360" end="450">
						<option value="${line }">${line }</option>
					</c:forEach>
				</select>
			</td>
			<td style="width: 15%;" rowspan = "3">
				<div class="searchdiv search">查询</div>
			</td>
		</tr>
		<tr>
			<td>方向</td>
			<td>
				<select class="lineDir selectw">
					<option value="">请选择</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>上车站</td>
			<td>
				<select class="sites selectw">
					<option value="">请选择</option>
				</select>
			</td>
		</tr>
	</table>
</div>

<!--  信息  -->
<div class="info">
	<div class="important"></div>
	<div class="outhe"></div>
</div>

<!-- 站点 -->
<div class="buses">
	<table class="tableclass">
	</table>
</div>
  </body>
  <script src="<%=basePath %>theme/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
  <script type="text/javascript">
var domain = "<%=basePath%>";
	  jQuery(document).ready(function() {
		  $(".line").change(function(){
			  var lineval = $(this).val();
			  $(".important").html("线路："+lineval);
			  var html = "<option value=''>请选择</option>";
			  if(lineval!=""&&lineval!=null){
				  $.ajax({
						url:domain +"site/getLineDir",
						data:{line:lineval},
						success: function(da){
							for(var i in da){
								var map = da[i];
								var mid = map.value;
								var mtext = map.name;
								html += "<option value='"+mid+"'>"+mtext+"</option>";
							}
							$(".lineDir").html(html);
						},
						error: function(a,b,c){
							var status = a.status;
							alert(status);
						}
					});
			  }else{
				  $(".lineDir").html(html);
			  }
			  $(".sites").html(html);
		  });
		  
		  $(".lineDir").change(function(){
			  var linedir = $(this).val();
			  var lineval = $(".line").val();
			  var html = "<option value=''>请选择</option>";
			  if(lineval!=""&&lineval!=null){
				  $.ajax({
						url:domain +"site/getLineSite",
						data:{line:lineval,lineDir:linedir},
						success: function(da){
							var htmlv = "";
							var inttmp = 0 ;
							for(var i in da){
								var map = da[i];
								var mid = map.value;
								var mtext = map.name;
								html += "<option value='"+mid+"'>"+mtext+"</option>";
								if(inttmp!=0){
									htmlv+='<tr><td class="tdm2"><img class="mclass" id="'+mid+'m" ></td><td></td></tr>';
								}
								htmlv +='<tr>'+
											'<td class="tdm2">'+
												'<img class="nosite" id="'+mid+'" >'+
											'</td>'+
// 											'<td class="tdm2">'+
												
// 											'</td>'+
											'<td class="tdm6">'+
												mtext+
											'</td>'+
										'</tr>';
								inttmp = inttmp+1;
							}
							$(".sites").html(html);
							$(".tableclass").html(htmlv);
						},
						error: function(a,b,c){
							var status = a.status;
							alert(status);
						}
					});
			  }else{
				  $(".sites").html(html);
			  }
		  });
		  
		  function winScroll(){
			  $(window).scroll(function(){
				  var infoTop = $(".info").position().top;
				  var winTop = $(window).scrollTop();
				  if(winTop>infoTop){
					  $(".info").css({
					  		"z-index":"500",
					  		"position":"fixed",
					  		"top":"0px",
					  		"background-color":"azure",
					  		"width":"100%"
					  	});
				  }else{
					  $(".info").css({
					  		"z-index":"500",
					  		"position":"initial",
					  		"top":"0px",
					  		"background-color":"#fff",
					  		"width":"100%"
					  	});
				  }
				  console.info(infoTop+"><"+winTop);
			  });
		  }
		  
		  $(".sites").change(function(){
			  var site = $(this).val();
			  var siteTr = $("#"+site).parent().parent();
			  var tds = siteTr.children().length;
			  var lastTd = siteTr.children()[tds-1];
			  
// 			  var gg = $(lastTd).offset().top;
// 				var g = $(lastTd).position().top;
// // 				document.body.scrollTop=g;
// // 			  var ggw = $(lastTd).style.top;
// 			  console.info(g+"<<>>");
// 			  window.scrollTo(10,g); 
// 			  window.scrollTo(10,$(lastTd).offset().top); 
// 			  $(window).scrollTop(g);
// 			  $("body").scrollTop($("#nurse").scrollTop() + $('#tr-three').offset().top - $("#nurse").offset().top); --普通
			   
// 			  $("#nurse").animate({ scrollTop: $("#nurse").scrollTop() + $('#tr-three').offset().top - $("#nurse").offset().top }, 1000); --有动画效果
			  $("td").css({
				  "font-weight": "normal"
			  });
			  $(lastTd).css({
				  "font-weight": "900"
			  });
		  });
		  var inte ;
		  $(".searchdiv").click(function(){
			  var site = $(".sites").val();
			  var siteTr = $("#"+site).parent().parent();
			  var tds = siteTr.children().length;
			  var lastTd = siteTr.children()[tds-1];
			  var gg = $(lastTd).offset().top;
			  var g = $(lastTd).position().top;
			  $(window).scrollTop(gg-350);
			  searc();
			  if(inte){
				  window.clearInterval(inte);
			  }
			  inte = window.setInterval(function(){searc()},30000);
			  $(window).unbind('scroll');
			  winScroll();
		  });
		  
		  function searc(){
			  console.info("aggag");
			  var linedir = $(".lineDir").val();
			  var lineval = $(".line").val();
			  var site = $(".sites").val();
			  $.ajax({
					url:domain +"site/refresh",
					data:{line:lineval,lineDir:linedir,site:site},
					success: function(da){
						if(da.code==1){
							alert(da.mes);
						}else{
							var strP2 = da.strP2;
							$(".outhe").html(strP2);
							$(".mclass").css({
								"width":"0px",
								"height":"0px"
							});
							$(".nosite").css({
								"width":"0px",
								"height":"0px"
							});
							var buscIdMs = da.buscIdMList;
							for(var i in buscIdMs){
								var buscIdM = buscIdMs[i];
								$("#"+buscIdM).attr("src",domain +"theme/img/vehicle_ico1.png");
								$("#"+buscIdM).css({
									"width":"25px",
									"height":"25px"
								});
							}
							var bussIds = da.bussIdList;
							for(var i in bussIds){
								var buscId = bussIds[i];
								$("#"+buscId).attr("src",domain +"theme/img/vehicle_ico2.png");
								$("#"+buscId).css({
									"width":"25px",
									"height":"25px"
								});
							}
						}
					},
					error: function(a,b,c){
						var status = a.status;
						alert(status+a.responseText);
					}
				});
		  }
		  
	  })
  </script>
  
</html>
