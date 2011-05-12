package com.ivan.consume.bean;

public class ConsumeRecord {
	
	private String consume_id;
	
	private String consume_name;
	
	private int consume_cate_id;
	
	private int consume_group_id;
	
	private String consume_date;
	
	private int price;
	
	private int total;
	
	private String comments;

	public String getConsume_id() {
		return consume_id;
	}

	public void setConsume_id(String consume_id) {
		this.consume_id = consume_id;
	}

	public String getConsume_name() {
		return consume_name;
	}

	public void setConsume_name(String consume_name) {
		this.consume_name = consume_name;
	}

	public int getConsume_cate_id() {
		return consume_cate_id;
	}

	public void setConsume_cate_id(int consume_cate_id) {
		this.consume_cate_id = consume_cate_id;
	}

	public int getConsume_group_id() {
		return consume_group_id;
	}

	public void setConsume_group_id(int consume_group_id) {
		this.consume_group_id = consume_group_id;
	}

	public String getConsume_date() {
		return consume_date;
	}

	public void setConsume_date(String consume_date) {
		this.consume_date = consume_date;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Consume Record Detail: " + 
			"\t" + this.consume_name +
			"\t" + this.consume_cate_id + 
			"\t" + this.consume_date + 
			"\t" + this.price +
			"\t" + this.total + 
			"\t" + this.comments;
	}

	public ConsumeRecord() {
		super();
	}

	

}
