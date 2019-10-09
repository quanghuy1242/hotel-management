package models;

public class KhachHang {
	private String maKH;
	private String hoTen;
	private String quocTich;
	private String maCMND;
	private String ngaySinh;
	private String gioiTinh;
	private String diaChi;
	private String soDienThoai;
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getQuocTich() {
		return quocTich;
	}
	public void setQuocTich(String quocTich) {
		this.quocTich = quocTich;
	}
	public String getMaCMND() {
		return maCMND;
	}
	public void setMaCMND(String maCMND) {
		this.maCMND = maCMND;
	}
	public String getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public KhachHang(String maKH, String hoTen, String quocTich, String maCMND, String ngaySinh, String gioiTinh,
			String diaChi, String soDienThoai) {
		super();
		this.maKH = maKH;
		this.hoTen = hoTen;
		this.quocTich = quocTich;
		this.maCMND = maCMND;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
	}
	public KhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return maKH + ";" + hoTen + ";" + quocTich + ";" + maCMND
				+ ";" + ngaySinh + ";" + gioiTinh + ";" + diaChi + ";"
				+ soDienThoai;
	}
	
}
