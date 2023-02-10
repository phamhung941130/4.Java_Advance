package com.vti.frontend;

import com.vti.utils.ScannerUltis;

public class Demo {
	public static void main(String[] args) {
		AccountFunction accountFunction = new AccountFunction();
		while (true) {
			System.out.println("------MỜI BẠN CHỌN CHỨC NĂNG------");
			String leftAlignFormat = "| %-72s |%n";
			System.out.format("+--------------------------------------------------------------------------+%n");
			System.out.format("|                        Choose please                                     |%n");
			System.out.format("+--------------------------------------------------------------------------+%n");
			System.out.format(leftAlignFormat, "1. Danh sách Account trên hệ thống");
			System.out.format(leftAlignFormat, "2. Danh sách Account Theo ID");
			System.out.format(leftAlignFormat, "3. Tạo mới Account");
			System.out.format(leftAlignFormat, "4. Xóa Account");
			System.out.format(leftAlignFormat, "5. Update Account");
			System.out.format(leftAlignFormat, "6.Exit");
			System.out.format("+--------------------------------------------------------------------------+%n");
			switch (ScannerUltis.inputIntPositive()) {
			case 1:
				accountFunction.getAllAccount();
				break;
			case 2:
				accountFunction.getAccountByID();
				break;
			case 3:
				accountFunction.createAccount();
				break;
			case 4:
				accountFunction.deleteAccount();
				break;
			case 5:
				accountFunction.updateAccount();
				break;
			case 6:
				return;
			default:
				System.out.println("Nhập lại:");
				break;
			}
		}
	}

}
