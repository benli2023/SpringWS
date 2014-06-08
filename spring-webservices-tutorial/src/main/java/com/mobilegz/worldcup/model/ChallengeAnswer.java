package com.mobilegz.worldcup.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ChallengeAnswer extends AbstractModel {

	private boolean correct;

	private String text;

	@ManyToOne
	private Challenge challenge;

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Challenge getChallenge() {
		return challenge;
	}

	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
	}

}
