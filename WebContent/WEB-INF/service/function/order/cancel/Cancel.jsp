<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	String orderId = "";
	if (request.getAttribute("orderId") != null) {
		orderId = (String)request.getAttribute("orderId");
	}
	List<String> errorMessageList = (List<String>)request.getAttribute("errorMessageList");
	String message = "";
	if (errorMessageList == null) {
		message = "注文番号：" + orderId + "　キャンセルが完了しました。";
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/nmnw/commons/css/bootstrap.min.css" rel="stylesheet">
<link href="/nmnw/commons/css/style.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="/nmnw/commons/js/bootstrap.min.js"></script>
<title>No Music No Work | 注文キャンセル</title>
</head>
<body class="service_body">
<jsp:include page="/WEB-INF/service/function/commons/Menu.jsp"/>
<table id="canvas">
<tr>
<td>
	<p>
		<font color="red">
<% 
	if (errorMessageList != null) {
		for(int i=0; i < errorMessageList.size(); i++) {
			String errorMessage = errorMessageList.get(i);
			out.print(errorMessage);
			out.print("<br>");
		}
	}
%>
		</font>
	</p>
	<p align="center">
		<%= message %>
		<br>
		<a href="/nmnw/order/search">注文履歴へ</a>
	</p>
</td>
</tr>
</table>
</body>
</html>