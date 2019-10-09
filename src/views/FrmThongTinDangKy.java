package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import controllers.QuanLyThongTinDangKy;
import database.Database;
import models.ThongTinDangKy;

public class FrmThongTinDangKy extends JFrame implements ActionListener, MouseListener {
	JLabel lblTieuDe;
	private JTextField txtTim;
	private JButton btnTim;
	private JButton btnXoaRong;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnLuu;
	private JButton btnNhanPhong;
	private JTable tableDKTP;
	private JLabel lblMaKhachHang;
	private JLabel lblMaDangKy;
	private JLabel lblSoLuongPhong;
	private JLabel lblNgayDen;
	private JLabel lblNgayDi;

	private JTextField txtMaKhachHang;
	private JTextField txtMaDangKy;
	private JTextField txtSoLuongPhong;
	private JTextField txtNgayDen;
	private JTextField txtNgayDi;

	public static DefaultTableModel tableModel;
	QuanLyThongTinDangKy dsdktp = new QuanLyThongTinDangKy();

	public FrmThongTinDangKy() {
		super("Quản lý Đăng ký thuê phòng");

		JPanel pTop = new JPanel();
		add(pTop, BorderLayout.NORTH);
		pTop.add(lblTieuDe = new JLabel("QUẢN LÝ THÔNG TIN ĐĂNG KÝ THUÊ PHÒNG"));
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 30));

		JPanel pCenter = new JPanel();
		pCenter.setLayout(new BoxLayout(pCenter, BoxLayout.Y_AXIS));

		JPanel pCenterAction = new JPanel();
		pCenterAction.add(txtTim = new JTextField(8));
		pCenterAction.add(btnTim = new JButton("Tìm", new ImageIcon("icons/search.png")));
		pCenterAction.add(btnThem = new JButton("Thêm", new ImageIcon("icons/add.png")));
		pCenterAction.add(btnXoa = new JButton("Xóa", new ImageIcon("icons/delete.png")));
		pCenterAction.add(btnSua = new JButton("Sửa", new ImageIcon("icons/sua.png")));
		pCenterAction.add(btnXoaRong = new JButton("Xóa Rỗng", new ImageIcon("icons/xoarong.png")));
		pCenterAction.add(btnLuu = new JButton("Lưu", new ImageIcon("icons/luu.png")));
