<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nmnw.service.dao.Order"%>
<%@ page import="com.nmnw.service.dao.OrderDetail"%>
<%@ page import="com.nmnw.service.constant.ConfigConstants"%>
<%@ page import="com.nmnw.service.utility.DateConversionUtility"%>
<%
	request.setCharacterEncoding("UTF-8");
	Order order = (Order)request.getAttribute("order");
	List<OrderDetail> orderDetailList = (List<OrderDetail>)request.getAttribute("orderDetail");
	List<String> errorMessageList = (List<String>)request.getAttribute("errorMessageList");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/nmnw/commons/css/bootstrap.min.css" rel="stylesheet">
<link href="/nmnw/commons/css/style.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="/nmnw/commons/js/bootstrap.min.js"></script>
<title>No Music No Work | 購入完了</title>
</head>
<body class="service_body">
<jsp:include page="/WEB-INF/service/function/commons/Menu.jsp"/>
<table id="canvas">
<tr>
<td>
	<p>
		<font color="red">
<% 
	if (errorMessageList != null) {
		for(int i=0; i < errorMessageList.size(); i++) {
			String errorMessage = errorMessageList.get(i);
			out.print(errorMessage);
			out.print("<br>");
		}
	}
%>
		</font>
	</p>
<% 
	if (errorMessageList == null) {
%>
<div id="data_table">
購入が完了しました。
<table class="table table-bordered table-condensed">
	<tr>
		<th>注文ID</th>
		<td><% out.print(order.getOrderId()); %></td>
	</tr>
	<tr>
		<th>注文日時</th>
		<td><% out.print(DateConversionUtility.dateTimeToString(order.getOrderTime())); %></td>
	</tr>
	<tr>
		<th>注文内容</th>
		<td>
			<table class="table table-bordered table-condensed">
				<tr>
					<th>商品名</th>
					<th>商品単価</th>
					<th>購入数</th>
					<th>小計</th>
				</tr>
<%
	for(int i = 0; i < orderDetailList.size(); i++) {
		out.println("<tr>");
			out.println("<td>" + orderDetailList.get(i).getItemName() + "</td>");
			out.println("<td>￥" + orderDetailList.get(i).getItemPrice() + "</td>");
			out.println("<td>" + orderDetailList.get(i).getItemCount() + "</td>");
			out.println("<td>￥" + (orderDetailList.get(i).getItemPrice() * orderDetailList.get(i).getItemCount()) + "</td>");
		out.println("</tr>");
	}
%>
				<tr>
					<th colspan="3">合計金額</th>
					<td>￥<% out.print(order.getTotalPrice()); %></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<th>会員名</th>
		<td><% out.print(order.getAccountNameConvertedHtml()); %></td>
	</tr>
	<tr>
		<th>会員名フリガナ</th>
		<td><% out.print(order.getAccountNameKanaConvertedHtml()); %></td>
	</tr>
	<tr>
		<th>メールアドレス</th>
		<td><% out.print(order.getAccountMailConvertedHtml()); %></td>
	</tr>
	<tr>
		<th>郵便番号</th>
		<td><% out.print(order.getAccountZipCodeConvertedHtml()); %></td>
	</tr>
	<tr>
		<th>住所</th>
		<td><% out.print(order.getAccountAddressConvertedHtml()); %></td>
	</tr>
	<tr>
		<th>電話番号</th>
		<td><% out.print(order.getAccountPhoneNumberConvertedHtml()); %></td>
	</tr>
</table>
</div>
<%
	}
%>
<a href="/nmnw/index">TOPへ</a>
</td>
</tr>
</table>
</body>
</html>