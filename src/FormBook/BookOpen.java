package FormBook;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Data.Jdbc;
import FormLogin.Login;
import FormMain.Main;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookOpen extends JFrame {

	private JPanel panel;
	
	public static int myBorrowCount;
	
	static int number;
	static String genre;
	static String name;
	static String author;
	static String publish;
	static int price;
	static float rating;
	public static int viewCount;
	static String content;

	/**
	 * Create the frame.
	 */
	public BookOpen() {
		setResizable(false);
		setBounds(100, 100, 1024, 768);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel lblBookImg = new JLabel("200x301");
		lblBookImg.setIcon(new ImageIcon("./images/" + name + ".png"));
		lblBookImg.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookImg.setBounds(26, 31, 200, 301);
		panel.add(lblBookImg);
		
		JLabel lblName = new JLabel(name);
		lblName.setFont(new Font("나눔바른고딕", Font.PLAIN, 50));
		lblName.setBounds(250, 44, 740, 50);
		panel.add(lblName);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(240, 106, 753, 2);
		panel.add(separator);
		
		JLabel lblAuthor = new JLabel("저자:");
		lblAuthor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAuthor.setFont(new Font("나눔바른고딕", Font.PLAIN, 23));
		lblAuthor.setBounds(262, 136, 100, 35);
		panel.add(lblAuthor);
		
		JLabel lblAuthorWrite = new JLabel(author);
		lblAuthorWrite.setFont(new Font("나눔바른고딕", Font.PLAIN, 23));
		lblAuthorWrite.setBounds(376, 136, 250, 35);
		panel.add(lblAuthorWrite);
		
		JLabel lblPublish = new JLabel("출판사:");
		lblPublish.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPublish.setFont(new Font("나눔바른고딕", Font.PLAIN, 23));
		lblPublish.setBounds(262, 176, 100, 35);
		panel.add(lblPublish);
		
		JLabel lblPublishWriter = new JLabel(publish);
		lblPublishWriter.setFont(new Font("나눔바른고딕", Font.PLAIN, 23));
		lblPublishWriter.setBounds(376, 176, 250, 35);
		panel.add(lblPublishWriter);
		
		JLabel lblPrice = new JLabel("가격:");
		lblPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrice.setFont(new Font("나눔바른고딕", Font.PLAIN, 23));
		lblPrice.setBounds(262, 216, 100, 35);
		panel.add(lblPrice);
		
		JLabel lblPriceWriter = new JLabel(price + "");
		lblPriceWriter.setFont(new Font("나눔바른고딕", Font.PLAIN, 23));
		lblPriceWriter.setBounds(376, 216, 250, 35);
		panel.add(lblPriceWriter);
		
		JLabel lblRating = new JLabel("평점:");
		lblRating.setHorizontalAlignment(SwingConstants.LEFT);
		lblRating.setFont(new Font("나눔바른고딕", Font.PLAIN, 23));
		lblRating.setBounds(848, 287, 80, 35);
		panel.add(lblRating);
		
		JLabel lblRatingCount = new JLabel(rating + "");
		lblRatingCount.setHorizontalAlignment(SwingConstants.LEFT);
		lblRatingCount.setFont(new Font("나눔바른고딕", Font.PLAIN, 23));
		lblRatingCount.setBounds(912, 287, 55, 35);
		panel.add(lblRatingCount);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(26, 344, 966, 2);
		panel.add(separator_1);
		
		JLabel lblContent = new JLabel("<html><body>" + content + "</body></html>"); // 이건 몰랐다. 자바에서 html이 사용된다.
		lblContent.setFont(new Font("나눔바른고딕", Font.PLAIN, 18));
		lblContent.setVerticalAlignment(SwingConstants.TOP);
		lblContent.setBounds(26, 358, 964, 266);
		panel.add(lblContent);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setBounds(0, 636, 1006, 2);
		panel.add(separator_2);
		
		JPanel panelBottom = new JPanel();
		panelBottom.setBackground(Color.WHITE);
		panelBottom.setBounds(0, 638, 1006, 83);
		panel.add(panelBottom);
		panelBottom.setLayout(null);
		
		JButton btnBorrow = new JButton("대출하기");
		btnBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Jdbc.query("select", "select * from borrow where member_number = " + Login.userNo);
					while(Jdbc.rs.next()) {
						myBorrowCount++;
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				try {
					boolean chk = true;
					Jdbc.query("select", "select * from borrow");
					while(Jdbc.rs.next()) {
						if(number == Jdbc.rs.getInt("book_number")) {
							JOptionPane.showMessageDialog(null, "이미 대출중인 도서입니다.", "", JOptionPane.ERROR_MESSAGE);
							chk = false;
						}
					}
					if(chk == true && myBorrowCount < 4) {
						Jdbc.query("insert", "insert into borrow values(null, '"+Login.userNo+"', '"+number+"', '"+name+"','"+1+"')");
						Jdbc.query("update", "update member set allBorrow = allBorrow + 1 where number = " + Login.userNo);
						JOptionPane.showMessageDialog(null, "해당 도서 대출이 완료되었습니다!");
					} else {
						JOptionPane.showMessageDialog(null, "회원당 최대 3권의 도서를 대출하실 수 있습니다.", "", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnBorrow.setBackground(Color.WHITE);
		btnBorrow.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnBorrow.setBounds(0, 0, 335, 83);
		panelBottom.add(btnBorrow);
		
		JButton btnBasket = new JButton("찜하기");
		btnBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Jdbc.query("select", "select * from cart");
					int index = 0;
					boolean chk = false;
					String bookName[] = new String[5];
					int bookNo[] = new int[5];
					while(Jdbc.rs.next()) {
						bookName[index] = Jdbc.rs.getString("book_name");
						bookNo[index] = Jdbc.rs.getInt("book_number");
						index++;
					}
					
					for(int i = 0; i < index; i++) {
						if(bookNo[i] == number) {
							chk = true;
						}
					}
					
					if(chk == true) {
						JOptionPane.showMessageDialog(null, "이미 찜목록에 존재하는 도서입니다.", "", JOptionPane.ERROR_MESSAGE);
					} else if(index >= 5) {
						JOptionPane.showMessageDialog(null, "찜 목록의 도서는 5권을 초과할 수 없습니다.", "", JOptionPane.ERROR_MESSAGE);
					} else {
						Jdbc.query("insert", "insert into cart values(null, '"+Login.userNo+"', '"+number+"', '"+name+"')");
						JOptionPane.showMessageDialog(null, "찜 목록에 추가되었습니다!");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnBasket.setBackground(Color.WHITE);
		btnBasket.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnBasket.setBounds(335, 0, 336, 83);
		panelBottom.add(btnBasket);
		
		JButton btnClose = new JButton("돌아가기");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setBackground(Color.WHITE);
		btnClose.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnClose.setBounds(671, 0, 335, 83);
		panelBottom.add(btnClose);
		
		setVisible(true);
	}
	
	public static void settingContent() {
		try {
			while(Jdbc.rs.next()) {
				number = Jdbc.rs.getInt("number");
				genre = Jdbc.rs.getString("genre");
				name = Jdbc.rs.getString("name");
				author = Jdbc.rs.getString("author");
				publish = Jdbc.rs.getString("publish");
				price = Jdbc.rs.getInt("price");
				rating = Jdbc.rs.getFloat("rating");
				viewCount = Jdbc.rs.getInt("viewCount");
				content = Jdbc.rs.getString("content");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
