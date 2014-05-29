<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String errorMessage = (String)request.getAttribute("errorMessage");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>No Music No Work | Error</title>
</head>
<body>
エラーが発生しました。<br>
下記メッセージを元にシステム管理者に問い合わせてください。<br>
<table>
	<tr>
		<td>メッセージ</td>
		<td><%= errorMessage %></td>
	</tr>
</table>
</body>
</html>