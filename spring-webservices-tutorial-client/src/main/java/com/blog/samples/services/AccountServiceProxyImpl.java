package com.blog.samples.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.blog.samples.webservices.Account;
import com.blog.samples.webservices.accountservice.AccountDetailsRequest;
import com.blog.samples.webservices.accountservice.AccountDetailsResponse;

/**
 * The Class AccountService.
 */
@Service
public class AccountServiceProxyImpl implements AccountService {

	@Autowired
	private WebServiceTemplate webServiceTemplate;

	/**
	 * Gets the account details.
	 * 
	 * @param accountNumber
	 *            the account number
	 * @return the account details
	 */
	public Account getAccountDetails(String accountNumber) {
		AccountDetailsRequest accountDetailsRequest = new AccountDetailsRequest();
		accountDetailsRequest.setAccountNumber(accountNumber);
		AccountDetailsResponse accountDetailsResponse = (AccountDetailsResponse) webServiceTemplate.marshalSendAndReceive(accountDetailsRequest);
		return accountDetailsResponse.getAccountDetails();
	}

	public long addAccount(Account account) {
		return 1111111l;
	}

	public void setWebServiceTemplate(WebServiceTemplate webServiceTemplate) {
		this.webServiceTemplate = webServiceTemplate;
	}

}
