package com.ivan.consume.bean;

public class ConsumeGroup {

	private String groupId;
	
	private String groupName;
	
	private int totalCost;
	
	private String date;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return " - " + this.groupName + " -  Ð¡¼Æ: " + this.totalCost;
	}

	@Override
	public boolean equals(Object o) {
		ConsumeGroup other = (ConsumeGroup)o;
		if(this.groupId != null && this.groupId.equalsIgnoreCase(other.getGroupId())){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Integer.parseInt(this.groupId);
	}	
	
}
