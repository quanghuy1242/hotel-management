package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.Year;
import java.util.ArrayList;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controllers.QuanLyThongTinNhanPhong;
import database.Database;
import models.ThongTinNhan;

public class FrmThongTinNhanPhong extends JFrame implements ActionListener, MouseListener, ListSelectionListener {
	JTextField txtMaNhanPhong, txtMaDangKy, txtMaPhong, txtNgayNhan, txtGioNhan, txtTim, txtCTNP;
	JLabel lblMaNhanPhong, lblMaDangKy, lblMaPhong, lblNgayNhan, lblGioNhan, lblTitle, lblCTNP;
	JButton btnTim, btnXoaRong, btnXoa, btnSua, btnThem, btnCTNP;
	JTable table;
	DefaultTableModel model;
	QuanLyThongTinNhanPhong dsTTNP = new QuanLyThongTinNhanPhong();
	
	String maNhanPhong, maDangKy, ngayNhan, gioNhan;
	public FrmThongTinNhanPhong() {
		super("Chi tiêt nhận phòng");
		
		JPanel pTop = new JPanel();
		pTop.add(lblTitle = new JLabel("Thông tin nhận phòng"));
		lblTitle.setFont(new Font("Times new roman",Font.BOLD,30));
		add(pTop, BorderLayout.NORTH);
		//--------------------------------------------------------------------
		JPanel pCenter = new JPanel();
		pCenter.setLayout(new BoxLayout(pCenter, BoxLayout.Y_AXIS));
		
		JPanel pCenterAction = new JPanel();
		pCenterAction.add(txtTim= new JTextField(10)); 
		pCenterAction.add(btnTim= new JButton("Tìm", new ImageIcon("icons/search.png")));
		pCenterAction.add(btnThem= new JButton("Thêm", new ImageIcon("icons/add.png")));
		pCenterAction.add(btnXoa = new JButton("Xoá", new ImageIcon("icons/delete.png")));
		pCenterAction.add(btnXoaRong = new JButton("Xóa Rỗng", new ImageIcon("icons/xoarong.png")));
		pCenterAction.add(btnSua = new JButton("Sửa", new ImageIcon("icons/sua.png")));
		
		
		btnThem.addActionListener(this);
		btnXoaRong.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnTim.addActionListener(this);
		
		
		JPanel pCenterTable = new JPanel();
		String[] cols = {"Mã nhận phòng", "Mã đăng kí", "Ngày nhận", "Giờ nhận"};
		model = new DefaultTableModel(cols, 0);
		table = new JTable(model);
		table.addMouseListener(this);
		table.getSelectionModel().addListSelectionListener(this);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultEditor(Object.class, null);
		JScrollPane pane = new JScrollPane(table);
		pane.setPreferredSize(new Dimension(550, 570));
		pCenterTable.add(pane);
		
		pCenter.add(pCenterAction);
		pCenter.add(pCenterTable);
		add(pCenter, BorderLayout.CENTER);
		//--------------------------------------------------------------------------
		JPanel pRight = new JPanel();
		pRight.setBorder(BorderFactory.createTitledBorder("Bảng Thông tin"));
		pRight.setLayout(new BoxLayout(pRight, BoxLayout.X_AXIS));

		JPanel pLable = new JPanel();
		pLable.add(lblCTNP = new JLabel(""));
		pLable.add(Box.createVerticalStrut(10));
		pLable.add(lblMaNhanPhong = new JLabel("Mã nhận phòng:"));
		pLable.add(Box.createVerticalStrut(10));
		pLable.add(lblMaDangKy = new JLabel("Mã đăng ký:"));
		pLable.add(Box.createVerticalStrut(10));
		pLable.add(lblNgayNhan = new JLabel("Ngày nhận:"));
		pLable.add(Box.createVerticalStrut(10));
		pLable.add(lblGioNhan = new JLabel("Giờ nhận:"));
		pLable.add(Box.createVerticalStrut(10));
		pLable.setLayout(new BoxLayout(pLable, BoxLayout.Y_AXIS));
		pLable.add(lblCTNP = new JLabel(""));
		pLable.add(Box.createVerticalStrut(10));
		pLable.add(Box.createVerticalGlue());
		
		JPanel pTextfield = new JPanel();
		pTextfield.add(lblCTNP = new JLabel(""));
		pTextfield.add(Box.createVerticalStrut(5));
		pTextfield.add(txtMaNhanPhong = new JTextField(20));
		pTextfield.add(Box.createVerticalStrut(5));
		pTextfield.add(txtMaDangKy= new JTextField(20));
		pTextfield.add(Box.createVerticalStrut(5));
		pTextfield.add(txtNgayNhan = new JTextField(20));
		pTextfield.add(Box.createVerticalStrut(5));
		pTextfield.add(txtGioNhan = new JTextField(20));
		pTextfield.add(Box.createVerticalStrut(5));
		txtMaNhanPhong.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtMaNhanPhong.getPreferredSize().height));
		txtMaDangKy.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtMaDangKy.getPreferredSize().height));
		txtNgayNhan.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtNgayNhan.getPreferredSize().height));
		txtGioNhan.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtGioNhan.getPreferredSize().height));
		pTextfield.setLayout(new BoxLayout(pTextfield, BoxLayout.Y_AXIS));
		pTextfield.add(btnCTNP = new JButton("Chi tiết nhận phòng"));
		pTextfield.add(Box.createVerticalGlue());
		btnCTNP.addActionListener(this);
		pRight.add(pLable);
		pRight.add(Box.createHorizontalStrut(10));
		pRight.add(pTextfield);
		pRight.add(Box.createHorizontalStrut(10));
		add(pRight, BorderLayout.EAST);
		
		Database.getInstance().connect();
		updateTableDate();
		
		setSize(950, 700);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		txtMaNhanPhong.setEditable(false);
}
	
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnThem)) {
			ThongTinNhan s = getTTNPFromTextField();
			if (s == null) {
				return;
			}
			if(dsTTNP.create(s)) {
				xoaRongTextfields();
				txtMaNhanPhong.setEditable(true);
				String[] row = {maNhanPhong, maDangKy, ngayNhan, gioNhan};
				model.addRow(row);
			} else {
				JOptionPane.showMessageDialog(this, "Mã không được trùng!");
			}
		}
		else if (o.equals(btnXoaRong)) {
			txtMaNhanPhong.setEditable(true);
			txtMaNhanPhong.setText("");
			txtMaDangKy.setText("");
			txtNgayNhan.setText("");
			txtGioNhan.setText("");
		}
		else if (o.equals(btnSua)) {
			int rowIndex = table.getSelectedRow();
			if(rowIndex >=0) {
				String maTTNPOld = txtMaNhanPhong.getText().trim();
				ThongTinNhan newTTNP = getTTNPFromTextField();
				if (dsTTNP.update(newTTNP)) {
					model.setValueAt(newTTNP.getNgayNhan(), rowIndex, 2);
					model.setValueAt(newTTNP.getGioNhan(), rowIndex, 3);

					JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
				} else {
					JOptionPane.showMessageDialog(this, "Dữ liệu chưa nhập hoặc không đúng");
				}
			}else {
				JOptionPane.showMessageDialog(this, "Chọn dòng cần cập nhật!");
			}
			
		}
		else if (o.equals(btnXoa)) {
			int row = table.getSelectedRow();
			if(row >= 0 ) {
				String maNhanPhong = (String) table.getValueAt(row, 0);
				int w = JOptionPane.showConfirmDialog(this, "Chắc chắn xóa không? ", "Chú ý", JOptionPane.YES_NO_OPTION);
				if (w == JOptionPane.YES_OPTION) {
				if(dsTTNP.delete(maNhanPhong)) {
					model.removeRow(row);
					xoaRongTextfields();
			}
				} else {
					JOptionPane.showMessageDialog(this, "Chọn dòng cần xoá!");
				}
			} 
		}
		else if (o.equals(btnCTNP)) {
			FrmChiTietNhanPhong frmChiTietNhanPhong = new FrmChiTietNhanPhong();
			frmChiTietNhanPhong.setVisible(true);
		}
		else if (o.equals(btnTim)) {
			String maCanTim = txtTim.getText().trim().toUpperCase();
			if (maCanTim.equals("")) {
				JOptionPane.showMessageDialog(this, "Hãy nhập mã cần tìm!");
			} else {
				for (int i = 0; i < model.getRowCount(); i++) {
					if (model.getValueAt(i, 0).equals(maCanTim)) {
						table.setRowSelectionInterval(i, i);
						table.scrollRectToVisible(table.getCellRect(i, 0, true));
						return;
					}
				}
				JOptionPane.showMessageDialog(this, "Không tìm thấy!");
			}
		}
	}
	private void xoaRongTextfields() {
		txtMaNhanPhong.setText("");
		txtMaDangKy.setText("");
		txtNgayNhan.setText("");
		txtGioNhan.setText("");
		txtMaNhanPhong.requestFocus();
	}
	private void updateTableDate() {
		ArrayList<ThongTinNhan> list = dsTTNP.docTuBangPhong();
		for(ThongTinNhan p : list) {
			String[] rowData = {p.getMaNhanPhong(), p.getMaDangKy(), p.getNgayNhan(), p.getGioNhan() + ""};
			model.addRow(rowData);
			}
		table.setModel(model);
		}
	private ThongTinNhan getTTNPFromTextField() {
		
		maNhanPhong = txtMaNhanPhong.getText().trim();
		maDangKy = txtMaDangKy.getText().trim();
		ngayNhan = txtNgayNhan.getText().trim();
		gioNhan = txtGioNhan.getText().trim();
		
		if (!maNhanPhong.matches("^NP[0-9]+$")) {
			JOptionPane.showMessageDialog(this, "Mã nhận phòng phải bắt đầu bằng NP và theo sau là số");
			txtMaNhanPhong.selectAll();
			txtMaNhanPhong.requestFocus();
			return null;
		}
		if (!maDangKy.matches("^DK[0-9]+$")) {
			JOptionPane.showMessageDialog(this, "Mã đăng ký phải bắt đầu bằng DK và theo sau là số");
			txtMaDangKy.selectAll();
			txtMaDangKy.requestFocus();
			return null;
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		df.setLenient(false);
		try {
			df.parse(ngayNhan);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Ngày nhận không đúng định dạng!");
			txtNgayNhan.selectAll();
			txtNgayNhan.requestFocus();
			return null;
		}
		return new ThongTinNhan(maNhanPhong, maDangKy, ngayNhan, gioNhan);
}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		txtMaNhanPhong.setText(table.getValueAt(row, 0).toString());
		txtMaDangKy.setText(table.getValueAt(row, 1).toString());
		txtNgayNhan.setText(table.getValueAt(row, 2).toString());
		txtGioNhan.setText(table.getValueAt(row, 3).toString());
		
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
}
