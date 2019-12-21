package FormUser;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Data.Jdbc;
import FormLogin.Login;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UserMyBorrow extends JFrame {

	private JPanel panel;
	
	JLabel lblBorrow1;
	JLabel lblBorrow2;
	JLabel lblBorrow3;
	JLabel lblCurrentCount;
	JLabel lblAllCount;
	

	String bookName[] = new String[3];
	int bookNo[] = new int[3];
	int allBorrow;
	
	public static int tmp;
	
	public static int myBorrowCount = 0;

	/**
	 * Create the frame.
	 */
	public UserMyBorrow() {
		setBounds(100, 100, 600, 480);
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		loadMyBorrow();
		
		JPanel panelLeft = new JPanel();
		panelLeft.setBackground(Color.DARK_GRAY);
		panelLeft.setBounds(0, 0, 140, 433);
		panel.add(panelLeft);
		panelLeft.setLayout(null);
		
		JButton btnBorrow = new JButton("도서관리");
		btnBorrow.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnBorrow.setEnabled(false);
		btnBorrow.setForeground(Color.WHITE);
		btnBorrow.setBackground(Color.LIGHT_GRAY);
		btnBorrow.setBounds(0, 0, 140, 60);
		panelLeft.add(btnBorrow);
		
		JButton btnInfo = new JButton("정보관리");
		btnInfo.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new UserMyInformation();
				dispose();
			}
		});
		
		JButton btnCart = new JButton("찜관리");
		btnCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new UserMyCart();
				dispose();
			}
		});
		btnCart.setForeground(Color.WHITE);
		btnCart.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnCart.setBackground(Color.GRAY);
		btnCart.setBounds(0, 60, 140, 60);
		panelLeft.add(btnCart);
		btnInfo.setForeground(Color.WHITE);
		btnInfo.setBackground(Color.GRAY);
		btnInfo.setBounds(0, 120, 140, 60);
		panelLeft.add(btnInfo);
		
		JButton btnLogout = new JButton("로그아웃");
		btnLogout.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setBackground(Color.GRAY);
		btnLogout.setBounds(0, 373, 140, 60);
		panelLeft.add(btnLogout);
		
		JLabel lblCurrentBorrow = new JLabel("현재 대출현황");
		lblCurrentBorrow.setFont(new Font("나눔바른고딕", Font.BOLD, 30));
		lblCurrentBorrow.setBounds(200, 30, 170, 40);
		panel.add(lblCurrentBorrow);
		
		lblCurrentCount = new JLabel(myBorrowCount + "");
		lblCurrentCount.setForeground(Color.RED);
		lblCurrentCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentCount.setFont(new Font("나눔바른고딕", Font.BOLD, 30));
		lblCurrentCount.setBounds(384, 30, 50, 40);
		panel.add(lblCurrentCount);

		JLabel lblAllBorrow = new JLabel("누적 대출현황");
		lblAllBorrow.setFont(new Font("나눔바른고딕", Font.BOLD, 30));
		lblAllBorrow.setBounds(200, 296, 170, 40);
		panel.add(lblAllBorrow);
		
		lblAllCount = new JLabel(allBorrow + "");
		lblAllCount.setForeground(Color.RED);
		lblAllCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblAllCount.setFont(new Font("나눔바른고딕", Font.BOLD, 30));
		lblAllCount.setBounds(384, 296, 50, 40);
		panel.add(lblAllCount);
		
		lblBorrow1 = new JLabel(bookName[0]);
		lblBorrow1.setFont(new Font("나눔바른고딕", Font.BOLD, 24));
		lblBorrow1.setBounds(173, 82, 280, 40);
		panel.add(lblBorrow1);
		
		lblBorrow2 = new JLabel(bookName[1]);
		lblBorrow2.setFont(new Font("나눔바른고딕", Font.BOLD, 24));
		lblBorrow2.setBounds(173, 134, 280, 40);
		panel.add(lblBorrow2);
		
		lblBorrow3 = new JLabel(bookName[2]);
		lblBorrow3.setFont(new Font("나눔바른고딕", Font.BOLD, 24));
		lblBorrow3.setBounds(173, 186, 280, 40);
		panel.add(lblBorrow3);
		
		JButton btnReturn1 = new JButton("반납");
		btnReturn1.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnReturn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(bookName[0] != null) {
						Jdbc.query("delete", "delete from borrow where book_number = " + bookNo[0]);
						tmp = bookNo[0];
						reLoadMyBorrow();
						lblBorrow1.setText("");
						JOptionPane.showMessageDialog(null, "해당 도서 반납 완료!");
						new UserMyBorrowReturn();
					} else {
						JOptionPane.showMessageDialog(null, "대출중인 도서가 없습니다.", "", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnReturn1.setBackground(Color.WHITE);
		btnReturn1.setBounds(463, 82, 105, 40);
		panel.add(btnReturn1);
		
		JButton btnReturn2 = new JButton("반납");
		btnReturn2.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnReturn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(bookName[1] != null) {
						Jdbc.query("delete", "delete from borrow where book_number = " + bookNo[1]);
						tmp = bookNo[1];
						reLoadMyBorrow();
						lblBorrow2.setText("");
						JOptionPane.showMessageDialog(null, "해당 도서 반납 완료!");
						new UserMyBorrowReturn();
					} else {
						JOptionPane.showMessageDialog(null, "대출중인 도서가 없습니다.", "", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		btnReturn2.setBackground(Color.WHITE);
		btnReturn2.setBounds(463, 134, 105, 40);
		panel.add(btnReturn2);
		
		JButton btnReturn3 = new JButton("반납");
		btnReturn3.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnReturn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(bookName[2] != null) {
						Jdbc.query("delete", "delete from borrow where book_number = " + bookNo[2]);
						tmp = bookNo[2];
						reLoadMyBorrow();
						lblBorrow3.setText("");
						JOptionPane.showMessageDialog(null, "해당 도서 반납 완료!");
						new UserMyBorrowReturn();
					} else {
						JOptionPane.showMessageDialog(null, "대출중인 도서가 없습니다.", "", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		btnReturn3.setBackground(Color.WHITE);
		btnReturn3.setBounds(463, 186, 105, 40);
		panel.add(btnReturn3);
		
		setVisible(true);
	}
	
	void loadMyBorrow() {
		try {
			for(int i = 0; i < 3; i++) {
				bookName[i] = null;
				bookNo[i] = 0;
			}
			myBorrowCount = 0;
			Jdbc.query("select", "select * from borrow where member_number = " + Login.userNo);
			while(Jdbc.rs.next()) {
				bookName[myBorrowCount] = new String(Jdbc.rs.getString("book_name"));
				bookNo[myBorrowCount] = Jdbc.rs.getInt("book_number");
				myBorrowCount++;
			}
			
			Jdbc.query("select", "select * from member where number = " + Login.userNo);
			while(Jdbc.rs.next()) {
				allBorrow = Jdbc.rs.getInt("allBorrow");
			}
		} catch (SQLException e23) {
			e23.printStackTrace();
		}
	}
	
	void reLoadMyBorrow() {
		try {
			for(int i = 0; i < 3; i++) {
				bookName[i] = null;
				bookNo[i] = 0;
			}
			lblBorrow1.setText("");
			lblBorrow2.setText("");
			lblBorrow3.setText("");
			myBorrowCount = 0;
			Jdbc.query("select", "select * from borrow where member_number = " + Login.userNo);
			while(Jdbc.rs.next()) {
				bookName[myBorrowCount] = new String(Jdbc.rs.getString("book_name"));
				bookNo[myBorrowCount] = Jdbc.rs.getInt("book_number");
				myBorrowCount++;
			}
			
			switch (myBorrowCount) {
			case 0:
				lblBorrow1.setText(bookName[0]);
				lblBorrow2.setText("");
				lblBorrow3.setText("");
				break;
			case 1:
				lblBorrow1.setText(bookName[0]);
				lblBorrow2.setText(bookName[1]);
				lblBorrow3.setText("");
				break;
			case 2:
				lblBorrow1.setText(bookName[0]);
				lblBorrow2.setText(bookName[1]);
				lblBorrow3.setText(bookName[2]);
				break;
			}
			
			lblCurrentCount.setText(myBorrowCount + "");
			
			Jdbc.query("select", "select * from member where number = " + Login.userNo);
			while(Jdbc.rs.next()) {
				allBorrow = Jdbc.rs.getInt("allBorrow");
			}
			lblAllCount.setText(allBorrow + "");
			
		} catch (SQLException e23) {
			e23.printStackTrace();
		}
	}
}
