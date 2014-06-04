<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nmnw.admin.dao.Item"%>
<%@ page import="com.nmnw.admin.Enum.ItemCategoryEnum"%>
<%
	request.setCharacterEncoding("UTF-8");
	String action = (String)request.getParameter("action");
	List<ItemCategoryEnum> itemCategoryList = (List<ItemCategoryEnum>)request.getAttribute("itemCategoryList");
	List<String> errorMessageList = (List<String>)request.getAttribute("errorMessageList");
	Map<String, String[]> inputDataList = (Map<String, String[]>)request.getAttribute("inputDataList");
	List<Item> resultList = (List<Item>)request.getAttribute("result");
	String actionParam = "";
	if (inputDataList.containsKey("action")) {
		actionParam = inputDataList.get("action")[0];
	}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>No Music No Work | 商品検索</title>
</head>
<body>
<div id="search_table">
<form method="post" action="search?action=search">
	<table border=1>
		<tr>
			<th colspan="2">検索</th>
		</tr>
		<tr>
			<th>商品ID</th>
			<td><input type="text" name="search_item_id" value="<%
			if ("search".equals(actionParam)) {
				out.print(inputDataList.get("search_item_id")[0]);
			} %>"></td>
		</tr>
		<tr>
			<th>商品名</th>
			<td><input type="text" name="search_item_name" value="<%
			if ("search".equals(actionParam)) {
				out.print(inputDataList.get("search_item_name")[0]);
			} %>"></td>
		</tr>
		<tr>
			<th>ジャンル</th>
			<td>
				<select name="search_item_category">
<% for(int i=0; i < itemCategoryList.size(); i++) {
		out.print("<option value='" + itemCategoryList.get(i).getCategoryCode() + "'");
		if("search".equals(actionParam)) {
			if (inputDataList.get("search_item_category")[0].equals(itemCategoryList.get(i).getCategoryCode())) {
				out.print(" selected='selected'");
			}
		}
		out.println(">" + itemCategoryList.get(i).name() + "</option>");
	}
%>
				</select>
			</td>
		</tr>
		<tr>
			<th>販売開始日</th>
			<td><input type="text" name="search_item_sales_period_from" value="<%
			if ("search".equals(actionParam)) {
				out.print(inputDataList.get("search_item_sales_period_from")[0]);
			} %>"></td>
		</tr>
		<tr>
			<th>販売終了日</th>
			<td><input type="text" name="search_item_sales_period_to" value="<%
			if ("search".equals(actionParam)) {
				out.print(inputDataList.get("search_item_sales_period_to")[0]);
			} %>"></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="検索">
			</td>
		</tr>
	</table>
</form>
</div>
<div id="data_table">
	<table border=1>
		<tr>
			<th>商品ID</th>
			<th>商品名</th>
			<th>商品画像</th>
			<th>商品単価</th>
			<th>ジャンル</th>
			<th>販売開始日</th>
			<th>販売終了日</th>
			<th>在庫数</th>
		</tr>
<%
for(int i = 0; i < resultList.size(); i++) {
	out.println("<tr>");
	out.println("<td><a href=\"detail?item_id=" + String.valueOf(resultList.get(i).getId()) + "\">" + String.valueOf(resultList.get(i).getId()) + "</td>");
	out.println("<td>" + resultList.get(i).getName() + "</td>");
	out.println("<td><img src='" + resultList.get(i).getImageUrl() + "' width='50' height='50'></td>");
	out.println("<td>" + resultList.get(i).getPrice() + "</td>");
	out.println("<td>" + resultList.get(i).getCategory() + "</td>");
	out.println("<td>" + resultList.get(i).getSalesPeriodFrom() + "</td>");
	out.println("<td>" + resultList.get(i).getSalesPeriodTo() + "</td>");
	out.println("<td>" + resultList.get(i).getStock() + "</td>");
	out.println("</tr>");
}
%>
	</table>
</div>
</body>
</html>