package FormMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import FormBook.BookMonth;
import FormBook.BookSearch;
import FormLogin.Login;

public class Main_noMem extends JFrame {

	private JPanel panel;

	/**
	 * Create the frame.
	 */
	public Main_noMem() {
		setResizable(false);
		setBounds(100, 100, 350, 150);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel lblRank = new JLabel("");
		lblRank.setIcon(new ImageIcon("src/images/rank/상단바랭크_n.png"));
		lblRank.setHorizontalAlignment(SwingConstants.CENTER);
		lblRank.setBounds(8, 5, 23, 23);
		panel.add(lblRank);
		
		JLabel lblName = new JLabel("비회원");
		lblName.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(27, 5, 58, 20);
		panel.add(lblName);
		
		JLabel lblWelcome = new JLabel("님 환영합니다.");
		lblWelcome.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		lblWelcome.setBounds(81, 8, 94, 15);
		panel.add(lblWelcome);
		
		JButton btnMonth = new JButton("이달의 도서");
		btnMonth.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BookMonth();
			}
		});
		btnMonth.setBackground(Color.WHITE);
		btnMonth.setBounds(18, 40, 140, 50);
		panel.add(btnMonth);
		
		JButton btnSearch = new JButton("도서검색");
		btnSearch.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BookSearch();
			}
		});
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setBounds(176, 40, 140, 50);
		panel.add(btnSearch);
		
		JButton btnLogin = new JButton("로그인");
		btnLogin.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login();
				dispose();
			}
		});
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setBounds(236, 5, 80, 22);
		panel.add(btnLogin);
		
		setVisible(true);
	}
}
