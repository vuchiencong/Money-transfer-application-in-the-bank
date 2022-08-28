package QLTKNH;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		int numberChoose;
		int n;
		long soTK;
		List<Account> listAccount = new ArrayList<Account>();
		do {
			AccountUtil.showMenu();
			System.out.println("Nhap lua chon");
			numberChoose = scanner.nextInt();
			switch (numberChoose) {
			case 1:
				// 1. nhap so luong khach hang
				n = AccountUtil.nhapSoKhachHang();

				// 2. nhap danh sach khach hang
				listAccount = AccountUtil.nhapDS(n);
				break;
			case 2:
				AccountUtil.hienThiDS(listAccount);
				break;
			case 3:
				System.out.println("Nhap so tai khoan can nap tien");
				soTK = scanner.nextLong();

				Account accountCase3 = AccountUtil.timKiemTK(listAccount, soTK);

				AccountUtil.napTien(accountCase3);
				break;
			case 4:
				System.out.println("Nhap so tai khoan can rut tien");
				soTK = scanner.nextLong();

				Account accountCase4 = AccountUtil.timKiemTK(listAccount, soTK);

				AccountUtil.rutTien(accountCase4);
				break;
			case 5:
				System.out.println("Nhap so tai khoan muon dao han");
				soTK = scanner.nextLong();

				Account accountCase5 = AccountUtil.timKiemTK(listAccount, soTK);

				AccountUtil.daoHan(accountCase5);
				break;
			case 6:
				long soTKKH1;
				long soTKKH2;

				System.out.println("Nhap so tai khoan chuyen tien");
				soTKKH1 = scanner.nextLong();

				System.out.println("Nhap so tai khoan nhan tien");
				soTKKH2 = scanner.nextLong();
				AccountUtil.chuyenKhoan(listAccount, soTKKH1, soTKKH2);
				break;
			default:
				System.out.println("Nhap lai lua chon !");

			}
		} while (numberChoose != 7);
	}

}
