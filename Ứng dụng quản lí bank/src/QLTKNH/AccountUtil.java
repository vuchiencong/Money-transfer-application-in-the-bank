package QLTKNH;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Class này chứa những hàm để thực hiện các giao dịch chuyển nhận tiền
 */
public class AccountUtil {

	static Scanner scanner = new Scanner(System.in);
	static final double LAIXUAT = 0.035;

	/**
	 * Phương thức  nạp tiền
	 * @param account : nhận được khi ta nhập vào 1 mã số của 1 account mà ta muốn nạp tiền
	 */
	public static void napTien(Account account) {
		double soTienNapVao;

		do {
			System.out.println("Nhap so tien muon nap:");
			soTienNapVao = scanner.nextDouble();

			account.setSoTienTrongTK(account.getSoTienTrongTK() + soTienNapVao);
			//dùng định dạng tiền tệ để tiền chuyển về đuôi VNĐ
			System.out.println("Ban vua nap " + dinhDangTienTe(soTienNapVao) + " vao tai khoan.");

			if (soTienNapVao < 0)
				System.out.println("So tien nap vao khong hop le !");

		} while (soTienNapVao < 0);

	}

	/**
	 * Phương thức rút tiền
	 * @param account : nhận được khi ta nhập vào 1 mã số của 1 account mà ta muốn rút tiền
	 */
	public static void rutTien(Account account) {
		double soTienMuonRut;
		double phiRutTien;

		phiRutTien = 1100;

		do {
			System.out.println("Nhap so tien muon rut:");
			soTienMuonRut = scanner.nextDouble();

			account.setSoTienTrongTK(account.getSoTienTrongTK() - (soTienMuonRut + phiRutTien));

			System.out.println("Ban vua rut " + dinhDangTienTe(soTienMuonRut) + " khoi tai khoan.");
			if (soTienMuonRut <= 0 || soTienMuonRut > (account.getSoTienTrongTK() + phiRutTien))
				System.out.println("So tien muon rut khong hop le !");

		} while (soTienMuonRut <= 0 || soTienMuonRut > (account.getSoTienTrongTK() + phiRutTien));

	}

	/**
	 * Phương thức đáo hạn tiền
	 * @param account : nhận được khi ta nhập vào 1 mã số của 1 account mà ta muốn đáo hạn tiền
	 */
	public static void daoHan(Account account) {
		long soTienTrongTK;

		account.setSoTienTrongTK(account.getSoTienTrongTK() + account.getSoTienTrongTK() * LAIXUAT);

		System.out.println("So tien trong tai khoan sau ki dao han la: " + dinhDangTienTe(account.getSoTienTrongTK()));

	}

	/**
	 * set up thông tin và số tiền cho từng tài khoản
	 * @return tài khoản ngân hàng
	 */
	public static Account nhapThongTin() {

		Account account = new Account();

		System.out.println("Nhap so tai khoan");
		long soTK = scanner.nextLong();
		account.setSoTK(soTK);

		scanner.nextLine();

		System.out.println("Nhap ten tai khoan");
		String tenTK = scanner.nextLine();
		account.setTenTK(tenTK);

		System.out.println("Nhap so tien trong tai khoan");
		double soTienTrongTK = scanner.nextDouble();
		account.setSoTienTrongTK(soTienTrongTK);

		return account;

	}

	/**
	 * thực hiện giao dịch chuyển tiền qua lại giữa 2 tài khoản ngân hàng
	 * @param listAccount được tạo nên từ số lượng tài khoản ta set up từ ban đầu
	 * @param soTKKH1 tìm kiếm trong dữ liệu-hàm tìm kiếm
	 * @param soTKKH2 tìm kiếm trong dữ liệu-hàm tìm kiếm
	 */
	public static void chuyenKhoan(List<Account> listAccount, long soTKKH1, long soTKKH2) {

		double soTienChuyen;
		double soTienTKKH1;
		double soTienTKKH2;

		Account user1 = timKiemTK(listAccount, soTKKH1);// tìm kiếm tk1 thông qua Số tk ngân hàng
		Account user2 = timKiemTK(listAccount, soTKKH2);

		soTienTKKH1 = user1.getSoTienTrongTK();

		if ((user1 != user2) && (user1 != null && user2 != null)) {
			soTienTKKH2 = user2.getSoTienTrongTK();

			System.out.println("Nhap so tien chuyen");
			soTienChuyen = scanner.nextDouble();

			if (soTienChuyen <= soTienTKKH1) {
				soTienTKKH1 = soTienTKKH1 - soTienChuyen;
				soTienTKKH2 = soTienTKKH2 + soTienChuyen;

				user1.setSoTienTrongTK(soTienTKKH1);
				user2.setSoTienTrongTK(soTienTKKH2);
				System.out.println("Giao dich thanh cong !");
				endProcess(listAccount, user1);
				endProcess(listAccount, user2);

			} else {
				System.out.println("So du khong du de thuc hien giao dich");
			}
		}

	}

