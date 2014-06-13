<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	List<String> errorMessageList = (List<String>)request.getAttribute("errorMessageList");
	Map<String, String[]> inputDataList = (Map<String, String[]>)request.getAttribute("inputDataList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/nmnw/commons/css/bootstrap.min.css" rel="stylesheet">
<link href="/nmnw/commons/css/style.css" rel="stylesheet">
<link href="/nmnw/commons/css/datepicker.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="/nmnw/commons/js/bootstrap-datepicker.js"></script>
<script src="/nmnw/commons/js/bootstrap.min.js"></script>
<title>No Music No Work | 会員登録</title>
</head>
<body>
<jsp:include page="/commons/Menu.jsp"/>
<table>
<tr>
<td>
<h1>会員登録</h1>
<p>
	<font color="red">
<% for(int i=0; i < errorMessageList.size(); i++) {
	String message = errorMessageList.get(i);
	out.println(message);
	out.println("<br>");
	}
%>
	</font>
</p>
<form method="post" action="register?action=regist_end" enctype="application/x-www-form-urlencoded">
	<table class="table table-bordered table-condensed">
		<tr>
			<th>会員名</th>
			<td><input type="text" name="account_name" size="50" value="${inputDataList.get('account_name')[0]}"></td>
		</tr>
		<tr>
			<th>会員名フリガナ</th>
			<td><input type="text" name="account_name_kana" size="50" value="${inputDataList.get('account_name_kana')[0]}"></td>
		</tr>
		<tr>
			<th>メールアドレス</th>
			<td><input type="text" name="account_mail" size="50" value="${inputDataList.get('account_mail')[0]}"></td>
		</tr>
		<tr>
			<th>郵便番号<br>(xxx-xxxx)</th>
			<td><input type="text" name="account_zip_code" size="20" value="${inputDataList.get('account_zip_code')[0]}"></td>
		</tr>
		<tr>
			<th>住所</th>
			<td><input type="text" name="account_address" size="100" value="${inputDataList.get('account_address')[0]}"></td>
		</tr>
		<tr>
			<th>電話番号<br>(半角ハイフン有で入力してください。)</th>
			<td><input type="text" name="account_phone_number" size="20" value="${inputDataList.get('account_phone_number')[0]}"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="hidden" name="action" value="regist_end">
				<button type="submit" class="btn btn-primary">登録</button>
			</td>
		</tr>
	</table>
</form>
</td>
</tr>
</table>
</body>
</html>