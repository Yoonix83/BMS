package Login;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Find_Final extends JFrame implements ActionListener{
	
	JButton loginWd = new JButton("로그인");
	
	public Find_Final(String name, String id, String pw){
		setTitle("반갑습니다. "+ name + " 님");
		
		JPanel panel = new JPanel(); // 패널 생성
		getContentPane().add(panel, BorderLayout.CENTER); // JFrame 에 panel 을 넣고 JFrame 의 CENTER 로 위치 조정
		panel.setLayout(null); // 패널안의 Layout 은 null 값으로 자유롭게
		
		// 이름 Lable
		JLabel fName = new JLabel(name);
		fName.setBounds(319, 113, 91, 15);
		panel.add(fName);

		// 비번 Lable
		JLabel fPw_tf = new JLabel(pw);
		fPw_tf.setBounds(322, 180, 105, 15);
		panel.add(fPw_tf);
	
		JLabel fId = new JLabel("번호");
		fId.setBounds(199, 113, 45, 15);
		panel.add(fId);

		JLabel fPw = new JLabel("비번");
		fPw.setBounds(199, 180, 38, 15);
		panel.add(fPw);
	
		// --버튼
		
		loginWd.setBounds(189, 258, 203, 29);
		panel.add(loginWd);
		loginWd.addActionListener(this);
		// 버튼--
		
		setLocation(100, 100);
		setPreferredSize(new Dimension(600, 400));

		pack();
		setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == loginWd) { // 로그인창 버튼
			new Login();
			dispose();

		}

	}
}
