package com.mobilegz.worldcup.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Challenge extends AbstractAmountFieldModel {

	@Column(length = 256)
	private String title;

	@Column(length = 512)
	private String content;

	private boolean multiple;

	@Transient
	private List<ChallengeAnswer> correctAnswers = null;

	@OneToMany(mappedBy = "challenge")
	@Cascade(value = { CascadeType.ALL })
	private Set<ChallengeAnswer> answers = new HashSet<ChallengeAnswer>();

	public List<ChallengeAnswer> findCorrectAnswers() {
		if (correctAnswers == null) {
			correctAnswers = new ArrayList<ChallengeAnswer>(1);
			Iterator<ChallengeAnswer> it = answers.iterator();
			while (it.hasNext()) {
				ChallengeAnswer ca = it.next();
				if (ca.isCorrect()) {
					correctAnswers.add(ca);
				}
			}
		}
		return correctAnswers;
	}

	public Set<ChallengeAnswer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<ChallengeAnswer> answers) {
		this.answers = answers;
	}

	public boolean isMultiple() {
		return multiple;
	}

	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
