package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controllers.QuanKyKhachHang;
import database.Database;
import models.KhachHang;

public class FrmKhachHang extends JFrame implements ActionListener, MouseListener {
	JLabel lblTitle;
	JTable tableKhachHang;
	DefaultTableModel model;
	JButton btnAdd, btnTim, btnXoa, btnSua, btnXoaRong, btnDangKy, btnLuu;
	JTextField txtTim, txtMa, txtHoTen, txtQuocTich, txtCMND, txtNgaySinh, txtGioiTinh, txtDiaChi, txtDienThoai;
	QuanKyKhachHang quanKyKhachHang = new QuanKyKhachHang();
	JComboBox<String> cbgioiTinh;
	DefaultComboBoxModel<String> modelGioiTinh;
	public FrmKhachHang() {
		super("Quản lý Khách Hàng");
		
		JPanel pTop = new JPanel();
		pTop.add(lblTitle = new JLabel("QUẢN LÝ KHÁCH HÀNG"));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 25));
		add(pTop, BorderLayout.NORTH);
		
		JPanel pCenter = new JPanel();
		pCenter.setLayout(new BoxLayout(pCenter, BoxLayout.Y_AXIS));
		JPanel pCenterAction = new JPanel();
		JPanel pCenterTable = new JPanel();
		pCenter.add(pCenterAction);
		pCenter.add(pCenterTable);
		
		pCenterAction.add(txtTim = new JTextField(10));
		pCenterAction.add(btnTim = new JButton("Tìm"));
		pCenterAction.add(btnAdd = new JButton("Thêm"));
		pCenterAction.add(btnXoa = new JButton("Xoá"));
		pCenterAction.add(btnXoaRong = new JButton("Xoá rỗng"));
		pCenterAction.add(btnSua = new JButton("Sửa"));
		pCenterAction.add(btnLuu = new JButton("Lưu"));
//		pCenterAction.add(btnDangKy = new JButton("Đăng kí"));
		
		btnAdd.setIcon(new ImageIcon("icons/add.png"));
		btnTim.setIcon(new ImageIcon("icons/search.png"));
//		btnDangKy.setIcon(new ImageIcon("icons/dangki.png"));
		btnXoa.setIcon(new ImageIcon("icons/delete.png"));
		btnXoaRong.setIcon(new ImageIcon("icons/xoarong.png"));
		btnLuu.setIcon(new ImageIcon("icons/luu.png"));
		btnSua.setIcon(new ImageIcon("icons/sua.png"));
		
		String[] cols = {"Mã khách", "Họ tên", "Quốc tịch", "CMND", "Ngày sinh", "Giới tính", "Địa chỉ", "Số điện thoại"};
		model = new DefaultTableModel(cols, 0);
		tableKhachHang = new JTable(model);
		JScrollPane pane = new JScrollPane(tableKhachHang);
		pane.setPreferredSize(new Dimension(750, 379));
		pCenterTable.add(pane);
		add(pCenter, BorderLayout.CENTER);
		
		JPanel pRight = new JPanel();
		pRight.setBorder(BorderFactory.createTitledBorder("Các thông tin"));
		pRight.setLayout(new BoxLayout(pRight, BoxLayout.X_AXIS));
		
		JPanel pLable = new JPanel();
		JPanel pTextfield = new JPanel();
		pLable.setLayout(new BoxLayout(pLable, BoxLayout.Y_AXIS));
		pTextfield.setLayout(new BoxLayout(pTextfield, BoxLayout.Y_AXIS));

		pRight.add(Box.createHorizontalStrut(10));
		pRight.add(pLable);
		pRight.add(Box.createHorizontalStrut(10));
		pRight.add(pTextfield);
		pRight.add(Box.createHorizontalStrut(10));
	
		pLable.add(Box.createVerticalStrut(5));
		pLable.add(new JLabel("Mã:"));
		pLable.add(Box.createVerticalStrut(9));
		pLable.add(new JLabel("Họ tên:"));
		pLable.add(Box.createVerticalStrut(9));
		pLable.add(new JLabel("Quốc tịch:"));
		pLable.add(Box.createVerticalStrut(9));
		pLable.add(new JLabel("CMND:"));
		pLable.add(Box.createVerticalStrut(9));
		pLable.add(new JLabel("Ngày sinh:"));
		pLable.add(Box.createVerticalStrut(13));
		pLable.add(new JLabel("Giới tính:"));
		pLable.add(Box.createVerticalStrut(9));
		pLable.add(new JLabel("Địa chỉ:"));
		pLable.add(Box.createVerticalStrut(9));
		pLable.add(new JLabel("Điện thoại:"));
		pLable.add(Box.createVerticalGlue());
		
		modelGioiTinh = new DefaultComboBoxModel<String>("Nam,Nữ".split(","));
		
		pTextfield.add(Box.createVerticalStrut(5));
		pTextfield.add(txtMa = new JTextField(20));
		pTextfield.add(Box.createVerticalStrut(5));
		pTextfield.add(txtHoTen = new JTextField(10));
		pTextfield.add(Box.createVerticalStrut(5));
		pTextfield.add(txtQuocTich = new JTextField(10));
		pTextfield.add(Box.createVerticalStrut(5));
		pTextfield.add(txtCMND = new JTextField(10));
		pTextfield.add(Box.createVerticalStrut(5));
		pTextfield.add(txtNgaySinh = new JTextField(10));
		pTextfield.add(Box.createVerticalStrut(5));
		pTextfield.add(cbgioiTinh = new JComboBox<String>(modelGioiTinh));
		pTextfield.add(Box.createVerticalStrut(5));
		pTextfield.add(txtDiaChi = new JTextField(10));
		pTextfield.add(Box.createVerticalStrut(5));
		pTextfield.add(txtDienThoai = new JTextField(10));
		pTextfield.add(Box.createVerticalGlue());
		
		txtMa.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtMa.getPreferredSize().height ));
		txtHoTen.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtHoTen.getPreferredSize().height ));
		txtQuocTich.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtQuocTich.getPreferredSize().height ));
		txtCMND.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtCMND.getPreferredSize().height ));
		txtNgaySinh.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtNgaySinh.getPreferredSize().height ));
		cbgioiTinh.setMaximumSize(new Dimension(Integer.MAX_VALUE, cbgioiTinh.getPreferredSize().height ));
		txtDiaChi.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtDiaChi.getPreferredSize().height ));
		txtDienThoai.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtDienThoai.getPreferredSize().height ));
		
		add(pRight, BorderLayout.EAST);
		
