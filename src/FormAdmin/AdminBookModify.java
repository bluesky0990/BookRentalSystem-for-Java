package FormAdmin;

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
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Data.Jdbc;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AdminBookModify extends JFrame {

	private JPanel panel;
	private JTextField txtName;
	private JTextField txtAuthor;
	private JTextField txtPublish;
	private JTextField txtPrice;
	
	public static int bookNumber = 404;
	private String bookGenre;
	public static String bookName;
	private String bookAuthor;
	private String bookPublish;
	private int bookPrice;
	public static String bookContent;

	/**
	 * Create the frame.
	 */
	public AdminBookModify() {
		setBounds(100, 100, 380, 510);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		try {
			Jdbc.query("select", "select * from book where number = " + AdminBookManage.rowValue);
			while(Jdbc.rs.next()) {
				bookNumber = Jdbc.rs.getInt("number");
				bookGenre = Jdbc.rs.getString("genre");
				bookName = Jdbc.rs.getString("name");
				bookAuthor = Jdbc.rs.getString("author");
				bookPublish = Jdbc.rs.getString("publish");
				bookPrice = Jdbc.rs.getInt("price");
				bookContent = Jdbc.rs.getString("content");
			}
			System.out.println(bookNumber + bookGenre + bookName + bookAuthor + bookPublish + bookPrice + bookContent);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		JLabel lblBookName = new JLabel(bookName);
		lblBookName.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookName.setFont(new Font("나눔바른고딕", Font.PLAIN, 30));
		lblBookName.setBounds(14, 12, 188, 30);
		panel.add(lblBookName);
		
		JLabel lblBookMsg = new JLabel("책 번호:");
		lblBookMsg.setFont(new Font("나눔바른고딕", Font.PLAIN, 23));
		lblBookMsg.setBounds(216, 12, 80, 25);
		panel.add(lblBookMsg);
		
		JLabel lblBookCode = new JLabel(bookNumber + "");
		lblBookCode.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookCode.setFont(new Font("나눔바른고딕", Font.PLAIN, 23));
		lblBookCode.setBounds(303, 12, 45, 25);
		panel.add(lblBookCode);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(216, 35, 130, 2);
		panel.add(separator);
		
		JLabel lblGenre = new JLabel("장르");
		lblGenre.setFont(new Font("나눔바른고딕", Font.PLAIN, 23));
		lblGenre.setBounds(30, 90, 60, 28);
		panel.add(lblGenre);
		
		JComboBox comboxGenre = new JComboBox();
		comboxGenre.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		comboxGenre.setModel(new DefaultComboBoxModel(new String[] {"경영", "경제", "만화책", "문학", "심리", "소설", "아동", "여행", "인문", "영문학", "자기계발", "철학"}));
		comboxGenre.setSelectedItem(bookGenre);
		comboxGenre.setBounds(95, 90, 230, 28);
		panel.add(comboxGenre);
		
		JLabel lblName = new JLabel("제목");
		lblName.setFont(new Font("나눔바른고딕", Font.PLAIN, 23));
		lblName.setBounds(30, 130, 60, 28);
		panel.add(lblName);
		
		txtName = new JTextField(bookName);
		txtName.setColumns(10);
		txtName.setBounds(95, 130, 230, 28);
		panel.add(txtName);
		
		JLabel lblAuthor = new JLabel("저자");
		lblAuthor.setFont(new Font("나눔바른고딕", Font.PLAIN, 23));
		lblAuthor.setBounds(30, 170, 60, 28);
		panel.add(lblAuthor);
		
		txtAuthor = new JTextField(bookAuthor);
		txtAuthor.setColumns(10);
		txtAuthor.setBounds(95, 170, 230, 28);
		panel.add(txtAuthor);
		
		JLabel lblPublish = new JLabel("출판사");
		lblPublish.setFont(new Font("나눔바른고딕", Font.PLAIN, 23));
		lblPublish.setBounds(20, 210, 70, 28);
		panel.add(lblPublish);
		
		txtPublish = new JTextField(bookPublish);
		txtPublish.setColumns(10);
		txtPublish.setBounds(95, 210, 230, 28);
		panel.add(txtPublish);
		
		JLabel lblPrice = new JLabel("가격");
		lblPrice.setFont(new Font("나눔바른고딕", Font.PLAIN, 23));
		lblPrice.setBounds(30, 250, 60, 28);
		panel.add(lblPrice);
		
		txtPrice = new JTextField(bookPrice + "");
		txtPrice.setColumns(10);
		txtPrice.setBounds(95, 250, 230, 28);
		panel.add(txtPrice);
		
		JLabel lblContent = new JLabel("줄거리");
		lblContent.setFont(new Font("나눔바른고딕", Font.PLAIN, 23));
		lblContent.setBounds(20, 290, 70, 28);
		panel.add(lblContent);
		
		JButton btnContentModify = new JButton("줄거리 수정");
		btnContentModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminBookModifyContent();
			}
		});
		btnContentModify.setBackground(Color.WHITE);
		btnContentModify.setFont(new Font("나눔바른고딕", Font.PLAIN, 17));
		btnContentModify.setBounds(95, 290, 230, 28);
		panel.add(btnContentModify);
		
		JPanel panelBottom = new JPanel();
		panelBottom.setBounds(0, 383, 362, 80);
		panel.add(panelBottom);
		panelBottom.setLayout(null);
		
		JButton btnConfirm = new JButton("확인");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Jdbc.query("update", "update book set genre = '"+comboxGenre.getSelectedItem()+"', name = '"+txtName.getText()+"', author = '"+txtAuthor.getText()+"', publish = '"+txtPublish.getText()+"', price = '"+Integer.parseInt(txtPrice.getText())+"' where number = '"+AdminBookManage.rowValue+"'");
					AdminBookManage.loadTable();
					dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "한글, 영문 또는 숫자만 입력이 가능합니다.", "", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnConfirm.setBackground(Color.WHITE);
		btnConfirm.setFont(new Font("나눔바른고딕", Font.PLAIN, 18));
		btnConfirm.setBounds(0, 0, 181, 80);
		panelBottom.add(btnConfirm);
		
		JButton btnCancle = new JButton("취소");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancle.setFont(new Font("나눔바른고딕", Font.PLAIN, 18));
		btnCancle.setBackground(Color.WHITE);
		btnCancle.setBounds(181, 0, 181, 80);
		panelBottom.add(btnCancle);
		
		setVisible(true);
	}
}
