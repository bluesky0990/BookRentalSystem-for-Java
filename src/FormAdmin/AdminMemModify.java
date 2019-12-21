package FormAdmin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Data.Jdbc;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AdminMemModify extends JFrame {

	private JPanel panel;
	private JTextField txtID;
	private JPasswordField txtPW;
	private JTextField txtName;
	
	private int memNo;
	private String memID;
	private String memPW;
	private String memName;
	private int memPermission;

	/**
	 * Create the frame.
	 */
	public AdminMemModify() {
		setBounds(100, 100, 300, 340);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		try {
			Jdbc.query("select", "select * from member where number = " + AdminMemList.rowValue);
			while(Jdbc.rs.next()) {
				memNo = Jdbc.rs.getInt("number");
				memID = Jdbc.rs.getString("id");
				memPW = Jdbc.rs.getString("pw");
				memName = Jdbc.rs.getString("name");
				memPermission = Jdbc.rs.getInt("permission");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		JLabel lblCaption = new JLabel("회원정보");
		lblCaption.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaption.setFont(new Font("나눔바른고딕", Font.BOLD, 25));
		lblCaption.setBounds(90, 12, 100, 30);
		panel.add(lblCaption);
		
		JLabel lblID = new JLabel("I D");
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
		lblID.setBounds(25, 62, 62, 18);
		panel.add(lblID);
		
		txtID = new JTextField(memID);
		txtID.setColumns(10);
		txtID.setBounds(105, 60, 150, 24);
		panel.add(txtID);
		
		JLabel lblPW = new JLabel("PW");
		lblPW.setHorizontalAlignment(SwingConstants.CENTER);
		lblPW.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
		lblPW.setBounds(25, 102, 62, 18);
		panel.add(lblPW);
		
		txtPW = new JPasswordField(memPW);
		txtPW.setBounds(105, 100, 150, 24);
		panel.add(txtPW);
		
		JLabel lblName = new JLabel("이름");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
		lblName.setBounds(25, 142, 62, 18);
		panel.add(lblName);
		
		txtName = new JTextField(memName);
		txtName.setColumns(10);
		txtName.setBounds(105, 140, 150, 24);
		panel.add(txtName);
		
		JLabel lblPer = new JLabel("권한");
		lblPer.setHorizontalAlignment(SwingConstants.CENTER);
		lblPer.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
		lblPer.setBounds(25, 182, 62, 18);
		panel.add(lblPer);
		
		JComboBox cmboxPer = new JComboBox();
		cmboxPer.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		cmboxPer.setBackground(Color.WHITE);
		cmboxPer.setModel(new DefaultComboBoxModel(new String[] {"0", "1"}));
		cmboxPer.setSelectedItem(memPermission + "");
		cmboxPer.setBounds(105, 180, 150, 24);
		panel.add(cmboxPer);
		
		JPanel panelBottom = new JPanel();
		panelBottom.setBounds(0, 239, 282, 54);
		panel.add(panelBottom);
		panelBottom.setLayout(null);
		
		JButton btnModify = new JButton("수정");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Jdbc.query("update", "update member set id = '"+txtID.getText()+"', pw = '"+txtPW.getText()+"', name = '"+txtName.getText()+"', permission = '"+cmboxPer.getSelectedIndex()+"' where number = '"+AdminMemList.rowValue+"'");
					AdminMemList.loadTable();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnModify.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnModify.setBounds(0, 0, 94, 54);
		panelBottom.add(btnModify);
		btnModify.setBackground(Color.WHITE);
		
		JButton btnDelete = new JButton("삭제");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = AdminMemList.table.getSelectedRow();
				AdminMemList.rowValue = AdminMemList.table.getValueAt(row, 0);
				try {
					Jdbc.query("delete", "delete from member where number = " + AdminMemList.rowValue);
					AdminMemList.loadTable();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setBounds(188, 0, 94, 54);
		panelBottom.add(btnDelete);
		
		JButton btnCancle = new JButton("취소");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancle.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnCancle.setBounds(94, 0, 94, 54);
		panelBottom.add(btnCancle);
		btnCancle.setBackground(Color.WHITE);
		
		setVisible(true);
	}
}
