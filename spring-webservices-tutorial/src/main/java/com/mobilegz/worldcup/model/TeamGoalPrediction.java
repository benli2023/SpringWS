package com.mobilegz.worldcup.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class TeamGoalPrediction extends Prediction {

	@Override
	public boolean isCorrect() {
		return (this.getPredictGoal() == this.getMatch().getGoal(this.getPredictTeam()));
	}

}
