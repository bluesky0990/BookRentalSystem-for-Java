package FormUser;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Data.Jdbc;
import Data.Launch;
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
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserMyInformation extends JFrame {

	private JPanel panel;
	private JTextField txtID;
	private JPasswordField txtPW;
	private JTextField txtName;

	/**
	 * Create the frame.
	 */
	public UserMyInformation() {
		setBounds(100, 100, 600, 480);
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JPanel panelLeft = new JPanel();
		panelLeft.setBackground(Color.DARK_GRAY);
		panelLeft.setBounds(0, 0, 140, 433);
		panel.add(panelLeft);
		panelLeft.setLayout(null);
		
		JButton btnBorrow = new JButton("도서관리");
		btnBorrow.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserMyBorrow();
				dispose();
			}
		});
		btnBorrow.setForeground(Color.WHITE);
		btnBorrow.setBackground(Color.GRAY);
		btnBorrow.setBounds(0, 0, 140, 60);
		panelLeft.add(btnBorrow);
		
		JButton btnCart = new JButton("찜관리");
		btnCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserMyCart();
				dispose();
			}
		});
		btnCart.setForeground(Color.WHITE);
		btnCart.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnCart.setBackground(Color.GRAY);
		btnCart.setBounds(0, 60, 140, 60);
		panelLeft.add(btnCart);
		
		JButton btnInfo = new JButton("정보관리");
		btnInfo.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnInfo.setEnabled(false);
		btnInfo.setForeground(Color.WHITE);
		btnInfo.setBackground(Color.LIGHT_GRAY);
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
		
		JLabel lblInfoManage = new JLabel("사용자 정보수정");
		lblInfoManage.setFont(new Font("나눔바른고딕", Font.BOLD, 30));
		lblInfoManage.setBounds(246, 30, 200, 40);
		panel.add(lblInfoManage);
		
		JLabel lblID = new JLabel("I D");
		lblID.setFont(new Font("나눔바른고딕", Font.PLAIN, 25));
		lblID.setBounds(226, 100, 50, 25);
		panel.add(lblID);
		
		txtID = new JTextField(Login.userID);
		txtID.setEnabled(false);
		txtID.setBounds(288, 99, 170, 25);
		panel.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblPW = new JLabel("PW");
		lblPW.setFont(new Font("나눔바른고딕", Font.PLAIN, 25));
		lblPW.setBounds(226, 136, 50, 25);
		panel.add(lblPW);
		
		txtPW = new JPasswordField(Login.userPW);
		txtPW.setBounds(288, 135, 170, 25);
		panel.add(txtPW);
		
		JLabel lblName = new JLabel("이름");
		lblName.setFont(new Font("나눔바른고딕", Font.PLAIN, 25));
		lblName.setBounds(226, 172, 50, 25);
		panel.add(lblName);
		
		txtName = new JTextField(Login.userName);
		txtName.setColumns(10);
		txtName.setBounds(288, 171, 170, 25);
		panel.add(txtName);
		
		JButton btnModify = new JButton("변경");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Jdbc.query("update", "update member set pw = '"+txtPW.getText()+"', name = '"+txtName.getText()+"' where number = " + Login.userNo);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnModify.setBackground(Color.WHITE);
		btnModify.setFont(new Font("나눔바른고딕", Font.PLAIN, 12));
		btnModify.setBounds(241, 230, 100, 40);
		panel.add(btnModify);
		
		JButton btnCancle = new JButton("취소");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtPW.setText(Login.userPW);
				txtName.setText(Login.userName);
			}
		});
		btnCancle.setFont(new Font("나눔바른고딕", Font.PLAIN, 12));
		btnCancle.setBackground(Color.WHITE);
		btnCancle.setBounds(346, 230, 100, 40);
		panel.add(btnCancle);
		
		JLabel lblUnsign = new JLabel("회원탈퇴");
		lblUnsign.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					int r = JOptionPane.showConfirmDialog(null, "정말 계정을 탈퇴하시겠습니까?", "confirm", JOptionPane.YES_NO_OPTION);
					if(r == JOptionPane.YES_OPTION) {
						Jdbc.query("delete", "delete from member where number = " + Login.userNo);
						JOptionPane.showMessageDialog(null, "계정이 삭제되었습니다.", "탈퇴완료", JOptionPane.INFORMATION_MESSAGE);
						System.exit(0);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		lblUnsign.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnsign.setFont(new Font("나눔바른고딕", Font.PLAIN, 20));
		lblUnsign.setBounds(482, 393, 90, 40);
		panel.add(lblUnsign);
		setVisible(true);
	}
}
