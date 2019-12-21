package FormAdmin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Data.Jdbc;

public class AdminBookAdd extends JFrame {

	private JPanel panel;
	private JTextField txtName;
	private JTextField txtAuthor;
	private JTextField txtPublish;
	private JTextField txtPrice;
	
	JFileChooser jfc;
	File file;
	int returnVal;
	
	public static String bookContent = null;

	/**
	 * Create the frame.
	 */
	public AdminBookAdd() {
		setBounds(100, 100, 380, 480);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel lblNotice = new JLabel("책 등록");
		lblNotice.setFont(new Font("나눔바른고딕", Font.PLAIN, 25));
		lblNotice.setBounds(140, 12, 85, 28);
		panel.add(lblNotice);
		
		JLabel lblGenre = new JLabel("장르");
		lblGenre.setFont(new Font("나눔바른고딕", Font.PLAIN, 23));
		lblGenre.setBounds(40, 61, 60, 28);
		panel.add(lblGenre);
		
		JComboBox comboxGenre = new JComboBox();
		comboxGenre.setModel(new DefaultComboBoxModel(new String[] {"경영", "경제", "만화책", "문학", "심리", "소설", "아동", "여행", "인문", "영문학", "자기계발", "철학"}));
		comboxGenre.setBounds(105, 61, 230, 28);
		panel.add(comboxGenre);
		
		JLabel lblName = new JLabel("제목");
		lblName.setFont(new Font("나눔바른고딕", Font.PLAIN, 23));
		lblName.setBounds(40, 101, 60, 28);
		panel.add(lblName);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(105, 101, 230, 28);
		panel.add(txtName);
		
		JLabel lblAuthor = new JLabel("저자");
		lblAuthor.setFont(new Font("나눔바른고딕", Font.PLAIN, 23));
		lblAuthor.setBounds(40, 141, 60, 28);
		panel.add(lblAuthor);
		
		txtAuthor = new JTextField();
		txtAuthor.setColumns(10);
		txtAuthor.setBounds(105, 141, 230, 28);
		panel.add(txtAuthor);
		
		JLabel lblPublish = new JLabel("출판사");
		lblPublish.setFont(new Font("나눔바른고딕", Font.PLAIN, 23));
		lblPublish.setBounds(30, 181, 70, 28);
		panel.add(lblPublish);
		
		txtPublish = new JTextField();
		txtPublish.setColumns(10);
		txtPublish.setBounds(105, 181, 230, 28);
		panel.add(txtPublish);
		
		JLabel lblPrice = new JLabel("가격");
		lblPrice.setFont(new Font("나눔바른고딕", Font.PLAIN, 23));
		lblPrice.setBounds(40, 221, 60, 28);
		panel.add(lblPrice);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(105, 221, 230, 28);
		panel.add(txtPrice);
		
		JLabel lblContent = new JLabel("줄거리");
		lblContent.setFont(new Font("나눔바른고딕", Font.PLAIN, 23));
		lblContent.setBounds(30, 261, 70, 28);
		panel.add(lblContent);
		
		JButton btnContentModify = new JButton("줄거리 수정");
		btnContentModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminBookAddContent();
			}
		});
		btnContentModify.setBackground(Color.WHITE);
		btnContentModify.setFont(new Font("나눔바른고딕", Font.PLAIN, 17));
		btnContentModify.setBounds(105, 261, 230, 28);
		panel.add(btnContentModify);
		
		JLabel lblImage = new JLabel("이미지");
		lblImage.setFont(new Font("나눔바른고딕", Font.PLAIN, 23));
		lblImage.setBounds(30, 299, 70, 28);
		panel.add(lblImage);
		
		JButton btnImageSelector = new JButton("이미지 선택");
		btnImageSelector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jfc = new JFileChooser();
				returnVal = jfc.showOpenDialog(null);
			}
		});
		btnImageSelector.setFont(new Font("나눔바른고딕", Font.PLAIN, 17));
		btnImageSelector.setBackground(Color.WHITE);
		btnImageSelector.setBounds(105, 299, 230, 28);
		panel.add(btnImageSelector);
		
		JPanel panelBottom = new JPanel();
		panelBottom.setBounds(0, 353, 362, 80);
		panel.add(panelBottom);
		panelBottom.setLayout(null);
		
		JButton btnConfirm = new JButton("확인");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				file = jfc.getSelectedFile();
				String[] fName = file.getName().split("\\.");
				String r = null;
				for(int i = 0; i < fName.length; i++) {
					r = fName[i];
				}
		        if(!r.equalsIgnoreCase("png")) {
					JOptionPane.showMessageDialog(null, "도서 이미지는 png 형식의 파일만 선택할 수 있습니다.", "", JOptionPane.ERROR_MESSAGE);
		        } else {
		        	if(returnVal == 0) {
						try {
							FileInputStream fis = new FileInputStream(file);
							FileOutputStream fos = new FileOutputStream("./images/" + txtName.getText() + ".png");
							
							int fileByte = 0;
							while((fileByte = fis.read()) != -1) {
				                fos.write(fileByte);
				            }
							fos.close();
							fis.close();
							
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
						try {
							Jdbc.query("insert",
								"insert into book values(null, '"+comboxGenre.getSelectedItem()+"', '"+txtName.getText()+"', '"+txtAuthor.getText()+"', '"+txtPublish.getText()+"', '"+Integer.parseInt(txtPrice.getText())+"', '"+0+"', '"+0+"', '"+0+"', '"+0+"', '"+bookContent+"', '"+file.getName()+"')");
							AdminBookManage.loadTable();
							dispose();
						} catch (SQLException e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "한글, 영문 또는 숫자만 입력이 가능합니다.", "", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "도서에 사용될 이미지를 선택하여 주십시오.", "", JOptionPane.ERROR_MESSAGE);
					}
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
