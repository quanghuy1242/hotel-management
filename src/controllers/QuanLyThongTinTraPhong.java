package controllers;

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
import models.ThongTinTra;

public class QuanLyThongTinTraPhong {
	ArrayList<ThongTinTra> dsThongTinTra;
	
	public QuanLyThongTinTraPhong() {
		dsThongTinTra = new ArrayList<ThongTinTra>();
	}
	
	public ArrayList<ThongTinTra> readFromTable() {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "SELECT * FROM ThongTinTraPhong";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			DateFormat df1 = new SimpleDateFormat("hh:mm:ss");
			while(rs.next()) {
				String maTra = rs.getString(1);
				String maNhan = rs.getString(2);
				String ngayTra = df.format(rs.getDate(3));
				String gioTra = df1.format(rs.getTime(4));
				ThongTinTra newTTT = new ThongTinTra(maTra, maNhan, ngayTra, gioTra);
				dsThongTinTra.add(newTTT);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dsThongTinTra;
	}
	
	public boolean add(ThongTinTra thongTinTra) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("INSERT ThongTinTraPhong VALUES (?, ?, ?, ?)");
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			DateFormat df1 = new SimpleDateFormat("hh:mm:ss");
			
			stmt.setString(1, thongTinTra.getMaTraPhong());
			stmt.setString(2, thongTinTra.getMaNhanPhong());
			try {
				stmt.setDate(3, new java.sql.Date(df.parse(thongTinTra.getMaNgayTra()).getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stmt.setTime(4, new java.sql.Time(df1.parse(thongTinTra.getGioTra()).getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return n > 0;
	}
	
	public boolean update(ThongTinTra newThongTinTra) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement(
					"update ThongTinTraPhong " + 
							"set NgayTra = ?," + 
							"GioTra = ? " +
					"where MaTraPhong = ?");
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			DateFormat df1 = new SimpleDateFormat("hh:mm:ss");

			try {
				stmt.setDate(1, new java.sql.Date(df.parse(newThongTinTra.getMaNgayTra()).getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stmt.setTime(2, new java.sql.Time(df1.parse(newThongTinTra.getGioTra()).getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stmt.setString(3, newThongTinTra.getMaTraPhong());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return n > 0;
	}
	
	public boolean delete(String ma) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from ThongTinTraPhong where MaTraPhong = ?");
			stmt.setString(1, ma);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return n > 0;
	}
	
	public static ThongTinTra get(String ma) {
		PreparedStatement stmt = null;
		ThongTinTra thongTinTra = null;
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat df1 = new SimpleDateFormat("hh:mm:ss");
		try {
			Connection con = Database.getInstance().getConnection();
			stmt = con.prepareStatement("Select * from ThongTinTraPhong where MaTraPhong = ?");
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maTra = rs.getString(1);
				String maNhan = rs.getString(2);
				String ngayTra = df.format(rs.getDate(3));
				String gioTra = df1.format(rs.getTime(4));
				thongTinTra = new ThongTinTra(maTra, maNhan, ngayTra, gioTra);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return thongTinTra;
	}
}
