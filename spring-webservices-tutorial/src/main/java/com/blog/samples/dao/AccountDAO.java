package com.blog.samples.dao;

import com.blog.samples.webservices.Account;

public interface AccountDAO {

	public void save(Account account);

	public Account get(String accountNumber);

}
