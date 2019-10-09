package models;

public class ThongTinTra {
	private String maTraPhong;
	private String maNhanPhong;
	private String maNgayTra;
	private String gioTra;
	public String getMaTraPhong() {
		return maTraPhong;
	}
	public void setMaTraPhong(String maTraPhong) {
		this.maTraPhong = maTraPhong;
	}
	public String getMaNhanPhong() {
		return maNhanPhong;
	}
	public void setMaNhanPhong(String maNhanPhong) {
		this.maNhanPhong = maNhanPhong;
	}
	public String getMaNgayTra() {
		return maNgayTra;
	}
	public void setMaNgayTra(String maNgayTra) {
		this.maNgayTra = maNgayTra;
	}
	public String getGioTra() {
		return gioTra;
	}
	public void setGioTra(String gioTra) {
		this.gioTra = gioTra;
	}
	public ThongTinTra(String maTraPhong, String maNhanPhong, String maNgayTra, String gioTra) {
		super();
		this.maTraPhong = maTraPhong;
		this.maNhanPhong = maNhanPhong;
		this.maNgayTra = maNgayTra;
		this.gioTra = gioTra;
	}
	public ThongTinTra() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return maTraPhong + ";" + maNhanPhong + ";" + maNgayTra + ";" + gioTra;
	}
	
}
