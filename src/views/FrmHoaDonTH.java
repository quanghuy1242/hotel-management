package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controllers.DanhSachChiTietHoaDon;
import controllers.DanhSachHD;
import controllers.QuanKyKhachHang;
import controllers.QuanLyDichVu;
import controllers.QuanLyNhanVien;
import controllers.QuanLyPhong;
import controllers.QuanLyThongTinChiTietNhan;
import controllers.QuanLyThongTinDangKy;
import controllers.QuanLyThongTinNhanPhong;
import controllers.QuanLyThongTinTraPhong;
import database.Database;
import models.ChiTietHoaDon;
import models.HoaDon;
import models.KhachHang;
import models.NhanVien;
import models.ThongTinChiTietNhan;
import models.ThongTinDangKy;
import models.ThongTinNhan;
import models.ThongTinTra;

public class FrmHoaDonTH extends JFrame implements ActionListener, MouseListener, ListSelectionListener {
	JLabel lblTitle;
	JTextField txtMaHoaDon, txtMaNhanVien, txtMaTraPhong, txtNgayLapHoaDon, txtTongHoaDon, txtMaHoaDonCT, txtHDXoaRong,
			txtMaDichVu, txtSoLuong, txtTongTien;
	JTable tableHoaDon, tableChiTietHoaDon;
	DefaultTableModel modelHoaDon, modelChiTietHoaDon;
	JButton btnHDThem, btnHDXoa, btnHDSua, btnHDLuu, btnCTHDThem, btnCTHDXoa, btnCTHDSua, btnCTHDLuu, btnHDXoaRong, btnCTHDXoaRong, btnXuatHoaDon;
	DanhSachHD dsHoaDon = new DanhSachHD();
	DanhSachChiTietHoaDon dscthd = new DanhSachChiTietHoaDon();
	String maHoaDon, maNhanVien, maTraPhong, ngayLapHoaDon;
	double tongHoaDon;

	public FrmHoaDonTH() {
		super("Quản lý Hoá Đơn");

		JPanel pTop = new JPanel();
		pTop.add(lblTitle = new JLabel("QUẢN LÝ HOÁ ĐƠN"));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 25));
		add(pTop, BorderLayout.NORTH);

		JSplitPane splitPane = new JSplitPane();
		add(splitPane, BorderLayout.CENTER);

		JPanel pHoaDon = new JPanel();
		JPanel pChiTietHoaDon = new JPanel();
		pHoaDon.setLayout(new BoxLayout(pHoaDon, BoxLayout.Y_AXIS));
		pChiTietHoaDon.setLayout(new BoxLayout(pChiTietHoaDon, BoxLayout.Y_AXIS));
		splitPane.setLeftComponent(pHoaDon);
		splitPane.setRightComponent(pChiTietHoaDon);
		splitPane.setDividerLocation(500 + splitPane.getInsets().left);
//		splitPane.setDividerLocation(splitPane.getSize().width - splitPane.getInsets().right - splitPane.getDividerSize() - 400);

