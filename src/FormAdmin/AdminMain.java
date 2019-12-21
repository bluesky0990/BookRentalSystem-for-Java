package FormAdmin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Data.Jdbc;
import FormBook.BookSearch;
import FormLogin.Login;

public class AdminMain extends JFrame {

	private JPanel panel;
	
	public static int borrowCount;

	/**
	 * Create the frame.
	 */
	public AdminMain() {
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
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		JLabel lblRank = new JLabel();
		lblRank.setIcon(new ImageIcon("src/images/rank/상단바랭크_x.png"));
		lblRank.setHorizontalAlignment(SwingConstants.CENTER);
		lblRank.setBounds(8, 5, 23, 23);
		panel.add(lblRank);
		
		JLabel lblName = new JLabel(Login.userName);
		lblName.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(27, 7, 58, 20);
		panel.add(lblName);
		
		JLabel lblWelcome = new JLabel("님 환영합니다.");
		lblWelcome.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		lblWelcome.setBounds(81, 9, 94, 15);
		panel.add(lblWelcome);
		
		JLabel label = new JLabel("반납대기도서");
		label.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		label.setBounds(213, 9, 88, 15);
		panel.add(label);
		
		JLabel lblCount = new JLabel(borrowCount + "");
		lblCount.setForeground(Color.RED);
		lblCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblCount.setFont(new Font("나눔바른고딕", Font.BOLD, 18));
		lblCount.setBounds(298, 6, 25, 20);
		panel.add(lblCount);
		
		JButton btnBookManage = new JButton("도서관리");
		btnBookManage.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnBookManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminBookManage();
			}
		});
		btnBookManage.setBackground(Color.WHITE);
		btnBookManage.setBounds(18, 40, 140, 50);
		panel.add(btnBookManage);
		
		JButton btnMemManage = new JButton("회원관리");
		btnMemManage.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnMemManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AdminMemList();
			}
		});
		btnMemManage.setBackground(Color.WHITE);
		btnMemManage.setBounds(176, 40, 140, 50);
		panel.add(btnMemManage);
		
		setVisible(true);
	}
}