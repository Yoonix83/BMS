package Login;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import DAO.DAO_login;
import DTO.DTO_mjr;
import DTO.DTO_std;

public class Register extends JFrame implements ActionListener{
	
	private final ButtonGroup rGroup = new ButtonGroup();
	private JRadioButton stdRd;
	private JTextField rNum_tf;
	private JTextField rName_tf;
	private JTextField rPw_tf;
	private JTextField rPhone_tf;

	JButton loginWd, regist, clear;
	String Year[] = { "1", "2", "3", "4" }; // �г�
	
	JComboBox year_combo, major_combo;

	DAO_login dao_lg = new DAO_login();
	DTO_std dto_std = new DTO_std();
	DTO_mjr dto_mjr = new DTO_mjr();
	
	
	public Register() {
		
		setTitle("��� ����");
		getContentPane().setLayout(null);

		stdRd = new JRadioButton("�л�");
		rGroup.add(stdRd);
		stdRd.setBounds(195, 34, 57, 23);
		getContentPane().add(stdRd);

//		profRd = new JRadioButton("����"); // 2019.08.10 ���� ���� ����
//		rGroup.add(profRd);
//		profRd.setBounds(333, 34, 57, 23);
//		getContentPane().add(profRd);

		JLabel num = new JLabel("��ȣ");
		num.setBounds(195, 89, 30, 15);
		getContentPane().add(num);

		JLabel name = new JLabel("�̸�");
		name.setBounds(195, 120, 30, 15);
		getContentPane().add(name);

		JLabel dept = new JLabel("�а�");
		dept.setBounds(196, 151, 30, 15);
		getContentPane().add(dept);

		JLabel year = new JLabel("�г�");
		year.setBounds(196, 182, 30, 15);
		getContentPane().add(year);

		JLabel pw = new JLabel("���");
		pw.setBounds(195, 217, 30, 15);
		getContentPane().add(pw);

		JLabel tel = new JLabel("��ȭ��ȣ");
		tel.setBounds(178, 248, 57, 15);
		getContentPane().add(tel);

		// ��ȣtext
		rNum_tf = new JTextField();
		rNum_tf.setBounds(260, 86, 145, 21);
		getContentPane().add(rNum_tf);
		rNum_tf.setColumns(10);	 

		// �̸�text
		rName_tf = new JTextField();
		rName_tf.setBounds(260, 117, 145, 21);
		getContentPane().add(rName_tf);
		rName_tf.setColumns(10);

		// ���text
		rPw_tf = new JTextField();
		rPw_tf.setBounds(260, 214, 145, 21);
		getContentPane().add(rPw_tf);
		rPw_tf.setColumns(10);

		// ��ȭ��ȣtext
		rPhone_tf = new JTextField();
		rPhone_tf.setBounds(260, 245, 145, 21);
		getContentPane().add(rPhone_tf);
		rPhone_tf.setColumns(10);

		// --��ư
		loginWd = new JButton("�α���â"); // �α���â
		loginWd.setBounds(164, 314, 90, 23);
		getContentPane().add(loginWd);
		loginWd.addActionListener(this);

		regist = new JButton("���"); // ���
		regist.setBounds(265, 314, 78, 23);
		getContentPane().add(regist);
		regist.addActionListener(this);

		clear = new JButton("CLEAR"); // CLEAR
		clear.setBounds(354, 314, 81, 23);
		getContentPane().add(clear);
		clear.addActionListener(this);
		// ��ư--

		// --JComboBox
		DAO_login dao_lg_list = new DAO_login<>();

		// -- �а�
		//
		String arr[] = new String[8];

		major_combo = new JComboBox();
		major_combo.setBounds(260, 148, 145, 21);
		getContentPane().add(major_combo);
		major_combo.addItem("����");

		ArrayList<DTO_mjr> list = dao_lg_list.select_mjr_info(); // DAO ���� list Ÿ������ ��� dto ������ ArrayList<> list �� �ٽ� ��´�
		
		for (DTO_mjr dto_mjr : list) { // list ���� �ִ� ������ ��ŭ for ���� �̿��� Name ���� ComboBox �� ���ʷ� �ִ´�.

			major_combo.addItem(dto_mjr.get_mjrName());
		}

		// -- �г�

		year_combo = new JComboBox();
		year_combo.setBounds(260, 179, 145, 21);
		getContentPane().add(year_combo);
		year_combo.addItem("����");
		for (int i = 0; i < Year.length; i++) {
			year_combo.addItem(Year[i]);

		}

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

		if (e.getSource() == regist) { // ��� ��ư

			if (stdRd.isSelected() == true) { // �л� ��

				if (!("����").equals(year_combo.getSelectedItem().toString())
						&& !("����").equals(major_combo.getSelectedItem().toString())) { // �г�/���� ComboBox �� "����"���� ������ �ʰ� �����̵� ���� �����ߴٸ�

					// - insert std dao (�л�)���� �κ�
					dao_lg.insert_std_info(rNum_tf.getText(), rName_tf.getText(),year_combo.getSelectedItem().toString(),
							major_combo.getSelectedItem().toString(), rPw_tf.getText(), rPhone_tf.getText());

					// id ������ name�� ã�ƿ� alert â�� ����ȯ�� �޼����� ����ش�.
					JOptionPane.showMessageDialog(null,
							dao_lg.select_std_num(rNum_tf.getText()).get_stdName() + "�� KOSEA�� �л��� �ǽ� �� ȯ���մϴ�. ^- ^/");

					dispose(); // ���� ȭ���� �ݰ� ���� ȭ������ ���̰� �ϱ����� 
					
				} else {

					JOptionPane.showMessageDialog(null, "[ �а� ] �� [ �г� ] �ٽ� Ȯ�����ּ��� !! ��_ ��");

				}

			}
		} // ��� END --

		if (e.getSource() == clear)

		{ // Clear��ư
			rNum_tf.setText("");
			rName_tf.setText("");
			rPw_tf.setText("");
			rPhone_tf.setText("");
		}

	}// action END --

}
