package com.ivan.consume.bean;

public class ConsumeRecord {
	
	private String consume_name;
	
	private String consume_category;
	
	private String consume_group_id;
	
	private String consume_date;
	
	private String consume_price;
	
	private String consume_quntity;
	
	private String consume_comments;

	public String getConsume_name() {
		return consume_name;
	}

	public void setConsume_name(String consume_name) {
		this.consume_name = consume_name;
	}

	public String getConsume_category() {
		return consume_category;
	}

	public void setConsume_category(String consume_category) {
		this.consume_category = consume_category;
	}

	public String getConsume_date() {
		return consume_date;
	}

	public void setConsume_date(String consume_date) {
		this.consume_date = consume_date;
	}

	public String getConsume_price() {
		return consume_price;
	}

	public void setConsume_price(String consume_price) {
		this.consume_price = consume_price;
	}

	public String getConsume_quntity() {
		return consume_quntity;
	}

	public void setConsume_quntity(String consume_quntity) {
		this.consume_quntity = consume_quntity;
	}

	public String getConsume_comments() {
		return consume_comments;
	}

	public void setConsume_comments(String consume_comments) {
		this.consume_comments = consume_comments;
	}

	@Override
	public String toString() {
		return "Consume Record Detail: " + 
			"\t" + this.consume_name +
			"\t" + this.consume_category + 
			"\t" + this.consume_date + 
			"\t" + this.consume_price +
			"\t" + this.consume_quntity + 
			"\t" + this.consume_comments;
	}

	public ConsumeRecord() {
		super();
	}

	public String getConsume_group_id() {
		return consume_group_id;
	}

	public void setConsume_group_id(String consumeGroupId) {
		consume_group_id = consumeGroupId;
	}

}
