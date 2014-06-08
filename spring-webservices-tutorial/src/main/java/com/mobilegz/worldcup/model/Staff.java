package com.mobilegz.worldcup.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Staff extends AbstractAmountFieldModel {

	private String name;

	@ManyToOne(optional = false)
	private Team team;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

}
