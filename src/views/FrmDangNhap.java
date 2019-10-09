package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controllers.QuanLyNhanVien;
import database.Database;
import models.NhanVien;

public class FrmDangNhap extends JFrame implements ActionListener {
	JLabel txtTitle;
	JTextField txtMaDangNhap;
	JPasswordField txtMatKhau;
	JButton btnDangNhap;
	QuanLyNhanVien quanLyNhanVien = new QuanLyNhanVien();
	public FrmDangNhap() {
		super("Đăng nhập");
		
		JPanel pTop = new JPanel();
		pTop.add(txtTitle = new JLabel("ĐĂNG NHẬP"));
		txtTitle.setFont(new Font("Arial", Font.BOLD, 25));
		add(pTop, BorderLayout.NORTH);
	
		JPanel pCenter = new JPanel();
		pCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pCenter.setLayout(new BoxLayout(pCenter, BoxLayout.X_AXIS));
		
		JPanel pLabel = new JPanel();
		pLabel.setLayout(new BoxLayout(pLabel, BoxLayout.Y_AXIS));
		pLabel.add(new JLabel("Mã đăng nhập:"));
		pLabel.add(Box.createVerticalStrut(10));
		pLabel.add(new JLabel("Mật khẩu:"));
		
		JPanel pTextField = new JPanel();
		pTextField.setLayout(new BoxLayout(pTextField, BoxLayout.Y_AXIS));
		pTextField.add(txtMaDangNhap = new JTextField(10));
		pTextField.add(Box.createVerticalStrut(5));
		pTextField.add(txtMatKhau = new JPasswordField(10));
		
		txtMaDangNhap.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtMaDangNhap.getPreferredSize().height ));
		txtMatKhau.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtMatKhau.getPreferredSize().height ));
		
		pCenter.add(pLabel);
		pCenter.add(Box.createHorizontalStrut(5));
		pCenter.add(pTextField);
		
		JPanel pAction = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pAction.add(btnDangNhap = new JButton("Đăng nhập"));
		pAction.setBorder(BorderFactory.createEmptyBorder(0, 0, 7, 7));
		add(pAction, BorderLayout.SOUTH);
		
		add(pCenter, BorderLayout.CENTER);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 190);
		setLocationRelativeTo(null);
		btnDangNhap.addActionListener(this);

		Database.getInstance().connect();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnDangNhap)) {
			String maDangNhap = txtMaDangNhap.getText();
			String matKhau = new String(txtMatKhau.getPassword());
			NhanVien nv = quanLyNhanVien.signIn(maDangNhap, matKhau);
			if (nv == null) {
				JOptionPane.showMessageDialog(this, "Mật Khẩu hoặc tên người dùng không chính xác");
				return;
			} else {
				(new FrmMain()).setVisible(true);
				this.setVisible(false);
			}
		}
	}
	
}
