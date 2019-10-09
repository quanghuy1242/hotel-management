package models;

public class ChiTietHoaDon {
	private String maHoaDon;
	private String maDichVu;
	private int soLuong;
	private double tongTien;
	
	
	
	public ChiTietHoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}



	public ChiTietHoaDon(String maHoaDon, String maDichVu, int soLuong, double tongTien) {
		super();
		this.maHoaDon = maHoaDon;
		this.maDichVu = maDichVu;
		this.soLuong = soLuong;
		this.tongTien = tongTien;
	}



	public String getMaHoaDon() {
		return maHoaDon;
	}



	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}



	public String getMaDichVu() {
		return maDichVu;
	}



	public void setMaDichVu(String maDichVu) {
		this.maDichVu = maDichVu;
	}



	public int getSoLuong() {
		return soLuong;
	}



	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}



	public double getTongTien() {
		return tongTien;
	}



	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}
	
	public String toString() {
		return maHoaDon + ";" + maDichVu + ";" + soLuong + ";" + tongTien;
	}
	
	
	
	

}
