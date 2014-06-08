package com.mobilegz.worldcup.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class BetResult extends AbstractModel {

	@ManyToOne
	private Bet bet;

	@ManyToOne
	private ChallengeAnswer challengeAnswer;

	public Bet getBet() {
		return bet;
	}

	public void setBet(Bet bet) {
		this.bet = bet;
	}

	public ChallengeAnswer getChallengeAnswer() {
		return challengeAnswer;
	}

	public void setChallengeAnswer(ChallengeAnswer challengeAnswer) {
		this.challengeAnswer = challengeAnswer;
	}

}
