<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>No Music No Work | 商品情報編集</title>
</head>
<body>
<div id="data_table">
<form method="post" action="edit">
	<table border=1>
		<tr>
			<th>商品名</th>
			<td><input type="text" name="item_name" size="60" value="${result[0].getItemName()}"></td>
		</tr>
		<tr>
			<th>商品単価</th>
			<td><input type="text" name="item_price" size="10" value="${result[0].getItemPrice()}">円</td>
		</tr>
		<tr>
			<th>ジャンル</th>
			<td>
				<select name="item_category">
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
			<th>商品画像</th>
			<td><input type="file" name="item_image" size="30"></td>
		</tr>
		<tr>
			<th>商品説明</th>
			<td><textarea name="item_explanation" rows="20" cols="60">${result[0].getItemExplanation()}</textarea></td>
		</tr>
		<tr>
			<th>販売開始日</th>
			<td><input type="text" name="item_sales_period_from" size="10" value="${result[0].getItemSalesPeriodFrom()}"></td>
		</tr>
		<tr>
			<th>販売終了日</th>
			<td><input type="text" name="item_sales_period_to" size="10" value="${result[0].getItemSalesPeriodTo()}"></td>
		</tr>
		<tr>
			<th>在庫数</th>
			<td><input type="text" name="item_stock" size="10" value="${result[0].getItemStock()}"></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="hidden" name="item_id" value="${result[0].getItemId()}">
				<input type="hidden" name="action" value="edit_end">
				<input type="submit" value="編集完了">
			</td>
		</tr>
	</table>
</form>
</div>
</body>
</html>