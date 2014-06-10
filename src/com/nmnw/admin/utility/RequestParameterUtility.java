package com.nmnw.admin.utility;

import javax.servlet.http.Part;

public class RequestParameterUtility {
	
	public RequestParameterUtility() {
		
	}
	
	public static boolean isEmptyParam (String value) {
		boolean status = false;
		if(value == null || value.length() == 0) {
			status = true;
		}
		return status;
	}

	public static boolean isEmptyImage (Part image) {
		boolean status = false;
		long emptyValue = 0;
		if(image.getSize() == emptyValue) {
			status = true;
		}
		return status;
	}
}