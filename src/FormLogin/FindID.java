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

import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class FindID extends JFrame {

	private JPanel panel;
	private JTextField txtName;
	
	private boolean findChk = false;

	/**
	 * Create the frame.
	 */
	public FindID() {
		setResizable(false);
		setBounds(100, 100, 280, 145);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		setContentPane(panel);
		
		JLabel lblName = new JLabel("이름");
		lblName.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(20, 20, 57, 15);
		panel.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(80, 16, 150, 21);
		panel.add(txtName);
		txtName.setColumns(10);
		
		JButton btnFind = new JButton("찾기");
		btnFind.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Jdbc.query("select", "select name from member");
					while(Jdbc.rs.next()) {
						if(Jdbc.rs.getString("name").equals(txtName.getText())) {
							JOptionPane.showMessageDialog(null, txtName.getText() + "귀하의 아이디는 [" + Jdbc.rs.getString("id") + "]입니다.");
							findChk = true;
						}
					}
					Jdbc.rs = null;
					if(findChk == false){
						JOptionPane.showMessageDialog(null, "입력정보를 다시 확인해주시길 바랍니다.", "", JOptionPane.ERROR_MESSAGE);
					}
					findChk = false;
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnFind.setBackground(Color.WHITE);
		btnFind.setBounds(35, 60, 92, 23);
		panel.add(btnFind);
		
		JButton btnCancle = new JButton("취소");
		btnCancle.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancle.setBackground(Color.WHITE);
		btnCancle.setBounds(134, 60, 92, 23);
		panel.add(btnCancle);
		
		setVisible(true);
	}
}