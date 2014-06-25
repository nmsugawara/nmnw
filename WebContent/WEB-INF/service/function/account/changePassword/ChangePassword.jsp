<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	String action = "";
	if (request.getAttribute("action") != null) {
		action = (String)request.getAttribute("action");
	}
	String token = "";
	if (request.getAttribute("token") != null) {
		token = (String)request.getAttribute("token");
	}
	String title = "";
	if (request.getAttribute("title") != null) {
		title = (String)request.getAttribute("title");
	}
	List<String> errorMessageList = (List<String>)request.getAttribute("errorMessageList");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/nmnw/commons/css/bootstrap.min.css" rel="stylesheet">
<link href="/nmnw/commons/css/style.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="/nmnw/commons/js/bootstrap.min.js"></script>
<title>No Music No Work | パスワード<%= title %></title>
</head>
<body class="service_body">
<jsp:include page="/WEB-INF/service/function/commons/Menu.jsp"/>
<table id="canvas">
<tr>
<td>
<%
	// エラー画面表示
	if ("".equals(action)) {
%>
	<p>
		<font color="red">
<% 
		if (errorMessageList != null) {
			for(int i=0; i < errorMessageList.size(); i++) {
				String message = errorMessageList.get(i);
				out.print(message);
				out.print("<br>");
			}
		}
%>
		</font>
	</p>
<%
	// パスワード変更画面表示
	} else if ("edit".equals(action)) {
%>
	<p>
		<font color="red">
<% 
		if (errorMessageList != null) {
			for(int i=0; i < errorMessageList.size(); i++) {
				String message = errorMessageList.get(i);
				out.print(message);
				out.print("<br>");
			}
		}
%>
		</font>
	</p>
	<form action="changePassword" method="post">
		<table>
			<tr>
				<td colspan="2" align="center"><h3>パスワード変更</h3></td>
			</tr>
			<tr>
				<td>パスワード</td>
				<td><input type="password" name="password" class="input-small" placeholder="Password" size="30"></td>
			</tr>
			<tr>
				<td>パスワード再入力</td>
				<td><input type="password" name="retype_password" class="input-small" placeholder="Retype Password" size="30"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="hidden" name="action" value="edit_end">
					<input type="hidden" name="token" value="<%= token %>">
					<button type="submit" class="btn btn-primary">変更</button>
				</td>
			</tr>
		</table>
	</form>
<%
	// パスワード変更完了画面表示
	} else if ("edit_end".equals(action)) {
%>
	<p align="center">
		パスワード変更が完了しました。
		<br>
		<a href="/nmnw/login">ログイン画面へ</a>
	</p>
<%
	}
%>
</td>
</tr>
</table>
</body>
</html>