	/**
	 * ham cap nhat lai so tien cua tai khoan trong he thong khi xu ly thanh cong
	 */
	public static void endProcess(List<Account> listAccount, Account user) {
		Account account = timKiemTK(listAccount, user.getSoTK());
		account.setSoTienTrongTK(user.getSoTienTrongTK());
	}

	public static int nhapSoKhachHang() {
		int soLuongKhachHang;

		do {
			System.out.println("Nhap so luong khach hang:");
			soLuongKhachHang = scanner.nextInt();

			if (soLuongKhachHang <= 0)
				System.out.println("So luong khach hang khong hop le. Nhap lai !");

		} while (soLuongKhachHang <= 0);

		return soLuongKhachHang;
	}

	/**
	 * Hàm nhập số lượng khách hàng mà t muốn
	 * @param soLuongKhachHang
	 * @return 1 list tk ngân hàng
	 */
	public static List<Account> nhapDS(int soLuongKhachHang) {

		List<Account> listAccount = new ArrayList<>();
		for (int i = 0; i < soLuongKhachHang; i++) {
			Account account = new Account();
			System.out.println("Nhap thong tin khach hang thu " + (i + 1));
			account = nhapThongTin();
			listAccount.add(account);

		}
		return listAccount;

	}

	/**
	 * tìm kiếm khách hàng theo số tk ngân hàng
	 * @param listAccount
	 * @param soTK
	 * @return account mong muốn theo số tk ngân hàng
	 */
	public static Account timKiemTK(List<Account> listAccount, long soTK) {
		boolean check = false;
		Account account = new Account();
		for (Account accountSearch : listAccount) {

			if (soTK == accountSearch.getSoTK()) {
				account = accountSearch;
				check = true;
				break;
			}
		}
		if (check == false) {
			System.out.println("Khong tim thay so tai khoan !");
			return null;
		}
		return account;

	}

	/**
	 * set up menu giao diện
	 */
	public static void showMenu() {
		System.out.println("**********Quan li tai khoan ngan hang**********");
		System.out.println("Chon 1: Nhap thong tin danh sach tai khoan");
		System.out.println("Chon 2: Hien thi danh sach tai khoan");
		System.out.println("Chon 3: Nap tien vao tai khoan");
		System.out.println("Chon 4: Rut tien khoi tai khoan");
		System.out.println("Chon 5: Dao han");
		System.out.println("Chon 6: Chuyen khoan");
		System.out.println("Chon 7: Thoat !");
	}

	/**
	 * hiển thị danh sách tài khoản ngân hàng
	 * @param listAccount
	 */
	public static void hienThiDS(List<Account> listAccount) {
		if (listAccount == null || listAccount.isEmpty())
			System.out.println("Khong co tai khoan nao !");

		else {
			System.out.println("************Danh sach tai khoan************");
			System.out.printf("%-20s%-20s%-20s\n", "So tai khoan", "Ten tai khoan", "So tien trong tai khoan");

			for (Account account : listAccount) {
				hienThiKQ(account);
			}
		}

	}

	/**
	 * hiển thị kết quả thông tin của 1 acoount
	 * @param account
	 */
	public static void hienThiKQ(Account account) {
		System.out.printf("%-20s%-20s%-20s\n", account.getSoTK(), account.getTenTK(),
				dinhDangTienTe(account.getSoTienTrongTK()));
	}

	/**
	 * dùng để định dạng tiền tệ- đổi đuôi tiền
	 * @param soTienTrongTK
	 * @return
	 */
	public static String dinhDangTienTe(double soTienTrongTK) {
		Locale localeVN = new Locale("vi", "VN");
		NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
		String tienTe = currencyVN.format(soTienTrongTK);
		return tienTe;
	}
}
