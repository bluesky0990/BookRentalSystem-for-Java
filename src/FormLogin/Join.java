package FormLogin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Data.Jdbc;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Join extends JFrame {

	private JPanel panel;
	private JTextField txtID;
	private JPasswordField txtPW;
	private JPasswordField txtPWr;
	private JTextField txtName;
	
	private boolean overlapChk;

	/**
	 * Create the frame.
	 */
	public Join() {
		setBounds(100, 100, 300, 290);
		setResizable(false);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel lblCaption = new JLabel("회원가입");
		lblCaption.setFont(new Font("나눔바른고딕", Font.BOLD, 20));
		lblCaption.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaption.setBounds(95, 12, 85, 18);
		panel.add(lblCaption);
		
		JLabel lblID = new JLabel("I D");
		lblID.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setBounds(14, 51, 62, 18);
		panel.add(lblID);
		
		txtID = new JTextField();
		txtID.setBounds(90, 48, 150, 24);
		panel.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblPW = new JLabel("PW");
		lblPW.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
		lblPW.setHorizontalAlignment(SwingConstants.CENTER);
		lblPW.setBounds(14, 81, 62, 18);
		panel.add(lblPW);
		
		txtPW = new JPasswordField();
		txtPW.setBounds(90, 78, 150, 24);
		panel.add(txtPW);
		
		JLabel lblPWr = new JLabel("R-PW");
		lblPWr.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
		lblPWr.setHorizontalAlignment(SwingConstants.CENTER);
		lblPWr.setBounds(14, 111, 62, 18);
		panel.add(lblPWr);
		
		txtPWr = new JPasswordField();
		txtPWr.setBounds(90, 108, 150, 24);
		panel.add(txtPWr);
		
		JLabel lblName = new JLabel("이름");
		lblName.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(14, 141, 62, 18);
		panel.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(90, 138, 150, 24);
		panel.add(txtName);
		txtName.setColumns(10);
		
		JButton btnSubmit = new JButton("회원가입");
		btnSubmit.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if((txtID.getText().equals("") || txtPW.getText().equals("") || txtPWr.getText().equals("") || txtName.getText().equals(""))) {
					JOptionPane.showMessageDialog(null, "모든 항목을 입력해주시기 바랍니다.", "", JOptionPane.ERROR_MESSAGE);
					
				} else if(!(txtPW.getText().equals(txtPWr.getText()))) {
					JOptionPane.showMessageDialog(null, "동일한 패스워드를 입력해주시기 바랍니다.", "", JOptionPane.ERROR_MESSAGE);
					
				} else {
					if(overlapChk == true) {
						try {
							Jdbc.query("insert", "insert into member values("+null+", '"+txtID.getText()+"','"+txtPW.getText()+"','"+txtName.getText()+"' ,'"+0+"', '"+0+"')");
							JOptionPane.showMessageDialog(null, "회원가입을 완료하였습니다!");
							dispose();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "아이디 중복확인을 해주시기 바랍니다.", "", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnSubmit.setBackground(Color.WHITE);
		btnSubmit.setBounds(26, 187, 110, 40);
		panel.add(btnSubmit);
		
		JButton btnCancle = new JButton("취소");
		btnCancle.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jdbc.dbDisconnect();
				dispose();
			}
		});
		btnCancle.setBackground(Color.WHITE);
		btnCancle.setBounds(146, 187, 110, 40);
		panel.add(btnCancle);
		
		JButton btnNewButton = new JButton("c");
		btnNewButton.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtID.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "아이디를 입력해주시기 바랍니다.", "", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						Jdbc.query("select", "select id from member");
						while(Jdbc.rs.next()) {
							if(txtID.getText().equals(Jdbc.rs.getString("id"))) {
								JOptionPane.showMessageDialog(null, "이미 사용중인 아이디입니다.", "", JOptionPane.ERROR_MESSAGE);
								overlapChk = false;
							}
						}
						Jdbc.rs = null;
						if(overlapChk == true) {
							JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다!");
						}
						overlapChk = true;
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(245, 47, 30, 25);
		panel.add(btnNewButton);
		
		setVisible(true);
	}
}
