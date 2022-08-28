package QLTKNH;

public class Account {

	protected static final double LAIXUAT = 0.035;
	protected long soTK;
	protected String tenTK;
	protected double soTienTrongTK;

	Account() {
	}

	Account(long soTK, String tenTK, double soTienTrongTK) {
		this.soTK = soTK;
		this.tenTK = tenTK;
		this.soTienTrongTK = soTienTrongTK;
	}

	Account(long soTK, String tenTK) {
		double soTienTrongTK;
		soTienTrongTK = 50;
	}

	public long getSoTK() {
		return soTK;
	}

	public void setSoTK(long soTK) {
		this.soTK = soTK;
	}

	public String getTenTK() {
		return tenTK;
	}

	public void setTenTK(String tenTK) {
		this.tenTK = tenTK;
	}

	public double getSoTienTrongTK() {
		return soTienTrongTK;
	}

	public void setSoTienTrongTK(double soTienTrongTK) {
		this.soTienTrongTK = soTienTrongTK;
	}

}
