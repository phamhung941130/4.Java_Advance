package com.vti.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.vti.entity.Account;
import com.vti.form.AccountFormForCreating;
import com.vti.form.AccountFormForCreatingRegister;
import com.vti.form.AccountFormForUpdating;

public interface IAccountService extends UserDetailsService {
	public Page<Account> getListAccount(Pageable pageable, String search);

	public void createAccount(AccountFormForCreating accountCreateFormInput);

	public void updateAccount(short id, AccountFormForUpdating form);

	public void deleteAccount(short id);

	public Account getAccountByUsername(String username);

	public Boolean existsByEmail(String email);

	public Boolean existsByUsername(String username);

	public void createAccountRegister(AccountFormForCreatingRegister accountCreateRegisterFormInput);
}
