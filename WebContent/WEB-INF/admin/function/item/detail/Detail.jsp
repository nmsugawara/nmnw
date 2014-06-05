<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String action = (String)request.getParameter("action");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>No Music No Work | 商品詳細</title>
</head>
<body>
<p>
	<h1>${message}</h1>
</p>
<div id="data_table">
<form method="post" action="edit?item_id=${result.getId()}&action=edit"  enctype="multipart/form-data">
	<table border=1>
		<tr>
			<th>商品名</th>
			<td><input type="hidden" name="item_name" value="${result.getName()}">${result.getName()}</td>
		</tr>
		<tr>
			<th>商品単価</th>
			<td><input type="hidden" name="item_price" value="${result.getPrice()}">${result.getPrice()}円</td>
		</tr>
		<tr>
			<th>ジャンル</th>
			<td><input type="hidden" name="item_category" value="${result.getCategory()}">${result.getCategory()}</td>
		</tr>
		<tr>
			<th>商品画像</th>
			<td>
				<img src="${result.getImageUrl()}" width="200" height="200">
				<input type="hidden" name="item_image" value="${result.getImageUrl()}">
			</td>
		</tr>
		<tr>
			<th>商品説明</th>
			<td><input type="hidden" name="item_explanation" value="${result.getExplanationConvertedHtml()}">${result.getExplanationConvertedHtml()}</td>
		</tr>
		<tr>
			<th>販売開始日</th>
			<td><input type="hidden" name="item_sales_period_from" value="${result.getSalesPeriodFrom()}">${result.getSalesPeriodFrom()}</td>
		</tr>
		<tr>
			<th>販売終了日</th>
			<td><input type="hidden" name="item_sales_period_to" value="${result.getSalesPeriodTo()}">${result.getSalesPeriodTo()}</td>
		</tr>
		<tr>
			<th>在庫数</th>
			<td><input type="hidden" name="item_stock" value="${result.getStock()}">${result.getStock()}</td>
		</tr>
<%  if(!("new_end".equals(action)) && !("edit_end".equals(action))) { %>
		<tr>
			<td colspan="2">
				<input type="submit" value="編集">
			</td>
		</tr>
<% }%>
	</table>
</form>
</div>
<%
if("new_end".equals(action) || "edit_end".equals(action)) {
	out.println("<a href=\"search\">商品管理TOPへ</a>");
}
%>
</body>
</html>