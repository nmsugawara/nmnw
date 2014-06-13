<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nmnw.service.dao.Account"%>
<%@ page import="com.nmnw.service.constant.ConfigConstants"%>
<%
	request.setCharacterEncoding("UTF-8");
	String action = (String)request.getParameter("action");
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
	<h1>${message}</h1>
</p>
<% 
	// 該当データがない場合
	if (result.getId() == 0) {
		out.println("<font color=\"red\">" + request.getAttribute("message") + "</font>");
	} else {
%>
<div id="data_table">
<form method="post" action="edit?item_id=<% out.print(result.getId()); %>&action=edit"  enctype="application/x-www-form-urlencoded">
<table class="table table-bordered table-condensed">
	<tr>
		<th>会員名</th>
		<td><input type="hidden" name="account_name" value="<% out.print(result.getNameConvertedHtml()); %>"><% out.print(result.getNameConvertedHtml()); %></td>
	</tr>
	<tr>
		<th>会員名フリガナ</th>
		<td><input type="hidden" name="account_name_kana" value="<% out.print(result.getNameKanaConvertedHtml()); %>"><% out.print(result.getNameKanaConvertedHtml()); %></td>
	</tr>
	<tr>
		<th>メールアドレス</th>
		<td><input type="hidden" name="account_mail" value="<% out.print(result.getMailConvertedHtml()); %>"><% out.print(result.getMailConvertedHtml()); %></td>
	</tr>
	<tr>
		<th>郵便番号</th>
		<td><input type="hidden" name="account_zip_code" value="<% out.print(result.getZipCodeConvertedHtml()); %>"><% out.print(result.getZipCodeConvertedHtml()); %></td>
	</tr>
	<tr>
		<th>住所</th>
		<td><input type="hidden" name="account_address" value="<% out.print(result.getAddressConvertedHtml()); %>"><% out.print(result.getAddressConvertedHtml()); %></td>
	</tr>
	<tr>
		<th>電話番号</th>
		<td><input type="hidden" name="account_phone_number" value="<% out.print(result.getPhoneNumberConvertedHtml()); %>"><% out.print(result.getPhoneNumberConvertedHtml()); %></td>
	</tr>
<%  if(!("regist_end".equals(action)) && !("edit_end".equals(action))) { %>
		<tr>
			<td colspan="2" align="center">
				<button type="submit" class="btn btn-primary">編集</button>
			</td>
		</tr>
<% }%>
</table>
</form>
</div>
<%
	if(("regist_end".equals(action)) || ("edit_end".equals(action))) {
		out.println("<a href=\"resetPassword\">パスワード登録/変更はこちら</a>");
	}
}
%>
<a href="/nmnw/index">TOPへ</a>
</body>
</html>