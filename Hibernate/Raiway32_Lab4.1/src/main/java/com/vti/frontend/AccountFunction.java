package com.vti.frontend;

import java.util.List;

import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.repository.AccountRepository;
import com.vti.repository.DepartmentRepository;
import com.vti.repository.PositionRepository;
import com.vti.utils.ScannerUltis;

public class AccountFunction {
	private AccountRepository accountRepository;

	public AccountFunction() {

		accountRepository = new AccountRepository();
	}

	public void showAllAccount() {
		System.out.println("Danh sách Account trên hệ thống");
		List<Account> listAcc = accountRepository.getAllAccount();
		String leftAlignFormat = "| %-2d | %-21s | %-15s | %-21s | %-14s | %-14s | %n";
		System.out.format(
				"+----+-----------------------+-----------------+-----------------------+----------------+----------------+%n");
		System.out.format(
				"|ID  | Email                 | Username        |   FullName            | Department     | Possition      |%n");
		System.out.format(
				"+----+-----------------------+-----------------+-----------------------+----------------+----------------+%n");

		for (Account acc : listAcc) {
			System.out.format(leftAlignFormat, acc.getId(), acc.getEmail(), acc.getUsername(), acc.getFullname(),
					acc.getDepartment().getName(), acc.getPosition().getName());
		}
		System.out.format(
				"+----+-----------------------+-----------------+-----------------------+----------------+----------------+%n");
	}

	public void getAccountByID() {
		System.out.println("Mời ban nhập ID cần tìm");
		int id = ScannerUltis.inputIntPositive();
		Account acc = accountRepository.getAccountByID((short) id);
		String leftAlignFormat = "| %-2d | %-21s | %-15s | %-21s | %-14s | %-14s | %n";
		System.out.format(
				"+----+-----------------------+-----------------+-----------------------+----------------+----------------+%n");
		System.out.format(
				"|ID  | Email                 | Username        |   FullName            | Department     | Possition      |%n");
		System.out.format(
				"+----+-----------------------+-----------------+-----------------------+----------------+----------------+%n");

		System.out.format(leftAlignFormat, acc.getId(), acc.getEmail(), acc.getUsername(), acc.getFullname(),
				acc.getDepartment().getName(), acc.getPosition().getName());
		System.out.format(
				"+----+-----------------------+-----------------+-----------------------+----------------+----------------+%n");
	}

	public void createAccount() {
		Account acc = new Account();
		System.out.println("Nhập vào Email: ");
		acc.setEmail(ScannerUltis.inputEmail());
		System.out.println("Nhập vào UserName: ");
		acc.setUsername(ScannerUltis.inputString());
		System.out.println("Nhập vào FullName: : ");
		acc.setFullname(ScannerUltis.inputString());
		System.out.println("Hãy chọn phòng nhân viên: ");
		Department dep = getDep();
		acc.setDepartment(dep);
		System.out.println("Hãy chọn vị trí nhân viên: ");
		Position pos = getPos();
		acc.setPosition(pos);
		accountRepository.createAccount(acc);
		showAllAccount();
	}

	private Position getPos() {
		while (true) {
			PositionRepository positionRepository = new PositionRepository();
			List<Position> listpos = positionRepository.getAllPosition();
			String leftAlignFormat = "| %-6d | %-21s |%n";

			System.out.format("+--------+-----------------------+%n");
			System.out.format("|   ID   | Position Name         |%n");
			System.out.format("+--------+-----------------------+%n");
			for (Position position : listpos) {
				System.out.format(leftAlignFormat, position.getId(), position.getName());
			}
			System.out.format("+--------+-----------------------+%n");
			System.out.println("Chọn vị trí theo ID:");
			int chossePos = ScannerUltis.inputIntPositive();
			Position pos = positionRepository.getPositionByID((short) chossePos);
			if (pos != null) {
				return pos;
			} else {
				System.out.println("chọn lại:");
			}
		}

	}

	private Department getDep() {
		while (true) {
			DepartmentRepository depRepository = new DepartmentRepository();
			List<Department> listDep = depRepository.getAllDepartment();
			String leftAlignFormat = "| %-6d | %-21s |%n";

			System.out.format("+--------+-----------------------+%n");
			System.out.format("|   ID   | Depament Name         |%n");
			System.out.format("+--------+-----------------------+%n");
			for (Department department : listDep) {
				System.out.format(leftAlignFormat, department.getId(), department.getName());
			}
			System.out.format("+--------+-----------------------+%n");
			System.out.println("Chọn phòng theo ID:");
			int chooseDep = ScannerUltis.inputIntPositive();
			Department dep = depRepository.getDepartmentByID((short) chooseDep);
			if (dep != null) {
				return dep;
			} else {
				System.out.println("Không có phòng này, hãy chọn lại: ");
			}
		}
	}

	public void deleteAccount() {
		System.out.println("nhập vào ID cần xóa: ");
		int idDel = ScannerUltis.inputIntPositive();
		accountRepository.deleteAccount((short) idDel);
	}

	public void updateAccount() {
		Account acc = new Account();
		System.out.println("ID: ");
		acc.setId((short) ScannerUltis.inputIntPositive());
		System.out.println("Nhập vào Email: ");
		acc.setEmail(ScannerUltis.inputEmail());
		System.out.println("Nhập vào UserName: ");
		acc.setUsername(ScannerUltis.inputString());
		System.out.println("Nhập vào FullName: : ");
		acc.setFullname(ScannerUltis.inputString());
		System.out.println("Hãy chọn phòng nhân viên: ");
		Department dep = getDep();
		acc.setDepartment(dep);
		System.out.println("Hãy chọn vị trí nhân viên: ");
		Position pos = getPos();
		acc.setPosition(pos);
		accountRepository.updateAccount(acc);
	}

	public void showFullnameByID() {
		System.out.println("Nhập vào id cần tìm: ");
		short id = (short) ScannerUltis.inputIntPositive();
		System.out.println(accountRepository.getFullnameByID(id));
	}

	public void showAccountIncurrentmonth() {
		List<Account> list = accountRepository.getAccountIncurrentmonth();
		String leftAlignFormat = "| %-2d | %-21s | %-15s | %-21s | %-14s | %-14s | %n";
		System.out.format(
				"+----+-----------------------+-----------------+-----------------------+----------------+----------------+%n");
		System.out.format(
				"|ID  | Email                 | Username        |   FullName            | Department     | Possition      |%n");
		System.out.format(
				"+----+-----------------------+-----------------+-----------------------+----------------+----------------+%n");

		for (Account acc1 : list) {
			System.out.format(leftAlignFormat, acc1.getId(), acc1.getEmail(), acc1.getUsername(), acc1.getFullname(),
					acc1.getDepartment().getName(), acc1.getPosition().getName());
		}
		System.out.format(
				"+----+-----------------------+-----------------+-----------------------+----------------+----------------+%n");

	}

	public void showCountAccountIncurrentmonth() {
		List<Object[]> list = accountRepository.getCountAccountIncurrentmonth();
		String leftAlignFormat = "| %-4d | %-2s |%n";
		System.out.format("+------+----+%n");
		System.out.format("|Month | SL |%n");
		System.out.format("+------+----+%n");

		for (Object[] oject : list) {
			System.out.format(leftAlignFormat, oject[0], oject[1]);
			System.out.format("+------+----+%n");

		}

	}
}
