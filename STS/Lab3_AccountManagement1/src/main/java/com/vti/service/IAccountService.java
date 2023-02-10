package com.vti.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.vti.entity.Account;
import com.vti.form.AccountFormForCreating;
import com.vti.form.AccountFormForCreatingRegister;
import com.vti.form.AccountFormForUpdating;

public interface IAccountService extends UserDetailsService {
	public Page<Account> getAllAccount(Pageable pageable, String search);

	public void createAccount(AccountFormForCreating forminputCreate);

	public void updateAccount(short idUpdate, AccountFormForUpdating forminputUpdate);

	public void deleteAccount(short idDel);

	public Account getAccountByUsername(String username);

	public Boolean existsByEmail(String email);

	public Boolean existsByUsername(String username);

	public void createAccountRegister(AccountFormForCreatingRegister accountFormForCreatingRegisterInput);

}
