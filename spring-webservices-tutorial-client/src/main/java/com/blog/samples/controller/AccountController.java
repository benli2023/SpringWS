package com.blog.samples.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.samples.services.AccountService;
import com.blog.samples.webservices.Account;

@Controller
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@RequestMapping({ "/account" })
	public String getAccount(ModelMap model, String id) {
		Account account = accountService.getAccountDetails(id);
		model.addAttribute("account", account);
		return "/account";
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

}
