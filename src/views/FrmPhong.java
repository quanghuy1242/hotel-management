package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controllers.QuanLyPhong;
import database.Database;
import models.Phong;

public class FrmPhong extends JFrame implements ActionListener, MouseListener {
	JLabel lblTieuDe;
	private JTextField txtTim;
	private JButton btnTim;
	private JButton btnXoaRong;
	private JTable tablePhong;
	private JLabel lblMaPhong;
	private JLabel lblLoaiPhong;
	private JLabel lblTinhTrangPhong;
	private JLabel lblDonGia;
	private JTextField txtMaPhong;
	private JTextField txtLoaiPhong;
	private JTextField txtTinhTrangPhong;
	private JTextField txtDonGia;

	public static DefaultTableModel tableModel;
	QuanLyPhong dsp = new QuanLyPhong();
	public FrmPhong() {
		super("Quản lý Phòng");

		JPanel pTop = new JPanel();
		add(pTop, BorderLayout.NORTH);
		pTop.add(lblTieuDe = new JLabel("QUẢN LÝ PHÒNG"));
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 30));

		JPanel pCenter = new JPanel();
		pCenter.setLayout(new BoxLayout(pCenter, BoxLayout.Y_AXIS));

		JPanel pCenterAction = new JPanel();
		pCenterAction.add(txtTim = new JTextField(20));
		pCenterAction.add(btnTim = new JButton("Tìm", new ImageIcon("icons/search.png")));
		pCenterAction.add(btnXoaRong = new JButton("Xóa Rỗng", new ImageIcon("icons/xoarong.png")));
		JPanel pCenterTable = new JPanel();

		String header[] = "Mã Phòng;Loại Phòng;Tình Trạng Phòng;Đơn Giá".split(";");
		tableModel = new DefaultTableModel(header, 0);
		tablePhong = new JTable(tableModel);
		JScrollPane pane = new JScrollPane(tablePhong);
		pane.setPreferredSize(new Dimension(800, 400));
		pCenterTable.add(pane);

		pCenter.add(pCenterAction);
		pCenter.add(pCenterTable);

		add(pCenter, BorderLayout.CENTER);

		JPanel pRight = new JPanel();
		pRight.setLayout(new BoxLayout(pRight, BoxLayout.X_AXIS));

		JPanel pLable = new JPanel();
		pLable.add(Box.createVerticalStrut(31));		
		pLable.add(lblMaPhong = new JLabel("Mã Phòng:"));
		pLable.add(Box.createVerticalStrut(12));
		pLable.add(lblLoaiPhong = new JLabel("Loại Phòng:"));
		pLable.add(Box.createVerticalStrut(15));
		pLable.add(lblTinhTrangPhong = new JLabel("Tình Trạng Phòng:"));
		pLable.add(Box.createVerticalStrut(13));
		pLable.add(lblDonGia = new JLabel("Đơn Giá:"));
		pLable.add(Box.createVerticalGlue());
		pLable.setLayout(new BoxLayout(pLable, BoxLayout.Y_AXIS));

		JPanel pTextfield = new JPanel();
		pLable.add(Box.createVerticalStrut(10));
		
		pTextfield.add(Box.createVerticalStrut(30));
		pTextfield.add(txtMaPhong = new JTextField(20));	
		txtMaPhong.setEditable(false);
		pTextfield.add(Box.createVerticalStrut(10));
		pTextfield.add(txtLoaiPhong = new JTextField(20));
		
		pTextfield.add(Box.createVerticalStrut(10));
		pTextfield.add(txtTinhTrangPhong = new JTextField(20));

		pTextfield.add(Box.createVerticalStrut(10));
		pTextfield.add(txtDonGia = new JTextField(20));
	
		pTextfield.add(Box.createVerticalGlue());

		txtMaPhong.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtMaPhong.getPreferredSize().height));
		txtLoaiPhong.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtLoaiPhong.getPreferredSize().height));

		
		txtTinhTrangPhong.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtTinhTrangPhong.getPreferredSize().height));
		txtDonGia.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtDonGia.getPreferredSize().height));

		pTextfield.setLayout(new BoxLayout(pTextfield, BoxLayout.Y_AXIS));
		pRight.add(pLable);
		pRight.add(Box.createHorizontalStrut(10));
		pRight.add(pTextfield);
		pRight.add(Box.createHorizontalStrut(10));
		pRight.setBorder(BorderFactory.createTitledBorder("Bảng thông tin"));
		add(pRight, BorderLayout.EAST);
		btnTim.addActionListener(this);
		btnXoaRong.addActionListener(this);
		tablePhong.addMouseListener(this);
		Database.getInstance().connect();
		updateTableDate();
		setSize(1200, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
	
	private void updateTableDate() {
		ArrayList<Phong> list = dsp.docTuBangPhong();
		for(Phong p : list) {
			String[] rowData = {p.getMaPhong(), p.getLoaiPhong(), p.getTinhTrangPhong(), p.getGiaPhong() + ""};
			tableModel.addRow(rowData);
		}
		tablePhong.setModel(tableModel);
	}
	private void xoaRong() {
		txtMaPhong.setText("");
		txtLoaiPhong.setText("");
		txtTinhTrangPhong.setText("");
		txtDonGia.setText("");
		txtMaPhong.requestFocus();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnXoaRong)) {
			xoaRong();
		}
		if (o.equals(btnTim)) {
			String maCanTim = txtTim.getText().trim().toUpperCase();
			if (maCanTim.equals("")) {
				JOptionPane.showMessageDialog(this, "Hãy nhập mã cần tìm!");
			} else {
				for (int i = 0; i < tableModel.getRowCount(); i++) {
					if (tableModel.getValueAt(i, 0).equals(maCanTim)) {
						tablePhong.setRowSelectionInterval(i, i);
						tablePhong.scrollRectToVisible(tablePhong.getCellRect(i, 0, true));
						return;
					}
				}
				JOptionPane.showMessageDialog(this, "Không tìm thấy!");
			}
		
		}
		

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tablePhong.getSelectedRow();
		txtMaPhong.setText(tablePhong.getValueAt(row,0).toString());
		txtLoaiPhong.setText(tablePhong.getValueAt(row,1).toString());
		txtTinhTrangPhong.setText(tablePhong.getValueAt(row,2).toString());
		txtDonGia.setText(tablePhong.getValueAt(row,3).toString());
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
