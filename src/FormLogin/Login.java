package FormLogin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Data.Jdbc;
import FormAdmin.AdminMain;
import FormMain.Main;
import FormMain.Main_noMem;

public class Login extends JFrame {

	private JPanel panel;
	private JTextField txtID;
	private JPasswordField txtPW;
	
	public static int userNo = 0;
	public static String userID = null;
	public static String userPW = null;
	public static String userName = null;
	
	private boolean chk = false;

	/**
	 * Create the frame.
	 */
	public Login() {
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 335, 175);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel lblID = new JLabel("I D");
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
		lblID.setBounds(14, 23, 40, 18);
		panel.add(lblID);
		
		JLabel lblPW = new JLabel("PW");
		lblPW.setHorizontalAlignment(SwingConstants.CENTER);
		lblPW.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
		lblPW.setBounds(14, 65, 40, 18);
		panel.add(lblPW);
		
		txtID = new JTextField();
		txtID.setColumns(10);
		txtID.setBounds(70, 20, 160, 24);
		panel.add(txtID);
		
		txtPW = new JPasswordField();
		txtPW.setBounds(70, 62, 160, 24);
		panel.add(txtPW);
		
		JButton btnOnLogin = new JButton("로그인");
		btnOnLogin.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnOnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtID.getText().equals("") || txtPW.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "아이디 또는 패스워드를 입력하십시오.", "", JOptionPane.ERROR_MESSAGE);
				}
				try {
					Jdbc.query("select", "select * from member");
					while(Jdbc.rs.next()) {
						if(Jdbc.rs.getString("id").equals(txtID.getText()) && Jdbc.rs.getString("pw").equals(txtPW.getText())) {
							userNo = Jdbc.rs.getInt("number");
							userID = txtID.getText();
							userPW = txtPW.getText();
							userName = Jdbc.rs.getString("name");
							if(Jdbc.rs.getInt("permission") == 1) {
								new AdminMain();
							} else {
								new Main();
							}
							dispose();
							chk = true;
						}
					}
					if(chk == false) {
						JOptionPane.showMessageDialog(null, "아이디 또는 패스워드를 확인해주십시오.", "", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnOnLogin.setBackground(Color.WHITE);
		btnOnLogin.setBounds(243, 15, 75, 51);
		panel.add(btnOnLogin);
		
		JButton btnUnLogin = new JButton("비회원");
		btnUnLogin.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnUnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Main_noMem();
				dispose();
			}
		});
		btnUnLogin.setBackground(Color.WHITE);
		btnUnLogin.setBounds(243, 68, 75, 22);
		panel.add(btnUnLogin);
		
		JButton btnJoin = new JButton("회원가입");
		btnJoin.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Join();
			}
		});
		btnJoin.setBackground(Color.WHITE);
		btnJoin.setBounds(14, 102, 95, 30);
		panel.add(btnJoin);
		
		JButton btnFind = new JButton("ID/PW 찾기");
		btnFind.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FindIDPW();
			}
		});
		btnFind.setBackground(Color.WHITE);
		btnFind.setBounds(116, 102, 110, 30);
		panel.add(btnFind);
		
		JButton btnExit = new JButton("종료");
		btnExit.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBackground(Color.WHITE);
		btnExit.setBounds(233, 102, 85, 30);
		panel.add(btnExit);
		
		setVisible(true);
	}
}
