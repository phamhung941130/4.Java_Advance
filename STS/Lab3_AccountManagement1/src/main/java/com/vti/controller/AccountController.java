package com.vti.controller;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.AccountDto;
import com.vti.entity.Account;
import com.vti.form.AccountFormForCreating;
import com.vti.form.AccountFormForUpdating;
import com.vti.service.IAccountService;

@RestController
@RequestMapping(value = "api/v1/accounts")
@CrossOrigin("*")
public class AccountController {
	@Autowired
	private IAccountService accountService;

	@GetMapping
	public ResponseEntity<?> getAllAccount(Pageable pageable, @RequestParam(required = false) String search) {
		Page<Account> pageAccount_DB = accountService.getAllAccount(pageable, search);

//		List<AccountDto> dtos = new ArrayList<>();
//
//		// convert entities --> dtos
//		for (Account account : lisAccounts) {
//			AccountDto accountDto = new AccountDto();
//			accountDto.setId(account.getId());
//			accountDto.setEmail(account.getEmail());
//			accountDto.setFullname(account.getFullname());
//			accountDto.setUsername(account.getUsername());
//			accountDto.setDepartment(account.getDepartment().getName());
//			accountDto.setPosition(account.getPosition().getName().toString());
//			accountDto.setCreateDate(account.getCreateDate());
//			dtos.add(accountDto);
//		}

		Page<AccountDto> dtoPage = pageAccount_DB.map(new Function<Account, AccountDto>() {
			@Override
			public AccountDto apply(Account account) {

				AccountDto accountDto = new AccountDto();
				accountDto.setId(account.getId());
				accountDto.setEmail(account.getEmail());
				accountDto.setFullname(account.getFullname());
				accountDto.setUsername(account.getUsername());
				accountDto.setDepartment(account.getDepartment().getName());
				accountDto.setPosition(account.getPosition().getName().toString());
				accountDto.setCreateDate(account.getCreateDate());

				return accountDto;
			}
		});

		return new ResponseEntity<>(dtoPage, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<?> createAccount(@RequestBody AccountFormForCreating forminputCreate) {
		accountService.createAccount(forminputCreate);
		return new ResponseEntity<>("Create Success!!", HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateAccount(@PathVariable(name = "id") short idUpdate,
			@RequestBody AccountFormForUpdating forminputUpdate) {
//		String newNameDep = formInput.getName();
		accountService.updateAccount(idUpdate, forminputUpdate);
		return new ResponseEntity<>("Update Success!!", HttpStatus.CREATED);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteAccount(@PathVariable(name = "id") short idDel) {
//		String newNameDep = formInput.getName();
		accountService.deleteAccount(idDel);
		return new ResponseEntity<>("Delete Success!!", HttpStatus.OK);
	}

//	Viết phương thức check Email có tồn tại hay không

	@GetMapping(value = "/existbyemail/{email}")
	public ResponseEntity<?> existsByEmail(@PathVariable(name = "email") String email) {
		Boolean resultCheck = accountService.existsByEmail(email);
		return new ResponseEntity<>(resultCheck, HttpStatus.OK);
	}

	@GetMapping(value = "/existbyusername/{username}")
	public ResponseEntity<?> existsByUsername(@PathVariable(name = "username") String username) {
		Boolean resultCheck = accountService.existsByUsername(username);
		return new ResponseEntity<>(resultCheck, HttpStatus.OK);
	}

}
