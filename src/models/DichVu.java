package models;

public class DichVu {
	private String maDV;
	private String loaiDV;
	private String donViRTinh;
	private double donGia;
	public String getMaDV() {
		return maDV;
	}
	public void setMaDV(String maDV) {
		this.maDV = maDV;
	}
	public String getLoaiDV() {
		return loaiDV;
	}
	public void setLoaiDV(String loaiDV) {
		this.loaiDV = loaiDV;
	}
	public String getDonViRTinh() {
		return donViRTinh;
	}
	public void setDonViRTinh(String donViRTinh) {
		this.donViRTinh = donViRTinh;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public DichVu(String maDV, String loaiDV, String donViRTinh, double donGia) {
		super();
		this.maDV = maDV;
		this.loaiDV = loaiDV;
		this.donViRTinh = donViRTinh;
		this.donGia = donGia;
	}
	public DichVu() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return maDV + ";" + loaiDV + ";" + donViRTinh + ";" + donGia;
	}
	
}
