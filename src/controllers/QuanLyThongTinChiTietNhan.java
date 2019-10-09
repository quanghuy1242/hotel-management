package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.Database;
import models.ChiTietHoaDon;
import models.ThongTinChiTietNhan;

public class QuanLyThongTinChiTietNhan {
	ArrayList<ThongTinChiTietNhan> dsCTNP;
	ThongTinChiTietNhan phongCTNP;

	public QuanLyThongTinChiTietNhan() {
		// TODO Auto-generated constructor stub
		dsCTNP = new ArrayList<ThongTinChiTietNhan>();
		phongCTNP = new ThongTinChiTietNhan();
	}

	public ArrayList<ThongTinChiTietNhan> docTuBangPhong() {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select * from ChiTietNhanPhong";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String MaNhanPhong = rs.getString(1);
				String MaPhong = rs.getString(2);
				ThongTinChiTietNhan p = new ThongTinChiTietNhan(MaNhanPhong, MaPhong);
				dsCTNP.add(p);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return dsCTNP;
	}
	public boolean create(ThongTinChiTietNhan themctnp) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
				stmt = con.prepareStatement("insert into ChiTietNhanPhong values(?, ?)");
				stmt.setString(1, themctnp.getMaNhanPhong());
				stmt.setString(2, themctnp.getMaPhong());
				n= stmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return n>0;
	}
	public boolean update(ThongTinChiTietNhan updatectnp) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n= 0;
		try {
			stmt = con.prepareStatement("update ChiTietNhanPhong "
					+"set MaPhong = ? "
					+"where MaNhanPhong = ?");
			stmt.setString(1, updatectnp.getMaPhong());
			stmt.setString(2, updatectnp.getMaNhanPhong());
			n= stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return n>0;
	}
	public boolean delete(String maNhanPhong) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
				stmt = con.prepareStatement("delete from ChiTietNhanPHong where MaNhanPhong=?");
				stmt.setString(1, maNhanPhong);
				n= stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return n>0;
	}
	
	public static ArrayList<ThongTinChiTietNhan> get(String ma) {
		PreparedStatement stmt = null;
		ArrayList<ThongTinChiTietNhan> ttct = new ArrayList<ThongTinChiTietNhan>();
		try {
			Connection con = Database.getInstance().getConnection();
			stmt = con.prepareStatement("Select * from ChiTietNhanPHong where MaNhanPhong = ?");
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String MaNhanPhong = rs.getString(1);
				String MaPhong = rs.getString(2);
				ThongTinChiTietNhan p = new ThongTinChiTietNhan(MaNhanPhong, MaPhong);
				ttct.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ttct;
	}
}
