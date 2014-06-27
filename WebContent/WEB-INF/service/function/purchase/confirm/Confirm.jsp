<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nmnw.service.dao.Cart"%>
<%@ page import="com.nmnw.service.dao.Account"%>
<%@ page import="com.nmnw.service.dao.Item"%>
<%@ page import="com.nmnw.service.constant.ConfigConstants"%>
<%@ page import="com.nmnw.service.utility.DateConversionUtility"%>
<%
	request.setCharacterEncoding("UTF-8");
	Account account = (Account)request.getAttribute("account");
	// cart情報
	List<Cart.CartItem> cartItemList = new ArrayList<Cart.CartItem>();
	if (session.getAttribute("cart") != null) {
		Cart cart = (Cart)session.getAttribute("cart");
		cartItemList = cart.getItemList();
	}
	List<String> errorMessageList = (List<String>)request.getAttribute("errorMessageList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/nmnw/commons/css/bootstrap.min.css" rel="stylesheet">
<link href="/nmnw/commons/css/style.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="/nmnw/commons/js/bootstrap.min.js"></script>
<title>No Music No Work | 購入確認</title>
</head>
<body>
<jsp:include page="/WEB-INF/admin/function/commons/Menu.jsp"/>
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
下記内容で注文します。よろしいですか？
<form method="post" action="complete">
<table class="table table-bordered table-condensed">
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
	int totalPrice = 0;
	for(int i = 0; i < cartItemList.size(); i++) {
		out.println("<tr>");
			out.println("<td>" + cartItemList.get(i).getItemName() + "</td>");
			out.println("<td>￥" + cartItemList.get(i).getItemPrice() + "</td>");
			out.println("<td>" + cartItemList.get(i).getItemCount() + "</td>");
			out.println("<td>￥" + (cartItemList.get(i).getItemPrice() * cartItemList.get(i).getItemCount()) + "</td>");
		out.println("</tr>");
		totalPrice += cartItemList.get(i).getItemPrice() * cartItemList.get(i).getItemCount();
	}
%>
				<tr>
					<th colspan="3">合計金額</th>
					<td>￥<% out.print(totalPrice); %></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<th>会員名</th>
		<td><% out.print(account.getNameConvertedHtml()); %></td>
	</tr>
	<tr>
		<th>会員名フリガナ</th>
		<td><% out.print(account.getNameKanaConvertedHtml()); %></td>
	</tr>
	<tr>
		<th>メールアドレス</th>
		<td><% out.print(account.getMailConvertedHtml()); %></td>
	</tr>
	<tr>
		<th>郵便番号</th>
		<td><% out.print(account.getZipCodeConvertedHtml()); %></td>
	</tr>
	<tr>
		<th>住所</th>
		<td><% out.print(account.getAddressConvertedHtml()); %></td>
	</tr>
	<tr>
		<th>電話番号</th>
		<td><% out.print(account.getPhoneNumberConvertedHtml()); %></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="hidden" name="action" value="complete">
			<a href="/nmnw/cart"><button type="button" class="btn btn-primary">戻る</button></a>
			&nbsp;&nbsp;
			<button type="submit" class="btn btn-danger">購入する</button>
		</td>
	</tr>
</table>
</form>
</div>
<%
	}
%>
</body>
</html>