//		Hoá đơn

		JPanel pHoaDonForm = new JPanel();
		pHoaDonForm.setLayout(new BoxLayout(pHoaDonForm, BoxLayout.X_AXIS));

		JPanel pHoaDonFormLabel = new JPanel();
		pHoaDonFormLabel.setLayout(new BoxLayout(pHoaDonFormLabel, BoxLayout.Y_AXIS));
		pHoaDonFormLabel.add(new JLabel("Mã Hoá Đơn: "));
		pHoaDonFormLabel.add(Box.createVerticalStrut(9));
		pHoaDonFormLabel.add(new JLabel("Mã Nhân Viên "));
		pHoaDonFormLabel.add(Box.createVerticalStrut(9));
		pHoaDonFormLabel.add(new JLabel("Mã Trả Phòng: "));
		pHoaDonFormLabel.add(Box.createVerticalStrut(9));
		pHoaDonFormLabel.add(new JLabel("Ngày Lập Hoá Đơn: "));
		pHoaDonFormLabel.add(Box.createVerticalStrut(9));
		pHoaDonFormLabel.add(new JLabel("Tổng Hoá Đơn: "));

		JPanel pHoaDonFormTextField = new JPanel();
		pHoaDonFormTextField.setLayout(new BoxLayout(pHoaDonFormTextField, BoxLayout.Y_AXIS));
		pHoaDonFormTextField.add(txtMaHoaDon = new JTextField(15));
		txtMaHoaDon.setEditable(false);
		pHoaDonFormTextField.add(Box.createVerticalStrut(5));
		pHoaDonFormTextField.add(txtMaNhanVien = new JTextField(15));
		txtMaNhanVien.setEditable(false);
		pHoaDonFormTextField.add(Box.createVerticalStrut(5));
		pHoaDonFormTextField.add(txtMaTraPhong = new JTextField(15));
		txtMaTraPhong.setEditable(false);
		pHoaDonFormTextField.add(Box.createVerticalStrut(5));
		pHoaDonFormTextField.add(txtNgayLapHoaDon = new JTextField(15));
		pHoaDonFormTextField.add(Box.createVerticalStrut(5));
		pHoaDonFormTextField.add(txtTongHoaDon = new JTextField(15));
		txtTongHoaDon.setEditable(false);
		pHoaDonFormTextField.add(Box.createVerticalStrut(5));

		pHoaDonForm.add(Box.createHorizontalStrut(10));
		pHoaDonForm.add(pHoaDonFormLabel);
		pHoaDonForm.add(Box.createHorizontalStrut(10));
		pHoaDonForm.add(pHoaDonFormTextField);
		pHoaDonForm.add(Box.createHorizontalStrut(10));

		JPanel pHoaDonAction = new JPanel();
		pHoaDonAction.add(btnHDThem = new JButton("Thêm"));
		pHoaDonAction.add(btnHDXoa = new JButton("Xoá"));
		pHoaDonAction.add(btnHDXoaRong = new JButton("Xoá Rỗng"));
		pHoaDonAction.add(btnHDSua = new JButton("Sửa"));
		pHoaDonAction.add(btnHDLuu = new JButton("Lưu"));
		pHoaDonAction.add(btnXuatHoaDon = new JButton("Xuất HD"));

		btnHDThem.addActionListener(this);
		btnHDXoa.addActionListener(this);
		btnHDSua.addActionListener(this);
		btnHDLuu.addActionListener(this);
		btnHDXoaRong.addActionListener(this);
		btnXuatHoaDon.addActionListener(this);
		JPanel pHoaDonTable = new JPanel();
		modelHoaDon = new DefaultTableModel("Mã Hoá Đơn,Mã Nhân Viên,Mã Trả Phòng,Ngày Lập,Tổng Tiền".split(","), 0);
		tableHoaDon = new JTable(modelHoaDon);
		tableHoaDon.addMouseListener(this);

		JScrollPane paneHoaDon = new JScrollPane(tableHoaDon);
		pHoaDonTable.add(paneHoaDon);

		pHoaDon.add(pHoaDonForm);
		pHoaDon.add(pHoaDonAction);
		pHoaDon.add(pHoaDonTable);

