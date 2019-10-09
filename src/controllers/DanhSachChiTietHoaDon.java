package controllers;

import java.sql.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import database.Database;
import models.ChiTietHoaDon;
import models.HoaDon;

public class DanhSachChiTietHoaDon {
	static ArrayList<ChiTietHoaDon> dscthd;
	ChiTietHoaDon cthd;

	public DanhSachChiTietHoaDon() {
		// TODO Auto-generated constructor stub
		dscthd = new ArrayList<ChiTietHoaDon>();
		cthd = new ChiTietHoaDon();
	}

	public ArrayList<ChiTietHoaDon> docTuBangDSCTHD() {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select * from ChiTietHoaDon";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maHoaDon = rs.getString(1);
				String maDichVu = rs.getString(2);
				int soLuong = rs.getInt(3);
				Double tongTien = rs.getDouble(4);
				ChiTietHoaDon ct = new ChiTietHoaDon(maHoaDon, maDichVu, soLuong, tongTien);
				dscthd.add(ct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dscthd;
	}

	public boolean delete(String maHoaDon) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from ChiTietHoaDon where MaHoaDon=?");
			stmt.setString(1, maHoaDon);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean add(ChiTietHoaDon cthd) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("INSERT ChiTietHoaDon VALUES (?, ?, ?, ?)");
			stmt.setString(1, cthd.getMaHoaDon());
			stmt.setString(2, cthd.getMaDichVu());
			stmt.setInt(3, cthd.getSoLuong());
			stmt.setDouble(4, cthd.getTongTien());

			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return n > 0;
	}
	
	
	public boolean update(ChiTietHoaDon newcthd) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement(
					"update ChiTietHoaDon " + 
					"set SoLuong = ?, " + 
					"TongTien = ? " + 
					"where MaHoaDon = ? and " + 
					"MaDichVu = ?");
			stmt.setInt(1, newcthd.getSoLuong());
			stmt.setDouble(2, newcthd.getTongTien());
			stmt.setString(3, newcthd.getMaHoaDon());
			stmt.setString(4, newcthd.getMaDichVu());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			System.out.println("Lỗi");
		}
		return n > 0;
	}
	
	public ArrayList<ChiTietHoaDon> get(String ma) {
		PreparedStatement stmt = null;
		ArrayList<ChiTietHoaDon> cthd = new ArrayList<ChiTietHoaDon>();
		try {
			Connection con = Database.getInstance().getConnection();
			stmt = con.prepareStatement("Select * from ChiTietHoaDon where MaHoaDon = ?");
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maHoaDon = rs.getString(1);
				String maDichVu = rs.getString(2);
				int soLuong = rs.getInt(3);
				Double tongTien = rs.getDouble(4);
				ChiTietHoaDon ct = new ChiTietHoaDon(maHoaDon, maDichVu, soLuong, tongTien);
				cthd.add(ct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cthd;
	}

}
