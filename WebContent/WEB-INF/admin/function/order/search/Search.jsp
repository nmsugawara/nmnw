<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nmnw.admin.dao.Order"%>
<%@ page import="com.nmnw.admin.constant.ConfigConstants"%>
<%
	request.setCharacterEncoding("UTF-8");
	List<String> errorMessageList = (List<String>)request.getAttribute("errorMessageList");
	Map<String, String[]> inputDataList = (Map<String, String[]>)request.getAttribute("inputDataList");
	List<Order> resultList = (List<Order>)request.getAttribute("result");
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
<link href="/nmnw/commons/css/datepicker.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="/nmnw/commons/js/bootstrap-datepicker.js"></script>
<script src="/nmnw/commons/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#from").datepicker({
		format: 'yyyy-mm-dd',
		autoclose: true
	});
	$("#to").datepicker({
		format: 'yyyy-mm-dd',
		autoclose: true
	});
	$("#cancel_date").datepicker({
		format: 'yyyy-mm-dd',
		autoclose: true
	});
	$("#shipping_date").datepicker({
		format: 'yyyy-mm-dd',
		autoclose: true
	});
});
</script>
<title>No Music No Work | 注文検索</title>
</head>
<body>
<jsp:include page="/commons/Menu.jsp"/>
<div id="search_table">
<form method="post" action="search?action=search">
	<table class="table table-bordered">
		<tr>
			<th colspan="2">検索</th>
		</tr>
		<tr>
			<th>注文ID</th>
			<td><input type="text" name="search_order_id" value="<%
			if ("search".equals(actionParam)) {
				out.print(inputDataList.get("search_order_id")[0]);
			} %>"></td>
		</tr>
		<tr>
			<th>注文期間(From）</th>
			<td><input type="text" id="from" name="search_order_date_from" value="<%
			if ("search".equals(actionParam)) {
				out.print(inputDataList.get("search_order_date_from")[0]);
			} %>"></td>
		</tr>
		<tr>
			<th>注文期間(To）</th>
			<td><input type="text" id="to" name="search_order_date_to" value="<%
			if ("search".equals(actionParam)) {
				out.print(inputDataList.get("search_order_date_to")[0]);
			} %>"></td>
		</tr>
		<tr>
			<th>会員名</th>
			<td><input type="text" name="search_account_name" value="<%
			if ("search".equals(actionParam)) {
				out.print(inputDataList.get("search_account_name")[0]);
			} %>"></td>
		</tr>
		<tr>
			<th>会員名フリガナ</th>
			<td><input type="text" name="search_account_name_kana" value="<%
			if ("search".equals(actionParam)) {
				out.print(inputDataList.get("search_account_name_kana")[0]);
			} %>"></td>
		</tr>
		<tr>
			<th>メールアドレス</th>
			<td><input type="text" name="search_account_mail" value="<%
			if ("search".equals(actionParam)) {
				out.print(inputDataList.get("search_account_mail")[0]);
			} %>"></td>
		</tr>
		<tr>
			<th>電話番号</th>
			<td><input type="text" name="search_account_phone_number" value="<%
			if ("search".equals(actionParam)) {
				out.print(inputDataList.get("search_account_phone_number")[0]);
			} %>"></td>
		</tr>
		<tr>
			<th>キャンセル日</th>
			<td><input type="text" id="cancel_date" name="search_cancel_date" value="<%
			if ("search".equals(actionParam)) {
				out.print(inputDataList.get("search_cancel_date")[0]);
			} %>"></td>
		</tr>
		<tr>
			<th>出荷日</th>
			<td><input type="text" id="shipping_date" name="search_shipping_date" value="<%
			if ("search".equals(actionParam)) {
				out.print(inputDataList.get("search_shipping_date")[0]);
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
	for(int i=0; i < errorMessageList.size(); i++) {
		String message = errorMessageList.get(i);
		out.println(message);
		out.println("<br>");
	}
%>
	</font>
</p>
<p>
	<font color="red">
<%
	if ("bulkShip".equals(actionParam)) {
		List<String> messageList = (List<String>)session.getAttribute("messageList");
		for(int i=0; i < messageList.size(); i++) {
			String message = messageList.get(i);
			out.println(message);
			out.println("<br>");
		}
	}
%>
	</font>
</p>
<div id="data_table">
<form method="post" action="bulkShip">
	<table class="table table-striped table-bordered table-condensed">
		<tr>
			<th>一括出荷</th>
			<th>注文ID</th>
			<th>注文日</th>
			<th>会員ID</th>
			<th>会員名</th>
			<th>会員名フリガナ</th>
			<th>メールアドレス</th>
			<th>電話番号</th>
			<th>注文金額</th>
			<th>キャンセル状況</th>
			<th>キャンセル日</th>
			<th>出荷状況</th>
			<th>出荷日</th>
		</tr>
<%
if ("search".equals(actionParam) && resultList != null && resultList.size() != 0) {
	for(int i = 0; i < resultList.size(); i++) {
		out.println("<tr>");
		// checkbox
		out.print("<td>");
		if (resultList.get(i).getCancelFlg() == 0 && resultList.get(i).getShippingFlg() == 0) {
			out.print("<input type=\"checkbox\" name=\"bulkShip\" value=\"" + String.valueOf(resultList.get(i).getOrderId()) + "\" >");
		}
		out.println("</td>");
		// 注文ID
		out.println("<td><a href=\"detail?order_id=" + String.valueOf(resultList.get(i).getOrderId()) + "\">" + String.valueOf(resultList.get(i).getOrderId()) + "</td>");
		// 注文日時
		out.println("<td>" + resultList.get(i).getOrderTime() + "</td>");
		// 会員ID
		out.println("<td>" + resultList.get(i).getAccountId() + "</td>");
		// 会員名
		out.println("<td>" + resultList.get(i).getAccountNameConvertedHtml() + "</td>");
		// 会員名カナ
		out.println("<td>" + resultList.get(i).getAccountNameKanaConvertedHtml() + "</td>");
		// メールアドレス
		out.println("<td>" + resultList.get(i).getAccountMailConvertedHtml() + "</td>");
		// 電話番号
		out.println("<td>" + resultList.get(i).getAccountPhoneNumberConvertedHtml() + "</td>");
		// 注文金額
		out.println("<td>￥" + resultList.get(i).getTotalPrice() + "</td>");
		// キャンセル状況
		out.print("<td>");
		if (resultList.get(i).getCancelFlg() == 1) {
			out.print("キャンセル済");
		}
		out.println("</td>");
		// キャンセル日時
		out.print("<td>");
		if (resultList.get(i).getCancelTime() != null) {
			out.print(resultList.get(i).getCancelTime());	
		}
		out.println("</td>");
		// 出荷状況
		out.print("<td>");
		if (resultList.get(i).getShippingFlg() == 1) {
			out.print("出荷済");
		}
		out.println("</td>");
		// 出荷日時
		out.print("<td>");
		if (resultList.get(i).getShippingTime() != null) {
			out.print(resultList.get(i).getShippingTime());	
		}
		out.println("</td>");
		out.println("</tr>");
	}
}
%>
	</table>
	<button type="submit" class="btn btn-primary">一括出荷</button>
</form>
</div>
</body>
</html>