//		Chi tiết hoá đơn

		JPanel pCTHoaDonForm = new JPanel();
		pCTHoaDonForm.setLayout(new BoxLayout(pCTHoaDonForm, BoxLayout.X_AXIS));

		JPanel pCTHoaDonFormLabel = new JPanel();
		pCTHoaDonFormLabel.setLayout(new BoxLayout(pCTHoaDonFormLabel, BoxLayout.Y_AXIS));
		pCTHoaDonFormLabel.add(new JLabel("Mã Hoá Đơn: "));
		pCTHoaDonFormLabel.add(Box.createVerticalStrut(9));
		pCTHoaDonFormLabel.add(new JLabel("Mã Dịch Vụ: "));
		pCTHoaDonFormLabel.add(Box.createVerticalStrut(9));
		pCTHoaDonFormLabel.add(new JLabel("Số Lượng: "));
		pCTHoaDonFormLabel.add(Box.createVerticalStrut(9));
		pCTHoaDonFormLabel.add(new JLabel("Tiền: "));
		pCTHoaDonFormLabel.add(Box.createVerticalStrut(25));

		JPanel pCTHoaDonFormTextField = new JPanel();
		pCTHoaDonFormTextField.setLayout(new BoxLayout(pCTHoaDonFormTextField, BoxLayout.Y_AXIS));
		pCTHoaDonFormTextField.add(txtMaHoaDonCT = new JTextField(15));
		txtMaHoaDonCT.setEditable(false);
		pCTHoaDonFormTextField.add(Box.createVerticalStrut(5));
		pCTHoaDonFormTextField.add(txtMaDichVu = new JTextField(15));
		txtMaDichVu.setEditable(false);
		pCTHoaDonFormTextField.add(Box.createVerticalStrut(5));
		pCTHoaDonFormTextField.add(txtSoLuong = new JTextField(15));
		pCTHoaDonFormTextField.add(Box.createVerticalStrut(5));
		pCTHoaDonFormTextField.add(txtTongTien = new JTextField(15));
		pCTHoaDonFormTextField.add(Box.createVerticalStrut(25));
		txtTongTien.setEditable(false);

		pCTHoaDonForm.add(Box.createHorizontalStrut(10));
		pCTHoaDonForm.add(pCTHoaDonFormLabel);
		pCTHoaDonForm.add(Box.createHorizontalStrut(10));
		pCTHoaDonForm.add(pCTHoaDonFormTextField);
		pCTHoaDonForm.add(Box.createHorizontalStrut(10));

		JPanel pCTHoaDonAction = new JPanel();
		pCTHoaDonAction.add(btnCTHDThem = new JButton("Thêm"));
		pCTHoaDonAction.add(btnCTHDXoa = new JButton("Xoá"));
		pCTHoaDonAction.add(btnCTHDXoaRong = new JButton("Xoá Rỗng"));
		pCTHoaDonAction.add(btnCTHDSua = new JButton("Sửa"));
		pCTHoaDonAction.add(btnCTHDLuu = new JButton("Lưu"));
		btnCTHDThem.addActionListener(this);
		btnCTHDXoaRong.addActionListener(this);
		btnCTHDXoa.addActionListener(this);
		btnCTHDSua.addActionListener(this);
		btnCTHDLuu.addActionListener(this);

		JPanel pCTHoaDonTable = new JPanel();
		modelChiTietHoaDon = new DefaultTableModel("Mã Hoá Đơn,Mã Dịch Vụ,Số Lượng,Tiền".split(","), 0);
		tableChiTietHoaDon = new JTable(modelChiTietHoaDon);
		tableChiTietHoaDon.addMouseListener(this);

		JScrollPane paneCTHoaDon = new JScrollPane(tableChiTietHoaDon);
		pCTHoaDonTable.add(paneCTHoaDon);

		pChiTietHoaDon.add(pCTHoaDonForm);
		pChiTietHoaDon.add(pCTHoaDonAction);
		pChiTietHoaDon.add(pCTHoaDonTable);

//		Chỉnh height:
		txtMaHoaDon.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtMaHoaDon.getPreferredSize().height));
		txtMaNhanVien.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtMaNhanVien.getPreferredSize().height));
		txtMaTraPhong.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtMaTraPhong.getPreferredSize().height));
		txtNgayLapHoaDon.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtNgayLapHoaDon.getPreferredSize().height));
		txtTongHoaDon.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtTongHoaDon.getPreferredSize().height));
		txtMaHoaDonCT.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtMaHoaDonCT.getPreferredSize().height));
		txtMaDichVu.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtMaDichVu.getPreferredSize().height));
		txtSoLuong.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtSoLuong.getPreferredSize().height));
		txtTongTien.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtTongTien.getPreferredSize().height));

		Database.getInstance().connect();
		updateTableDate();
