package models;

public class HoaDon {
	private String maHoaDon;
	private String maNhanVien;
	private String maTraPhong;
	private String ngayLapHoaDon;
	private double tongHoaDon;

	public HoaDon(String maHoaDon) {
		super();
		this.maHoaDon = maHoaDon;
	}

	public HoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HoaDon(String maHoaDon, String maNhanVien, String maTraPhong, String ngayLapHoaDon, double tongHoaDon) {
		super();
		this.maHoaDon = maHoaDon;
		this.maNhanVien = maNhanVien;
		this.maTraPhong = maTraPhong;
		this.ngayLapHoaDon = ngayLapHoaDon;
		this.tongHoaDon = tongHoaDon;
	}

	@Override
	public String toString() {
		return "HoaDon [maHoaDon=" + maHoaDon + ", maNhanVien=" + maNhanVien + ", maTraPhong=" + maTraPhong
				+ ", ngayLapHoaDon=" + ngayLapHoaDon + ", tongHoaDon=" + tongHoaDon + "]";
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getMaTraPhong() {
		return maTraPhong;
	}

	public void setMaTraPhong(String maTraPhong) {
		this.maTraPhong = maTraPhong;
	}

	public String getNgayLapHoaDon() {
		return ngayLapHoaDon;
	}

	public void setNgayLapHoaDon(String ngayLapHoaDon) {
		this.ngayLapHoaDon = ngayLapHoaDon;
	}

	public double getTongHoaDon() {
		return tongHoaDon;
	}

	public void setTongHoaDon(double tongHoaDon) {
		this.tongHoaDon = tongHoaDon;
	}
	
	
}
