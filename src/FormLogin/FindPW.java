package FormLogin;


import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Data.Jdbc;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class FindPW extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtID;
	
	private boolean findChk = false;

	/**
	 * Create the frame.
	 */
	public FindPW() {
		setResizable(false);
		setBounds(100, 100, 280, 170);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblName = new JLabel("이름");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
		lblName.setBounds(20, 20, 57, 15);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(80, 16, 150, 21);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblID = new JLabel("I D");
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
		lblID.setBounds(20, 46, 57, 15);
		contentPane.add(lblID);
		
		txtID = new JTextField();
		txtID.setBounds(80, 43, 150, 21);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		JButton btnFind = new JButton("찾기");
		btnFind.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Jdbc.query("select", "select * from member");
					while(Jdbc.rs.next()) {
						if(Jdbc.rs.getString("name").equals(txtName.getText()) && Jdbc.rs.getString("id").equals(txtID.getText())) {
							JOptionPane.showMessageDialog(null, txtName.getText() + "귀하의 패스워드는 [" + Jdbc.rs.getString("pw") + "]입니다.");
							findChk = true;
						}
					}
					Jdbc.rs = null;
					if(findChk == false) {
						JOptionPane.showMessageDialog(null, "입력정보를 다시 확인해주시길 바랍니다.", "", JOptionPane.ERROR_MESSAGE);
					}
					findChk = false;
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnFind.setBackground(Color.WHITE);
		btnFind.setBounds(35, 85, 92, 23);
		contentPane.add(btnFind);
		
		JButton btnCancle = new JButton("취소");
		btnCancle.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancle.setBackground(Color.WHITE);
		btnCancle.setBounds(134, 85, 92, 23);
		contentPane.add(btnCancle);
		
		setVisible(true);
	}
}