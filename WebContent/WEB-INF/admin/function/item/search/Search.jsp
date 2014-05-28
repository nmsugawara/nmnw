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
<title>No Music No Work | 商品検索</title>
</head>
<body>
<div id="search_table">
<form method="post" action="search">
	<table border=1>
		<tr>
			<th colspan="2">検索</th>
		</tr>
		<tr>
			<th>商品ID</th>
			<td><input type="text" name="search_item_id"></td>
		</tr>
		<tr>
			<th>商品名</th>
			<td><input type="text" name="search_item_name"></td>
		</tr>
		<tr>
			<th>商品単価</th>
			<td><input type="text" name="search_item_price">円</td>
		</tr>
		<tr>
			<th>ジャンル</th>
			<td>
				<select name="search_item_category">
					<option value="0">選択してください</option>
					<option value="1">邦楽POP</option>
					<option value="2">邦楽HIP-HOP</option>
					<option value="3">邦楽レゲエ</option>
					<option value="4">邦楽リミックス</option>
					<option value="99">その他</option>					
				</select>
			</td>
		</tr>
		<tr>
			<th>販売開始日</th>
			<td><input type="text" name="search_item_sales_period_from"></td>
		</tr>
		<tr>
			<th>販売終了日</th>
			<td><input type="text" name="search_item_sales_period_to"></td>
		</tr>
		<tr>
			<th>在庫数</th>
			<td><input type="text" name="search_item_stock"></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="hidden" name="action" value="search">
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
			<th>商品単価</th>
			<th>ジャンル</th>
			<th>販売開始日</th>
			<th>販売終了日</th>
			<th>在庫数</th>
		</tr>
	</table>
</div>
</body>
</html>