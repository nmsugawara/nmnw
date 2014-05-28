package com.nmnw.admin.function.item.edit;

import com.nmnw.admin.dao.Item;

public class EditModel {
	public boolean validationCheck(Item item) {
		return true;
	}
}

class ValidationCheckException extends Exception {
	public ValidationCheckException() {
		super();
	}
}