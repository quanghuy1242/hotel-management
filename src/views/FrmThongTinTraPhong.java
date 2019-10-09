package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controllers.QuanLyPhong;
import controllers.QuanLyThongTinChiTietNhan;
import controllers.QuanLyThongTinNhanPhong;
import controllers.QuanLyThongTinTraPhong;
import models.KhachHang;
import models.ThongTinChiTietNhan;
import models.ThongTinTra;

public class FrmThongTinTraPhong extends JFrame implements ActionListener, MouseListener {
	JLabel lblTitle;
	JTable table;
	DefaultTableModel model;
	JButton btnAdd, btnTim, btnXoa, btnSua, btnXoaRong, btnHoaDon, btnLuu;
	JTextField txtMaTraPhong, txtMaNhanPhong, txtNgayTra, txtGioiTra, txtTim;
	
	QuanLyThongTinTraPhong quanLyThongTinTraPhong = new QuanLyThongTinTraPhong();
	
	public FrmThongTinTraPhong() {
		super("Thông tin trả phòng!");
		JPanel pTop = new JPanel();
		pTop.add(lblTitle = new JLabel("QUẢN LÝ THÔNG TIN TRẢ PHÒNG"));
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
//		pCenterAction.add(btnHoaDon = new JButton("Hoá đơn"));
		
		btnAdd.setIcon(new ImageIcon("icons/add.png"));
		btnTim.setIcon(new ImageIcon("icons/search.png"));
//		btnHoaDon.setIcon(new ImageIcon("icons/hoadon.png"));
		btnXoa.setIcon(new ImageIcon("icons/delete.png"));
		btnXoaRong.setIcon(new ImageIcon("icons/xoarong.png"));
		btnLuu.setIcon(new ImageIcon("icons/luu.png"));
//		btnSua.setIcon(new ImageIcon("icons/sua.png"));
		
		String[] cols = {"Mã Trả phòng", "Mã Nhận Phòng", "Ngày trả", "Giờ trả"};
		model = new DefaultTableModel(cols, 0);
		table = new JTable(model);
		JScrollPane pane = new JScrollPane(table);
		pane.setPreferredSize(new Dimension(755, 379));
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
		pLable.add(new JLabel("Mã Trả phòng:"));
		pLable.add(Box.createVerticalStrut(9));
		pLable.add(new JLabel("Mã Nhận Phòng:"));
		pLable.add(Box.createVerticalStrut(9));
		pLable.add(new JLabel("Ngày trả:"));
		pLable.add(Box.createVerticalStrut(9));
		pLable.add(new JLabel("Giờ trả:"));
		pLable.add(Box.createVerticalGlue());
		
		pTextfield.add(Box.createVerticalStrut(5));
		pTextfield.add(txtMaTraPhong = new JTextField(20));
		pTextfield.add(Box.createVerticalStrut(5));
		pTextfield.add(txtMaNhanPhong = new JTextField(10));
		pTextfield.add(Box.createVerticalStrut(5));
		pTextfield.add(txtNgayTra = new JTextField(10));
		pTextfield.add(Box.createVerticalStrut(5));
		pTextfield.add(txtGioiTra = new JTextField(10));
		pTextfield.add(Box.createVerticalGlue());
		
		txtMaTraPhong.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtMaTraPhong.getPreferredSize().height ));
		txtMaNhanPhong.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtMaNhanPhong.getPreferredSize().height ));
		txtNgayTra.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtNgayTra.getPreferredSize().height ));
		txtGioiTra.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtGioiTra.getPreferredSize().height ));
		
		add(pRight, BorderLayout.EAST);
		
//		Xu Kien
		btnAdd.addActionListener(this);
