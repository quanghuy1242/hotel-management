package models;

public class ThongTinDangKy {
	private String maDangKy;
	private String maKhachHang;
	private String soLuongPhong;
	private String ngayDen;
	private String ngayDi;
	public ThongTinDangKy() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ThongTinDangKy(String maDangKy, String maKhachHang, String soLuongPhong, String ngayDen, String ngayDi) {
		super();
		this.maDangKy = maDangKy;
		this.maKhachHang = maKhachHang;
		this.soLuongPhong = soLuongPhong;
		this.ngayDen = ngayDen;
		this.ngayDi = ngayDi;
	}
	
	public ThongTinDangKy(String maDangKy) {
		this(maDangKy, "ma khach hang", "so luong phong", "ngay den", "ngay di" );
	}
	public String getMaDangKy() {
		return maDangKy;
		
	}
	public void setMaDangKy(String maDangKy) {
		this.maDangKy = maDangKy;
	}
	public String getMaKhachHang() {
		return maKhachHang;
	}
	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	public String getSoLuongPhong() {
		return soLuongPhong;
	}
	public void setSoLuongPhong(String soLuongPhong) {
		this.soLuongPhong = soLuongPhong;
	}
	public String getNgayDen() {
		return ngayDen;
	}
	public void setNgayDen(String ngayDen) {
		this.ngayDen = ngayDen;
	}
	public String getNgayDi() {
		return ngayDi;
	}
	public void setNgayDi(String ngayDi) {
		this.ngayDi = ngayDi;
	}
	@Override
	public String toString() {
		return maDangKy + ";" + maKhachHang + ";" + soLuongPhong + ";" + ngayDen + ";" + ngayDi;
	}
}
