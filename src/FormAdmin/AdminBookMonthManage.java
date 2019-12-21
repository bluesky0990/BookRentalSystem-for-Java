package FormAdmin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Data.Jdbc;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminBookMonthManage extends JFrame {

	private JPanel panel;
	JComboBox comboxBookName;

	/**
	 * Create the frame.
	 */
	public AdminBookMonthManage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 240);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel lblNotice = new JLabel("이달의 도서 관리");
		lblNotice.setBounds(5, 5, 422, 35);
		lblNotice.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotice.setFont(new Font("나눔바른고딕", Font.PLAIN, 30));
		panel.add(lblNotice);
		
		JLabel lblName = new JLabel("제목");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("나눔바른고딕", Font.PLAIN, 25));
		lblName.setBounds(24, 65, 60, 30);
		panel.add(lblName);
		
		comboxBookName = new JComboBox();
		loadCombox();
		comboxBookName.setBackground(Color.WHITE);
		comboxBookName.setFont(new Font("나눔바른고딕", Font.PLAIN, 18));
		comboxBookName.setBounds(98, 63, 300, 35);
		panel.add(comboxBookName);
		
		JButton btnRemove = new JButton("삭제");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Jdbc.query("delete", "delete from month where book_name = '"+comboxBookName.getSelectedItem()+"'");
					loadCombox();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnRemove.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnRemove.setBackground(Color.WHITE);
		btnRemove.setBounds(98, 141, 110, 40);
		panel.add(btnRemove);
		
		JButton button_1 = new JButton("취소");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(218, 141, 110, 40);
		panel.add(button_1);
		
		setVisible(true);
	}
	
	void loadCombox() {
		try {
			comboxBookName.setModel(new DefaultComboBoxModel(new String[] {}));
			Jdbc.query("select", "select * from month");
			while(Jdbc.rs.next()) {
				comboxBookName.addItem(Jdbc.rs.getString("book_name"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
