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
    
    <title> 打卡记录</title>
	<meta content="width=device-width, initial-scale=1.0, user-scalable=no" name="viewport">
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="format-detection" content="telephone=no">
	<meta name="viewport" id="viewport" content="width=device-width, initial-scale=1">
	<meta charset="utf-8">
  </head>
  
  <body>
    <table style="text-align: center; border: 1px;" border = "1" cellspacing = "0" cellpadding="0">
    	<thead style="font-weight: 800;">
    		<tr>
    			<td>编号</td>
    			<td>姓名</td>
    			<td>打卡时间</td>
    		</tr>
    	</thead>
    	<tbody>
	    	<c:forEach var="cl" items="${list }">
	    		<tr>
	    			<td style="width: 15%;">${cl.id }</td>
	    			<td style="width: 25%;">${cl.name }</td>
	    			<td style="width: 60%;">${cl.time }</td>
	    		</tr>
	    	</c:forEach>
    	</tbody>
    </table>
  </body>
</html>
