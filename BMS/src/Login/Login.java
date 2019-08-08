package Login;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import DAO.DAO_login;
import DTO.DTO_std;

import javax.swing.JPasswordField;


public class Login extends JFrame implements ActionListener {

	private final ButtonGroup lGroup = new ButtonGroup(); //���� ��ư �� �ϳ��� ��ư�� ������ �� �ְ� �ϱ� ���� ��ư�׷� Ŭ������ ���������.
	private JTextField Id_tf; // JFrame ���̵� �Է��� �׸�ĭ
	private JPasswordField Pw_tf; // JFrame ��� �Է��� �׸�ĭ *** �� ǥ�õ�
	JButton login, find, register; // �α���, ID/PW ã��, ��� ��ư
	JRadioButton stdLB, profLB, mngLB; // ���� ��ư

	public Login() {
		setTitle("�α��� "); // JFrame ����
		getContentPane().setLayout(null); // ���� Pane�� ���̾ƿ�(��ġ������)��  �����Ӱ� ������ ���̱� ������ null ������ ����

		stdLB = new JRadioButton("�л�"); // �л�
		lGroup.add(stdLB);
		stdLB.setBounds(187, 45, 60, 37); // setBounds ��ư�� ��ġ ���� (or setSize, setLocation �ΰ����� ���� �����ؾ���)
		getContentPane().add(stdLB); // ���� ContentPane �� �߰� �ϰڴٴ� �ǹ� �Լ�

		profLB = new JRadioButton("����"); // ����
		lGroup.add(profLB);
		profLB.setBounds(267, 45, 60, 37);
		getContentPane().add(profLB);

		mngLB = new JRadioButton("������"); // ������
		lGroup.add(mngLB);
		mngLB.setBounds(348, 45, 82, 37);
		getContentPane().add(mngLB);

		JLabel lid = new JLabel("���̵�");
		lid.setBounds(174, 128, 57, 15);
		getContentPane().add(lid);

		JLabel lpw = new JLabel("���");
		lpw.setBounds(184, 195, 32, 15);
		getContentPane().add(lpw);

		// ���̵�text
		Id_tf = new JTextField();
		Id_tf.setBounds(293, 125, 126, 21);
		getContentPane().add(Id_tf);
		Id_tf.setColumns(10);

		// ���text
		Pw_tf = new JPasswordField();
		Pw_tf.setBounds(293, 192, 126, 21);
		getContentPane().add(Pw_tf);
		Pw_tf.setColumns(10);

		// --��ư
		login = new JButton("�α���");
		login.setBounds(113, 263, 90, 37);
		getContentPane().add(login);
		login.addActionListener(this); // addActionListener ActionListener�� ����(implements)�ϴ� �ƹ� ���ڰ� ���� �ȴ�
		                               // ���⼭ this�� ActionListener�� implements �޴� Login Ŭ���� �ڱ��ڽ��� ���ϴ� ���̴�.
		
		find = new JButton("ID/PW ã��");
		find.setBounds(242, 263, 111, 37);
		getContentPane().add(find);
		find.addActionListener(this);

		register = new JButton("����");
		register.setBounds(396, 263, 82, 37);
		getContentPane().add(register);
		register.addActionListener(this);

		// ��ư--

		setLocation(100, 100); // JFrame �� ������ ������ ��ġ
	    setPreferredSize(new Dimension(600, 400)); //JFrame�� ������ �Ǵ� ������

		setDefaultCloseOperation(EXIT_ON_CLOSE); // JFrame ������â ����� (x ǥ�� Ŭ��) �޸𸮿��� ���μ��� ����
		pack(); // JFrame â�ȿ� �ִ� ��� ���� Component �� setPreferredSize �������� �ڵ����� �����ִ� �Լ�
		setVisible(true); //JFrame�� ���� �������� �Լ� *�ݵ�� true, false �ʿ� ������ â�� ���� ����
	} // public Login() -- END

	@Override
	public void actionPerformed(ActionEvent e) { //ActionListener �� �����Ҷ� �ݵ�� ���;ߵǴ� �Լ� , ��ư �������� �����Լ�

		DTO_std dto_std = new DTO_std();
		DAO_login dao_lg = new DAO_login();

		

		// �α��� �κ�
		if (e.getSource() == login) { // login ��ư ������ �´��� Ȯ��

			if (stdLB.isSelected() == true) { // �л� ������ư Ŭ�� �Ȱ��� Ȯ�� 

				dto_std = dao_lg.select_std_num(Id_tf.getText()); // std ���̺�, ���̵�
				
			

				if (Id_tf.getText().equals(dto_std.get_stdNum())) { // -- text ���� ��� name �� ��

					
					if (String.valueOf(Pw_tf.getPassword()).equals(dto_std.get_stdPw())) { //String.valueOf() : char ���� string���� ��ȯ
						
						
						
						JOptionPane.showMessageDialog(null, "�ݰ����ϴ�. \t " + " \t " + dto_std.get_stdName() + "�� ^-^");


						dispose(); // ���� â �ݱ�

					}else {
						JOptionPane.showMessageDialog(null, " ��� �� �ٽ� Ȯ���Ͽ� �ֽʽÿ�.^-^");
					} // if (String.valueOf(Pw_tf.getPassword()).equals(dto_std.get_stdPw())) -- END
					
				} // if (Id_tf.getText().equals(dto_std.get_stdNum())) -- END
				
			}//if (stdLB.isSelected() == true) -- END
			
		} //if (e.getSource() == login) -- END
		
	}// public void actionPerformed(ActionEvent e) -- END
	
}// public class Login extends JFrame implements ActionListener -- END
