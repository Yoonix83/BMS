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
	
	JButton loginWd = new JButton("�α���");
	
	public Find_Final(String name, String id, String pw){
		setTitle("�ݰ����ϴ�. "+ name + " ��");
		
		JPanel panel = new JPanel(); // �г� ����
		getContentPane().add(panel, BorderLayout.CENTER); // JFrame �� panel �� �ְ� JFrame �� CENTER �� ��ġ ����
		panel.setLayout(null); // �гξ��� Layout �� null ������ �����Ӱ�
		
		// �̸� Lable
		JLabel fName = new JLabel(name);
		fName.setBounds(319, 113, 91, 15);
		panel.add(fName);

		// ��� Lable
		JLabel fPw_tf = new JLabel(pw);
		fPw_tf.setBounds(322, 180, 105, 15);
		panel.add(fPw_tf);
	
		JLabel fId = new JLabel("��ȣ");
		fId.setBounds(199, 113, 45, 15);
		panel.add(fId);

		JLabel fPw = new JLabel("���");
		fPw.setBounds(199, 180, 38, 15);
		panel.add(fPw);
	
		// --��ư
		
		loginWd.setBounds(189, 258, 203, 29);
		panel.add(loginWd);
		loginWd.addActionListener(this);
		// ��ư--
		
		setLocation(100, 100);
		setPreferredSize(new Dimension(600, 400));

		pack();
		setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == loginWd) { // �α���â ��ư
			new Login();
			dispose();

		}

	}
}
