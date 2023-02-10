package com.vti.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.entity.RegistrationUserToken;
import com.vti.event.onConfirmRegistrationviaEmailEvent;
import com.vti.form.AccountFormForCreating;
import com.vti.form.AccountFormForCreatingRegister;
import com.vti.form.AccountFormForUpdating;
import com.vti.repository.IAccountRepository;
import com.vti.repository.IDepartmentRepository;
import com.vti.repository.IPossitionRepository;
import com.vti.repository.IRegistrationUserTokenRepositiry;
import com.vti.specification.AccountSpecification;

@Service
public class AccountSevice implements IAccountService {
	@Autowired
	private IAccountRepository accountRepository;

	@Autowired
	private IDepartmentRepository departmentRepository;

	@Autowired
	private IPossitionRepository possitionRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private IRegistrationUserTokenRepositiry registrationUserTokenRepositiry;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Override
	public Page<Account> getAllAccount(Pageable pageable, String search) {
		Specification<Account> where = null;

		if (!StringUtils.isEmpty(search)) {

			AccountSpecification fullnameSpecification = new AccountSpecification("fullname", "LIKE", search);
			AccountSpecification emailSpecification = new AccountSpecification("email", "LIKE", search);
			AccountSpecification departmentSpecification = new AccountSpecification("department", "LIKE", search);

			where = Specification.where(fullnameSpecification).or(emailSpecification).or(departmentSpecification);
		}
		return accountRepository.findAll(where, pageable);
	}

	@Override
	public void createAccount(AccountFormForCreating forminputCreate) {
		Account account = new Account();
		account.setEmail(forminputCreate.getEmail());
		account.setUsername(forminputCreate.getUserName());
		account.setFullname(forminputCreate.getFullName());
		Department department = departmentRepository.getById(forminputCreate.getDepartmentID());
		account.setDepartment(department);
		Position position = possitionRepository.getById(forminputCreate.getPositionID());
		account.setPosition(position);

		Account acc = accountRepository.save(account);
	}

	@Override
	public void updateAccount(short idUpdate, AccountFormForUpdating forminputUpdate) {
		Account account = accountRepository.getById(idUpdate);

		account.setFullname(forminputUpdate.getFullName());

		Department department = departmentRepository.getById(forminputUpdate.getDepartmentID());
		account.setDepartment(department);

		Position position = possitionRepository.getById(forminputUpdate.getDepartmentID());
		account.setPosition(position);

		accountRepository.save(account);

	}

	@Override
	public void deleteAccount(short idDel) {
		accountRepository.deleteById(idDel);

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Tu Username nhan duoc tim ra thong tin Account tuong ung(username, password)
//		Goi lai Repository
		Account accountLoginDB = accountRepository.findByUsername(username);
		if (accountLoginDB == null) {
			throw new UsernameNotFoundException(username);
		} else {
			return new User(accountLoginDB.getUsername(), accountLoginDB.getPassword(),
					AuthorityUtils.createAuthorityList("User"));
		}
	}

	@Override
	public Account getAccountByUsername(String username) {
		// TODO Auto-generated method stub
//		accountRepository.existsById(null);
		return accountRepository.findByUsername(username);

	}

	@Override
	public Boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return accountRepository.existsByEmail(email);
	}

	@Override
	public Boolean existsByUsername(String username) {
		// TODO Auto-generated method stub
		return accountRepository.existsByUsername(username);
	}

	@Override
	public void createAccountRegister(AccountFormForCreatingRegister accountFormForCreatingRegisterInput) {
//		Tạo mới Account
		Account account = new Account();
		account.setEmail(accountFormForCreatingRegisterInput.getEmail());
		account.setFullname(accountFormForCreatingRegisterInput.getFullName());
		account.setUsername(accountFormForCreatingRegisterInput.getUserName());
		short depid = accountFormForCreatingRegisterInput.getDepartmentID();
		short posid = accountFormForCreatingRegisterInput.getPositionID();

		Department department = departmentRepository.getById(depid);
		Position position = possitionRepository.getById(posid);

		account.setDepartment(department);
		account.setPosition(position);

		account.setPassword(passwordEncoder.encode(accountFormForCreatingRegisterInput.getPassword()));

//		Lưu Account được tạo mới xuống database
		accountRepository.save(account);

//		Tạo ra 1 đoạn mã token / lưu thông tin token tương ứng với Account xuống DB

		String token = UUID.randomUUID().toString();

		RegistrationUserToken registrationUserToken = new RegistrationUserToken();
		registrationUserToken.setAccount(account);
		registrationUserToken.setToken(token);

		registrationUserTokenRepositiry.save(registrationUserToken);

//		gửi token về email người dùng đăng ký
//		Bắn ra event
		applicationEventPublisher.publishEvent(new onConfirmRegistrationviaEmailEvent(account.getEmail()));
	}

}
