package com.mobilegz.worldcup.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractAmountFieldModel extends AbstractModel {

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal amount;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
