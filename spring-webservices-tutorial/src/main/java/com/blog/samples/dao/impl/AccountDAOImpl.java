package com.blog.samples.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.blog.samples.dao.AccountDAO;
import com.blog.samples.webservices.Account;
import com.blog.samples.webservices.Customer;

@Repository
@Transactional(readOnly = true)
public class AccountDAOImpl implements AccountDAO {

	@Autowired
	@Qualifier("sqliteSessionFactory")
	private SessionFactory sessionFactory;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void save(Account account) {
		Customer customer = saveCustomer();
		account.setCustomer(customer);
		sessionFactory.getCurrentSession().save(account);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Customer saveCustomer() {
		Customer customer = new Customer();
		customer.setCustName("NICK ZHANG");
		sessionFactory.getCurrentSession().save(customer);
		return customer;
	}

	public Account get(String accountNumber) {
		return (Account) sessionFactory.getCurrentSession().get(Account.class, accountNumber);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
