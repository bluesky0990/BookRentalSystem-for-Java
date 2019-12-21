package FormAdmin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import Data.Jdbc;
import javax.swing.SwingConstants;

public class AdminBookModifyContent extends JFrame {

	private JPanel panel;
	public JEditorPane editContent;

	/**
	 * Create the frame.
	 */
	public AdminBookModifyContent() {
		setBounds(100, 100, 450, 450);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel lblBookName = new JLabel(AdminBookModify.bookName);
		lblBookName.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookName.setFont(new Font("나눔바른고딕", Font.PLAIN, 30));
		lblBookName.setBounds(14, 17, 258, 30);
		panel.add(lblBookName);
		
		JLabel lblBookMsg = new JLabel("책 번호:");
		lblBookMsg.setFont(new Font("나눔바른고딕", Font.PLAIN, 23));
		lblBookMsg.setBounds(286, 12, 80, 25);
		panel.add(lblBookMsg);
		
		JLabel lblBookCode = new JLabel(AdminBookModify.bookNumber + "");
		lblBookCode.setFont(new Font("나눔바른고딕", Font.PLAIN, 23));
		lblBookCode.setBounds(373, 12, 45, 25);
		panel.add(lblBookCode);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(286, 36, 130, 2);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.LIGHT_GRAY);
		separator_1.setBounds(14, 66, 404, 9);
		panel.add(separator_1);
		
		JLabel lblNotice = new JLabel("줄거리 편집기");
		lblNotice.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		lblNotice.setBounds(24, 80, 85, 18);
		panel.add(lblNotice);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 110, 385, 200);
		panel.add(scrollPane);
		
		editContent = new JEditorPane();
		editContent.setText(AdminBookModify.bookContent);
		scrollPane.setViewportView(editContent);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.LIGHT_GRAY);
		separator_2.setBounds(14, 320, 404, 9);
		panel.add(separator_2);
		
		JPanel panelBottom = new JPanel();
		panelBottom.setLayout(null);
		panelBottom.setBounds(0, 333, 432, 70);
		panel.add(panelBottom);
		
		JButton btnConfirm = new JButton("확인");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Jdbc.query("update", "update book set content = '"+editContent.getText()+"' where number = " + AdminBookModify.bookNumber);
					AdminBookModify.bookContent = editContent.getText();
					dispose();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "�ѱ�, ����, ���ڸ� �Է��� �� �ֽ��ϴ�.", "", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		btnConfirm.setFont(new Font("나눔바른고딕", Font.PLAIN, 18));
		btnConfirm.setBackground(Color.WHITE);
		btnConfirm.setBounds(0, 0, 216, 70);
		panelBottom.add(btnConfirm);
		
		JButton btnCancle = new JButton("취소");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancle.setFont(new Font("나눔바른고딕", Font.PLAIN, 18));
		btnCancle.setBackground(Color.WHITE);
		btnCancle.setBounds(216, 0, 216, 70);
		panelBottom.add(btnCancle);
		
		setVisible(true);
	}
}
