<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nmnw.service.dao.Item"%>
<%@ page import="com.nmnw.service.Enum.ItemCategoryEnum"%>
<%@ page import="com.nmnw.service.Enum.ItemSortEnum"%>
<%@ page import="com.nmnw.service.constant.ConfigConstants"%>
<%
	request.setCharacterEncoding("UTF-8");
	List<ItemCategoryEnum> itemCategoryList = new ArrayList<ItemCategoryEnum>(Arrays.asList(ItemCategoryEnum.values()));
	List<ItemSortEnum> itemSortList = new ArrayList<ItemSortEnum>(Arrays.asList(ItemSortEnum.values()));
	List<String> errorMessageList = (List<String>)request.getAttribute("errorMessageList");
	Map<String, String[]> inputDataList = (Map<String, String[]>)request.getAttribute("inputDataList");
	List<Item> resultList = (List<Item>)request.getAttribute("result");
	String action= "";
	if (inputDataList != null) { 
		if (inputDataList.containsKey("action")) {
			action = inputDataList.get("action")[0];
		}
	}
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
<title>No Music No Work | 商品検索</title>
</head>
<body>
<jsp:include page="/WEB-INF/service/function/commons/Menu.jsp"/>
<div id="search_table">
	<form method="post" action="search?action=search">
		<table class="table table-bordered">
			<tr>
				<th>商品名</th>
				<td><input type="text" name="search_name" value="<%
				if ("search".equals(action)) {
					out.print(inputDataList.get("search_name")[0]);
				} %>"></td>
				<th>ジャンル</th>
				<td>
					<select name="search_category">
<% 
		for(int i=0; i < itemCategoryList.size(); i++) {
			out.print("<option value='" + itemCategoryList.get(i).getCategoryCode() + "'");
			if("search".equals(action)) {
				if (inputDataList.get("search_category")[0].equals(itemCategoryList.get(i).getCategoryCode())) {
					out.print(" selected='selected'");
				}
			}
			out.println(">" + itemCategoryList.get(i).name() + "</option>");
		}
%>
					</select>
				</td>
				<th>表示順</th>
				<td>
					<select name="search_sort">
<% 
		for(int i=0; i < itemSortList.size(); i++) {
			out.print("<option value='" + itemSortList.get(i).getSortCode() + "'");
			if("search".equals(action)) {
				if (inputDataList.get("search_sort")[0].equals(itemSortList.get(i).getSortCode())) {
					out.print(" selected='selected'");
				}
			}
			out.println(">" + itemSortList.get(i).name() + "</option>");
		}
%>
					</select>
				</td>
				<td>
					<button type="submit" class="btn btn-primary">Search</button>
				</td>
			</tr>
		</table>
	</form>
</div>
<p>
	<font color="red">
<% 
	if (errorMessageList != null) {
		for(int i=0; i < errorMessageList.size(); i++) {
			String message = errorMessageList.get(i);
			out.println(message);
			out.println("<br>");
		}
	}
%>
	</font>
</p>
<div id="service_item_list">
	<table>
		<tr><td>
<%
if ("search".equals(action) && resultList != null && resultList.size() != 0) {
	out.println("<ul class=\"ul-list\">");
	for(int i = 0; i < resultList.size(); i++) {
		out.println("<li><dl>");
		out.print("<div class=\"image\">");
		out.print("<dt><a href=\"detail?item_id=" + String.valueOf(resultList.get(i).getId()) + "\">");
		out.print("<img src='" + ConfigConstants.IMAGE_DIR_ITEM + resultList.get(i).getImageUrl() + "' width='150' height='150'></dt>");
		out.println("</div>");
		out.println("<dd><a href=\"detail?item_id=" + String.valueOf(resultList.get(i).getId()) + "\">" + resultList.get(i).getNameConvertedHtml() + "</dd>");
		out.println("<dd>￥" + resultList.get(i).getPrice() + "</dd>");
		out.println("</li></dl>");
	}
	out.println("</ul");
}
%>
		</tr></td>
	</table>
</div>
</body>
</html>