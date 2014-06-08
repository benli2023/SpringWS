package com.mobilegz.worldcup.model;

import javax.persistence.Entity;

@Entity
public class Team extends AbstractModel {

	private String name;

	private String desc;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
