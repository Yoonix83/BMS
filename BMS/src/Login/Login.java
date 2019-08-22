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
import DTO.DTO_emp;
import DTO.DTO_pro;
import DTO.DTO_std;
import Pro.Professor_Page;
import Std.Student_Page;

import javax.swing.JPasswordField;


public class Login extends JFrame implements ActionListener {

	private final ButtonGroup lGroup = new ButtonGroup(); //���� ��ư �� �ϳ��� ��ư�� ������ �� �ְ� �ϱ� ���� ��ư�׷� Ŭ������ ���������.
	private JTextField Num_tf; // JFrame ���̵� �Է��� �׸�ĭ
	private JPasswordField Pw_tf; // JFrame ��� �Է��� �׸�ĭ *** �� ǥ�õ�
	JButton login, find, register; // �α���, PW ã��, ��� ��ư
	JRadioButton stdRB, profRB, empRB; // ���� ��ư

	public Login() {
		setTitle("�α��� "); // JFrame ����
		getContentPane().setLayout(null); // ���� Pane�� ���̾ƿ�(��ġ������)��  �����Ӱ� ������ ���̱� ������ null ������ ����

		stdRB = new JRadioButton("�л�"); // �л�
		lGroup.add(stdRB);
		stdRB.setBounds(187, 45, 60, 37); // setBounds ��ư�� ��ġ ���� (or setSize, setLocation �ΰ����� ���� �����ؾ���)
		getContentPane().add(stdRB); // ���� ContentPane �� �߰� �ϰڴٴ� �ǹ� �Լ�

		profRB = new JRadioButton("����"); // ����
		lGroup.add(profRB);
		profRB.setBounds(267, 45, 60, 37);
		getContentPane().add(profRB);

		empRB = new JRadioButton("������"); // ������
		lGroup.add(empRB);
		empRB.setBounds(348, 45, 82, 37);
		getContentPane().add(empRB);

		JLabel lnum = new JLabel("��ȣ");
		lnum.setBounds(187, 129, 57, 15);
		getContentPane().add(lnum);

		JLabel lpw = new JLabel("���");
		lpw.setBounds(187, 196, 32, 15);
		getContentPane().add(lpw);

		// ���̵�text
		Num_tf = new JTextField();
		Num_tf.setBounds(293, 125, 126, 21);
		getContentPane().add(Num_tf);
		Num_tf.setColumns(10);

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
		
		find = new JButton("PW ã��");
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
		DTO_pro dto_pro = new DTO_pro();
		DTO_emp dto_emp = new DTO_emp();
		DAO_login dao_lg = new DAO_login();

		

		// �α��� �κ�
		if (e.getSource() == login) { // login ��ư ������ �´��� Ȯ��

			if (stdRB.isSelected() == true) { // �л� ������ư Ŭ�� �Ȱ��� Ȯ�� 

				dto_std = dao_lg.select_std_num(Num_tf.getText()); // std ���̺�, ���̵�
				
				if (Num_tf.getText().equals(dto_std.get_stdNum())) { // -- text ���� ��� name �� ��
	
					if (String.valueOf(Pw_tf.getPassword()).equals(dto_std.get_stdPw())) { //String.valueOf() : char ���� string���� ��ȯ

						JOptionPane.showMessageDialog(null, "�ݰ����ϴ�. \t " + " \t " + dto_std.get_stdName() + "�� ^-^");
						
						new Student_Page(dto_std.get_stdNum());
						
						dispose(); // ���� â �ݱ�

					}else {
						JOptionPane.showMessageDialog(null, " ��� �� �ٽ� Ȯ���Ͽ� �ֽʽÿ�.^-^");
					} 
					
				} // if (Num_tf.getText().equals(dto_std.get_stdNum())) -- END
				
			}//if (stdRB.isSelected() == true) -- END
			else if (profRB.isSelected() == true) { // ���� Ȯ�� 

				dto_pro = dao_lg.select_pro_num(Num_tf.getText()); 
				
				if (Num_tf.getText().equals(dto_pro.get_proNum())) {
					
					if (String.valueOf(Pw_tf.getPassword()).equals(dto_pro.get_proPw())) {
						
						JOptionPane.showMessageDialog(null, "�ݰ����ϴ�. \t " + " \t " + dto_pro.get_proName() + "�� ^-^");
						
						new Professor_Page(dto_pro.get_proNum(), dto_pro.get_proMajor());//�α��� �� ���� ��ȣ �� �Ѱ��ֱ�
						
						dispose(); //  â �ݱ�

						}else {
							JOptionPane.showMessageDialog(null, " ��� �� �ٽ� Ȯ���Ͽ� �ֽʽÿ�.^-^");
						}
					
					}//if (Num_tf.getText().equals(dto_pro.get_proNum()))  -- END
				
			}//else if (profRB.isSelected() == true) -- END
			else if (empRB.isSelected() == true) { // ���� Ȯ�� 

				dto_emp = dao_lg.select_emp_num(Num_tf.getText()); 

				if (Num_tf.getText().equals(dto_emp.get_empNum())) {

					if (String.valueOf(Pw_tf.getPassword()).equals(dto_emp.get_empPw())) {
						
						JOptionPane.showMessageDialog(null, "�ݰ����ϴ�. \t " + " \t " + dto_emp.get_empName() + "�� ^-^");

						dispose(); //  â �ݱ�

						}else {
							JOptionPane.showMessageDialog(null, " ��� �� �ٽ� Ȯ���Ͽ� �ֽʽÿ�.^-^");
						}
					}//if (Num_tf.getText().equals(dto_emp.get_empNum())) -- END
				
			}//else if (empRB.isSelected() == true) -- END
			
		}//if (e.getSource() == login) -- END
		else if(e.getSource() == find) {
			
			new Find_Main();
			
			dispose(); //���� ������ ����
			
		}else if (e.getSource() == register) { // ����
			
			new Register();
			
		}

		
		
	}// public void actionPerformed(ActionEvent e) -- END
	
}// public class Login extends JFrame implements ActionListener -- END
