package com.mobliegz.worldcup.transaction;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

import com.mobilegz.worldcup.dao.BaseDAO;
import com.mobilegz.worldcup.model.Staff;

public class AddStaffTransaction implements Transaction<Serializable> {

	private final BaseDAO baseDAO;

	private final Staff staff;

	public AddStaffTransaction(BaseDAO baseDAO, Staff staff) {
		super();
		this.baseDAO = baseDAO;
		this.staff = staff;
	}

	@Transactional(readOnly = false)
	public Serializable execute() {
		return baseDAO.save(staff);
	}

}
