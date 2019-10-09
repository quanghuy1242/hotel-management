package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controllers.QuanLyNhanVien;

public class FrmMain extends JFrame implements ActionListener {
	JLabel lblTitile, lblName;
	JButton btnQuanLyKhachHang, btnQuanLyPhong, btnQuanLyDangKy, btnQuanLyNhan, btnQuanLyTra, btnQuanLyHoaDon, btnDangXuat;
	public FrmMain() {
		super("Màn hình chính");
		JPanel pTop = new JPanel();
		pTop.add(lblTitile = new JLabel("KHÁCH SẠN SAO AROMA"));
		lblTitile.setFont(new Font("Arial", Font.BOLD, 25));
		add(pTop, BorderLayout.NORTH);
		
		JPanel pCenter = new JPanel();
		
		JPanel pCenterLeft = new JPanel();
		JPanel pCenterRight = new JPanel();
		pCenterLeft.setLayout(new BoxLayout(pCenterLeft, BoxLayout.Y_AXIS));
		pCenterRight.setLayout(new BoxLayout(pCenterRight, BoxLayout.Y_AXIS));
		
		pCenter.add(pCenterLeft);
		
		pCenterLeft.add(lblName = new JLabel(QuanLyNhanVien.currentNhanVien.getTenNhanVien()));
		pCenterLeft.add(Box.createVerticalStrut(45));
		pCenterLeft.add(btnQuanLyKhachHang = new JButton("Quản Lý Khách Hàng"));
		pCenterLeft.add(Box.createVerticalStrut(5));
		pCenterLeft.add(btnQuanLyPhong = new JButton("Quản Lý Phòng"));
		pCenterLeft.add(Box.createVerticalStrut(5));
		pCenterLeft.add(btnQuanLyDangKy = new JButton("Quản Lý Đăng Ký Phòng"));
		pCenterLeft.add(Box.createVerticalStrut(5));
		pCenterLeft.add(btnQuanLyNhan = new JButton("Quản Lý Nhận Phòng"));
		pCenterLeft.add(Box.createVerticalStrut(5));
		pCenterLeft.add(btnQuanLyTra = new JButton("Quản Lý Trả Phòng"));
		pCenterLeft.add(Box.createVerticalStrut(5));
		pCenterLeft.add(btnQuanLyHoaDon = new JButton("Quản Lý Hoá Đơn"));
		pCenterLeft.add(Box.createVerticalStrut(5));
		pCenterLeft.add(btnDangXuat = new JButton("Đăng xuất"));
		
		btnQuanLyKhachHang.addActionListener(this);
		btnQuanLyPhong.addActionListener(this);
		btnQuanLyDangKy.addActionListener(this);
		btnQuanLyNhan.addActionListener(this);
		btnQuanLyTra.addActionListener(this);
		btnQuanLyHoaDon.addActionListener(this);
		btnDangXuat.addActionListener(this);
		
		add(pCenter, BorderLayout.CENTER);
		
		changeWidth(btnQuanLyKhachHang);
		changeWidth(btnQuanLyPhong);
		changeWidth(btnQuanLyDangKy);
		changeWidth(btnQuanLyNhan);
		changeWidth(btnQuanLyTra);
		changeWidth(btnQuanLyHoaDon);
		changeWidth(btnDangXuat);
		
		setSize(700, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	void changeWidth(JButton btn) {
		btn.setMinimumSize(new Dimension(300, btn.getMinimumSize().height));
		btn.setPreferredSize(new Dimension(300, btn.getPreferredSize().height));
		btn.setMaximumSize(new Dimension(300, btn.getMaximumSize().height));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnQuanLyKhachHang)) {
			FrmKhachHang frmKhachHang = new FrmKhachHang();
			frmKhachHang.setVisible(true);
		}
		else if (o.equals(btnQuanLyTra)) {
			FrmThongTinTraPhong frmThongTinTraPhong = new FrmThongTinTraPhong();
			frmThongTinTraPhong.setVisible(true);
		}
		else if (o.equals(btnDangXuat)) {
			if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn đăng xuất?", "Xác Nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				QuanLyNhanVien.currentNhanVien = null;
				(new FrmDangNhap()).setVisible(true);
				this.setVisible(false);
			}
		}
		else if (o.equals(btnQuanLyHoaDon)) {
			FrmHoaDonTH frmHoaDonTH = new FrmHoaDonTH();
			frmHoaDonTH.setVisible(true);
		}
		else if (o.equals(btnQuanLyNhan)) {
			FrmThongTinNhanPhong frmThongTinNhanPhong = new FrmThongTinNhanPhong();
			frmThongTinNhanPhong.setVisible(true);
		}
		else if (o.equals(btnQuanLyPhong)) {
			FrmPhong frmPhong = new FrmPhong();
			frmPhong.setVisible(true);
		}
		else if (o.equals(btnQuanLyDangKy)) {
			FrmThongTinDangKy frmThongTinDangKy = new FrmThongTinDangKy();
			frmThongTinDangKy.setVisible(true);
		}
	}
}
