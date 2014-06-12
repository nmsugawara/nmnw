<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nmnw.admin.dao.Item"%>
<%@ page import="com.nmnw.admin.Enum.ItemCategoryEnum"%>
<%@ page import="com.nmnw.admin.constant.ConfigConstants"%>
<%
	request.setCharacterEncoding("UTF-8");
	List<ItemCategoryEnum> itemCategoryList = new ArrayList<ItemCategoryEnum>(Arrays.asList(ItemCategoryEnum.values()));
	List<String> errorMessageList = (List<String>)request.getAttribute("errorMessageList");
	Map<String, String[]> inputDataList = (Map<String, String[]>)request.getAttribute("inputDataList");
	Item result = (Item)request.getAttribute("result");
	String actionParam = inputDataList.get("action")[0];
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/nmnw/commons/css/bootstrap.min.css" rel="stylesheet">
<link href="/nmnw/commons/css/style.css" rel="stylesheet">
<link href="/nmnw/commons/css/datepicker.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="/nmnw/commons/js/bootstrap-datepicker.js"></script>
<script src="/nmnw/commons/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#from").datepicker({
		format: 'yyyy-mm-dd',
		autoclose: true
	});
	$("#to").datepicker({
		format: 'yyyy-mm-dd',
		autoclose: true
	});
});
</script>
<title>No Music No Work | 商品情報編集</title>
</head>
<body>
<jsp:include page="/commons/Menu.jsp"/>
<table>
<tr>
<td>
<h1>商品編集</h1>
<p>
	<font color="red">
<% 
	for(int i=0; i < errorMessageList.size(); i++) {
		String message = errorMessageList.get(i);
		out.print(message);
		out.print("<br>");
	}
%>
	</font>
</p>
<%
	// 該当データがある場合
	if (result.getId() != 0) {
%>
<div id="data_table">
<form method="post" action="edit?action=edit_end"  enctype="multipart/form-data">
	<table class="table table-bordered table-condensed">
		<tr>
			<th>商品名</th>
			<td><input type="text" name="item_name" size="60" value="<%
			if ("edit_end".equals(actionParam)) {
				out.print(inputDataList.get("item_name")[0]);
			} else {
				out.print(result.getName());
			} %>"></td>
		</tr>
		<tr>
			<th>商品単価</th>
			<td><input type="text" name="item_price" size="10" value="<%
			if ("edit_end".equals(actionParam)) {
				out.print(inputDataList.get("item_price")[0]);
 			} else {
				out.print(result.getPrice());
			} %>">円</td>
		</tr>
		<tr>
			<th>ジャンル</th>
			<td>
				<select name="item_category">
<% for(int i=0; i < itemCategoryList.size(); i++) {
		out.print("<option value='" + itemCategoryList.get(i).getCategoryCode() + "'");
		String selectedCateory = "";
		if("edit_end".equals(actionParam)) {
			selectedCateory= inputDataList.get("item_category")[0];
		} else {
			selectedCateory= result.getCategory();
		}
		if (selectedCateory.equals(itemCategoryList.get(i).getCategoryCode())) {
			out.print(" selected='selected'");
		}
		out.println(">" + itemCategoryList.get(i).name() + "</option>");
	}
%>
				</select>
			</td>
		</tr>
		<tr>
			<th>商品画像</th>
			<td>
				<img src="<%= ConfigConstants.IMAGE_DIR_ITEM %>${result.getImageUrl()}" width="200" height="200">
				<input type="hidden" name="old_item_image" value="${result.getImageUrl()}">
				<input type="file" name="item_image" size="30">
			</td>
		</tr>
		<tr>
			<th>商品説明</th>
			<td><textarea name="item_explanation" rows="20" cols="60"><%
			if ("edit_end".equals(actionParam)) {
				out.print(inputDataList.get("item_explanation")[0]);
 			} else {
				out.print(result.getExplanation());
			}%></textarea></td>
		</tr>
		<tr>
			<th>販売開始日</th>
			<td><input type="text" id="from" name="item_sales_period_from" size="20" value="<%
			if ("edit_end".equals(actionParam)) {
				out.print(inputDataList.get("item_sales_period_from")[0]);
 			} else {
				out.print(result.getSalesPeriodFrom().toString());
			} %>"></td> 
		</tr>
		<tr>
			<th>販売終了日</th>
			<td><input type="text" id="to" name="item_sales_period_to" size="20" value="<%
			if ("edit_end".equals(actionParam)) {
				out.print(inputDataList.get("item_sales_period_to")[0]);
 			} else {
 				out.print(result.getSalesPeriodTo().toString());
			} %>"></td> 
		</tr>
		<tr>
			<th>在庫数</th>
			<td><input type="text" name="item_stock" size="10" value="<%
			if ("edit_end".equals(actionParam)) {
				out.print(inputDataList.get("item_stock")[0]);
 			} else {
				out.print(result.getStock());
			} %>"></td> 
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="hidden" name="item_id" value="<%
				if ("edit_end".equals(actionParam)) {
					out.print(inputDataList.get("item_id")[0]);
 				} else {
					out.print(result.getId());
				} %>">
				<button type="submit" class="btn btn-primary">編集完了</button>
			</td>
		</tr>
	</table>
</form>
</div>
<%} %>
</td>
</tr>
</table>
</body>
</html>