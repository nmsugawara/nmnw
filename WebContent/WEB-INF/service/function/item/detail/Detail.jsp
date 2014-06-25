<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nmnw.service.dao.Item"%>
<%@ page import="com.nmnw.service.Enum.ItemCategoryEnum"%>
<%@ page import="com.nmnw.service.constant.ConfigConstants"%>
<%
	request.setCharacterEncoding("UTF-8");
	String action = (String)request.getParameter("action");
	Item result = (Item)request.getAttribute("result");
	HttpSession loginInfo = request.getSession();
	int accountId = 0;
	if (loginInfo.getAttribute("id") != null) {
		accountId = (Integer)loginInfo.getAttribute("id");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/nmnw/commons/css/bootstrap.min.css" rel="stylesheet">
<link href="/nmnw/commons/css/style.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="/nmnw/commons/js/bootstrap.min.js"></script>
<title>No Music No Work | 商品詳細</title>
</head>
<body class="service_body">
<jsp:include page="/WEB-INF/service/function/commons/Menu.jsp"/>
<table id="canvas">
<tr>
<td>

<p>
	<h1>${message}</h1>
</p>
<% 
	// 該当データがある場合
	if (result.getId() != 0) {
%>
<div id="service_item_detail">
	<table class="table table-bordered table-condensed">
		<tr>
			<td rowspan="6" width="300">
				<div class="image">
					<img src="<% out.print(ConfigConstants.IMAGE_DIR_ITEM + result.getImageUrl()); %>" width="250" height="250">
				</div>
			</td>
		</tr>
		<tr>
			<th><% out.print(result.getNameConvertedHtml()); %></th>
		</tr>
		<tr>
			<td><% out.print(ItemCategoryEnum.getEnum(result.getCategory()).name()); %></td>
		</tr>
		<tr>
			<td>￥<% out.print(result.getPrice()); %></td>
		</tr>
		<tr>
			<td>販売開始日　　<% out.print(result.getSalesPeriodFrom().toString()); %></td>
		</tr>
		<tr>
			<td>
<%
		if(accountId != 0) {
			out.println("<button type=\"button\" class=\"btn btn-primary\">カートに入れる</button>");
		} else {
			out.println("<a href=\"/nmnw/account/register\"><button type=\"button\" class=\"btn btn-primary\">会員登録</button></a>");
		}
%>
			</td>
		</tr>
		<tr>
			<th colspan="2">商品説明</th>
		</tr>
		<tr>
			<td colspan="2"><% out.print(result.getExplanationConvertedHtml()); %></td>
		</tr>
	</table>
<%
	}
%>
</div>
	<a href="search">商品検索へ</a>
</td>
</tr>
</table>
</body>
</html>