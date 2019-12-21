package FormMain;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Data.Jdbc;
import FormBook.BookMonth;
import FormBook.BookSearch;
import FormLogin.Login;
import FormUser.UserMyBorrow;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class Main extends JFrame {

	private JPanel panel;
	
	public static int borrowCount;
	public static int allBorrow;

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 350, 150);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		try {
			Jdbc.query("select", "select * from borrow where member_number = " + Login.userNo);
			while(Jdbc.rs.next()) {
				borrowCount++;
			}
			Jdbc.query("select", "select * from member where number = " + Login.userNo);
			while(Jdbc.rs.next()) {
				allBorrow = Jdbc.rs.getInt("allBorrow");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JLabel lblRank = new JLabel("");
		if(allBorrow >= 1000) {
			lblRank.setIcon(new ImageIcon("src/images/rank/상단바랭크_s.png"));
		} else if(allBorrow >= 300) {
			lblRank.setIcon(new ImageIcon("src/images/rank/상단바랭크_a.png"));
		} else if(allBorrow >= 100) {
			lblRank.setIcon(new ImageIcon("src/images/rank/상단바랭크_b.png"));
		} else {
			lblRank.setIcon(new ImageIcon("src/images/rank/상단바랭크_c.png"));
		}
		lblRank.setHorizontalAlignment(SwingConstants.CENTER);
		lblRank.setBounds(8, 5, 23, 23);
		panel.add(lblRank);
		
		JLabel lblName = new JLabel(Login.userName);
		lblName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new UserMyBorrow();
			}
		});
		lblName.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(27, 5, 58, 20);
		panel.add(lblName);
		
		JLabel lblWelcome = new JLabel("님 환영합니다.");
		lblWelcome.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		lblWelcome.setBounds(81, 8, 94, 15);
		panel.add(lblWelcome);
		
		JLabel lblReturn = new JLabel("반납대기도서");
		lblReturn.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		lblReturn.setBounds(213, 8, 88, 15);
		panel.add(lblReturn);
		
		JLabel lblCount = new JLabel(borrowCount + "");
		lblCount.setForeground(Color.RED);
		lblCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblCount.setFont(new Font("나눔바른고딕", Font.BOLD, 18));
		lblCount.setBounds(298, 3, 25, 20);
		panel.add(lblCount);
		
		JButton btnMonth = new JButton("이달의도서");
		btnMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BookMonth();
			}
		});
		btnMonth.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnMonth.setBackground(Color.WHITE);
		btnMonth.setBounds(18, 40, 140, 50);
		panel.add(btnMonth);
		
		JButton btnSearch = new JButton("도서검색");
		btnSearch.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new BookSearch();
			}
		});
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setBounds(176, 40, 140, 50);
		panel.add(btnSearch);
		
		setVisible(true);
	}
}