//		updateTableDate1();
		setSize(1000, 700);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub

	}
	
	private void updateHoaDon() {
		int rowIndex = tableHoaDon.getSelectedRow();
		if (rowIndex >= 0) {
			HoaDon newHoaDon = getHoaDonFromTextField();
			if (dsHoaDon.update(newHoaDon)) {
				modelHoaDon.setValueAt(newHoaDon.getMaHoaDon(), rowIndex, 0);
				modelHoaDon.setValueAt(newHoaDon.getNgayLapHoaDon(), rowIndex, 3);
				modelHoaDon.setValueAt(newHoaDon.getTongHoaDon(), rowIndex, 4);

				JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
			} else {
				JOptionPane.showMessageDialog(this, "Dữ liệu chưa nhập hoặc không đúng");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Chọn dòng cần cập nhật!");
		}
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnHDThem)) {
			HoaDon s = getHoaDonFromTextField();
			if (s == null) {
				return;
			}
			if (dsHoaDon.create(s)) {
				txtMaHoaDon.setEditable(true);
				txtMaTraPhong.setEditable(true);
//				txtTongHoaDon.setEditable(true);
				xoaRongTextfields();
				txtMaHoaDon.setEditable(true);
				String[] row = { maHoaDon, maNhanVien, maTraPhong, ngayLapHoaDon, Double.toString(tongHoaDon) };
				modelHoaDon.addRow(row);
			} else {
				JOptionPane.showMessageDialog(this, "Mã không được trùng!");
			}
		} else if (o.equals(btnHDXoaRong)) {
			txtMaHoaDon.setEditable(true);
			txtMaTraPhong.setEditable(true);
			txtTongHoaDon.setEditable(true);
			txtMaNhanVien.setText(QuanLyNhanVien.currentNhanVien.getMaNhanVien());
			
			txtMaHoaDon.setText("");
			txtMaTraPhong.setText("");
			txtNgayLapHoaDon.setText("");
			txtTongHoaDon.setText("");
		} else if (o.equals(btnHDSua)) {
			updateHoaDon();

		} else if (o.equals(btnHDXoa)) {
			int row = tableHoaDon.getSelectedRow();
			if (row >= 0) {
				String maHoaDon = (String) tableHoaDon.getValueAt(row, 0);
				int w = JOptionPane.showConfirmDialog(this, "Chắc chắn xóa không? ", "Chú ý",
						JOptionPane.YES_NO_OPTION);
				if (w == JOptionPane.YES_OPTION) {
					if (dsHoaDon.delete(maHoaDon)) {
						modelHoaDon.removeRow(row);
						xoaRongTextfields();
					} else {
						JOptionPane.showMessageDialog(this, "Hoá đơn đang chứa các chi tiết và không thể xoá!");
					}
				} else {
					JOptionPane.showMessageDialog(this, "Chọn dòng cần xoá!");
				}
			}
		} else if (o.equals(btnHDLuu)) {
			HoaDon s = getHoaDonFromTextField();
			if (dsHoaDon.create(s)) {
				Object[] rowData = { txtMaHoaDon.getText(), txtMaNhanVien.getText(), txtMaTraPhong.getText(),
						txtNgayLapHoaDon.getText(), s.getTongHoaDon() };
				modelHoaDon.addRow(rowData);
			}
		}
		if (o.equals(btnCTHDLuu)) {

			ChiTietHoaDon cthd = getCTHDFromTextField();
			if (cthd == null) {
				return;
			}
			if (dscthd.add(cthd)) {
				modelChiTietHoaDon.addRow(cthd.toString().split(";"));
				tableChiTietHoaDon.setRowSelectionInterval(modelChiTietHoaDon.getRowCount() - 1,
						tableChiTietHoaDon.getRowCount() - 1);
				updateHoaDon();
				txtMaHoaDon.setEditable(false);
				txtMaDichVu.setEditable(false);

				JOptionPane.showMessageDialog(this, "Thêm thành công một dữ liệu");
			} else {
				JOptionPane.showMessageDialog(this, "Mã trùng hoặc mã hóa đơn chưa có trong bảng Thông Tin Đăng Ký!");

			}
		}

		if (o.equals(btnCTHDThem)) {

			xoaRong();
			txtMaHoaDonCT.setEditable(true);
			txtMaDichVu.setEditable(true);
//			txtTongTien.setEditable(true);

		}

		if (o.equals(btnCTHDXoa)) {
			int row1 = tableChiTietHoaDon.getSelectedRow();
			int hoiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc xóa không?", "chú ý",
					JOptionPane.YES_NO_OPTION);
			if (hoiNhac == JOptionPane.YES_OPTION) {
				if (row1 >= 0) {
					String maDangKy = (String) tableChiTietHoaDon.getValueAt(row1, 0);
					if (dscthd.delete(maDangKy)) {
						updateHoaDon();
						modelChiTietHoaDon.removeRow(row1);
						xoaRong();
					}
				}
			}
		}
		if(o.equals(btnCTHDXoaRong)) {
			xoaRong();
		}

		if (o.equals(btnCTHDSua)) {
			int rowIndex = tableChiTietHoaDon.getSelectedRow();
			if (rowIndex >= 0) {
				ChiTietHoaDon newcthd = getCTHDFromTextField();
				if (newcthd == null) {
					return;
				}
				if (dscthd.update(newcthd)) {
					tableChiTietHoaDon.setValueAt(Integer.toString(newcthd.getSoLuong()), rowIndex, 2);
					tableChiTietHoaDon.setValueAt(Double.toString(newcthd.getTongTien()), rowIndex, 3);
					txtMaHoaDonCT.setEditable(false);
					txtMaDichVu.setEditable(false);
					txtTongTien.setEditable(false);
					// Cập nhật bảng Hoá Đơn
					updateHoaDon();

					JOptionPane.showMessageDialog(this, "Thông tin chi tiết hóa đơn được cập nhật!");
				} else {
					JOptionPane.showMessageDialog(this,
							"Mã trùng hoặc mã hóa đơn chưa có trong bảng Chi Tiết Hóa Đơn!");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Chọn dòng cần sửa!");
			}
		} else if (o.equals(btnXuatHoaDon)) {
			String hoadon = "---------HOÁ ĐƠN KHÁCH SẠN SAO AROMA----------\n";
			if (tableHoaDon.getSelectedRow() >= 0) {
				HoaDon hd = getHoaDonFromTextField();
				ArrayList<ChiTietHoaDon> chiTietHoaDon = dscthd.get(hd.getMaHoaDon());
				NhanVien nhanVien = QuanLyNhanVien.get(hd.getMaNhanVien());
				ThongTinTra thongTinTra = QuanLyThongTinTraPhong.get(hd.getMaTraPhong());
				ThongTinNhan thongTinNhan = QuanLyThongTinNhanPhong.get(thongTinTra.getMaNhanPhong());
				ThongTinDangKy thongTinDangKy = QuanLyThongTinDangKy.get(thongTinNhan.getMaDangKy());
				KhachHang khachHang = QuanKyKhachHang.get(thongTinDangKy.getMaKhachHang());
				hoadon += "Hôm nay: " + (new SimpleDateFormat("dd/MM/yyyy").format(new Date())) + "\n";
				hoadon += "Nhân viên: " + nhanVien.getTenNhanVien() + "\n";
				hoadon += "----------------------------------------------\n";
				hoadon += "Khách hàng: " + khachHang.getHoTen() + "\n";
				hoadon += "Ngày nhận phòng: " + thongTinNhan.getNgayNhan() + "\n";
				hoadon += "Ngày trả phòng: " + thongTinTra.getMaNgayTra() + "\n";
				hoadon += "Số ngày ở: " + getSoNgayO(thongTinNhan.getNgayNhan(), thongTinTra.getMaNgayTra()) + "\n";
				hoadon += "--------------CHI TIẾT HOÁ ĐƠN----------------\n";
				for(ChiTietHoaDon cthd : chiTietHoaDon) {
					hoadon += "Tên: " + QuanLyDichVu.get(cthd.getMaDichVu()).getLoaiDV()+ " - SL: " 
							+ cthd.getSoLuong() + " - Tiền:" 
							+ cthd.getTongTien() + "\n";
				}
				hoadon += "----------------------------------------------\n";
				hoadon += "Tổng tiền: " + hd.getTongHoaDon() + "\n";
				hoadon += "-----------------HẸN GẶP LẠI------------------\n";
				FrmXuatHoaDon frmXuatHoaDon = new FrmXuatHoaDon(hoadon);
				frmXuatHoaDon.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn một hoá đơn!");
			}
		}
	}

	private void xoaRongTextfields() {
		txtMaHoaDon.setText("");
		txtMaNhanVien.setText(QuanLyNhanVien.currentNhanVien.getMaNhanVien());
		txtMaTraPhong.setText("");
		txtNgayLapHoaDon.setText("");
		txtTongHoaDon.requestFocus();
	}

	private void updateTableDate() {
		DanhSachHD ds = new DanhSachHD();
		ArrayList<HoaDon> list = ds.docTuBangPhong();
		for (HoaDon p : list) {
			String[] rowData = { p.getMaHoaDon(), p.getMaNhanVien(), p.getMaTraPhong(), p.getNgayLapHoaDon(),
					p.getTongHoaDon() + "" };
			modelHoaDon.addRow(rowData);
		}
		tableHoaDon.setModel(modelHoaDon);
	}

	private ChiTietHoaDon getCTHDFromTextField() {
		String maHoaDon = txtMaHoaDonCT.getText().trim();
		
		String maDichVu = txtMaDichVu.getText().trim();
		int soLuong = Integer.parseInt(txtSoLuong.getText().trim());
//		double tongTien = Double.parseDouble(txtTongTien.getText().trim());
		double tongTien = QuanLyDichVu.get(txtMaDichVu.getText().trim()).getDonGia() * soLuong;
		System.out.println(tongTien);
		
		if (!maHoaDon.matches("^HD\\d{1,3}")) {
			JOptionPane.showMessageDialog(this, "Mã hóa đơn không đúng định dạng!");
			txtMaHoaDonCT.selectAll();
			txtMaHoaDonCT.requestFocus();
			return null;
		}
		
		if (!maDichVu.matches("^DV\\d{1,3}")) {
			JOptionPane.showMessageDialog(this, "Mã dịch vụ không đúng định dạng!");
			txtMaDichVu.selectAll();
			txtMaDichVu.requestFocus();
			return null;
		}
	
	return new ChiTietHoaDon(maHoaDon, maDichVu, soLuong, tongTien);

	}

	private void xoaRong() {
		txtMaHoaDonCT.setText("");
		txtMaHoaDonCT.setEditable(true);
		txtMaDichVu.setText("");
		txtMaDichVu.setEditable(true);
		txtSoLuong.setText("");
		txtTongTien.setText("");
//		txtTongTien.setEditable(true);
		txtMaHoaDon.requestFocus();
	}

	private void removeAllRowFromTable(DefaultTableModel model) {
		while(model.getRowCount() > 0) {
			model.removeRow(0);
		}
	}
	
	private void updateTableDate1(String maHD) {
		removeAllRowFromTable(modelChiTietHoaDon);
		ArrayList<ChiTietHoaDon> list = dscthd.get(maHD);
		System.out.println(list);
		for (ChiTietHoaDon p : list) {
			String[] rowData = { p.getMaHoaDon(), p.getMaDichVu(), Integer.toString(p.getSoLuong()),
					Double.toString(p.getTongTien()) };
			modelChiTietHoaDon.addRow(rowData);
		}

	}
	


	private HoaDon getHoaDonFromTextField() {

		maHoaDon = txtMaHoaDon.getText().trim();
		maNhanVien = txtMaNhanVien.getText().trim();
		maTraPhong = txtMaTraPhong.getText().trim();
		ngayLapHoaDon = txtNgayLapHoaDon.getText().trim();
		tongHoaDon = getTongHoaDon(maHoaDon, maTraPhong);
		
		if (!maHoaDon.matches("^HD[0-9]+$")) {
			JOptionPane.showMessageDialog(this, "Mã hóa đơn phải bắt đầu bằng HD và theo sau là số");
			txtMaHoaDon.selectAll();
			txtMaHoaDon.requestFocus();
			return null;
		}
		if (!maNhanVien.matches("^NV\\d{1,3}+$")) {
			JOptionPane.showMessageDialog(this, "Mã nhân viên phải bắt đầu bằng NV và theo sau là số");
			txtMaNhanVien.selectAll();
			txtMaNhanVien.requestFocus();
			return null;
		}
		if (!maTraPhong.matches("^TP\\d{1,3}+$")) {
			JOptionPane.showMessageDialog(this, "Mã trả phòng phải bắt đầu bằng TP và theo sau là số");
			txtMaTraPhong.selectAll();
			txtMaTraPhong.requestFocus();
			return null;
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		df.setLenient(false);
		try {
			df.parse(ngayLapHoaDon);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Ngày trả phòng không đúng định dạng!");
			txtNgayLapHoaDon.selectAll();
			txtNgayLapHoaDon.requestFocus();
			return null;
		}
		return new HoaDon(maHoaDon, maNhanVien, maTraPhong, ngayLapHoaDon, tongHoaDon);
	}
	
	private double getTongHoaDon(String maHoaDon, String maTraPhong) {
		tongHoaDon = 0;
		ArrayList<ChiTietHoaDon> chiTietHoaDons = dscthd.get(maHoaDon);
		
		ThongTinTra thongTinTra = QuanLyThongTinTraPhong.get(maTraPhong);
		ThongTinNhan thongTinNhan = QuanLyThongTinNhanPhong.get(thongTinTra.getMaNhanPhong());
		
		long soNgayo = getSoNgayO(thongTinNhan.getNgayNhan(), thongTinTra.getMaNgayTra());
		
		ArrayList<ThongTinChiTietNhan> chiTietNhans = new ArrayList<ThongTinChiTietNhan>();
		chiTietNhans = QuanLyThongTinChiTietNhan.get(thongTinTra.getMaNhanPhong());
		
		for(ThongTinChiTietNhan chiTietNhan : chiTietNhans) {
			tongHoaDon += QuanLyPhong.get(chiTietNhan.getMaPhong()).getGiaPhong() * soNgayo;
		}
		
		for (ChiTietHoaDon orderDetail : chiTietHoaDons) {
			tongHoaDon += orderDetail.getTongTien();
		}
		return tongHoaDon;
	}
	
	public long getSoNgayO(String ngayNhanstr, String ngayTrastr) {
		DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
		Date ngayTra = null, ngayNhan = null;
		try {
			ngayTra = df1.parse(ngayTrastr);
			ngayNhan = df2.parse(ngayNhanstr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return TimeUnit.DAYS.convert(ngayTra.getTime() - ngayNhan.getTime(), TimeUnit.MILLISECONDS);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(tableHoaDon)) {
			int row = tableHoaDon.getSelectedRow();
			txtMaHoaDon.setText(tableHoaDon.getValueAt(row, 0).toString());
			txtMaNhanVien.setText(tableHoaDon.getValueAt(row, 1).toString());
			txtMaTraPhong.setText(tableHoaDon.getValueAt(row, 2).toString());
			txtNgayLapHoaDon.setText(tableHoaDon.getValueAt(row, 3).toString());
			txtTongHoaDon.setText(tableHoaDon.getValueAt(row, 4).toString());
			updateTableDate1(tableHoaDon.getValueAt(row, 0).toString());
		} else if (e.getSource().equals(tableChiTietHoaDon)) {
			int row = tableChiTietHoaDon.getSelectedRow();
			txtMaHoaDonCT.setText(tableChiTietHoaDon.getValueAt(row, 0).toString());
			txtMaHoaDonCT.setEditable(false);
			txtMaDichVu.setText(tableChiTietHoaDon.getValueAt(row, 1).toString());
			txtMaDichVu.setEditable(false);
			txtSoLuong.setText(tableChiTietHoaDon.getValueAt(row, 2).toString());
			txtTongTien.setText(tableChiTietHoaDon.getValueAt(row, 3).toString());
			txtTongTien.setEditable(false);

		}
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
