package com.vti.backend;

import java.util.Scanner;

import com.vti.entity.Employee;
import com.vti.entity.Manager;
import com.vti.entity.Waiter;

public class Exercise6 {
	Scanner sc;

	public Exercise6() {
		sc = new Scanner(System.in);
	}

	public void question23() {
		while (true) {
			System.out.println("======================================================================");
			System.out.println("=================Lựa chọn chức năng bạn muốn sử dụng==================");
			System.out.println("===              1. Thêm Employee.                                 ===");
			System.out.println("===              2. Thêm Manager.                                  ===");
			System.out.println("===              3. Thêm Waiter .                                  ===");
			System.out.println("===              4. Thoát khỏi chương trình.                       ===");
			System.out.println("======================================================================");
			int menuChoose = sc.nextInt();
			switch (menuChoose) {
			case 1:
				System.out.println("Nhập vào tên Employee: ");
				String emName = sc.next();
				System.out.println("Nhập vào SalaryRatio: ");
				Double emSalaryRatio = sc.nextDouble();
				Employee em = new Employee(emName, emSalaryRatio);
				em.displayInfor();
				break;

			case 2:
				System.out.println("Nhập vào tên Manager: ");
				String managerName = sc.next();
				System.out.println("Nhập vào SalaryRatio: ");
				Double managerSalaryRatio = sc.nextDouble();
				Manager manager = new Manager(managerName, managerSalaryRatio);
				manager.displayInfor();
				break;
			case 3:
				System.out.println("Nhập vào tên Waiter: ");
				String waiterName = sc.next();
				System.out.println("Nhập vào SalaryRatio: ");
				Double waiterSalaryRatio = sc.nextDouble();
				Waiter waiter = new Waiter(waiterName, waiterSalaryRatio);
				waiter.displayInfor();
				break;
			case 4:
				return;

			default:
				System.out.println("Alarm: Lựa chọn đúng số trên menu");
				break;
			}
		}
	}
}
