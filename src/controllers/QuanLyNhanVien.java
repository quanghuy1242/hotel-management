package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import database.Database;
import models.ChiTietHoaDon;
import models.NhanVien;

public class QuanLyNhanVien {
	ArrayList<NhanVien> dsNV;
	public static NhanVien currentNhanVien = null;
	
//	public ArrayList<NhanVien> getListNhanVien() {
//		try {
//			Connection con = Database.getInstance().getConnection();
//			String sql = "SELECT * FROM NhanVien";
//			Statement statement = con.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//			while(rs.next()) {
//				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//				String ma = rs.getString(1);
//				String hoten = rs.getString(2);
//				String cmnd = rs.getString(3);
//				String ngaysinh = df.format(rs.getDate(4));
//				String gioiTinh = rs.getString(5);
//				String diaChi = rs.getString(6);
//				String sdt = rs.getString(7);
//				String tenDangNhap = rs.getString(8);
//				String matKhau = rs.getString(9);
//				
//				dsNV.add(new NhanVien(ma, hoten));
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return dsNV;
//	}
//	
	public NhanVien signIn(String maDN, String pass) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("select * from NhanVien where MaDangNhap = ? and MatKhau = ?");
			stmt.setString(1, maDN);
			stmt.setString(2, pass);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				String ma = rs.getString(1);
				String hoten = rs.getString(2);
				String cmnd = rs.getString(3);
				String ngaysinh = df.format(rs.getDate(4));
				String gioiTinh = rs.getString(5);
				String diaChi = rs.getString(6);
				String sdt = rs.getString(7);
				String tenDangNhap = rs.getString(8);
				String matKhau = rs.getString(9);
				currentNhanVien = new NhanVien(ma, hoten, cmnd, ngaysinh, diaChi, gioiTinh, sdt, tenDangNhap, matKhau);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return currentNhanVien;
	}
	
	public static NhanVien get(String ma) {
		PreparedStatement stmt = null;
		NhanVien nv = null;
		try {
			Connection con = Database.getInstance().getConnection();
			stmt = con.prepareStatement("Select * from NhanVien where MaNV = ?");
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				String manv = rs.getString(1);
				String hoten = rs.getString(2);
				String cmnd = rs.getString(3);
				String ngaysinh = df.format(rs.getDate(4));
				String gioiTinh = rs.getString(5);
				String diaChi = rs.getString(6);
				String sdt = rs.getString(7);
				String tenDangNhap = rs.getString(8);
				String matKhau = rs.getString(9);
				nv = new NhanVien(manv, hoten, cmnd, ngaysinh, diaChi, gioiTinh, sdt, tenDangNhap, matKhau);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nv;
	}
}
