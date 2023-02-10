package com.vti.frontend;

import java.util.Scanner;

public class DemoCRUDHibernate {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Function function = new Function();

		while (true) {
			System.out.println("Mời bạn chọn chức năng");
			System.out.println("1.Lấy danh sách tất cả các phòng ban");
			System.out.println("2.tìm phòng ban theo ID");
			System.out.println("6.Exit");
			int choose = scanner.nextInt();

			switch (choose) {
			case 1:
				function.showlistDep();
				break;
			case 2:
				function.findByID();
				break;
			case 6:

				return;
			default:
				break;
			}
		}
	}
}
