package com.vti.frontend;

import java.util.List;

import com.vti.entity.Account;
import com.vti.entity.DetailDepartment;
import com.vti.repository.AccountRepository;
import com.vti.repository.DetailDepartmentRepository;

public class Function {
	private AccountRepository accountRepository;
	private DetailDepartmentRepository detailDepartmentRepository;

	public Function() {
		accountRepository = new AccountRepository();
		detailDepartmentRepository = new DetailDepartmentRepository();
	}

	public void showAccount() {
		List<Account> list = accountRepository.getAllAccount();

		for (Account account : list) {
			DetailDepartment detailDepartment = detailDepartmentRepository
					.getDetailDepartmentByID(account.getDepartment().getId());
			System.out.println(account.getId() + " " + account.getEmail() + " " + account.getFullname() + " "
					+ account.getDepartment().getName() + " " + detailDepartment.getAddress().getName() + " "
					+ detailDepartment.getEmulationPoint());

		}
	}

	public void getdetail() {
		System.out.println("Thông tin chi tiết tất cả phòng ban trên hệ thống");
		List<DetailDepartment> lisDetailDepartments = detailDepartmentRepository.getAllDetailDepartment();
		for (DetailDepartment detailDepartment : lisDetailDepartments) {
			System.out.println(detailDepartment.getId() + " " + detailDepartment.getName());
		}
	}

}
