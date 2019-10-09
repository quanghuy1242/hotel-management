package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.Database;
import models.ChiTietHoaDon;
import models.Phong;

public class QuanLyPhong {
	ArrayList<Phong> dsPhong;
	Phong phong;

	public QuanLyPhong() {
		// TODO Auto-generated constructor stub
		dsPhong = new ArrayList<Phong>();
		phong = new Phong();
	}

	public ArrayList<Phong> docTuBangPhong() {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select * from Phong";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maPhong = rs.getString(1);
				String loaiPhong = rs.getString(2);
				Double giaPhong = rs.getDouble(3);
				String tinhTrangPhong = rs.getString(4);
				Phong p = new Phong(maPhong, loaiPhong, giaPhong, tinhTrangPhong);
				dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
	
	public static Phong get(String ma) {
		PreparedStatement stmt = null;
		Phong phong = null;
		try {
			Connection con = Database.getInstance().getConnection();
			stmt = con.prepareStatement("Select * from Phong where MaPhong = ?");
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maPhong = rs.getString(1);
				String loaiPhong = rs.getString(2);
				Double giaPhong = rs.getDouble(3);
				String tinhTrangPhong = rs.getString(4);
				phong = new Phong(maPhong, loaiPhong, giaPhong, tinhTrangPhong);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return phong;
	}
	
	public static boolean toggleStatus(String ma) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
				stmt = con.prepareStatement("Update Phong set " + 
											"TinhTrangPhong = ? " + 
											"Where MaPhong = ?");
				String tinhTrang = QuanLyPhong.get(ma).getTinhTrangPhong().equals("Trống") ? "Đã đặt" : "Trống";
				stmt.setString(1, tinhTrang);
				stmt.setString(2, ma);
				n= stmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return n>0;
	}
	
	public static ArrayList<Phong> getEmptyRoom() {
		PreparedStatement stmt = null;
		ArrayList<Phong> dsPhongTrong = new ArrayList<Phong>();
		try {
			Connection con = Database.getInstance().getConnection();
			stmt = con.prepareStatement("Select * from Phong where TinhTrangPhong = ?");
			stmt.setString(1, "Trống");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maPhong = rs.getString(1);
				String loaiPhong = rs.getString(2);
				Double giaPhong = rs.getDouble(3);
				String tinhTrangPhong = rs.getString(4);
				Phong p = new Phong(maPhong, loaiPhong, giaPhong, tinhTrangPhong);
				dsPhongTrong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhongTrong;
	}
}
