package models;

public class ThongTinChiTietNhan {
	private String maNhanPhong;
	private String maPhong;
	public ThongTinChiTietNhan() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ThongTinChiTietNhan(String maNhanPhong, String maPhong) {
		super();
		this.maNhanPhong = maNhanPhong;
		this.maPhong = maPhong;
	}
	public String getMaNhanPhong() {
		return maNhanPhong;
	}
	public void setMaNhanPhong(String maNhanPhong) {
		this.maNhanPhong = maNhanPhong;
	}
	public String getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}
	@Override
	public String toString() {
		return "ChiTietNhanPhong [maNhanPhong=" + maNhanPhong + ", maPhong=" + maPhong + "]";
	}
	public ThongTinChiTietNhan(String maNhanPhong) {
		super();
		this.maNhanPhong = maNhanPhong;
	}
}
