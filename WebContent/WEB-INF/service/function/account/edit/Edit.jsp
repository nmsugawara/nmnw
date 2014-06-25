<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nmnw.service.dao.Account"%>
<%@ page import="com.nmnw.service.constant.ConfigConstants"%>
<%
	request.setCharacterEncoding("UTF-8");
	List<String> errorMessageList = (List<String>)request.getAttribute("errorMessageList");
	Map<String, String[]> inputDataList = (Map<String, String[]>)request.getAttribute("inputDataList");
	Account result = (Account)request.getAttribute("result");
	String action = "";
	if (inputDataList.containsKey("action")) {
		action = inputDataList.get("action")[0];
	}
%> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/nmnw/commons/css/bootstrap.min.css" rel="stylesheet">
<link href="/nmnw/commons/css/style.css" rel="stylesheet">
<link href="/nmnw/commons/css/datepicker.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="/nmnw/commons/js/bootstrap-datepicker.js"></script>
<script src="/nmnw/commons/js/bootstrap.min.js"></script>
<title>No Music No Work | 会員情報編集</title>
</head>
<body class="service_body">
<jsp:include page="/WEB-INF/service/function/commons/Menu.jsp"/>
<table id="canvas">
<tr>
<td>
<h1>会員情報編集</h1>
</td>
</tr>
<tr>
<td>
<p>
	<font color="red">
<% 
	for(int i=0; i < errorMessageList.size(); i++) {
		String message = errorMessageList.get(i);
		out.print(message);
		out.print("<br>");
	}
%>
	</font>
</p>
<%
	if ("edit".equals(action) || "edit_end".equals(action)) {
		// 該当データがある場合
		if (result.getId() != 0) {
%>
<div id="data_table">
<form method="post" action="edit?action=edit_end"  enctype="application/x-www-form-urlencoded">
	<table class="table table-bordered table-condensed">
		<tr>
			<th>会員名</th>
			<td><input type="text" name="account_name" size="50" value="<%
			if ("edit_end".equals(action)) {
				out.print(inputDataList.get("account_name")[0]);
			} else {
				out.print(result.getName());
			} %>"></td>
		</tr>
		<tr>
			<th>会員名フリガナ</th>
			<td><input type="text" name="account_name_kana" size="50" value="<%
			if ("edit_end".equals(action)) {
				out.print(inputDataList.get("account_name_kana")[0]);
			} else {
				out.print(result.getNameKana());
			} %>"></td>
		</tr>
		<tr>
			<th>メールアドレス</th>
			<td><input type="text" name="account_mail" size="50" value="<%
			if ("edit_end".equals(action)) {
				out.print(inputDataList.get("account_mail")[0]);
			} else {
				out.print(result.getMail());
			} %>"></td>
		</tr>
		<tr>
			<th>郵便番号<br>(xxx-xxxx)</th>
			<td><input type="text" name="account_zip_code" size="20" value="<%
			if ("edit_end".equals(action)) {
				out.print(inputDataList.get("account_zip_code")[0]);
			} else {
				out.print(result.getZipCode());
			} %>"></td>
		</tr>
		<tr>
			<th>住所</th>
			<td><input type="text" name="account_address" size="100" value="<%
			if ("edit_end".equals(action)) {
				out.print(inputDataList.get("account_address")[0]);
			} else {
				out.print(result.getAddress());
			} %>"></td>
		</tr>
		<tr>
			<th>電話番号<br>(半角ハイフン有で入力してください。)</th>
			<td><input type="text" name="account_phone_number" size="20" value="<%
			if ("edit_end".equals(action)) {
				out.print(inputDataList.get("account_phone_number")[0]);
			} else {
				out.print(result.getPhoneNumber());
			} %>"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="hidden" name="account_id" value="<%
				if ("edit_end".equals(action)) {
					out.print(inputDataList.get("account_id")[0]);
 				} else {
					out.print(result.getId());
				} %>">
				<button type="submit" class="btn btn-primary">編集完了</button>
			</td>
		</tr>
	</table>
</form>
</div>
<%
		}
	}
%>
</td>
</tr>
</table>
</body>
</html>