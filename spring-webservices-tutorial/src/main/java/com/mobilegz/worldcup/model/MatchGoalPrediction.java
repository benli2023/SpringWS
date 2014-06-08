package com.mobilegz.worldcup.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class MatchGoalPrediction extends Prediction {

	@Override
	public boolean isCorrect() {
		return this.getPredictGoal() == this.getMatch().getGoal();
	}

}
