<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	String action = "";
	if (request.getAttribute("action") != null) {
		action = (String)request.getAttribute("action");
	}
	String mail = "";
	if (request.getAttribute("mail") != null) {
		mail = (String)request.getAttribute("mail");
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
	// パスワードリセット画面表示
	} else if ("reset".equals(action)) {
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
	<form action="resetPassword" method="post">
		<table>
			<tr>
				<td colspan="2" align="center"><h3>パスワードリセット</h3></td>
			</tr>
			<tr>
				<td>URL送付先メールアドレス</td>
				<td><input type="text" name="mail" class="input-small" placeholder="Mail" value="<%= mail %>" size="30"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="hidden" name="action" value="reset_end">
					<input type="hidden" name="mail" value="<%= mail %>">
					<button type="submit" class="btn btn-primary">送信</button>
				</td>
			</tr>
		</table>
	</form>
<%
	// パスワードリセット完了画面表示
	} else if ("reset_end".equals(action)) {
%>
	<p align="center">
		パスワードリセット用メールを送信しましたのでご確認ください。
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