package FormAdmin;

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
import FormBook.BookOpen;
import javax.swing.ListSelectionModel;

public class AdminMemList extends JFrame {

	private JPanel panel;
	private JTextField txtSearch;
	private JButton btnToAdminMain;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public static JTable table;
	int rows = 1;

	public static Object rowValue;
	public static DefaultTableModel model;
	/**
	 * Create the frame.
	 */
	public AdminMemList() {
		setResizable(false);
		setBounds(100, 100, 800, 600);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JComboBox comboxRank = new JComboBox();
		comboxRank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(comboxRank.getSelectedItem() == "전체") {
						Jdbc.query("select", "select * from member");
					} else if(comboxRank.getSelectedItem() == "X") {
						Jdbc.query("select", "select * from member where permission = 1");
					} else if(comboxRank.getSelectedItem() == "S") {
						Jdbc.query("select", "select * from member where allBorrow >= 1000");
					} else if(comboxRank.getSelectedItem() == "A") {
						Jdbc.query("select", "select * from member where allBorrow >= 300");
					} else if(comboxRank.getSelectedItem() == "B") {
						Jdbc.query("select", "select * from member where allBorrow >= 100");
					} else if(comboxRank.getSelectedItem() == "C") {
						Jdbc.query("select", "select * from member where allBorrow < 100");
					}
					model.setNumRows(0);
					while(Jdbc.rs.next()) {
						model.addRow(new Object[] {Jdbc.rs.getInt("number"), Jdbc.rs.getString("id"),
								Jdbc.rs.getString("pw"), Jdbc.rs.getString("name"), Jdbc.rs.getInt("permission")});
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		comboxRank.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		comboxRank.setBackground(Color.WHITE);
		comboxRank.setMaximumRowCount(5);
		comboxRank.setModel(new DefaultComboBoxModel(new String[] {"전체", "X", "S", "A", "B", "C"}));
		comboxRank.setBounds(14, 13, 110, 30);
		panel.add(comboxRank);
		
		txtSearch = new JTextField("ID");
		txtSearch.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		txtSearch.setBounds(138, 13, 390, 30);
		panel.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnSearch = new JButton("검색");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(comboxRank.getSelectedItem() == "전체") {
						Jdbc.query("select", "select * from member where id like '%" + txtSearch.getText() + "%'");
					} else if(comboxRank.getSelectedItem() == "X") {
						Jdbc.query("select", "select * from member where id like '%" + txtSearch.getText() + "%' and permission = 1");
					} else if(comboxRank.getSelectedItem() == "S") {
						Jdbc.query("select", "select * from member where id like '%" + txtSearch.getText() + "%' and allBorrow >= 1000");
					} else if(comboxRank.getSelectedItem() == "A") {
						Jdbc.query("select", "select * from member where id like '%" + txtSearch.getText() + "%' and allBorrow >= 300");
					} else if(comboxRank.getSelectedItem() == "B") {
						Jdbc.query("select", "select * from member where id like '%" + txtSearch.getText() + "%' and allBorrow >= 100");
					} else if(comboxRank.getSelectedItem() == "C") {
						Jdbc.query("select", "select * from member where id like '%" + txtSearch.getText() + "%' and allBorrow < 100");
					}
					model.setNumRows(0);
					while(Jdbc.rs.next()) {
						model.addRow(new Object[] {Jdbc.rs.getInt("number"), Jdbc.rs.getString("id"),
								Jdbc.rs.getString("pw"), Jdbc.rs.getString("name"), Jdbc.rs.getInt("permission")});
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
		
		btnToAdminMain = new JButton("관리자메인");
		btnToAdminMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnToAdminMain.setBackground(Color.WHITE);
		btnToAdminMain.setFont(new Font("나눔바른고딕", Font.PLAIN, 11));
		btnToAdminMain.setBounds(672, 15, 93, 25);
		panel.add(btnToAdminMain);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBackground(Color.WHITE);
		separator.setBounds(14, 55, 754, 2);
		panel.add(separator);
		
		JRadioButton rdbtnNormal = new JRadioButton("기본순");
		rdbtnNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboxRank.setSelectedItem("전체");
				txtSearch.setText("ID");
				loadTable();
			}
		});
		rdbtnNormal.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		rdbtnNormal.setSelected(true);
		buttonGroup.add(rdbtnNormal);
		rdbtnNormal.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNormal.setBounds(563, 67, 70, 25);
		panel.add(rdbtnNormal);
		
