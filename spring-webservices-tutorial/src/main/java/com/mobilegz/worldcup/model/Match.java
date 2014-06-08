package com.mobilegz.worldcup.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Match extends AbstractModel {

	@ManyToOne
	private FootballTeam teamA;

	@ManyToOne
	private FootballTeam teamB;

	private int goalA;

	private int goalB;

	private Date date;

	private String address;

	public FootballTeam getWinnerTeam() {
		if (isTie()) {
			return FootballTeam.TIE_FOOTBALLTEAM;
		}
		return (goalA > goalB ? teamA : teamB);
	}

	public boolean hasResult() {
		return this.getStatus() > 0;
	}

	public int getGoal(FootballTeam ft) {
		return (ft.equals(teamA) ? goalA : goalB);
	}

	public int getGoal() {
		return goalA + goalB;
	}

	public boolean isTie() {
		return goalA == goalB;
	}

	public FootballTeam getTeamA() {
		return teamA;
	}

	public void setTeamA(FootballTeam teamA) {
		this.teamA = teamA;
	}

	public FootballTeam getTeamB() {
		return teamB;
	}

	public void setTeamB(FootballTeam teamB) {
		this.teamB = teamB;
	}

	public int getGoalA() {
		return goalA;
	}

	public void setGoalA(int goalA) {
		this.goalA = goalA;
	}

	public int getGoalB() {
		return goalB;
	}

	public void setGoalB(int goalB) {
		this.goalB = goalB;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
