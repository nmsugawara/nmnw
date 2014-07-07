<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nmnw.admin.dao.Account"%>
<%@ page import="com.nmnw.admin.constant.ConfigConstants"%>
<%
	request.setCharacterEncoding("UTF-8");
	List<String> errorMessageList = (List<String>)request.getAttribute("errorMessageList");
	Map<String, String[]> inputDataList = (Map<String, String[]>)request.getAttribute("inputDataList");
	List<Account> resultList = (List<Account>)request.getAttribute("result");
	String actionParam = "";
	if (inputDataList.containsKey("action")) {
		actionParam = inputDataList.get("action")[0];
	}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/nmnw/commons/css/bootstrap.min.css" rel="stylesheet">
<link href="/nmnw/commons/css/style.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="/nmnw/commons/js/bootstrap.min.js"></script>
<title>No Music No Work | 会員検索</title>
</head>
<body>
<jsp:include page="/WEB-INF/admin/function/commons/Menu.jsp"/>
<div id="search_table">
<form method="post" action="search?action=search">
	<table class="table table-bordered">
		<tr>
			<th colspan="2">検索</th>
		</tr>
		<tr>
			<th>会員ID</th>
			<td><input type="text" name="search_id" value="<%
			if ("search".equals(actionParam)) {
				out.print(inputDataList.get("search_id")[0]);
			} %>"></td>
		</tr>
		<tr>
			<th>会員名</th>
			<td><input type="text" name="search_name" value="<%
			if ("search".equals(actionParam)) {
				out.print(inputDataList.get("search_name")[0]);
			} %>"></td>
		</tr>
		<tr>
			<th>会員名フリガナ</th>
			<td><input type="text" name="search_name_kana" value="<%
			if ("search".equals(actionParam)) {
				out.print(inputDataList.get("search_name_kana")[0]);
			} %>"></td>
		</tr>
		<tr>
			<th>メールアドレス</th>
			<td><input type="text" name="search_mail" value="<%
			if ("search".equals(actionParam)) {
				out.print(inputDataList.get("search_mail")[0]);
			} %>"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="submit" class="btn btn-primary">Search</button>
			</td>
		</tr>
	</table>
</form>
</div>
<p>
	<font color="red">
<%
	if (errorMessageList != null) {
		for(int i=0; i < errorMessageList.size(); i++) {
			String message = errorMessageList.get(i);
			out.println(message);
			out.println("<br>");
		}
	}
%>
	</font>
</p>
<div id="data_table">
	<table class="table table-striped table-bordered table-condensed">
		<tr>
			<th>会員ID</th>
			<th>会員名</th>
			<th>会員名フリガナ</th>
			<th>メールアドレス</th>
			<th>電話番号</th>
			<th>削除</th>
		</tr>
<%
if ("search".equals(actionParam) && resultList != null && resultList.size() != 0) {
	for(int i = 0; i < resultList.size(); i++) {
		out.println("<tr>");
		out.println("<td><a href=\"detail?account_id=" + String.valueOf(resultList.get(i).getId()) + "\">" + String.valueOf(resultList.get(i).getId()) + "</td>");
		out.println("<td>" + resultList.get(i).getNameConvertedHtml() + "</td>");
		out.println("<td>" + resultList.get(i).getNameKanaConvertedHtml() + "</td>");
		out.println("<td>" + resultList.get(i).getMailConvertedHtml() + "</td>");
		out.println("<td>" + resultList.get(i).getPhoneNumberConvertedHtml() + "</td>");
		out.print("<td>");
		if (resultList.get(i).getDelFlg() == true) {
			out.print("削除済");
		}
		out.println("</td>");
		out.println("</tr>");
	}
}
%>
	</table>
</div>
</body>
</html>