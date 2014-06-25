<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nmnw.service.dao.Order"%>
<%@ page import="com.nmnw.service.dao.OrderDetail"%>
<%@ page import="com.nmnw.service.Enum.OrderPeriodEnum"%>
<%@ page import="com.nmnw.service.constant.ConfigConstants"%>
<%@ page import="com.nmnw.service.utility.DateConversionUtility"%>
<%
	request.setCharacterEncoding("UTF-8");
	List<OrderPeriodEnum> orderPeriodList = new ArrayList<OrderPeriodEnum>(Arrays.asList(OrderPeriodEnum.values()));
	Map<String, String[]> inputDataList = (Map<String, String[]>)request.getAttribute("inputDataList");
	List<Map<String, Object>> resultList = (List<Map<String, Object>>)request.getAttribute("result");
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
<title>No Music No Work | 購入履歴</title>
</head>
<body class="service_body">
<jsp:include page="/WEB-INF/service/function/commons/Menu.jsp"/>
<table id="canvas">
<tr>
<td>
	<form method="post" action="search">
		<table class="table table-bordered">
			<tr>
				<th>注文時期</th>
				<td>
					<select name="order_period">
<% 
		for(int i=0; i < orderPeriodList.size(); i++) {
			out.print("<option value='" + orderPeriodList.get(i).getPeriodCode() + "'");
			if(request.getAttribute("inputDataList") != null && inputDataList.containsKey("order_period")) {
				if (inputDataList.get("order_period")[0].equals(orderPeriodList.get(i).getPeriodCode())) {
					out.print(" selected='selected'");
				}
			}
			out.println(">" + orderPeriodList.get(i).name() + "</option>");
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
	<p>
		<font color="red">
<% 
		if (errorMessageList != null) {
			for(int i=0; i < errorMessageList.size(); i++) {
				String message = errorMessageList.get(i);
				out.print(message);
				out.print("<br>");
			}
		}
%>
		</font>
	</p>
	<table class="table table-bordered">
<%
if (resultList != null && resultList.size() != 0) {
	for(int i = 0; i < resultList.size(); i++) {
		Order o = (Order)resultList.get(i).get("order");
		List<OrderDetail> orderDetailList = (List<OrderDetail>)resultList.get(i).get("orderDetail");
		out.println("<tr><td>");
			// ステータス
			String status = "";
			if ("注文済".equals((String)resultList.get(i).get("orderStatus"))) {
				status = "<span class=\"label label-warning\">" + resultList.get(i).get("orderStatus") + "</span>";
			} else if ("発送済".equals((String)resultList.get(i).get("orderStatus"))) {
				status = "<span class=\"label label-success\">" + resultList.get(i).get("orderStatus") + "</span>";
			} else {
				status = "<span class=\"label label-important\">" + resultList.get(i).get("orderStatus") + "</span>";
			}
			out.println(status);
			out.println("<br>");
			// 注文日
			out.println(DateConversionUtility.dateTimeToString(o.getOrderTime()));
			out.println("<br>");
			// 注文番号
			out.println("注文番号：" + o.getOrderId());
			out.println("<br>");
		out.println("</td>");
		out.println("<td>");
		out.println("<table>");
		for(int j = 0; j < orderDetailList.size(); j++) {
			out.println("<tr>");
			out.println("<td>" + orderDetailList.get(j).getItemNameConvertedHtml() + "</td>");
			out.println("<td>￥" + orderDetailList.get(j).getItemPrice() + "</td>");
			out.println("<td>" + orderDetailList.get(j).getItemCount() + "</td>");
			out.println("<td>￥" + (orderDetailList.get(j).getItemPrice() * orderDetailList.get(j).getItemCount()) + "</td>");
			out.println("</tr>");
		}
		out.println("<tr><td align=\"right\" colspan=\"4\">合計  ￥" + o.getTotalPrice() + "</td></tr>");
		out.println("</table>");
		out.println("</td>");
		out.println("<td>");
		if ("注文済".equals((String)resultList.get(i).get("orderStatus"))) {
			out.println("<form method=\"get\" action=\"cancel?order_id=" + o.getOrderId() + "\">");
			out.println("<button type=\"submit\" class=\"btn btn-danger\">注文取消</button>");
			out.println("</form>");
		}
		out.println("</td></tr>");
	}
}
%>
	</table>
</td>
</tr>
</table>
</body>
</html>