package com.mobilegz.worldcup.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Bet extends AbstractModel {

	@ManyToOne
	private Staff staff;

	@ManyToOne
	private Challenge challenge;

	private boolean correct;

	public Bet() {
		super();
	}

	public Bet(Staff staff, Challenge challenge, boolean correct, Set<BetResult> betResults) {
		super();
		this.staff = staff;
		this.challenge = challenge;
		this.correct = correct;
		this.betResults = betResults;
		checkAnswer();
	}

	@OneToMany(mappedBy = "bet")
	@Cascade(value = { CascadeType.ALL })
	private Set<BetResult> betResults = new HashSet<BetResult>();

	private boolean checkAnswer() {
		List<ChallengeAnswer> challengeAnswers = challenge.findCorrectAnswers();
		if (challengeAnswers.size() != betResults.size()) {
			correct = false;
			return correct;
		}
		for (ChallengeAnswer answer : challengeAnswers) {
			boolean found = false;
			for (BetResult betR : betResults) {
				if (betR.getChallengeAnswer().equals(answer)) {
					found = true;
					break;
				}
			}
			if (!found) {
				correct = false;
				return correct;
			}
		}
		correct = true;
		return correct;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public void setBetResults(Set<BetResult> betResults) {
		this.betResults = betResults;
	}

	public Set<BetResult> getBetResults() {
		return betResults;
	}

	public Challenge getChallenge() {
		return challenge;
	}

	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

}
