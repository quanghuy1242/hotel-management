package models;

import java.util.ArrayList;
import java.util.Date;

public class ThongTinNhan {
	private String maNhanPhong;
	private String maDangKy;
	private String ngayNhan;
	private String gioNhan;
	public ThongTinNhan() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMaNhanPhong() {
		return maNhanPhong;
	}
	public void setMaNhanPhong(String maNhanPhong) {
		this.maNhanPhong = maNhanPhong;
	}
	public String getMaDangKy() {
		return maDangKy;
	}
	public void setMaDangKy(String maDangKy) {
		this.maDangKy = maDangKy;
	}
	public String getNgayNhan() {
		return ngayNhan;
	}
	public void setNgayNhan(String ngayNhan) {
		this.ngayNhan = ngayNhan;
	}
	public String getGioNhan() {
		return gioNhan;
	}
	public void setGioNhan(String gioNhan) {
		this.gioNhan = gioNhan;
	}
	public ThongTinNhan(String maNhanPhong, String maDangKy, String ngayNhan, String gioNhan) {
		super();
		this.maNhanPhong = maNhanPhong;
		this.maDangKy = maDangKy;
		this.ngayNhan = ngayNhan;
		this.gioNhan = gioNhan;
	}
	@Override
	public String toString() {
		return "TTNP [maNhanPhong=" + maNhanPhong + ", maDangKy=" + maDangKy + ", ngayNhan=" + ngayNhan
				+ ", gioNhan=" + gioNhan + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maNhanPhong == null) ? 0 : maNhanPhong.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ThongTinNhan other = (ThongTinNhan) obj;
		if (maNhanPhong == null) {
			if (other.maNhanPhong != null)
				return false;
		} else if (!maNhanPhong.equals(other.maNhanPhong))
			return false;
		return true;
	}
	public ThongTinNhan(String maNhanPhong) {
		super();
		this.maNhanPhong = maNhanPhong;
	}
}