//		pCenterAction.add(btnNhanPhong = new JButton("Nhận Phòng", new ImageIcon("icons/hoadon.png")));

		JPanel pCenterTable = new JPanel();

		String header[] = "Mã Đăng Ký;Mã Khách Hàng;Số Lượng Phòng;Ngày Đến; Ngày Đi".split(";");
		tableModel = new DefaultTableModel(header, 0);
		tableDKTP = new JTable(tableModel);
		JScrollPane pane = new JScrollPane(tableDKTP);
		pane.setPreferredSize(new Dimension(800, 400));
		pCenterTable.add(pane);

		pCenter.add(pCenterAction);
		pCenter.add(pCenterTable);

		add(pCenter, BorderLayout.CENTER);

		JPanel pRight = new JPanel();
		pRight.setLayout(new BoxLayout(pRight, BoxLayout.X_AXIS));

		JPanel pLable = new JPanel();

		pLable.add(Box.createVerticalStrut(31));
		pLable.add(lblMaDangKy = new JLabel("Mã Đăng Ký:"));
		pLable.add(Box.createVerticalStrut(12));
		pLable.add(lblMaKhachHang = new JLabel("Mã Khách Hàng:"));
		pLable.add(Box.createVerticalStrut(15));
		pLable.add(lblSoLuongPhong = new JLabel("Số Lượng:"));
		pLable.add(Box.createVerticalStrut(13));
		pLable.add(lblNgayDen = new JLabel("Ngày Đến:"));
		pLable.add(Box.createVerticalStrut(13));
		pLable.add(lblNgayDi = new JLabel("Ngày Đi:"));
		pLable.add(Box.createVerticalGlue());

		pLable.setLayout(new BoxLayout(pLable, BoxLayout.Y_AXIS));

		JPanel pTextfield = new JPanel();
		pLable.add(Box.createVerticalStrut(10));
		pTextfield.add(Box.createVerticalStrut(30));

		pTextfield.add(txtMaDangKy = new JTextField(20));
		txtMaDangKy.setEditable(false);
		pTextfield.add(Box.createVerticalStrut(10));
		pTextfield.add(txtMaKhachHang = new JTextField(20));
		txtMaKhachHang.setEditable(false);
		pTextfield.add(Box.createVerticalStrut(10));
		pTextfield.add(txtSoLuongPhong = new JTextField(20));

		pTextfield.add(Box.createVerticalStrut(10));
		pTextfield.add(txtNgayDen = new JTextField(20));
		pTextfield.add(Box.createVerticalStrut(10));
		pTextfield.add(txtNgayDi = new JTextField(20));
		pTextfield.add(Box.createVerticalGlue());

		txtMaDangKy.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtMaDangKy.getPreferredSize().height));
		txtMaKhachHang.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtMaKhachHang.getPreferredSize().height));
		txtSoLuongPhong.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtSoLuongPhong.getPreferredSize().height));
		txtNgayDen.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtNgayDen.getPreferredSize().height));
		txtNgayDi.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtNgayDi.getPreferredSize().height));

		pTextfield.setLayout(new BoxLayout(pTextfield, BoxLayout.Y_AXIS));
		pRight.add(pLable);
		pRight.add(Box.createHorizontalStrut(10));
		pRight.add(pTextfield);
		pRight.add(Box.createHorizontalStrut(10));
		pRight.setBorder(BorderFactory.createTitledBorder("Bảng thông tin"));

		add(pRight, BorderLayout.EAST);
		btnTim.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnLuu.addActionListener(this);
		btnXoaRong.addActionListener(this);
		tableDKTP.addMouseListener(this);
		Database.getInstance().connect();
		updateTableDate();
		setSize(1200, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	private void updateTableDate() {
		ArrayList<ThongTinDangKy> list = dsdktp.docTuBangDKTP();
		for (ThongTinDangKy p : list) {
			String[] rowData = { p.getMaDangKy(), p.getMaKhachHang(), p.getSoLuongPhong(), p.getNgayDen(),
					p.getNgayDi() + "" };
			tableModel.addRow(rowData);
		}
		tableDKTP.setModel(tableModel);
	}

	private void xoaRong() {
		txtMaDangKy.setText("");
		txtMaKhachHang.setText("");
		txtSoLuongPhong.setText("");
		txtNgayDen.setText("");
		txtNgayDi.setText("");
		txtMaDangKy.requestFocus();
	}
	
	private boolean validateData(ThongTinDangKy dktp) {
		if (!dktp.getMaDangKy().matches("DK\\d{1,4}")) {
			JOptionPane.showMessageDialog(this, "Mã trả phòng không đúng định dạng!");
			txtMaDangKy.selectAll();
			txtMaDangKy.requestFocus();
			return false;
		}
		if (!dktp.getMaKhachHang().matches("KH\\d{1,4}")) {
			JOptionPane.showMessageDialog(this, "Mã nhận phòng không đúng định dạng!");
			txtMaKhachHang.selectAll();
			txtMaKhachHang.requestFocus();
			return false;
		}
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);
		try {
			df.parse(dktp.getNgayDen());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Ngày đến không đúng định dạng!");
			txtNgayDen.selectAll();
			txtNgayDen.requestFocus();
			return false;
		}
		
		try {
			df.parse(dktp.getNgayDi());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Ngày đi không đúng định dạng!");
			txtNgayDi.selectAll();
			txtNgayDi.requestFocus();
			return false;
		}
		
		try {
			if(!(df.parse(dktp.getNgayDen()).compareTo(df.parse(dktp.getNgayDi())) < 0)) {	
				JOptionPane.showMessageDialog(this, "Ngày đến phải bé hơn ngày đi");
				return false;
			}
				
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Ngày đến phải bé hơn ngày đi");
			return false;
		}
		return true;
	}
	

	private ThongTinDangKy getTTNPFromTextField() {
		String maDangKy = txtMaDangKy.getText().trim();
		String maKhachHang = txtMaKhachHang.getText().trim();
		String soLuongPhong = txtSoLuongPhong.getText().trim();
	    String ngayDen = txtNgayDen.getText().trim();
		String ngayDi = txtNgayDi.getText().trim();
		ThongTinDangKy dktp = new ThongTinDangKy(maDangKy, maKhachHang, soLuongPhong, ngayDen, ngayDi);
		if (!validateData(dktp)) {
			return null;
		}
		return dktp;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnTim)) {
			String maCanTim = txtTim.getText().trim().toUpperCase();
			if (maCanTim.equals("")) {
				JOptionPane.showMessageDialog(this, "Hãy nhập mã cần tìm!");
			} else {
				for (int i = 0; i < tableModel.getRowCount(); i++) {
					if (tableModel.getValueAt(i, 0).equals(maCanTim)) {
						tableDKTP.setRowSelectionInterval(i, i);
						tableDKTP.scrollRectToVisible(tableDKTP.getCellRect(i, 0, true));
						return;
					}
				}
				JOptionPane.showMessageDialog(this, "Không tìm thấy!");
			}
		
		}
		
		if(o.equals(btnLuu)) {
			
			ThongTinDangKy dktp = getTTNPFromTextField();
			if (dktp == null) {
				return;
			}
			if (Integer.parseInt(dktp.getSoLuongPhong()) > QuanLyPhong.getEmptyRoom().size()) {
				JOptionPane.showMessageDialog(this, "Số Lượng phòng không đủ để đặt");
				return;
			}
			if (dsdktp.add(dktp)) {
				tableModel.addRow(dktp.toString().split(";"));
				tableDKTP.setRowSelectionInterval(tableModel.getRowCount() - 1, tableModel.getRowCount() - 1);
				txtMaDangKy.setEditable(false);
				txtMaKhachHang.setEditable(false);

				JOptionPane.showMessageDialog(this, "Thêm thành công một dữ liệu");
			} else {
				JOptionPane.showMessageDialog(this, "Mã trùng hoặc mã đăng ký chưa có trong bảng Thông Tin Đăng Ký!");
		
			}
		}
		
		if (o.equals(btnThem)) {
			
						
				xoaRong();
				txtMaDangKy.setEditable(true);
				txtMaKhachHang.setEditable(true);


				}
				
			
		
		if (o.equals(btnXoaRong)) {
			xoaRong();
		}

		if (o.equals(btnXoa)) {
			int row = tableDKTP.getSelectedRow();
			int hoiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc xóa không?", "chú ý",
					JOptionPane.YES_NO_OPTION);
			if (hoiNhac == JOptionPane.YES_OPTION) {
				if (row >= 0) {
					String maDangKy = (String) tableDKTP.getValueAt(row, 0);
					if (dsdktp.delete(maDangKy)) {
						tableModel.removeRow(row);
						xoaRong();
					} else {
						JOptionPane.showMessageDialog(this, "Thông tin đăng kí đã được nhận phòng và không thể xoá!");
					}
				}
			}
		}
		
		if(o.equals(btnSua)) {
			int rowIndex = tableDKTP.getSelectedRow();
			if (rowIndex >= 0) {
				ThongTinDangKy newdktp = getTTNPFromTextField();
				if (newdktp == null) {
					return;
				}
				if (dsdktp.update(newdktp)) {
					tableDKTP.setValueAt(newdktp.getSoLuongPhong(), rowIndex, 2);
					tableDKTP.setValueAt(newdktp.getNgayDen(), rowIndex, 3);
					tableDKTP.setValueAt(newdktp.getNgayDi(), rowIndex, 4);

					txtMaDangKy.setEditable(false);
					txtMaKhachHang.setEditable(false);

					JOptionPane.showMessageDialog(this, "Thông tin đăng ký được cập nhật!");
				} else {
					JOptionPane.showMessageDialog(this, "Mã trùng hoặc mã đăng ký chưa có trong bảng Thông Tin Đăng Ký!");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Chọn dòng cần sửa!");
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableDKTP.getSelectedRow();
		txtMaDangKy.setText(tableDKTP.getValueAt(row, 0).toString());
		txtMaDangKy.setEditable(false);
		txtMaKhachHang.setText(tableDKTP.getValueAt(row, 1).toString());
		txtMaKhachHang.setEditable(false);
		txtSoLuongPhong.setText(tableDKTP.getValueAt(row, 2).toString());
		txtNgayDen.setText(tableDKTP.getValueAt(row, 3).toString());
		txtNgayDi.setText(tableDKTP.getValueAt(row, 4).toString());

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
