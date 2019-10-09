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
import java.util.Date;

import database.Database;
import models.KhachHang;
import models.ThongTinDangKy;

public class QuanKyKhachHang {
	ArrayList<KhachHang> dsKH;
	
	public QuanKyKhachHang() {
		dsKH = new ArrayList<KhachHang>();
	}
	
	public ArrayList<KhachHang> readFromTable() {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "SELECT * FROM KhachHang";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				String ma = rs.getString(1);
				String hoten = rs.getString(2);
				String quoctich = rs.getString(3);
				String cmnd = rs.getString(4);
				String ngaysinh = df.format(rs.getDate(5));
				String gioiTinh = rs.getString(6);
				String diaChi = rs.getString(7);
				String sdt = rs.getString(8);
				KhachHang newKH = new KhachHang(ma, hoten, quoctich, cmnd, ngaysinh, gioiTinh, diaChi, sdt);
				dsKH.add(newKH);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dsKH;
	}
	
	public boolean add(KhachHang khachHang) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("INSERT KHACHHANG VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			
			stmt.setString(1, khachHang.getMaKH());
			stmt.setString(2, khachHang.getHoTen());
			stmt.setString(3, khachHang.getQuocTich());
			stmt.setString(4, khachHang.getMaCMND());
			try {
				stmt.setDate(5, new java.sql.Date(df.parse(khachHang.getNgaySinh()).getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stmt.setString(6, khachHang.getGioiTinh());
			stmt.setString(7, khachHang.getDiaChi());
			stmt.setString(8, khachHang.getSoDienThoai());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return n > 0;
	}
	
	public boolean update(KhachHang newKhachHang) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement(
					"update KhachHang " + 
							"set HoTen = ?," + 
							"QuocTich = ?," + 
							"MaCMND = ?," + 
							"NgaySinh = ?," + 
							"GioiTinh = ?," + 
							"DiaChi = ?," + 
							"SoDienThoai = ? " +
					"where MaKH = ?");
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			
			stmt.setString(1, newKhachHang.getHoTen());
			stmt.setString(2, newKhachHang.getQuocTich());
			stmt.setString(3, newKhachHang.getMaCMND());
			try {
				stmt.setDate(4, new java.sql.Date(df.parse(newKhachHang.getNgaySinh()).getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stmt.setString(5, newKhachHang.getGioiTinh());
			stmt.setString(6, newKhachHang.getDiaChi());
			stmt.setString(7, newKhachHang.getSoDienThoai());
			stmt.setString(8, newKhachHang.getMaKH());
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
			stmt = con.prepareStatement("delete from KhachHang where MaKH = ?");
			stmt.setString(1, ma);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return n > 0;
	}
	
	public static KhachHang get(String ma) {
		PreparedStatement stmt = null;
		KhachHang khachHang = null;
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Connection con = Database.getInstance().getConnection();
			stmt = con.prepareStatement("Select * from KhachHang where MaKH = ?");
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String makh = rs.getString(1);
				String hoten = rs.getString(2);
				String quoctich = rs.getString(3);
				String cmnd = rs.getString(4);
				String ngaysinh = df.format(rs.getDate(5));
				String gioiTinh = rs.getString(6);
				String diaChi = rs.getString(7);
				String sdt = rs.getString(8);
				khachHang = new KhachHang(makh, hoten, quoctich, cmnd, ngaysinh, gioiTinh, diaChi, sdt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return khachHang;
	}
}
