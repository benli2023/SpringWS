package com.mobilegz.worldcup.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("3")
public class WinnerPrediction extends Prediction {

	@Override
	public boolean isCorrect() {
		FootballTeam ft = this.getPredictTeam();
		if (ft == null) {
			if (this.getMatch().getWinnerTeam().equals(FootballTeam.TIE_FOOTBALLTEAM)) {
				return true;
			}
		}
		return this.getMatch().getWinnerTeam().equals(this.getPredictTeam());
	}

}
