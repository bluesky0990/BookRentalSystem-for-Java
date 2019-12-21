package FormAdmin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Data.Jdbc;
import FormBook.BookMonth;
import FormBook.BookOpen;
import FormBook.BookSearch;
import FormLogin.Login;

import javax.swing.ListSelectionModel;

public class AdminBookManage extends JFrame {

	private JPanel panel;
	private JTextField txtSearch;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable table;
	private static DefaultTableModel model;
	
	public static Object rowValue;

	/**
	 * Create the frame.
	 */
	public AdminBookManage() {
		setResizable(false);
		setBounds(100, 100, 800, 600);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JComboBox comboxGenre = new JComboBox();
		comboxGenre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					model.setNumRows(0);
					if(comboxGenre.getSelectedItem() == "전체") {
						loadTable();
					} else {
						Jdbc.query("select", "select * from book where genre = '"+comboxGenre.getSelectedItem()+"'");
						while(Jdbc.rs.next()) {
							model.addRow(new Object[] {Jdbc.rs.getString("number"), Jdbc.rs.getString("genre"),
									Jdbc.rs.getString("name"), Jdbc.rs.getString("author"), Jdbc.rs.getString("publish"),
									Jdbc.rs.getString("rating"), Jdbc.rs.getString("viewCount")});
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		comboxGenre.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		comboxGenre.setBackground(Color.WHITE);
		comboxGenre.setMaximumRowCount(5);
		comboxGenre.setModel(new DefaultComboBoxModel(new String[] {"전체", "경영", "경제", "만화책", "문학", "심리", "소설", "아동", "여행", "인문", "영문학", "자기계발", "철학"}));
		comboxGenre.setBounds(14, 13, 110, 30);
		panel.add(comboxGenre);
		
		txtSearch = new JTextField();
		txtSearch.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		txtSearch.setBounds(138, 13, 500, 30);
		panel.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnSearch = new JButton("검색");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					model.setNumRows(0);
					if(comboxGenre.getSelectedItem() == "전체") {
						Jdbc.query("select", "select * from book where name like '%"+txtSearch.getText()+"%'");
					} else {
						Jdbc.query("select", "select * from book where name like '%"+txtSearch.getText()+"%' and genre = '"+comboxGenre.getSelectedItem()+"'");
					}
					while(Jdbc.rs.next()) {
						model.addRow(new Object[] {Jdbc.rs.getString("number"), Jdbc.rs.getString("genre"),
								Jdbc.rs.getString("name"), Jdbc.rs.getString("author"), Jdbc.rs.getString("publish"),
								Jdbc.rs.getString("rating"), Jdbc.rs.getString("viewCount")});
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSearch.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setBounds(650, 12, 115, 32);
		panel.add(btnSearch);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBackground(Color.WHITE);
		separator.setBounds(14, 55, 754, 2);
		panel.add(separator);
		
		JButton btnAddBook = new JButton("책 추가");
		btnAddBook.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminBookAdd();
			}
		});
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setForeground(Color.GRAY);
		separator_2.setBounds(578, 55, 15, 46);
		panel.add(separator_2);
		btnAddBook.setBackground(Color.WHITE);
		btnAddBook.setBounds(14, 63, 115, 30);
		panel.add(btnAddBook);
		
		JRadioButton rdbtnNormal = new JRadioButton("기본순");
		rdbtnNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTable();
				comboxGenre.setSelectedItem("전체");
				txtSearch.setText("책 제목");
			}
		});
		
		JButton btnMonthBookManage = new JButton("이달의 책 관리");
		btnMonthBookManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminBookMonthManage();
			}
		});
		btnMonthBookManage.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnMonthBookManage.setBackground(Color.WHITE);
		btnMonthBookManage.setBounds(140, 63, 120, 30);
		panel.add(btnMonthBookManage);
		rdbtnNormal.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		rdbtnNormal.setSelected(true);
		buttonGroup.add(rdbtnNormal);
		rdbtnNormal.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNormal.setBounds(346, 68, 70, 25);
		panel.add(rdbtnNormal);
		
		JRadioButton rdbtnOpenCount = new JRadioButton("조회순");
		rdbtnOpenCount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboxGenre.setSelectedItem("전체");
				txtSearch.setText("책 제목");
				try {
					model.setNumRows(0);
					Jdbc.query("select", "select * from book order by viewCount desc");
					while(Jdbc.rs.next()) {
						model.addRow(new Object[] {Jdbc.rs.getInt("number"), Jdbc.rs.getString("genre"),
								Jdbc.rs.getString("name"), Jdbc.rs.getString("author"), Jdbc.rs.getString("publish"),
								Jdbc.rs.getString("rating"), Jdbc.rs.getString("viewCount")});
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		rdbtnOpenCount.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		buttonGroup.add(rdbtnOpenCount);
		rdbtnOpenCount.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnOpenCount.setBounds(422, 68, 70, 25);
		panel.add(rdbtnOpenCount);
		
		JRadioButton rdbtnScore = new JRadioButton("평점순");
		rdbtnScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboxGenre.setSelectedItem("전체");
				txtSearch.setText("책 제목");
				try {
					model.setNumRows(0);
					Jdbc.query("select", "select * from book order by rating desc");
					while(Jdbc.rs.next()) {
						model.addRow(new Object[] {Jdbc.rs.getInt("number"), Jdbc.rs.getString("genre"),
								Jdbc.rs.getString("name"), Jdbc.rs.getString("author"), Jdbc.rs.getString("publish"),
								Jdbc.rs.getString("rating"), Jdbc.rs.getString("viewCount")});
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		rdbtnScore.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		buttonGroup.add(rdbtnScore);
		rdbtnScore.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnScore.setBounds(498, 68, 70, 25);
		panel.add(rdbtnScore);
		
		JButton btnBookMonth = new JButton("이달의도서");
		btnBookMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BookMonth();
			}
		});
		btnBookMonth.setFont(new Font("나눔바른고딕", Font.PLAIN, 14));
		btnBookMonth.setBackground(Color.WHITE);
		btnBookMonth.setBounds(584, 63, 100, 30);
		panel.add(btnBookMonth);
		
		JButton btnBookSearch = new JButton("도서검색");
		btnBookSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BookSearch();
			}
		});
		btnBookSearch.setFont(new Font("나눔바른고딕", Font.PLAIN, 14));
		btnBookSearch.setBackground(Color.WHITE);
		btnBookSearch.setBounds(683, 63, 85, 30);
		panel.add(btnBookSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 100, 754, 369);
		panel.add(scrollPane);
		

		String clname[] = {"No", "분야", "제목", "저자", "출판사", "평점", "조회수"};
		model = new DefaultTableModel(clname, 0) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false
				};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		
		loadTable();
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		scrollPane.setViewportView(table);
		
		
		table.getColumnModel().getColumn(0).setPreferredWidth(35);
		table.getColumnModel().getColumn(0).setMinWidth(35);
		table.getColumnModel().getColumn(0).setMaxWidth(35);
		table.getColumnModel().getColumn(1).setPreferredWidth(65);
		table.getColumnModel().getColumn(1).setMinWidth(65);
		table.getColumnModel().getColumn(1).setMaxWidth(65);
		table.getColumnModel().getColumn(2).setPreferredWidth(400);
		table.getColumnModel().getColumn(2).setMinWidth(400);
		table.getColumnModel().getColumn(2).setMaxWidth(400);
		table.getColumnModel().getColumn(3).setPreferredWidth(85);
		table.getColumnModel().getColumn(3).setMinWidth(85);
		table.getColumnModel().getColumn(3).setMaxWidth(85);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setMinWidth(90);
		table.getColumnModel().getColumn(4).setMaxWidth(90);
		table.getColumnModel().getColumn(5).setPreferredWidth(37);
		table.getColumnModel().getColumn(5).setMinWidth(37);
		table.getColumnModel().getColumn(5).setMaxWidth(37);
		table.getColumnModel().getColumn(6).setPreferredWidth(40);
		table.getColumnModel().getColumn(6).setMinWidth(40);
		table.getColumnModel().getColumn(6).setMaxWidth(40);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(0, 479, 794, 11);
		panel.add(separator_1);
		
		JPanel panelBottom = new JPanel();
		panelBottom.setBounds(0, 481, 794, 84);
		panel.add(panelBottom);
		panelBottom.setLayout(null);
		
		
		JButton btnModify = new JButton("수정하기");
		btnModify.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row != -1) {
					rowValue = table.getValueAt(row, 0);
					new AdminBookModify();
				} else {
					JOptionPane.showMessageDialog(null, "데이터를 선택하여 주십시오.", "", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnModify.setBounds(0, 0, 198, 84);
		panelBottom.add(btnModify);
		btnModify.setBackground(Color.WHITE);
		
		JButton btnDelete = new JButton("삭제하기");
		btnDelete.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row != -1) {
					rowValue = table.getValueAt(row, 0);
					try {
						Jdbc.query("delete", "delete from book where number = " + rowValue);
						loadTable();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "데이터를 선택하여 주십시오.", "", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton btnAddMonth = new JButton("이달의 책 추가");
		btnAddMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row != -1) {
					rowValue = table.getValueAt(row, 0);
					int index = 0;
					try {
						Jdbc.query("select", "select * from month where book_number = " + rowValue);
						while(Jdbc.rs.next()) {
							index++;
						}
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					
					if(index > 0) {
						JOptionPane.showMessageDialog(null, "이달의 도서란에 이미 등록되어있는 도서입니다.", "", JOptionPane.ERROR_MESSAGE);
					} else {
						try {
							int count = 0;
							Jdbc.query("select", "select * from month");
							while(Jdbc.rs.next()) {
								count++;
							}
							if(count < 8) {
								String bookName = null;
								String imgPath = null;
								
								Jdbc.query("select", "select * from book where number = " + rowValue);
								while(Jdbc.rs.next()) {
									bookName = Jdbc.rs.getString("name");
									imgPath = Jdbc.rs.getString("imagePath");
								}
								Jdbc.query("insert", "insert into month values(null, '"+rowValue+"', '"+bookName+"', '"+imgPath+"')");
								JOptionPane.showMessageDialog(null, "해당 도서를 이달의 도서로 등록하였습니다!");
							} else {
								JOptionPane.showMessageDialog(null, "이달의 도서 목록이 가득 찬 상태입니다.", "", JOptionPane.ERROR_MESSAGE);
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "데이터를 선택하여 주십시오.", "", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAddMonth.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnAddMonth.setBackground(Color.WHITE);
		btnAddMonth.setBounds(198, 0, 199, 84);
		panelBottom.add(btnAddMonth);
		btnDelete.setBounds(397, 0, 199, 84);
		panelBottom.add(btnDelete);
		btnDelete.setBackground(Color.WHITE);
		
		JButton btnClose = new JButton("돌아가기");
		btnClose.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(596, 0, 198, 84);
		panelBottom.add(btnClose);
		btnClose.setBackground(Color.WHITE);
		
		setVisible(true);
	}
	
	public static void loadTable() {
		try {
			model.setNumRows(0);
			Jdbc.query("select", "select * from book");
			while(Jdbc.rs.next()) {
				model.addRow(new Object[] {Jdbc.rs.getString("number"), Jdbc.rs.getString("genre"),
						Jdbc.rs.getString("name"), Jdbc.rs.getString("author"), Jdbc.rs.getString("publish"),
						Jdbc.rs.getString("rating"), Jdbc.rs.getString("viewCount")});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
