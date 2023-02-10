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
			System.out.format(leftAlignFormat, "1. get_FROM");
			System.out.format(leftAlignFormat, "2. get_ByID");
			System.out.format(leftAlignFormat, "3. get_FullName");
			System.out.format(leftAlignFormat, "4. get_ByCurrentMonth");
			System.out.format(leftAlignFormat, "5. get_ByCurrentMonthOderBy");
			System.out.format(leftAlignFormat, "6. get_CountAccByMonthInYearCurrent");
			System.out.format(leftAlignFormat, "7. get_CountAccByMonthInYearCurrentgt2");
			System.out.format(leftAlignFormat, "8. updateEmailUsernameAccountByID");
			System.out.format(leftAlignFormat, "9. deleteAccount");
			System.out.format(leftAlignFormat, "10. get_AccountByPaging");
			System.out.format(leftAlignFormat, "11. Exit");
			System.out.format("+--------------------------------------------------------------------------+%n");
			switch (ScannerUltis.inputIntPositive()) {
			case 1:
				accountFunction.showAllAccount();
				break;
			case 3:
				accountFunction.showFullnameByID();
				break;
			case 4:
				accountFunction.showAccountIncurrentmonth();
				break;

			case 2:
				accountFunction.getAccountByID();
				break;
//			case 5:
//				get_ByCurrentMonthOderBy();
//				break;
			case 6:
				accountFunction.showCountAccountIncurrentmonth();
				break;
//			case 7:
//				get_CountAccByMonthInYearCurrentgt2();
//				break;
//			case 8:
//				updateEmailUsernameAccountByID();
//				break;
//			case 9:
//				deleteAccount();
//				break;
//			case 10:
//				get_AccountByPaging();
//				break;
			case 11:
				return;
			default:
				System.out.println("Nhập lại:");
				break;
			}
		}
	}

}
