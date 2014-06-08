package com.mobilegz.worldcup.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class InOutCome extends AbstractAmountFieldModel {

	private int type;

	@ManyToOne
	private Staff staff;

	private String desc;

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

}
