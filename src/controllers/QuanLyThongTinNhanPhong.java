package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import database.Database;
import models.ThongTinNhan;
import models.ThongTinTra;

public class QuanLyThongTinNhanPhong {
	ArrayList<ThongTinNhan> dsPhongTTNP;
	ThongTinNhan phongttnp;

	public QuanLyThongTinNhanPhong() {
		// TODO Auto-generated constructor stub
		dsPhongTTNP = new ArrayList<ThongTinNhan>();
		phongttnp = new ThongTinNhan();
	}

	public ArrayList<ThongTinNhan> docTuBangPhong() {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select * from ThongTinNhan";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String MaNhanPhong = rs.getString(1);
				String MaDangky = rs.getString(2);
				String NgayNhan= rs.getString(3);
				String GioNhan = rs.getString(4);
				ThongTinNhan p = new ThongTinNhan(MaNhanPhong, MaDangky, NgayNhan, GioNhan);
				dsPhongTTNP.add(p);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return dsPhongTTNP;
	}
	public boolean create(ThongTinNhan themttnp) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
				stmt = con.prepareStatement("insert into ThongTinNhan values(?, ?, ?, ?)");
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				DateFormat df1 = new SimpleDateFormat("hh:mm:ss");
				stmt.setString(1, themttnp.getMaNhanPhong());
				stmt.setString(2, themttnp.getMaDangKy());
				stmt.setString(3, themttnp.getNgayNhan());
				stmt.setString(4, themttnp.getGioNhan());
				n= stmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return n>0;
	}
	public boolean update(ThongTinNhan updatettnp) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n= 0;
		try {
			stmt = con.prepareStatement("update ThongTinNhan "
					+"set NgayNhan =?,"
					+"GioNhan = ? "
					+"where MaNhanPhong = ?");
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			DateFormat df1 = new SimpleDateFormat("hh:mm:ss");
			stmt.setString(1, updatettnp.getNgayNhan());
			stmt.setString(2, updatettnp.getGioNhan());
			stmt.setString(3, updatettnp.getMaNhanPhong());
			n= stmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return n>0;
	}
	public boolean delete(String MaNhanPhong) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
				stmt = con.prepareStatement("delete from ThongTinNhan where MaNhanPhong=?");
				stmt.setString(1, MaNhanPhong);
				n= stmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return n>0;
	}
	
	public static ThongTinNhan get(String ma) {
		PreparedStatement stmt = null;
		ThongTinNhan p = null;
		try {
			Connection con = Database.getInstance().getConnection();
			stmt = con.prepareStatement("Select * from ThongTinNhan where MaNhanPhong = ?");
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String MaNhanPhong = rs.getString(1);
				String MaDangky = rs.getString(2);
				String NgayNhan= rs.getString(3);
				String GioNhan = rs.getString(4);
				p = new ThongTinNhan(MaNhanPhong, MaDangky, NgayNhan, GioNhan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
}
