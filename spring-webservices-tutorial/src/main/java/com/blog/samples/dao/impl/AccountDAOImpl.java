package com.blog.samples.dao.impl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blog.samples.dao.AccountDAO;
import com.blog.samples.webservices.Account;

@Transactional
@Component
public class AccountDAOImpl implements AccountDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void save(Account account) {
		Session session = sessionFactory.openSession();
		session.saveOrUpdate(account);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Account get(String accountNumber) {
		Session session = sessionFactory.openSession();
		return (Account) session.get(Account.class, accountNumber);
	}
}
