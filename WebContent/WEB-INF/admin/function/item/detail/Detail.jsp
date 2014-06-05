<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nmnw.admin.dao.Item"%>
<%@ page import="com.nmnw.admin.Enum.ItemCategoryEnum"%>
<%@ page import="com.nmnw.admin.constant.ConfigConstants"%>
<%
	request.setCharacterEncoding("UTF-8");
	String action = (String)request.getParameter("action");
	Item result = (Item)request.getAttribute("result");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/nmnw/commons/css/bootstrap.min.css" rel="stylesheet">
<link href="/nmnw/commons/css/style.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="/commons/js/bootstrap.min.js"></script>
<title>No Music No Work | 商品詳細</title>
</head>
<body>
<p>
	<h1>${message}</h1>
</p>
<div id="data_table">
<form method="post" action="edit?item_id=<% out.print(result.getId()); %>&action=edit"  enctype="multipart/form-data">
	<table class="table table-bordered table-condensed">
		<tr>
			<th>商品名</th>
			<td><input type="hidden" name="item_name" value="<% out.print(result.getNameConvertedHtml()); %>"><% out.print(result.getNameConvertedHtml()); %></td>
		</tr>
		<tr>
			<th>商品単価</th>
			<td><input type="hidden" name="item_price" value="<% out.print(result.getPrice()); %>"><% out.print(result.getPrice()); %>円</td>
		</tr>
		<tr>
			<th>ジャンル</th>
			<td><input type="hidden" name="item_category" value="<% out.print(result.getCategory()); %>"><% out.print(ItemCategoryEnum.getEnum(result.getCategory()).name()); %></td>
		</tr>
		<tr>
			<th>商品画像</th>
			<td>
				<img src="<% out.print(ConfigConstants.IMAGE_DIR_ITEM + result.getImageUrl()); %>" width="200" height="200">
				<input type="hidden" name="item_image" value="<% out.print(result.getImageUrl()); %>">
			</td>
		</tr>
		<tr>
			<th>商品説明</th>
			<td><input type="hidden" name="item_explanation" value="<% out.print(result.getExplanationConvertedHtml()); %>"><% out.print(result.getExplanationConvertedHtml()); %></td>
		</tr>
		<tr>
			<th>販売開始日</th>
			<td><input type="hidden" name="item_sales_period_from" value="<% out.print(result.getSalesPeriodFrom()); %>"><% out.print(result.getSalesPeriodFrom()); %></td>
		</tr>
		<tr>
			<th>販売終了日</th>
			<td><input type="hidden" name="item_sales_period_to" value="<% out.print(result.getSalesPeriodTo()); %>"><% out.print(result.getSalesPeriodTo()); %></td>
		</tr>
		<tr>
			<th>在庫数</th>
			<td><input type="hidden" name="item_stock" value="<% out.print(result.getStock()); %>"><% out.print(result.getStock()); %></td>
		</tr>
<%  if(!("new_end".equals(action)) && !("edit_end".equals(action))) { %>
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
if("new_end".equals(action) || "edit_end".equals(action)) {
	out.println("<a href=\"search\">商品管理TOPへ</a>");
}
%>
</body>
</html>