package com.blog.samples.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.blog.samples.dao.AccountDAO;
import com.blog.samples.webservices.Account;

@Repository
@Transactional(readOnly = true)
public class AccountDAOImpl implements AccountDAO {

	@Autowired
	@Qualifier("hsqlSessionFactory")
	private SessionFactory sessionFactory;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void save(Account account) {
		sessionFactory.getCurrentSession().save(account);
	}

	public Account get(String accountNumber) {
		return (Account) sessionFactory.getCurrentSession().get(Account.class, accountNumber);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
