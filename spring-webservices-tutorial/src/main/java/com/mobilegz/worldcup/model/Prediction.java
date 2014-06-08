package com.mobilegz.worldcup.model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Prediction")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "predictType", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("0")
public class Prediction extends AbstractAmountFieldModel {

	@ManyToOne
	private Staff staff;

	@ManyToOne
	private Match match;

	@ManyToOne
	private FootballTeam predictTeam;

	private int predictGoal;

	public FootballTeam getPredictTeam() {
		return predictTeam;
	}

	public void setPredictTeam(FootballTeam predictTeam) {
		this.predictTeam = predictTeam;
	}

	public int getPredictGoal() {
		return predictGoal;
	}

	public void setPredictGoal(int predictGoal) {
		this.predictGoal = predictGoal;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public boolean isCorrect() {
		return false;
	}

}
