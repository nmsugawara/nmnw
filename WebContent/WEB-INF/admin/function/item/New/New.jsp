<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nmnw.admin.Enum.ItemCategoryEnum"%>
<%
	request.setCharacterEncoding("UTF-8");
	List<ItemCategoryEnum> itemCategoryList = new ArrayList<ItemCategoryEnum>(Arrays.asList(ItemCategoryEnum.values()));
	List<String> errorMessageList = (List<String>)request.getAttribute("errorMessageList");
	Map<String, String[]> inputDataList = (Map<String, String[]>)request.getAttribute("inputDataList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/common/css/bootstrap.min.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="/common/js/bootstrap.min.js"></script>
<title>No Music No Work | 商品登録</title>
</head>
<body>
<h1>商品登録</h1>
<p>
	<font color="red">
<% for(int i=0; i < errorMessageList.size(); i++) {
	String message = errorMessageList.get(i);
	out.println(message);
	out.println("<br>");
	}
%>
	</font>
</p>
<form method="post" action="new?action=new_end" enctype="multipart/form-data">
	<table>
		<tr>
			<th>商品名</th>
			<td><input type="text" name="item_name" size="60" value="${inputDataList.get('item_name')[0]}"></td>
		</tr>
		<tr>
			<th>商品単価</th>
			<td><input type="text" name="item_price" size="10" value="${inputDataList.get('item_price')[0]}">円</td>
		</tr>
		<tr>
			<th>ジャンル</th>
			<td>
				<select name="item_category">
<% for(int i=0; i < itemCategoryList.size(); i++) {%>
	<option value="<%= itemCategoryList.get(i).getCategoryCode()%>"
	<% if(!inputDataList.isEmpty()) {
			if (inputDataList.get("item_category")[0].equals(itemCategoryList.get(i).getCategoryCode())) {
					out.println(" selected='selected'");
			}
		}
	%>><%= itemCategoryList.get(i).name()%></option>
<%} %>
				</select>
			</td>
		</tr>
		<tr>
			<th>商品画像</th>
			<td><input type="file" name="item_image" size="30"></td>
		</tr>
		<tr>
			<th>商品説明</th>
			<td><textarea name="item_explanation" rows="20" cols="60">${inputDataList.get('item_explanation')[0]}</textarea></td>
		</tr>
		<tr>
			<th>販売開始日</th>
			<td><input type="text" name="item_sales_period_from" size="20" value="${inputDataList.get('item_sales_period_from')[0]}"></td>
		</tr>
		<tr>
			<th>販売終了日</th>
			<td><input type="text" name="item_sales_period_to" size="20" value="${inputDataList.get('item_sales_period_to')[0]}"></td>
		</tr>
		<tr>
			<th>在庫数</th>
			<td><input type="text" name="item_stock" size="10" value="${inputDataList.get('item_stock')[0]}"></td>
		</tr>
		<tr>
			<td>
				<input type="hidden" name="action" value="new_end">
				<input type="submit" value="登録">
			</td>
		</tr>
	</table>
</form>
</body>
</html>