//		btnHoaDon.addActionListener(this);
		btnSua.addActionListener(this);
		btnLuu.addActionListener(this);
		btnTim.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaRong.addActionListener(this);
		table.addMouseListener(this);
		updateTableData();
		
		setSize(1120, 502);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
	
	private void updateTableData() {
		ArrayList<ThongTinTra> listTTT = quanLyThongTinTraPhong.readFromTable();
		for(ThongTinTra ttt : listTTT) {
			String[] rowData = ttt.toString().split(";");
			model.addRow(rowData);
		}
		table.setModel(model);
	}
	
	private void xoaRong() {
		txtMaTraPhong.setText("");
		txtMaNhanPhong.setText("");
		txtNgayTra.setText("");
		txtGioiTra.setText("");
		txtMaTraPhong.requestFocus();
	}
	
	private boolean validateData(ThongTinTra thongTinTra) {
		if (!thongTinTra.getMaTraPhong().matches("TP\\d{1,4}")) {
			JOptionPane.showMessageDialog(this, "Mã trả phòng không đúng định dạng!");
			txtMaTraPhong.selectAll();
			txtMaTraPhong.requestFocus();
			return false;
		}
		if (!thongTinTra.getMaNhanPhong().matches("NP\\d{1,4}")) {
			JOptionPane.showMessageDialog(this, "Mã nhận phòng không đúng định dạng!");
			txtMaNhanPhong.selectAll();
			txtMaNhanPhong.requestFocus();
			return false;
		}
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);
		try {
			df.parse(thongTinTra.getMaNgayTra());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Ngày trả trả phòng không đúng định dạng!");
			txtNgayTra.selectAll();
			txtNgayTra.requestFocus();
			return false;
		}
		if (!thongTinTra.getGioTra().matches("^(([01][0-9])|([2][0-3])):([0-5][0-9]):([0-5][0-9])$")) {
			JOptionPane.showMessageDialog(this, "Giờ trả phòng không đúng định dạng!");
			txtGioiTra.selectAll();
			txtGioiTra.requestFocus();
			return false;
		}
		return true;
	}
	
	private ThongTinTra getTTTFromForm() {
		String maTra = txtMaTraPhong.getText();
		String maNhan = txtMaNhanPhong.getText();
		String ngayTra = txtNgayTra.getText();
		String gioTra = txtGioiTra.getText();
		
		ThongTinTra thongTinTra = new ThongTinTra(maTra, maNhan, ngayTra, gioTra);
		
		if (!validateData(thongTinTra)) {
			return null;
		}
		return thongTinTra;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		txtMaTraPhong.setEditable(false);
		int row = table.getSelectedRow();
		txtMaTraPhong.setText(table.getValueAt(row, 0).toString());
		txtMaNhanPhong.setText(table.getValueAt(row, 1).toString());
		txtNgayTra.setText(table.getValueAt(row, 2).toString());
		txtGioiTra.setText(table.getValueAt(row, 3).toString());
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
			txtMaTraPhong.setEditable(true);
		}
		else if (o.equals(btnLuu)) {
			ThongTinTra thongTinTra = getTTTFromForm();
			if (thongTinTra == null) {
				return;
			}
			if (quanLyThongTinTraPhong.add(thongTinTra)) {
				model.addRow(thongTinTra.toString().split(";"));
				table.setRowSelectionInterval(model.getRowCount() - 1, model.getRowCount() - 1);
				txtMaTraPhong.setEditable(false);
				
				// CẬP NHẬT LẠI PHÒNG TRỐNG
				ArrayList<ThongTinChiTietNhan> chiTietNhans = QuanLyThongTinChiTietNhan.get(thongTinTra.getMaNhanPhong());
				for (ThongTinChiTietNhan chiTietNhan : chiTietNhans) {
					QuanLyPhong.toggleStatus(chiTietNhan.getMaPhong());
				}
				
				JOptionPane.showMessageDialog(this, "Thêm thành công một dữ liệu");
			} else {
				JOptionPane.showMessageDialog(this, "Mã trùng hoặc mã nhận phòng chưa có trong bảng Thông Tin Nhận phòng!");
			}
		}
		else if (o.equals(btnSua)) {
			int rowIndex = table.getSelectedRow();
			if (rowIndex >= 0) {
				ThongTinTra newthongTinTra = getTTTFromForm();
				if (newthongTinTra == null) {
					return;
				}
				if (quanLyThongTinTraPhong.update(newthongTinTra)) {
					table.setValueAt(newthongTinTra.getMaTraPhong(), rowIndex, 0);
					table.setValueAt(newthongTinTra.getMaNhanPhong(), rowIndex, 1);
					table.setValueAt(newthongTinTra.getMaNgayTra(), rowIndex, 2);
					table.setValueAt(newthongTinTra.getGioTra(), rowIndex, 3);
					txtMaTraPhong.setEditable(false);
					JOptionPane.showMessageDialog(this, "Thông tin trả phòng được cập nhật!");
				} else {
					JOptionPane.showMessageDialog(this, "Mã trùng hoặc mã nhận phòng chưa có trong bảng Thông Tin Nhận phòng!");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Chọn dòng cần sửa!");
			}
		}
		else if (o.equals(btnXoa)) {
			if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn xoá?", "Xác Nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				int rowIndex = table.getSelectedRow();
				if (rowIndex >= 0) {
					String ma = (String) table.getValueAt(rowIndex, 0);
					if (quanLyThongTinTraPhong.delete(ma)) {
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
						table.setRowSelectionInterval(i, i);
						table.scrollRectToVisible(table.getCellRect(i, 0, true));
						return;
					}
				}
				JOptionPane.showMessageDialog(this, "Không tìm thấy!");
			}
		}
	}

}