//		Xu Kien
		btnAdd.addActionListener(this);
//		btnDangKy.addActionListener(this);
		btnSua.addActionListener(this);
		btnLuu.addActionListener(this);
		btnTim.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaRong.addActionListener(this);
		tableKhachHang.addMouseListener(this);
		
		setSize(1080, 500);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		// Load dữ liệu
		updateTableData();
	}
	
	private void updateTableData() {
		ArrayList<KhachHang> listKH = quanKyKhachHang.readFromTable();
		for(KhachHang kh : listKH) {
			String[] rowData = kh.toString().split(";");
			model.addRow(rowData);
		}
		tableKhachHang.setModel(model);
	}
	
	private void xoaRong() {
		txtMa.setText("");
		txtHoTen.setText("");
		txtQuocTich.setText("");
		txtCMND.setText("");
		txtNgaySinh.setText("");
		txtDiaChi.setText("");
		txtDienThoai.setText("");
		txtMa.requestFocus();
	}
	
	private boolean validateData(KhachHang khachHang) {
//		Pattern
		String patternMa = "KH\\d{1,4}";
		String patternCMND = "\\d{9}";
		String patternSoDienThoai = "\\d{10,11}";
		if (!khachHang.getMaKH().matches(patternMa)) {
			JOptionPane.showMessageDialog(this, "Mã khách hàng phải có dạng " + patternMa);
			txtMa.selectAll();
			txtMa.requestFocus();
			return false;
		}
		if (!khachHang.getMaCMND().matches(patternCMND)) {
			JOptionPane.showMessageDialog(this, "Số CMND phải có dạng " + patternCMND);
			txtCMND.selectAll();
			txtCMND.requestFocus();
			return false;
		}
//		if (!khachHang.getNgaySinh().matches(patternNgaySinh)) {
//			JOptionPane.showMessageDialog(this, "Ngày sinh phải có dạng " + patternNgaySinh);
//			txtNgaySinh.selectAll();
//			txtNgaySinh.requestFocus();
//			return false;
//		}
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);
		try {
			df.parse(khachHang.getNgaySinh());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Ngày trả trả phòng không đúng định dạng!");
			txtNgaySinh.selectAll();
			txtNgaySinh.requestFocus();
			return false;
		}
		if (!khachHang.getSoDienThoai().matches(patternSoDienThoai)) {
			JOptionPane.showMessageDialog(this, "Số điện thoại phải có dạng ít nhất là 10 số, nhiều nhất là 11 số ");
			txtDienThoai.selectAll();
			txtDienThoai.requestFocus();
			return false;
		}
		return true;
	}
	
	private KhachHang getKhachHangFromTextForm() {
		String ma = txtMa.getText();
		String hoTen = txtHoTen.getText();
		String quocTich = txtQuocTich.getText();
		String maCMND = txtCMND.getText();
		String ngaySinh = txtNgaySinh.getText();
		String gioiTinh = (String)cbgioiTinh.getSelectedItem();
		String diaChi = txtDiaChi.getText();
		String soDienThoai = txtDienThoai.getText();
		
		KhachHang khachHang = new KhachHang(ma, hoTen, quocTich, maCMND, ngaySinh, gioiTinh, diaChi, soDienThoai);
		if (!validateData(khachHang)) {
			return null;
		}
		
		return khachHang;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		txtMa.setEditable(false);
		int row = tableKhachHang.getSelectedRow();
		txtMa.setText(tableKhachHang.getValueAt(row, 0).toString());
		txtHoTen.setText(tableKhachHang.getValueAt(row, 1).toString());
		txtQuocTich.setText(tableKhachHang.getValueAt(row, 2).toString());
		txtCMND.setText(tableKhachHang.getValueAt(row, 3).toString());
		txtNgaySinh.setText(tableKhachHang.getValueAt(row, 4).toString());
		modelGioiTinh.setSelectedItem(tableKhachHang.getValueAt(row, 5).toString());
		txtDiaChi.setText(tableKhachHang.getValueAt(row, 6).toString());
		txtDienThoai.setText(tableKhachHang.getValueAt(row, 7).toString());
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnAdd)) {
			xoaRong();
			txtMa.setEditable(true);
		}
		else if (o.equals(btnLuu)) {
			KhachHang khachHang = getKhachHangFromTextForm();
			if (khachHang == null) {
				return;
			}
			if (quanKyKhachHang.add(khachHang)) {
				model.addRow(khachHang.toString().split(";"));
				tableKhachHang.setRowSelectionInterval(model.getRowCount() - 1, model.getRowCount() - 1);
				txtMa.setEditable(false);
				JOptionPane.showMessageDialog(this, "Thêm thành công một khách hàng");
			} else {
				JOptionPane.showMessageDialog(this, "Mã Khách hàng trùng!");
			}
		}
		else if (o.equals(btnSua)) {
			int rowIndex = tableKhachHang.getSelectedRow();
			if (rowIndex >= 0) {
				KhachHang newKhachHang = getKhachHangFromTextForm();
				if (newKhachHang == null) {
					return;
				}
				if (quanKyKhachHang.update(newKhachHang)) {
					tableKhachHang.setValueAt(newKhachHang.getHoTen(), rowIndex, 1);
					tableKhachHang.setValueAt(newKhachHang.getQuocTich(), rowIndex, 2);
					tableKhachHang.setValueAt(newKhachHang.getMaCMND(), rowIndex, 3);
					tableKhachHang.setValueAt(newKhachHang.getNgaySinh(), rowIndex, 4);
					tableKhachHang.setValueAt(newKhachHang.getGioiTinh(), rowIndex, 5);
					tableKhachHang.setValueAt(newKhachHang.getDiaChi(), rowIndex, 6);
					tableKhachHang.setValueAt(newKhachHang.getSoDienThoai(), rowIndex, 7);
					txtMa.setEditable(false);
					JOptionPane.showMessageDialog(this, "Thông tin khách hàng được cập nhật!");
				} else {
					JOptionPane.showMessageDialog(this, "Có lỗi xảy ra!");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Chọn dòng cần sửa!");
			}
		}
		else if (o.equals(btnXoa)) {
			if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn xoá?", "Xác Nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				int rowIndex = tableKhachHang.getSelectedRow();
				if (rowIndex >= 0) {
					String maKH = (String) tableKhachHang.getValueAt(rowIndex, 0);
					if (quanKyKhachHang.delete(maKH)) {
						model.removeRow(rowIndex);
						xoaRong();
					}
				}
			}
		}
		else if (o.equals(btnXoaRong)) {
			xoaRong();
		}
		else if (o.equals(btnTim)) {
			String maCanTim = txtTim.getText().trim().toUpperCase();
			if (maCanTim.equals("")) {
				JOptionPane.showMessageDialog(this, "Hãy nhập mã cần tìm!");
			} else {
				for (int i = 0; i < model.getRowCount(); i++) {
					if (model.getValueAt(i, 0).equals(maCanTim)) {
						tableKhachHang.setRowSelectionInterval(i, i);
						tableKhachHang.scrollRectToVisible(tableKhachHang.getCellRect(i, 0, true));
						return;
					}
				}
				JOptionPane.showMessageDialog(this, "Không tìm thấy!");
			}
		}
	}
}
