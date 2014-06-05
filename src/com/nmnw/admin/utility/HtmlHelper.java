package com.nmnw.admin.utility;

public class HtmlHelper {
	
	public static String nl2br (String s) {
		return s.replaceAll("\n", "<br>");
	}

	public static String htmlspecialchars (String s) {
		String[] escape = {"&", "<", ">", "\"", "\'", "\n", "\t"};
		String[] replace = {"&amp;", "&lt;", "&gt;", "&quot;", "&#39;", "<br>", "&#x0009;"};
		for (int i=0; i < escape.length; i++) {
			s = s.replace(escape[i], replace[i]);
		}
		return s;
	}
}