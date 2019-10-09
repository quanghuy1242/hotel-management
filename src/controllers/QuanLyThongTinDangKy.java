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
import models.ThongTinDangKy;
import models.ThongTinNhan;

public class QuanLyThongTinDangKy {
	static ArrayList<ThongTinDangKy> dsdktp;
	ThongTinDangKy dktp;

	public QuanLyThongTinDangKy() {
		// TODO Auto-generated constructor stub
		dsdktp = new ArrayList<ThongTinDangKy>();
		dktp = new ThongTinDangKy();
	}

	public ArrayList<ThongTinDangKy> docTuBangDKTP() {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select * from ThongTinDangKy";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			while (rs.next()) {
				String maDangKy = rs.getString(1);
				String maKhachHang = rs.getString(2);
				String soLuongPhong = rs.getString(3);
				String ngayDen = df.format(rs.getDate(4));
				String ngayDi = df.format(rs.getDate(5));
				
				ThongTinDangKy dk = new ThongTinDangKy(maDangKy, maKhachHang, soLuongPhong, ngayDen, ngayDi);
				dsdktp.add(dk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsdktp;
	}

	public boolean delete(String maDangKy) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from ThongTinDangKy where MaDangKy=?");
			stmt.setString(1, maDangKy);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean add(ThongTinDangKy dktp) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("INSERT ThongTinDangKy VALUES (?, ?, ?, ?, ?)");
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");;
			
			stmt.setString(1, dktp.getMaDangKy());
			stmt.setString(2, dktp.getMaKhachHang());
			stmt.setString(3, dktp.getSoLuongPhong());
			try {
				stmt.setDate(4, new java.sql.Date(df.parse(dktp.getNgayDen()).getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stmt.setDate(5, new java.sql.Date(df.parse(dktp.getNgayDi()).getTime()));
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
	
	
	public boolean update(ThongTinDangKy newdktp) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement(
					"update ThongTinDangKy " + 
			
							"set SoLuongPhong = ?," + 
							"NgayDen = ?, " +
							"NgayDi = ? " +
					"where MaDangKy = ?");
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			
			stmt.setString(1, newdktp.getSoLuongPhong());

			try {
				stmt.setDate(2, new java.sql.Date(df.parse(newdktp.getNgayDen()).getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stmt.setDate(3, new java.sql.Date(df.parse(newdktp.getNgayDi()).getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stmt.setString(4, newdktp.getMaDangKy());

			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return n > 0;
	}
	
	public static ThongTinDangKy get(String ma) {
		PreparedStatement stmt = null;
		ThongTinDangKy dk = null;
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Connection con = Database.getInstance().getConnection();
			stmt = con.prepareStatement("Select * from ThongTinDangKy where MaDangKy = ?");
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maDangKy = rs.getString(1);
				String maKhachHang = rs.getString(2);
				String soLuongPhong = rs.getString(3);
				String ngayDen = df.format(rs.getDate(4));
				String ngayDi = df.format(rs.getDate(5));
				dk = new ThongTinDangKy(maDangKy, maKhachHang, soLuongPhong, ngayDen, ngayDi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dk;
	}
}
