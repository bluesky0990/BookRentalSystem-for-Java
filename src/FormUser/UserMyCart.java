package FormUser;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Data.Jdbc;
import FormBook.BookOpen;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSeparator;

public class UserMyCart extends JFrame {

	private JPanel panel;
	

	int bookNo[] = new int[5];
	String bookName[] = new String[5];
	int allBorrow;
	
	public static int tmp;
	
	public static int myBorrowCount = 0;

	/**
	 * Create the frame.
	 */
	public UserMyCart() {
		setBounds(100, 100, 600, 480);
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		try {
			Jdbc.query("select", "select * from cart where member_number = " + Login.userNo);
			
			int index = 0;
			
			while(Jdbc.rs.next()) {
				bookNo[index] = Jdbc.rs.getInt("book_number");
				bookName[index] = Jdbc.rs.getString("book_name");
				index++;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		JPanel panelLeft = new JPanel();
		panelLeft.setBackground(Color.DARK_GRAY);
		panelLeft.setBounds(0, 0, 140, 433);
		panel.add(panelLeft);
		panelLeft.setLayout(null);
		
		JButton btnBorrow = new JButton("도서관리");
		btnBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserMyBorrow();
				dispose();
			}
		});
		btnBorrow.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnBorrow.setForeground(Color.WHITE);
		btnBorrow.setBackground(Color.GRAY);
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
		btnCart.setEnabled(false);
		btnCart.setForeground(Color.WHITE);
		btnCart.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnCart.setBackground(Color.LIGHT_GRAY);
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
		
		JLabel lblCartList = new JLabel("현재 찜목록");
		lblCartList.setHorizontalAlignment(SwingConstants.CENTER);
		lblCartList.setFont(new Font("나눔바른고딕", Font.BOLD, 30));
		lblCartList.setBounds(280, 40, 170, 40);
		panel.add(lblCartList);
		
		JLabel lblCart1 = new JLabel(bookName[0]);
		lblCart1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(bookNo[0] != 0) {
					try {
						Jdbc.query("select", "select * from book where number = " + bookNo[0]);
						BookOpen.settingContent();
						new BookOpen();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		lblCart1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCart1.setFont(new Font("나눔바른고딕", Font.BOLD, 25));
		lblCart1.setBounds(170, 103, 330, 40);
		panel.add(lblCart1);
		
		JLabel lblCart2 = new JLabel(bookName[1]);
		lblCart2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(bookNo[1] != 0) {
					try {
						Jdbc.query("select", "select * from book where number = " + bookNo[1]);
						BookOpen.settingContent();
						new BookOpen();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		JButton btnReturn1 = new JButton("삭제");
		btnReturn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Jdbc.query("delete", "delete from cart where member_number = " + Login.userNo + " and book_number = " + bookNo[0]);
					dispose();
					new UserMyCart();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnReturn1.setBackground(Color.WHITE);
		btnReturn1.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnReturn1.setBounds(505, 103, 70, 40);
		panel.add(btnReturn1);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(170, 145, 405, 13);
		panel.add(separator);
		lblCart2.setHorizontalAlignment(SwingConstants.CENTER);
		lblCart2.setFont(new Font("나눔바른고딕", Font.BOLD, 25));
		lblCart2.setBounds(170, 155, 330, 40);
		panel.add(lblCart2);
		
		JLabel lblCart3 = new JLabel(bookName[2]);
		lblCart3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(bookNo[2] != 0) {
					try {
						Jdbc.query("select", "select * from book where number = " + bookNo[2]);
						BookOpen.settingContent();
						new BookOpen();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		JButton btnReturn2 = new JButton("삭제");
		btnReturn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Jdbc.query("delete", "delete from cart where member_number = " + Login.userNo + " and book_number = " + bookNo[1]);
					dispose();
					new UserMyCart();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnReturn2.setBackground(Color.WHITE);
		btnReturn2.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnReturn2.setBounds(505, 155, 70, 40);
		panel.add(btnReturn2);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.LIGHT_GRAY);
		separator_1.setBounds(170, 197, 405, 13);
		panel.add(separator_1);
		lblCart3.setHorizontalAlignment(SwingConstants.CENTER);
		lblCart3.setFont(new Font("나눔바른고딕", Font.BOLD, 25));
		lblCart3.setBounds(170, 207, 330, 40);
		panel.add(lblCart3);
		
		JLabel lblCart4 = new JLabel(bookName[3]);
		lblCart4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(bookNo[3] != 0) {
					try {
						Jdbc.query("select", "select * from book where number = " + bookNo[3]);
						BookOpen.settingContent();
						new BookOpen();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		JButton btnReturn3 = new JButton("삭제");
		btnReturn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Jdbc.query("delete", "delete from cart where member_number = " + Login.userNo + " and book_number = " + bookNo[2]);
					dispose();
					new UserMyCart();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		btnReturn3.setBackground(Color.WHITE);
		btnReturn3.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnReturn3.setBounds(505, 207, 70, 40);
		panel.add(btnReturn3);
		lblCart4.setHorizontalAlignment(SwingConstants.CENTER);
		lblCart4.setFont(new Font("나눔바른고딕", Font.BOLD, 25));
		lblCart4.setBounds(170, 259, 330, 40);
		panel.add(lblCart4);
		
		JLabel lblCart5 = new JLabel(bookName[4]);
		lblCart5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(bookNo[4] != 0) {
					try {
						Jdbc.query("select", "select * from book where number = " + bookNo[4]);
						BookOpen.settingContent();
						new BookOpen();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		JButton btnReturn4 = new JButton("삭제");
		btnReturn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Jdbc.query("delete", "delete from cart where member_number = " + Login.userNo + " and book_number = " + bookNo[3]);
					dispose();
					new UserMyCart();
				} catch (SQLException e3) {
					e3.printStackTrace();
				}
			}
		});
		btnReturn4.setBackground(Color.WHITE);
		btnReturn4.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnReturn4.setBounds(505, 259, 70, 40);
		panel.add(btnReturn4);
		lblCart5.setHorizontalAlignment(SwingConstants.CENTER);
		lblCart5.setFont(new Font("나눔바른고딕", Font.BOLD, 25));
		lblCart5.setBounds(170, 311, 330, 40);
		panel.add(lblCart5);
		
		JButton btnReturn5 = new JButton("삭제");
		btnReturn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Jdbc.query("delete", "delete from cart where member_number = " + Login.userNo + " and book_number = " + bookNo[4]);
					dispose();
					new UserMyCart();
				} catch (SQLException e4) {
					e4.printStackTrace();
				}
			}
		});
		btnReturn5.setBackground(Color.WHITE);
		btnReturn5.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnReturn5.setBounds(505, 311, 70, 40);
		panel.add(btnReturn5);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.LIGHT_GRAY);
		separator_2.setBounds(170, 249, 405, 13);
		panel.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.LIGHT_GRAY);
		separator_3.setBounds(170, 301, 405, 13);
		panel.add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(Color.LIGHT_GRAY);
		separator_4.setBounds(170, 354, 405, 13);
		panel.add(separator_4);
		
		setVisible(true);
	}
}