		JRadioButton rdbtnBorrowNum = new JRadioButton("대출누적순");
		rdbtnBorrowNum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					comboxRank.setSelectedItem("전체");
					txtSearch.setText("ID");
					model.setNumRows(0);
					Jdbc.query("select", "select * from member order by allBorrow desc");
					while(Jdbc.rs.next()) {
						model.addRow(new Object[] {Jdbc.rs.getString("number"), Jdbc.rs.getString("id"),
								Jdbc.rs.getString("pw"), Jdbc.rs.getString("name"), Jdbc.rs.getString("permission")});
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		rdbtnBorrowNum.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		buttonGroup.add(rdbtnBorrowNum);
		rdbtnBorrowNum.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnBorrowNum.setBounds(633, 67, 95, 25);
		panel.add(rdbtnBorrowNum);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 100, 754, 369);
		panel.add(scrollPane);
		

		String clname[] = {"No", "ID", "PW", "Name", "Permission"};
		model = new DefaultTableModel(clname, 0) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
				};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		scrollPane.setViewportView(table);
		
		try {
			Jdbc.query("select", "select * from member");
			while(Jdbc.rs.next()) {
				model.addRow(new Object[] {Jdbc.rs.getInt("number"), Jdbc.rs.getString("id"),
						Jdbc.rs.getString("pw"), Jdbc.rs.getString("name"), Jdbc.rs.getInt("permission")});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(0).setMinWidth(100);
		table.getColumnModel().getColumn(0).setMaxWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(185);
		table.getColumnModel().getColumn(1).setMinWidth(185);
		table.getColumnModel().getColumn(1).setMaxWidth(185);
		table.getColumnModel().getColumn(2).setPreferredWidth(185);
		table.getColumnModel().getColumn(2).setMinWidth(185);
		table.getColumnModel().getColumn(2).setMaxWidth(185);
		table.getColumnModel().getColumn(3).setPreferredWidth(185);
		table.getColumnModel().getColumn(3).setMinWidth(185);
		table.getColumnModel().getColumn(3).setMaxWidth(185);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setMinWidth(100);
		table.getColumnModel().getColumn(4).setMaxWidth(100);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(0, 479, 794, 11);
		panel.add(separator_1);
		
		JPanel panelBottom = new JPanel();
		panelBottom.setBounds(0, 481, 794, 84);
		panel.add(panelBottom);
		panelBottom.setLayout(null);
		
		
		JButton btnModify = new JButton("수정하기");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if(row != -1) {
					rowValue = table.getValueAt(row, 0);
					new AdminMemModify();
				} else {
					JOptionPane.showMessageDialog(null, "데이터를 선택하여 주십시오.", "", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnModify.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnModify.setBounds(264, 0, 266, 84);
		panelBottom.add(btnModify);
		btnModify.setBackground(Color.WHITE);
		
		JButton btnBorrowCheck = new JButton("대출내역");
		btnBorrowCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row != -1) {
					rowValue = table.getValueAt(row, 0);
					try {
						int index = 0;
						String bookName[] = new String[3];
						Jdbc.query("select", "select * from borrow where member_number = " + rowValue);
						while(Jdbc.rs.next()) {
							bookName[index] = Jdbc.rs.getString("book_name");
							index++;
						}
						
						switch (index) {
						case 1:
							JOptionPane.showMessageDialog(null, "현재 대출중인 도서: [ " + bookName[0] + " ]");
							break;

						case 2:
							JOptionPane.showMessageDialog(null, "현재 대출중인 도서: [ " + bookName[0] + " ], [ " + bookName[1] + " ]");
							break;

						case 3:
							JOptionPane.showMessageDialog(null, "현재 대출중인 도서: [ " + bookName[0] + " ], [ " + bookName[1] + " ], [ " + bookName[2] + " ]");
							break;
							
						default:
							JOptionPane.showMessageDialog(null, "현재 대출중인 도서가 없습니다.");
							break;
								
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "데이터를 선택하여 주십시오.", "", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnBorrowCheck.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnBorrowCheck.setBounds(0, 0, 264, 84);
		panelBottom.add(btnBorrowCheck);
		btnBorrowCheck.setBackground(Color.WHITE);
		
		JButton btnCancle = new JButton("돌아가기");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancle.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnCancle.setBackground(Color.WHITE);
		btnCancle.setBounds(530, 0, 264, 84);
		panelBottom.add(btnCancle);
		
		setVisible(true);
	}
	
	public static void loadTable() {
		try {
			model.setNumRows(0);
			Jdbc.query("select", "select * from member");
			while(Jdbc.rs.next()) {
				model.addRow(new Object[] {Jdbc.rs.getString("number"), Jdbc.rs.getString("id"),
						Jdbc.rs.getString("pw"), Jdbc.rs.getString("name"), Jdbc.rs.getString("permission")});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
