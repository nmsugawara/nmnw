<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nmnw.admin.dao.Order"%>
<%@ page import="com.nmnw.admin.dao.OrderDetail"%>
<%@ page import="com.nmnw.admin.constant.ConfigConstants"%>
<%
	request.setCharacterEncoding("UTF-8");
	Order result = (Order)request.getAttribute("result");
	List<OrderDetail> resultList = (List<OrderDetail>)request.getAttribute("resultList");	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/nmnw/commons/css/bootstrap.min.css" rel="stylesheet">
<link href="/nmnw/commons/css/style.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="/commons/js/bootstrap.min.js"></script>
<title>No Music No Work | 注文情報詳細</title>
</head>
<body>
<p>
	<h1>商品詳細情報</h1>
</p>
<div id="data_table">
<table class="table table-bordered table-condensed">
	<tr>
		<th>注文ID</th>
		<td><% out.print(result.getOrderId()); %></td>
	</tr>
	<tr>
		<th>注文日時</th>
		<td><% out.print(result.getOrderTime()); %></td>
	</tr>
	<tr>
		<th>注文詳細</th>
		<td>
			<table class="table table-bordered table-condensed">
				<tr>
					<th>商品ID</th>
					<th>商品名</th>
					<th>商品単価</th>
					<th>購入個数</th>
					<th>小計</th>
				</tr>
<%
	for(int i = 0; i < resultList.size(); i++) {
		out.println("<tr>");
			out.println("<td>" + resultList.get(i).getItemId() + "</td>");
			out.println("<td>" + resultList.get(i).getItemName() + "</td>");
			out.println("<td>￥" + resultList.get(i).getItemPrice() + "</td>");
			out.println("<td>" + resultList.get(i).getItemNumber() + "</td>");
			out.println("<td>￥" + (resultList.get(i).getItemPrice() * resultList.get(i).getItemNumber()) + "</td>");
		out.println("</tr>");
	}
%>
				<tr>
					<th colspan="4">注文金額</th>
					<td>￥<% out.print(result.getTotalPrice()); %></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<th>キャンセル状況</th>
		<td><%
		if (result.getCancelFlg() == 1) {
			out.print("キャンセル済");
		}
		%></td>
	</tr>
	<tr>
		<th>キャンセル日時</th>
		<td><%
		if (result.getCancelTime() != null) {
			out.print(result.getCancelTime());	
		}
		%></td>
	</tr>
	<tr>
		<th>出荷状況</th>
		<td><%
		if (result.getShippingFlg() == 1) {
			out.print("出荷済");
		}
		%></td>
	</tr>
	<tr>
		<th>出荷日時</th>
		<td><%
		if (result.getShippingTime() != null) {
			out.print(result.getShippingTime());	
		}
		%></td>
	</tr>
	<tr>
		<th>会員名</th>
		<td><% out.print(result.getAccountNameConvertedHtml()); %></td>
	</tr>
	<tr>
		<th>会員名フリガナ</th>
		<td><% out.print(result.getAccountNameKanaConvertedHtml()); %></td>
	</tr>
	<tr>
		<th>メールアドレス</th>
		<td><% out.print(result.getAccountMailConvertedHtml()); %></td>
	</tr>
	<tr>
		<th>郵便番号</th>
		<td><% out.print(result.getAccountZipCodeConvertedHtml()); %></td>
	</tr>
	<tr>
		<th>住所</th>
		<td><% out.print(result.getAccountAddressConvertedHtml()); %></td>
	</tr>
	<tr>
		<th>電話番号</th>
		<td><% out.print(result.getAccountPhoneNumberConvertedHtml()); %></td>
	</tr>
</table>
</div>
<% out.println("<a href=\"search\">注文管理TOPへ</a>"); %>
</body>
</html>