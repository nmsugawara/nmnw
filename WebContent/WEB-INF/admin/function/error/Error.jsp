<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String exceptionMessage = (String)request.getAttribute("exceptionMessage");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/nmnw/commons/css/bootstrap.min.css" rel="stylesheet">
<link href="/nmnw/commons/css/style.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="/commons/js/bootstrap.min.js"></script>
<title>No Music No Work | Error</title>
</head>
<body>
エラーが発生しました。<br>
下記メッセージを元にシステム管理者に問い合わせてください。<br>
<p>
<table border=1>
	<tr>
		<td>メッセージ</td>
		<td><%= exceptionMessage %></td>
	</tr>
</table>
</p>
</body>
</html>