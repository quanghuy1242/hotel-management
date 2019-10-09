package views;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FrmXuatHoaDon extends JFrame {
	JTextArea txtHoaDon;
	public FrmXuatHoaDon(String hoaDonContent) {
		super("Hoá đơn khách sạn");
		txtHoaDon = new JTextArea(hoaDonContent, 50, 50);
		txtHoaDon.setFont(new Font("Consolas", Font.PLAIN, 15));
		add(new JScrollPane(txtHoaDon), BorderLayout.CENTER);
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
}
