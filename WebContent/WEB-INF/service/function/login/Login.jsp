<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/nmnw/commons/css/bootstrap.min.css" rel="stylesheet">
<link href="/nmnw/commons/css/style.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="/nmnw/commons/js/bootstrap.min.js"></script>
<title>No Music No Work | ログイン</title>
</head>
<body>
<jsp:include page="/WEB-INF/service/function/commons/Menu.jsp"/>
<form action="login" method="post">
	<table>
		<tr>
			<td colspan="2" align="center"><p class="aleart">${message}</p></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><h3>ログイン</h3></td>
		</tr>
		<tr>
			<td>メールアドレス</td>
			<td><input type="text" name="login_mail" class="input-small" placeholder="Email" size="30"></td>
		</tr>
		<tr>
			<td>パスワード</td>
			<td><input type="password" name="login_password" class="input-small" placeholder="Password" size="30"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="hidden" name="action" value="login">
				<button type="submit" class="btn btn-primary">Login</button>
			</td>
		</tr>
	</table>
</form>
<p align="center">
	<a href="/nmnw/account/register">会員登録へ</a>
</p>
<p align="center">
	<a href="/nmnw/account/resetPassword?action=reset">パスワードを忘れたらこちら</a>
</p>
</body>
</html>