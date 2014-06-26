<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nmnw.service.dao.Cart"%>
<%@ page import="com.nmnw.service.dao.Item"%>
<%@ page import="com.nmnw.service.constant.ConfigConstants"%>
<%
	request.setCharacterEncoding("UTF-8");
	// ログイン情報
	Integer id = (Integer)session.getAttribute("id");
	// cart情報
	List<Cart.CartItem> cartItemList = new ArrayList<Cart.CartItem>();
	if (session.getAttribute("cart") != null) {
		Cart cart = (Cart)session.getAttribute("cart");
		cartItemList = cart.getItemList();
	}
	// 遷移元URL
	String refUrl = "/nmnw/item/search";
	if (request.getAttribute("refUrl") != null) {
		refUrl = (String)request.getAttribute("refUrl");
	}
	String messageCode = "";
	String message = "";
	if (request.getAttribute("messageCode") != null) {
		if ("add".equals((String)request.getAttribute("messageCode"))) {
			message = "カートに商品を追加しました。";
		} else if ("delete".equals((String)request.getAttribute("messageCode"))) {
			message = "カートから商品を削除しました。";
		} else if ("modify".equals((String)request.getAttribute("messageCode"))) {
			message = "商品数を変更しました。";
		}
	}
	List<String> errorMessageList = (List<String>)request.getAttribute("errorMessageList");
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
<title>No Music No Work | カート</title>
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
	// ログインしている場合
	if (id != null) {
%>
	<p><%= message %></p>
	<table class="table table-bordered">
		<tr>
			<td>商品名</td>
			<td>金額</td>
			<td>個数</td>
			<td>小計</td>
			<td></td>
		</tr>
<%
		int totalPrice = 0;
		for(int i = 0; i < cartItemList.size(); i++) {
			Cart.CartItem cartItem = (Cart.CartItem)cartItemList.get(i);
			out.println("<tr>");
			out.println("<td>" + cartItem.getItemName() + "</td>");
			out.println("<td>￥" + cartItem.getItemPrice() + "</td>");
			out.println("<td>" + cartItem.getItemCount() + "</td>");
			out.println("<td>￥" + (cartItem.getItemPrice() * cartItem.getItemCount()) + "</td>");
			out.println("<td>");
				out.println("<form method=\"post\" action=\"cart?action=delete&item_id=" + cartItem.getItemId() + "\">");
				out.println("<input type=\"hidden\" name=\"ref_url\" value=\"" + refUrl + "\">");
				out.println("<button type=\"submit\" class=\"btn btn-danger\">削除</button>");
				out.println("</form>");
			out.println("</td>");
			out.println("</tr>");
			totalPrice += cartItem.getItemPrice() * cartItem.getItemCount();
		}
		out.println("<tr>");
		out.println("<td colspan=\"3\">合計</td>");
		out.println("<td>￥" + totalPrice + "</td>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td class=\"center\">");
		if (cartItemList.size() > 0) {
			out.println("<p class=\"cnter\">");
			out.println("<a href=\"/nmnw/purchase/confirm\"><button type=\"action\" class=\"btn btn-success\">購入する</button></a>");
			out.println("</p>");
		}
		out.println("</td>");
		out.println("</tr>");
	}
%>
<tr>
<td>
	<a href="<%= refUrl %>">戻る</a>
</td>
</tr>
</table>
</body>
</html>