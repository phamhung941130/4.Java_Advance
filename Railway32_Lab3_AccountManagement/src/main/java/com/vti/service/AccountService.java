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
import com.vti.event.onConfirmRegistrationViaEmailEvent;
import com.vti.form.AccountFormForCreating;
import com.vti.form.AccountFormForCreatingRegister;
import com.vti.form.AccountFormForUpdating;
import com.vti.repository.IAccountRepository;
import com.vti.repository.IDepartmentRepository;
import com.vti.repository.IPossitionRepository;
import com.vti.repository.IRegistrationUserTokenRepository;
import com.vti.specification.AccountSpecification;

@Service
public class AccountService implements IAccountService {
	@Autowired
	private IAccountRepository accountRepository;

	@Autowired
	private IDepartmentRepository departmentRepository;

	@Autowired
	private IPossitionRepository possitionRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private IRegistrationUserTokenRepository registrationUserTokenRepository;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Override
	public Page<Account> getListAccount(Pageable pageable, String search) {
		Specification<Account> where = null;
//
		if (!StringUtils.isEmpty(search)) {
			AccountSpecification fullnameSpecifilcation = new AccountSpecification("fullname", "LIKE", search);
			AccountSpecification emailSpecifilcation = new AccountSpecification("email", "LIKE", search);
			AccountSpecification departmentSpecification = new AccountSpecification("department", "LIKE", search);
			where = Specification.where(fullnameSpecifilcation).or(emailSpecifilcation).or(departmentSpecification);
		}

		return accountRepository.findAll(where, pageable);
	}

	@Override
	public void createAccount(AccountFormForCreating accountCreateFormInput) {
		String newEmail = accountCreateFormInput.getEmail();
		String newUsername = accountCreateFormInput.getUsername();
		String newFullname = accountCreateFormInput.getFullname();
		short newDepartmentId = accountCreateFormInput.getDepartmentId();
		short newPositionId = accountCreateFormInput.getPositionId();

		Account accountNew = new Account();
		accountNew.setEmail(newEmail);
		accountNew.setUsername(newUsername);
		accountNew.setFullname(newFullname);

		Department department = departmentRepository.getById(newDepartmentId);
		accountNew.setDepartment(department);

		Position possition = possitionRepository.getById(newPositionId);
		accountNew.setPosition(possition);

		accountRepository.save(accountNew);

	}

	@Override
	public void updateAccount(short id, AccountFormForUpdating form) {
		Account account = accountRepository.getById(id);

		Department department = departmentRepository.getById(form.getDepartmentId());
		Position position = possitionRepository.getById(form.getPositionId());

		account.setFullname(form.getFullname());
		account.setDepartment(department);
		account.setPosition(position);

		accountRepository.save(account);

	}

	@Override
	public void deleteAccount(short id) {
		accountRepository.deleteById(id);

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Từ Username nhận được tìm ra thông tin của Account tương
		// ứng(username,password)
		// Gọi lại Repository
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
//		accountRepository.existsById(null)
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
	public void createAccountRegister(AccountFormForCreatingRegister accountCreateRegisterFormInput) {
//			Tạo mới Accont
		Account account = new Account();
		account.setEmail(accountCreateRegisterFormInput.getEmail());
		account.setFullname(accountCreateRegisterFormInput.getFullname());
		account.setUsername(accountCreateRegisterFormInput.getUsername());

		short depid = accountCreateRegisterFormInput.getDepartmentId();
		short posid = accountCreateRegisterFormInput.getPositionId();
		Department department = departmentRepository.getById(depid);
		Position position = possitionRepository.getById(posid);

		account.setDepartment(department);
		account.setPosition(position);

		account.setPassword(passwordEncoder.encode(accountCreateRegisterFormInput.getPassword()));

//		Lưu Account vừa tạo xuống DB
		accountRepository.save(account);

//		Tạo ra 1 đoạn mã ngẫu nhiên, token lưu thông tin token tương ứng với Account xuống DB
		String token = UUID.randomUUID().toString();

		RegistrationUserToken registrationUserToken = new RegistrationUserToken();
		registrationUserToken.setAccount(account);
		registrationUserToken.setToken(token);

		registrationUserTokenRepository.save(registrationUserToken);

//		Gửi token về Email người dùng đăng ký
//		Bắn ra event
		applicationEventPublisher.publishEvent(new onConfirmRegistrationViaEmailEvent(account.getEmail()));
//		
	}

}
