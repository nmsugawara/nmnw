<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	Map<String, Map<String, String>> resultList = (Map<String, Map<String, String>>)request.getAttribute("result");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/nmnw/commons/css/bootstrap.min.css" rel="stylesheet">
<link href="/nmnw/commons/css/style.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="/nmnw/commons/js/bootstrap.min.js"></script>
<title>No Music No Work | 月別総合レポート</title>
</head>
<body>
<jsp:include page="/WEB-INF/admin/function/commons/Menu.jsp"/>
<p>
	<h1>月別総合レポート</h1>
</p>
<div id="data_table">
	<table class="table table-bordered table-condensed　table-hover">
	<tr>
		<th>月</th>
		<th>注文数</th>
		<th>キャンセル数</th>
		<th>キャンセル率</th>
		<th>発送数</th>
		<th>売上</th>
		<th>販売枚数</th>
		<th>発送率</th>
		<th>発送後キャンセル数</th>
		<th>発送後キャンセル率</th>
	</tr>
<%
	Iterator<String> iterator = resultList.keySet().iterator();
	while (iterator.hasNext()) {
		String key = (String)iterator.next();
		out.println("<tr>");
		out.println("<td>" + key + "</td>");
		out.println("<td>" + resultList.get(key).get("order_count") + "</td>");
		out.println("<td>" + resultList.get(key).get("cancel_count") + "</td>");
		out.println("<td>" + resultList.get(key).get("cancel_rate") + "%</td>");
		out.println("<td>" + resultList.get(key).get("shipping_count") + "</td>");
		out.println("<td>￥" + resultList.get(key).get("sales") + "</td>");
		out.println("<td>" + resultList.get(key).get("sales_item_count") + "</td>");
		out.println("<td>" + resultList.get(key).get("shipping_rate") + "%</td>");
		out.println("<td>" + resultList.get(key).get("cancel_count_after_shipping") + "</td>");
		out.println("<td>" + resultList.get(key).get("cancel_rate_after_shipping") + "%</td>");
		out.println("</tr>");
	}
%>
	</table>
</div>
</body>
</html>