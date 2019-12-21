package FormBook;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Data.Jdbc;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookMonth extends JFrame {

	private JPanel panel;

	/**
	 * Create the frame.
	 */
	public BookMonth() {
		setBounds(100, 100, 1024, 768);
		setResizable(false);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		String bookPath[] = new String[8];
		int bookNo[] = new int[8];
		int index = 0;
		try {
			Jdbc.query("select", "select * from month");
			while(Jdbc.rs.next()) {
				bookPath[index] = "./images/" + Jdbc.rs.getString("book_imagePath");
				bookNo[index] = Jdbc.rs.getInt("book_number");
				index++;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		JLabel lblMonth = new JLabel("이달의도서");
		lblMonth.setFont(new Font("나눔바른고딕", Font.PLAIN, 30));
		lblMonth.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonth.setBounds(428, 20, 150, 50);
		panel.add(lblMonth);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 80, 1006, 2);
		panel.add(separator);
		
		JLabel lblMonthBook1 = new JLabel("없음");
		lblMonthBook1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(bookNo[0] != 0) {
					try {
						Jdbc.query("select", "select * from book where number = " + bookNo[0]);
						BookOpen.settingContent();
						new BookOpen();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		lblMonthBook1.setIcon(new ImageIcon(bookPath[0]));
		lblMonthBook1.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonthBook1.setBounds(43, 100, 200, 301);
		panel.add(lblMonthBook1);
		
		JLabel lblMonthBook2 = new JLabel("없음");
		lblMonthBook2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(bookNo[1] != 0) {
					try {
						Jdbc.query("select", "select * from book where number = " + bookNo[1]);
						BookOpen.settingContent();
						new BookOpen();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		lblMonthBook2.setIcon(new ImageIcon(bookPath[1]));
		lblMonthBook2.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonthBook2.setBounds(283, 100, 200, 301);
		panel.add(lblMonthBook2);
		
		JLabel lblMonthBook3 = new JLabel("없음");
		lblMonthBook3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(bookNo[2] != 0) {
					try {
						Jdbc.query("select", "select * from book where number = " + bookNo[2]);
						BookOpen.settingContent();
						new BookOpen();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		lblMonthBook3.setIcon(new ImageIcon(bookPath[2]));
		lblMonthBook3.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonthBook3.setBounds(523, 100, 200, 301);
		panel.add(lblMonthBook3);
		
		JLabel lblMonthBook4 = new JLabel("없음");
		lblMonthBook4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(bookNo[3] != 0) {
					try {
						Jdbc.query("select", "select * from book where number = " + bookNo[3]);
						BookOpen.settingContent();
						new BookOpen();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		lblMonthBook4.setIcon(new ImageIcon(bookPath[3]));
		lblMonthBook4.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonthBook4.setBounds(763, 100, 200, 301);
		panel.add(lblMonthBook4);
		
		JLabel lblMonthBook5 = new JLabel("없음");
		lblMonthBook5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(bookNo[4] != 0) {
					try {
						Jdbc.query("select", "select * from book where number = " + bookNo[4]);
						BookOpen.settingContent();
						new BookOpen();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		lblMonthBook5.setIcon(new ImageIcon(bookPath[4]));
		lblMonthBook5.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonthBook5.setBounds(43, 408, 200, 301);
		panel.add(lblMonthBook5);
		
		JLabel lblMonthBook6 = new JLabel("없음");
		lblMonthBook6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(bookNo[5] != 0) {
					try {
						Jdbc.query("select", "select * from book where number = " + bookNo[5]);
						BookOpen.settingContent();
						new BookOpen();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		lblMonthBook6.setIcon(new ImageIcon(bookPath[5]));
		lblMonthBook6.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonthBook6.setBounds(283, 408, 200, 301);
		panel.add(lblMonthBook6);
		
		JLabel lblMonthBook7 = new JLabel("없음");
		lblMonthBook7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(bookNo[6] != 0) {
					try {
						Jdbc.query("select", "select * from book where number = " + bookNo[6]);
						BookOpen.settingContent();
						new BookOpen();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		lblMonthBook7.setIcon(new ImageIcon(bookPath[6]));
		lblMonthBook7.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonthBook7.setBounds(523, 408, 200, 301);
		panel.add(lblMonthBook7);
		
		JLabel lblMonthBook8 = new JLabel("없음");
		lblMonthBook8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(bookNo[7] != 0) {
					try {
						Jdbc.query("select", "select * from book where number = " + bookNo[7]);
						BookOpen.settingContent();
						new BookOpen();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		lblMonthBook8.setIcon(new ImageIcon(bookPath[7]));
		lblMonthBook8.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonthBook8.setBounds(763, 408, 200, 301);
		panel.add(lblMonthBook8);
		
		JButton btnSearchBook = new JButton("도서검색");
		btnSearchBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BookSearch();
				dispose();
			}
		});
		btnSearchBook.setFont(new Font("나눔바른고딕", Font.PLAIN, 11));
		btnSearchBook.setBackground(Color.WHITE);
		btnSearchBook.setBounds(887, 25, 93, 25);
		panel.add(btnSearchBook);
		
		setVisible(true);
	}
}
