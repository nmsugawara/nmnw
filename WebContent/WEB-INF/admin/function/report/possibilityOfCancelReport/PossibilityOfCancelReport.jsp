<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	List<Map<String, String>> resultList = (List<Map<String, String>>)request.getAttribute("result");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/nmnw/commons/css/bootstrap.min.css" rel="stylesheet">
<link href="/nmnw/commons/css/style.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="/nmnw/commons/js/bootstrap.min.js"></script>
<title>No Music No Work | 未発送/発送後8日以内の注文一覧レポート</title>
</head>
<body>
<jsp:include page="/WEB-INF/admin/function/commons/Menu.jsp"/>
<p>
	<h1>未発送/発送後8日以内の注文一覧レポート</h1>
</p>
<div id="data_table">
	<table class="table table-bordered table-condensed　table-hover">
	<tr>
		<th>注文番号</th>
		<th>注文日時</th>
		<th>注文金額</th>
		<th>会員ID</th>
		<th>過去「発送後キャンセル」有無</th>
	</tr>
<%
	for (int i = 0; i < resultList.size(); i++) {
		out.println("<tr>");
		out.println("<td>" + resultList.get(i).get("order_id") + "</td>");
		out.println("<td>" + resultList.get(i).get("order_time") + "</td>");
		out.println("<td>" + resultList.get(i).get("total_price") + "</td>");
		out.println("<td><a href=\"/nmnw/admin/account/detail?account_id=" + resultList.get(i).get("account_id") + "\">" + resultList.get(i).get("account_id") + "</a></td>");
		out.println("<td>" + resultList.get(i).get("cancel_experience") + "</td>");
		out.println("</tr>");
	}
%>
	</table>
</div>
</body>
</html>