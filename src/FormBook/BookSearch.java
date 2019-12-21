package FormBook;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

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
import FormLogin.Login;
import javax.swing.ListSelectionModel;

public class BookSearch extends JFrame {

	private JPanel panel;
	private JTextField txtSearch;
	private JButton btnMonth;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable table;
	int rows = 1;
	
	public static int number;
	public static String genre;
	public static String name;
	public static String author;
	public static String publish;
	public static int price;
	public static float rating;
	public static int viewCount;
	public static String content;
	
	DefaultTableModel model;
	public static Object rowValue = 0;

	/**
	 * Create the frame.
	 */
	public BookSearch() {
		setResizable(false);
		setBounds(100, 100, 800, 600);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JComboBox comboxGenre = new JComboBox();
		comboxGenre.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		comboxGenre.setBackground(Color.WHITE);
		comboxGenre.setMaximumRowCount(5);
		comboxGenre.setModel(new DefaultComboBoxModel(new String[] {"전체", "경영", "경제", "만화책", "문학", "심리", "소설", "아동", "여행", "인문", "영문학", "자기계발", "철학"}));
		comboxGenre.setBounds(14, 13, 110, 30);
		panel.add(comboxGenre);
		
		txtSearch = new JTextField("책 제목");
		txtSearch.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		txtSearch.setBounds(138, 13, 390, 30);
		panel.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnSearch = new JButton("검색");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Jdbc.query("select", "select * from book where name like '%" + txtSearch.getText() + "%'");
					model.setNumRows(0);
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
		btnSearch.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setBounds(535, 12, 115, 32);
		panel.add(btnSearch);
		
		btnMonth = new JButton("이달의도서");
		btnMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new BookMonth();
				dispose();
			}
		});
		btnMonth.setBackground(Color.WHITE);
		btnMonth.setFont(new Font("나눔바른고딕", Font.PLAIN, 11));
		btnMonth.setBounds(672, 15, 93, 25);
		panel.add(btnMonth);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBackground(Color.WHITE);
		separator.setBounds(14, 55, 754, 2);
		panel.add(separator);
		
		JRadioButton rdbtnNormal = new JRadioButton("기본순");
		rdbtnNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboxGenre.setSelectedItem("전체");
				txtSearch.setText("책 제목");
				tableLoad();
			}
		});
		rdbtnNormal.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		rdbtnNormal.setSelected(true);
		buttonGroup.add(rdbtnNormal);
		rdbtnNormal.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNormal.setBounds(514, 65, 70, 25);
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
		rdbtnOpenCount.setBounds(590, 65, 70, 25);
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
		rdbtnScore.setBounds(666, 65, 70, 25);
		panel.add(rdbtnScore);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 100, 754, 369);
		panel.add(scrollPane);
		

		String clname[] = {"No", "장르", "제목", "저자", "출판사", "평점", "조회수"};
		model = new DefaultTableModel(clname, 0) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false
				};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		
		tableLoad();
		
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
		
		JButton btnOpen = new JButton("조회하기");
		btnOpen.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row != -1) {
					rowValue = table.getValueAt(row, 0);
					try {
						Jdbc.query("select", "select * from book where number = '"+rowValue+"'");
						BookOpen.settingContent();
						Jdbc.query("update", "update book set viewCount = " + ++BookOpen.viewCount + " where number = " + rowValue);
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					new BookOpen();
				} else {
					JOptionPane.showMessageDialog(null, "도서를 선택하여 주십시오.", "", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnOpen.setBounds(0, 0, 264, 84);
		panelBottom.add(btnOpen);
		btnOpen.setBackground(Color.WHITE);
		
		JButton btnCocer = new JButton("찜하기");
		btnCocer.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnCocer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Login.userID != null && Login.userPW != null && Login.userName != null) {
					int row = table.getSelectedRow();
					if(row != -1) {
						try {
							rowValue = table.getValueAt(row, 0);
							Jdbc.query("select", "select * from book where number = " + rowValue);
							while(Jdbc.rs.next()) {
								number = Jdbc.rs.getInt("number");
								name = Jdbc.rs.getString("name");
							}
							
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
					} else {
						JOptionPane.showMessageDialog(null, "도서를 선택하여 주십시오.", "", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "비회원은 이용할 수 없는 기능입니다.", "", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCocer.setBounds(264, 0, 266, 84);
		panelBottom.add(btnCocer);
		btnCocer.setBackground(Color.WHITE);
		
		JButton btnClose = new JButton("돌아가기");
		btnClose.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(530, 0, 264, 84);
		panelBottom.add(btnClose);
		btnClose.setBackground(Color.WHITE);
		
		setVisible(true);
	}
	
	void tableLoad() {
		try {
			model.setNumRows(0);
			Jdbc.query("select", "select * from book");
			while(Jdbc.rs.next()) {
				model.addRow(new Object[] {Jdbc.rs.getInt("number"), Jdbc.rs.getString("genre"),
						Jdbc.rs.getString("name"), Jdbc.rs.getString("author"), Jdbc.rs.getString("publish"),
						Jdbc.rs.getString("rating"), Jdbc.rs.getString("viewCount")});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
