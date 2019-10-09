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
import models.HoaDon;

public class DanhSachHD {
	
		ArrayList<HoaDon> dsHoaDon;
		HoaDon hoadon;

		public DanhSachHD() {
			// TODO Auto-generated constructor stub
			dsHoaDon = new ArrayList<HoaDon>();
			hoadon = new HoaDon();
		}

		public ArrayList<HoaDon> docTuBangPhong() {
			try {
				Connection con = Database.getInstance().getConnection();
				String sql = "Select * from HoaDon";
				Statement statement = con.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				while (rs.next()) {
					String MaHoaDon = rs.getString(1);
					String MaNhanVien = rs.getString(2);
					String MaTraPhong= rs.getString(3);
					String NgayLapHoaDon = rs.getString(4);
					Double TongHoaDon = rs.getDouble(5);
					HoaDon p = new HoaDon(MaHoaDon, MaNhanVien, MaTraPhong, NgayLapHoaDon,TongHoaDon);
					dsHoaDon.add(p);
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			return dsHoaDon;
		}
		public boolean create(HoaDon themhd) {
			Connection con = Database.getInstance().getConnection();
			PreparedStatement stmt = null;
			int n = 0;
			try {
					stmt = con.prepareStatement("insert into HoaDon values(?, ?, ?, ?, ?)");
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					DateFormat df1 = new SimpleDateFormat("hh:mm:ss");
					stmt.setString(1, themhd.getMaHoaDon());
					stmt.setString(2, themhd.getMaNhanVien());
					stmt.setString(3, themhd.getMaTraPhong());
					stmt.setString(4, themhd.getNgayLapHoaDon());
					stmt.setDouble(5, themhd.getTongHoaDon());
					n= stmt.executeUpdate();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			return n>0;
		}
		public boolean update(HoaDon updatehd) {
			Connection con = Database.getInstance().getConnection();
			PreparedStatement stmt = null;
			int n= 0;
			try {
				stmt = con.prepareStatement("update HoaDon "
						+"set NgayLapHoaDon =?,"
						+"TongHoaDon = ? "
						+"where MaHoaDon= ?");
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				DateFormat df1 = new SimpleDateFormat("hh:mm:ss");
//				try {
//				stmt.setDate(1, new java.sql.Date(df.parse(updatettnp.getNgayNhan()).getTime()));
//				}catch(ParseException e) {
//					e.printStackTrace();
//				}
				stmt.setString(1, updatehd.getNgayLapHoaDon());
				stmt.setDouble(2, updatehd.getTongHoaDon());
				stmt.setString(3, updatehd.getMaHoaDon());
				n= stmt.executeUpdate();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			return n>0;
		}
		public boolean delete(String MaHoaDon) {
			Connection con = Database.getInstance().getConnection();
			PreparedStatement stmt = null;
			int n = 0;
			try {
					stmt = con.prepareStatement("delete from HoaDon where MaHoaDon=?");
					stmt.setString(1, MaHoaDon);
					n= stmt.executeUpdate();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			return n>0;
		}
		
	}
