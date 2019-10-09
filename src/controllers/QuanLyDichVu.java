package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.Database;
import models.DichVu;

public class QuanLyDichVu {
	public static DichVu get(String ma) {
		PreparedStatement stmt = null;
		DichVu dv = null;
		try {
			Connection con = Database.getInstance().getConnection();
			stmt = con.prepareStatement("Select * from DichVu where MaDV = ?");
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maDichVu = rs.getString(1);
				String laoDichVu = rs.getString(2);
				String donViTinh = rs.getString(3);
				double donGia = rs.getDouble(4);
				dv = new DichVu(maDichVu, laoDichVu, donViTinh, donGia);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dv;
	}
}
