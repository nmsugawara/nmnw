<%@ page language="java" pageEncoding="UTF-8"%>
<%
	Integer id = (Integer)session.getAttribute("id");
	String name = (String)session.getAttribute("name");
%>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<div id="logo">
				<div class="flip-3d">
					<figure>
						<img src="/nmnw/commons/images/logo.JPG" width="100" height="100">
						<figcaption>No Music<br>No Work</figcaption>
					</figure>
				</div>
			</div>
			<a class="brand pull-right" href="/nmnw/index">No Music, No Work</a>
			<ul class="nav nav-pills">
				<li><a href="/nmnw/index">Top</a></li>
				<li><a href="/nmnw/item/search">商品検索</a></li>
				<li><a href="/nmnw/login">ログイン</a></li>
				<li><a href="/nmnw/account/register">新規会員登録</a></li>
<%
	if (name != null) {
		out.println("<li class=\"navbar-text\">こんにちは" + name + "さん。</li>");
	}
%>
<%
	if (id != null) {
%>
				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">会員Menu<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a tabindex="-1" href="/nmnw/cart">カートを見る</a></li>
						<li><a tabindex="-1" href="/nmnw/account/detail?account_id=<%= id %>">会員情報</a></li>
						<li><a tabindex="-1" href="/nmnw/order/search?account_id=<%= id %>">購入履歴</a></li>
						<li><a tabindex="-1" href="/nmnw/logout">ログアウト</a></li>
					</ul>
				</li>
<%
	}
%>
			</ul>
		</div>
	</div>
</div>