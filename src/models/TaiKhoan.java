package models;

public class TaiKhoan {
	private String maDangNhap;
	private String matKhau;
	private NhanVien nhanVien;
	public String getMaDangNhap() {
		return maDangNhap;
	}
	public void setMaDangNhap(String maDangNhap) {
		this.maDangNhap = maDangNhap;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public TaiKhoan(String maDangNhap, String matKhau, NhanVien nhanVien) {
		super();
		this.maDangNhap = maDangNhap;
		this.matKhau = matKhau;
		this.nhanVien = nhanVien;
	}
	public TaiKhoan() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "TaiKhoan [maDangNhap=" + maDangNhap + ", matKhau=" + matKhau + ", nhanVien=" + nhanVien + "]";
	}
	
}
