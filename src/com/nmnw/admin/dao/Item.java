package com.nmnw.admin.dao;
import com.nmnw.admin.utility.HtmlHelper;

public class Item {

	private int _id;
	private String _name = "";
	private int _price;
	private String _category = "";
	private String _imageUrl = "";
	private String _explanation = "";
	private String _salesPeriodFrom = "";
	private String _salesPeriodTo = "";
	private int _stock;

	/**
	 * Construct
	 */
	public Item() {
	} 

	public void setId(int id) {
		_id = id;
	}

	public int getId() {
		return _id;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getName() {
		return _name;
	}

	public String getNameConvertedHtml() {
		String str = HtmlHelper.htmlspecialchars(_name);
		str = HtmlHelper.nl2br(str);
		return str;
	}

	public void setPrice(int price) {
		_price = price;
	}

	public Integer getPrice() {
		return _price;
	}

	public void setCategory(String category) {
		_category = category;
	}

	public String getCategory() {
		return _category;
	}

	public void setImageUrl(String imageUrl) {
		_imageUrl = imageUrl;
	}

	public String getImageUrl() {
		return _imageUrl;
	}

	public void setExplanation(String explanation) {
		_explanation = explanation;
	}

	public String getExplanation() {
		return _explanation;
	}

	public String getExplanationConvertedHtml() {
		String str = HtmlHelper.htmlspecialchars(_explanation);
		str = HtmlHelper.nl2br(str);
		return str;
	}

	public void setSalesPeriodFrom(String salesPeriodFrom) {
		_salesPeriodFrom = salesPeriodFrom;
	}

	public String getSalesPeriodFrom() {
		return _salesPeriodFrom;
	}

	public void setSalesPeriodTo(String salesPeriodTo) {
		_salesPeriodTo = salesPeriodTo;
	}

	public String getSalesPeriodTo() {
		return _salesPeriodTo;
	}

	public void setStock(int stock) {
		_stock = stock;
	}

	public int getStock() {
		return _stock;
	}
}