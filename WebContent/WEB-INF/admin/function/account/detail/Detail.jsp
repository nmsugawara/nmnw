<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nmnw.admin.dao.Account"%>
<%@ page import="com.nmnw.admin.constant.ConfigConstants"%>
<%
	request.setCharacterEncoding("UTF-8");
	Account result = (Account)request.getAttribute("result");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/nmnw/commons/css/bootstrap.min.css" rel="stylesheet">
<link href="/nmnw/commons/css/style.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="/nmnw/commons/js/bootstrap.min.js"></script>
<title>No Music No Work | 会員情報詳細</title>
</head>
<body>
<jsp:include page="/commons/Menu.jsp"/>
<p>
	<h1>会員詳細情報</h1>
</p>
<div id="data_table">
<table class="table table-bordered table-condensed">
	<tr>
		<th>会員名</th>
		<td><input type="hidden" name="name" value="<% out.print(result.getNameConvertedHtml()); %>"><% out.print(result.getNameConvertedHtml()); %></td>
	</tr>
	<tr>
		<th>会員名フリガナ</th>
		<td><input type="hidden" name="name_kana" value="<% out.print(result.getNameKanaConvertedHtml()); %>"><% out.print(result.getNameKanaConvertedHtml()); %></td>
	</tr>
	<tr>
		<th>メールアドレス</th>
		<td><input type="hidden" name="mail" value="<% out.print(result.getMailConvertedHtml()); %>"><% out.print(result.getMailConvertedHtml()); %></td>
	</tr>
	<tr>
		<th>郵便番号</th>
		<td><input type="hidden" name="zip_code" value="<% out.print(result.getZipCodeConvertedHtml()); %>"><% out.print(result.getZipCodeConvertedHtml()); %></td>
	</tr>
	<tr>
		<th>住所</th>
		<td><input type="hidden" name="address" value="<% out.print(result.getAddressConvertedHtml()); %>"><% out.print(result.getAddressConvertedHtml()); %></td>
	</tr>
	<tr>
		<th>電話番号</th>
		<td><input type="hidden" name="phone_number" value="<% out.print(result.getPhoneNumberConvertedHtml()); %>"><% out.print(result.getPhoneNumberConvertedHtml()); %></td>
	</tr>
	<tr>
		<th>削除</th>
		<td><input type="hidden" name="del_flg" value="<% out.print(result.getDelFlg()); %>"><% if (result.getDelFlg() == 1) {out.print("削除済");} %></td>
	</tr>
</table>
</div>
<% out.println("<a href=\"search\">会員管理TOPへ</a>"); %>
</body>
</html>