package com.mobilegz.worldcup.model;

import javax.persistence.Entity;

@Entity
public class FootballTeam extends AbstractModel {

	public static FootballTeam TIE_FOOTBALLTEAM = new FootballTeam(-1, "9999", "Tie FootballTeam");

	private String name;

	private String code;

	public FootballTeam() {
		super();
	}

	public FootballTeam(long id, String code, String name) {
		super(id);
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
