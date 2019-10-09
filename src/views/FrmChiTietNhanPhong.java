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
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controllers.QuanLyPhong;
import controllers.QuanLyThongTinChiTietNhan;
import database.Database;
import models.ThongTinChiTietNhan;

public class FrmChiTietNhanPhong extends JFrame implements ActionListener, MouseListener, ListSelectionListener {
	JTextField txtMaNhanPhong, txtMaDangKy, txtMaPhong, txtNgayNhan, txtGioNhan, txtTim;
	JLabel lblMaNhanPhong, lblMaDangKy, lblMaPhong, lblNgayNhan, lblGioNhan, lblTitle, lblCTNP;
	JButton btnTim, btnXoaRong, btnXoa, btnSua, btnThem;
	JTable table;
	DefaultTableModel model;
	
	QuanLyThongTinChiTietNhan dsCTNP = new QuanLyThongTinChiTietNhan();
	String maNhanPhong, maPhong;
	
	public FrmChiTietNhanPhong() {
		super("Chi tiết nhận phòng");
		
		JPanel pTop = new JPanel();
		pTop.add(lblTitle = new JLabel("Chi tiết nhận phòng"));
		lblTitle.setFont(new Font("Times new roman",Font.BOLD,30));
		add(pTop, BorderLayout.NORTH);
		//--------------------------------------------------------------------
		JPanel pCenter = new JPanel();
		pCenter.setLayout(new BoxLayout(pCenter, BoxLayout.Y_AXIS));
		
		JPanel pCenterAction = new JPanel();
		pCenterAction.add(txtTim= new JTextField(20)); 
		pCenterAction.add(btnTim= new JButton("Tìm", new ImageIcon("icons/search.png")));
		pCenterAction.add(btnThem= new JButton("Thêm", new ImageIcon("icons/add.png")));
		pCenterAction.add(btnXoa = new JButton("Xoá", new ImageIcon("icons/delete.png")));
		pCenterAction.add(btnXoaRong = new JButton("Xoá rỗng", new ImageIcon("icons/xoarong.png")));
		pCenterAction.add(btnSua = new JButton("Sửa", new ImageIcon("icons/sua.png")));
		btnThem.addActionListener(this);
		btnXoaRong.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnTim.addActionListener(this);
		
		JPanel pUI = new JPanel();
		pUI.setLayout(new BoxLayout(pUI, BoxLayout.X_AXIS));
		JPanel pLable = new JPanel();
		pLable.add(lblMaNhanPhong = new JLabel("Mã nhận phòng:"));
		pLable.add(Box.createVerticalStrut(10));
		pLable.add(lblMaPhong = new JLabel("Mã phòng:"));
		pLable.add(Box.createVerticalStrut(10));
		pLable.setLayout(new BoxLayout(pLable, BoxLayout.Y_AXIS));
		
		JPanel pTextfield = new JPanel();
		pTextfield.add(txtMaNhanPhong = new JTextField(10));
		pTextfield.add(Box.createVerticalStrut(5));
		pTextfield.add(txtMaPhong = new JTextField(10));
		pTextfield.add(Box.createVerticalStrut(5));
		pTextfield.setLayout(new BoxLayout(pTextfield, BoxLayout.Y_AXIS));
		txtMaNhanPhong.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtMaNhanPhong.getPreferredSize().height));
		txtMaPhong.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtMaPhong.getPreferredSize().height));
	
		pUI.add(Box.createHorizontalStrut(10));
		pUI.add(pLable);
		pUI.add(Box.createHorizontalStrut(10));
		pUI.add(pTextfield);
		pUI.add(Box.createHorizontalStrut(10));
	
		JPanel pCenterTable = new JPanel();
		String[] cols = {"Mã nhận phòng", "Mã phòng"};
		model = new DefaultTableModel(cols, 0);
		table = new JTable(model);
		table.addMouseListener(this);
		table.getSelectionModel().addListSelectionListener(this);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultEditor(Object.class, null);
		JScrollPane pane = new JScrollPane(table);
		pane.setPreferredSize(new Dimension(670, 350));
		pCenterTable.add(pane);
		
		pCenter.add(pUI);
		pCenter.add(pCenterAction);
	
		pCenter.add(pCenterTable);
		add(pCenter, BorderLayout.CENTER);
		
		//--------------------------------------------------------------------------
		
		Database.getInstance().connect();
		updateTableDate();
		
		setSize(700, 600);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		txtMaNhanPhong.setEditable(false);
	}	
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnThem)) {
			ThongTinChiTietNhan s = getCTNPFromTextField();
			if (s == null) {
				return;
			}
			if(dsCTNP.create(s)) {
				xoaRongTextfields();
				String[] row = {maNhanPhong, maPhong};
				if (!QuanLyPhong.get(maPhong).getTinhTrangPhong().equals("Trống")) {
					JOptionPane.showMessageDialog(this, "Phòng đã được sử dụng!");
					return;
				}
				model.addRow(row);
				QuanLyPhong.toggleStatus(maPhong);
			} else {
				JOptionPane.showMessageDialog(this, "Mã không được trùng!");
			}
		}
			else if (o.equals(btnXoaRong)) {
				txtMaNhanPhong.setEditable(true);
				txtMaNhanPhong.setText("");
				txtMaPhong.setText("");
			}
			else if (o.equals(btnSua)) {
				int rowIndex = table.getSelectedRow();
				if(rowIndex >=0) {

					String maCTNPOld = txtMaNhanPhong.getText().trim();
					ThongTinChiTietNhan newCTNP = getCTNPFromTextField();
					if (dsCTNP.update(newCTNP)) {
						model.setValueAt(newCTNP.getMaPhong(), rowIndex, 1);
						JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
					} else {
						JOptionPane.showMessageDialog(this, "Dữ liệu không đúng");
					}
				}else {
					JOptionPane.showMessageDialog(this, "Chọn dòng cần cập nhật!");
				}
				
			}
				else if (o.equals(btnXoa)) {
				int row = table.getSelectedRow();
				if(row >= 0 ) {
					int w = JOptionPane.showConfirmDialog(this, "Chắc chắn xóa không? ", "Chú ý", JOptionPane.YES_NO_OPTION);
					if (w == JOptionPane.YES_OPTION) {
					String maNhanPhong = (String) table.getValueAt(row, 0);
					if(dsCTNP.delete(maNhanPhong)) {
						model.removeRow(row);
						xoaRongTextfields();
				}
					} else {
						JOptionPane.showMessageDialog(this, "Chọn dòng cần xoá!");
					}
				} 
				}
	}

	private void xoaRongTextfields() {
		txtMaPhong.setText("");
		txtMaPhong.requestFocus();
	}
	private void updateTableDate() {
		ArrayList<ThongTinChiTietNhan> list = dsCTNP.docTuBangPhong();
		for(ThongTinChiTietNhan p : list) {
			String[] rowData = {p.getMaNhanPhong(), p.getMaPhong() + ""};
			model.addRow(rowData);
			}
		table.setModel(model);
		}
	private ThongTinChiTietNhan getCTNPFromTextField() {
		
		maNhanPhong = txtMaNhanPhong.getText().trim();
		maPhong = txtMaPhong.getText().trim();
	
		
		
		if (!maPhong.matches("^P[0-9]+$")) {
			JOptionPane.showMessageDialog(this, "Mã phòng phải bắt đầu bằng P, và sau là số");
			txtMaPhong.selectAll();
			txtMaPhong.requestFocus();
			return null;
		}

		if (!maNhanPhong.matches("^NP[0-9]+$")) {
			JOptionPane.showMessageDialog(this, "Mã nhận Phòng phải bắt đầu bằng NP, và sau là số");
			txtMaNhanPhong.selectAll();
			txtMaNhanPhong.requestFocus();
			return null;
		}
		return new ThongTinChiTietNhan(maNhanPhong, maPhong);
}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		txtMaNhanPhong.setText(table.getValueAt(row, 0).toString());
		txtMaPhong.setText(table.getValueAt(row, 1).toString());
		
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
