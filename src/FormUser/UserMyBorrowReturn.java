package FormUser;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Data.Jdbc;
import FormAdmin.AdminBookManage;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UserMyBorrowReturn extends JFrame {

	private JPanel panel;

	/**
	 * Create the frame.
	 */
	public UserMyBorrowReturn() {
		setBounds(100, 100, 290, 240);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel lblNotice = new JLabel("감상에 대한 후기");
		lblNotice.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotice.setBounds(14, 12, 231, 57);
		lblNotice.setFont(new Font("나눔바른고딕", Font.BOLD, 30));
		panel.add(lblNotice);
		
		JLabel lblRating = new JLabel("점수");
		lblRating.setHorizontalAlignment(SwingConstants.CENTER);
		lblRating.setFont(new Font("나눔바른고딕", Font.BOLD, 25));
		lblRating.setBounds(42, 81, 121, 38);
		panel.add(lblRating);
		
		JComboBox comboxRating = new JComboBox();
		comboxRating.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		comboxRating.setModel(new DefaultComboBoxModel(new String[] {"5", "4", "3", "2", "1"}));
		comboxRating.setBounds(144, 85, 50, 30);
		panel.add(comboxRating);
		
		JPanel panelBottom = new JPanel();
		panelBottom.setLayout(null);
		panelBottom.setBounds(0, 144, 272, 49);
		panel.add(panelBottom);
		
		JButton btnChk = new JButton("확인");
		btnChk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float rating = 0;
				int ratingAll = 0;
				int ratingCount = 0;
				String tmp = null;
				try {
					Jdbc.query("select", "select * from book where number = " + UserMyBorrow.tmp);
					while(Jdbc.rs.next()) {
						rating = Jdbc.rs.getFloat("rating");
						ratingAll = Jdbc.rs.getInt("ratingAll");
						ratingCount = Jdbc.rs.getInt("ratingCount");
					}
					tmp = comboxRating.getSelectedItem() + "";
					ratingAll += Integer.parseInt(tmp);
					++ratingCount;
					rating = (float)ratingAll / (float)ratingCount;
					Jdbc.query("update", "update book set rating = '"+rating+"', ratingAll = '"+ratingAll+"', ratingCount = '"+ratingCount+"' where number = '"+UserMyBorrow.tmp+"'");
					dispose();
					JOptionPane.showMessageDialog(null, "별점 등록 완료!");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnChk.setFont(new Font("나눔바른고딕", Font.PLAIN, 18));
		btnChk.setBackground(Color.WHITE);
		btnChk.setBounds(0, 0, 135, 49);
		panelBottom.add(btnChk);
		
		JButton btnCancle = new JButton("취소");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancle.setFont(new Font("나눔바른고딕", Font.PLAIN, 18));
		btnCancle.setBackground(Color.WHITE);
		btnCancle.setBounds(136, 0, 135, 49);
		panelBottom.add(btnCancle);
		
		setVisible(true);
	}
}
