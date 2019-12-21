package FormLogin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class FindIDPW extends JFrame {

	private JPanel panel;
	
	
	/**
	 * Create the frame.
	 */
	public FindIDPW() {
		setResizable(false);
		setBounds(100, 100, 360, 180);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JButton btnFindID = new JButton("ID 찾기");
		btnFindID.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnFindID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FindID();
			}
		});
		btnFindID.setBackground(Color.WHITE);
		btnFindID.setBounds(15, 20, 150, 50);
		panel.add(btnFindID);
		
		JButton btnFindPW = new JButton("PW 찾기");
		btnFindPW.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnFindPW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FindPW();
			}
		});
		btnFindPW.setBackground(Color.WHITE);
		btnFindPW.setBounds(175, 20, 150, 50);
		panel.add(btnFindPW);
		
		JButton btnCancle = new JButton("취소");
		btnCancle.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancle.setBackground(Color.WHITE);
		btnCancle.setBounds(113, 90, 110, 30);
		panel.add(btnCancle);
		
		setVisible(true);
	}
}
