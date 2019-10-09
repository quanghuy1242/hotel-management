package models;

public class Phong {
	private String maPhong;
	private String loaiPhong;
	private Double giaPhong;
	private String tinhTrangPhong;

	public Phong() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Phong(String maPhong, String loaiPhong, Double giaPhong, String tinhTrangPhong) {
		super();
		this.maPhong = maPhong;
		this.loaiPhong = loaiPhong;
		this.giaPhong = giaPhong;
		this.tinhTrangPhong = tinhTrangPhong;
	}

	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public String getLoaiPhong() {
		return loaiPhong;
	}

	public void setLoaiPhong(String loaiPhong) {
		this.loaiPhong = loaiPhong;
	}

	public Double getGiaPhong() {
		return giaPhong;
	}

	public void setGiaPhong(Double giaPhong) {
		this.giaPhong = giaPhong;
	}

	public String getTinhTrangPhong() {
		return tinhTrangPhong;
	}

	public void setTinhTrangPhong(String tinhTrangPhong) {
		this.tinhTrangPhong = tinhTrangPhong;
	}

	@Override
	public String toString() {
		return "Phong [maPhong=" + maPhong + ", loaiPhong=" + loaiPhong + ", giaPhong=" + giaPhong + ", tinhTrangPhong="
				+ tinhTrangPhong + "]";
	}
}
