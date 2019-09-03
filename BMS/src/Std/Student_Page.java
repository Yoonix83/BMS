package Std;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DAO.DAO_login;
import Login.Login;



public class Student_Page extends JFrame implements ActionListener {
	
	private JTextField sMajor_tf;
	private JTextField sName_tf;
	private JTextField sNum_tf;
	
	JButton classChk; 	// ������ȸ
	JButton classRegi;  // ������û
	JButton gradeChk;	// ������ȸ
	JButton sInfo;		// ��������
	JButton lOut;		// �α׾ƿ�
	
	private JPanel panel_1;
	private JPanel panel_2;
	
	String stdNum ; // dao_lgin ���� �й� �޾ƿ���
	 
	public Student_Page(String stdNum_lg ) {
		this.stdNum = stdNum_lg ;
		
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel(); // ���� �� �� �ؽ�Ʈ�ʵ� ���� �гλ���
		panel.setBounds(101, 0, 483, 56);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		//--DAO id�� ��Ī�Ǵ� �л� ���� ȣ��
		DAO_login dao_lg = new DAO_login();
		// DAO--
		
		JLabel sDept = new JLabel("�а� : "); // �а� �� ����
		sDept.setBounds(35, 25, 57, 15);
		panel.add(sDept);
		
		sMajor_tf = new JTextField(); // �а� �ؽ�Ʈ�ʵ� ����
		sMajor_tf.setEditable(false);
		sMajor_tf.setText(dao_lg.select_std_num(stdNum).get_stdMajor()); // ��Ī�� �л� DB �� �ҷ�����
		sMajor_tf.setBounds(78, 22, 81, 21);
		panel.add(sMajor_tf);
		sMajor_tf.setColumns(10);
		
		JLabel sName = new JLabel("�̸� : "); // �̸� �� ����
		sName.setBounds(182, 25, 57, 15);
		panel.add(sName);
		
		sName_tf = new JTextField(); // �̸� �ؽ�Ʈ�ʵ�
		sName_tf.setEditable(false);
		sName_tf.setText(dao_lg.select_std_num(stdNum).get_stdName());
		sName_tf.setBounds(219, 22, 64, 21);
		panel.add(sName_tf);
		sName_tf.setColumns(10);
		
		JLabel sNum = new JLabel("�й� : "); // �й� �� ����
		sNum.setBounds(295, 25, 57, 15);
		panel.add(sNum);
		
		sNum_tf = new JTextField(); // �й� �ؽ�Ʈ�ʵ�
		sNum_tf.setEditable(false);
		sNum_tf.setText(dao_lg.select_std_num(stdNum).get_stdNum());
		sNum_tf.setBounds(337, 22, 43, 21);
		panel.add(sNum_tf);
		sNum_tf.setColumns(10);
		
		panel_1 = new JPanel(); // ��ư ���� �гλ���
		panel_1.setBounds(0, 0, 99, 362);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		classChk = new JButton("������ȸ"); // ������ȸ ��ư ����
		classChk.setBounds(0, 141, 96, 23);
		panel_1.add(classChk);
		
		classRegi = new JButton("������û"); // ������û ��ư ����
		classRegi.setBounds(0, 96, 96, 23);
		panel_1.add(classRegi);
		
		gradeChk = new JButton("������ȸ"); // ������ȸ ��ư����
		gradeChk.setBounds(0, 188, 96, 23);
		panel_1.add(gradeChk);
		
		sInfo = new JButton("��������"); // �������� ��ư����
		sInfo.setBounds(0, 236, 96, 23);
		panel_1.add(sInfo);
		
		lOut = new JButton("�α׾ƿ�"); // �α׾ƿ� ��ư
		lOut.setBounds(0, 293, 96, 23);
		panel_1.add(lOut);
		
		panel_2 = new JPanel(); // ����(��ư ���� �� ��� ���ϴ� �г�)�г� ����
		panel_2.setBounds(100, 62, 484, 300);
		getContentPane().add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		// �� ��ư �̺�Ʈ ����
		sInfo.addActionListener(this); 
		gradeChk.addActionListener(this);
		classRegi.addActionListener(this);
		classChk.addActionListener(this);
		lOut.addActionListener(this);
		
		// ��üȭ�鼳��
		setLocation(100, 100);
		setPreferredSize(new Dimension(600, 400));

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		if (e.getSource() == classRegi) { // ������û ��ư
			
			panel_2.removeAll();
			Student_Takecourse stk = new Student_Takecourse(stdNum);
			panel_2.add(stk, BorderLayout.CENTER);
			setVisible(true);
			
		}
		
		if (e.getSource() == classChk) { // ������ȸ ��ư
			
			panel_2.removeAll();
			Student_Takecourse_chk stk_chk = new Student_Takecourse_chk(stdNum);
			panel_2.add(stk_chk, BorderLayout.CENTER);
			setVisible(true);
			
			
		}
		
		if (e.getSource() == gradeChk) { // ������ȸ ��ư
			
			panel_2.removeAll();
			Student_Grade_chk chk = new Student_Grade_chk(stdNum);
			panel_2.add(chk, BorderLayout.CENTER);
			setVisible(true);
	
		}
		
		if (e.getSource() == sInfo) { // �������� ��ư
			
			// ���� ���� 19.08.23
		
		}
		
		if (e.getSource() == lOut) { // �α׾ƿ�
			new Login();
			dispose();
		}
		
	}
}
