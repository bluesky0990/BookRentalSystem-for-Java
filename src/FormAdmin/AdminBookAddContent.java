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

public class AdminBookAddContent extends JFrame {

	private JPanel panel;
	public JEditorPane editContent;

	/**
	 * Create the frame.
	 */
	public AdminBookAddContent() {
		setBounds(100, 100, 450, 400);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.LIGHT_GRAY);
		separator_1.setBounds(14, 16, 404, 9);
		panel.add(separator_1);
		
		JLabel lblNotice = new JLabel("줄거리 편집기");
		lblNotice.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		lblNotice.setBounds(24, 30, 85, 18);
		panel.add(lblNotice);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 60, 385, 200);
		panel.add(scrollPane);
		
		editContent = new JEditorPane();
		editContent.setText(AdminBookModify.bookContent);
		scrollPane.setViewportView(editContent);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.LIGHT_GRAY);
		separator_2.setBounds(14, 270, 404, 9);
		panel.add(separator_2);
		
		JPanel panelBottom = new JPanel();
		panelBottom.setLayout(null);
		panelBottom.setBounds(0, 283, 432, 70);
		panel.add(panelBottom);
		
		JButton btnConfirm = new JButton("확인");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminBookAdd.bookContent = editContent.getText();
				dispose();
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
