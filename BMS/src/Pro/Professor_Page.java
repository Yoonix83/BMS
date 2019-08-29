package Pro;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DAO.DAO_pro;
import DTO.DTO;
import Login.Login;

import java.awt.Font;
import java.awt.BorderLayout;

public class Professor_Page extends JFrame implements ActionListener {

	 JTextField pMajor_tf; //�а�:
	 JTextField pName_tf; //�̸�:
	 JTextField pNum_tf; //������ȣ:

	JButton gradeMng; //�������� Ŭ���ϴ� ��ư
	JButton majorInfo; // �а����� Ŭ���ϴ� ��ư
	JButton pInfo; // ������������ Ŭ���ϴ� ��ư
	JButton lOut; // �α׾ƿ� ��ư
	
	private JPanel main_content; //���빰 ��¿� ���� �г�

	String pNum; // �α��ο��� �޾ƿ� ������ȣ �������
	String pMajor;
	
	public Professor_Page(String pNum_lg, String pMajor_lg) // Login.java ���� �Ű������� ���� ��ȣ, ���� ���� ���� �޾ƿ´�
	{
		this.pNum = pNum_lg;
		this.pMajor = pMajor_lg;
		
		getContentPane().setFont(new Font("����", Font.PLAIN, 10));
		getContentPane().setLayout(null);

		JPanel text_content = new JPanel(); //  �а�/ �̸� / ������ȣ �� ���� panelĭ
		text_content.setBounds(90, 0, 494, 31);
		getContentPane().add(text_content);
		text_content.setLayout(null);

		
		//-- �ش� ���� ��ȣ ���� �ҷ�����
		DAO_pro dao_pro = new DAO_pro();
		DTO dto_pro = new DTO();
		
		dto_pro = dao_pro.select_pro_info(pNum);
		//  �ش� ���� ��ȣ ���� �ҷ����� --END
		

		JLabel lmajor = new JLabel("�� ��:");
		lmajor.setBounds(76, 10, 32, 15);
		text_content.add(lmajor);
			
		pMajor_tf = new JTextField();  // �а�.�ؽ�Ʈ �ʵ�
		pMajor_tf.setText(dto_pro.get_proMajor());
		pMajor_tf.setEditable(false);
		pMajor_tf.setColumns(10);
		pMajor_tf.setBounds(113, 7, 92, 21);
		text_content.add(pMajor_tf);

		JLabel pName = new JLabel("�� ��:");
		pName.setBounds(227, 10, 32, 15);
		text_content.add(pName);
			
		pName_tf = new JTextField();// �̸�.�ؽ�Ʈ �ʵ�
		pName_tf.setText(dto_pro.get_proName());
		pName_tf.setEditable(false);
		pName_tf.setBounds(261, 7, 49, 21);
		text_content.add(pName_tf);
		pName_tf.setColumns(10);

		JLabel lnum = new JLabel("������ȣ:");
		lnum.setBounds(321, 10, 57, 15);
		text_content.add(lnum);

		pNum_tf = new JTextField();  // ���� ��ȣ .�ؽ�Ʈ �ʵ�
		pNum_tf.setText(dto_pro.get_proNum());
		pNum_tf.setEditable(false);
		pNum_tf.setBounds(378, 7, 49, 21);
		text_content.add(pNum_tf);
		pNum_tf.setColumns(10);

		JPanel action_content = new JPanel(); // �������� �а����� �������� Action ��ư  panelĭ
		action_content.setBounds(0, 0, 87, 362);
		getContentPane().add(action_content);
		action_content.setLayout(null);

		// ���� ��ư
		gradeMng = new JButton("��������");
		gradeMng.setFont(new Font("����", Font.PLAIN, 12));
		gradeMng.setBounds(0, 69, 87, 37);
		action_content.add(gradeMng);

		gradeMng.addActionListener(this);
		
		//�а� ��ư
		majorInfo = new JButton("�а�����");
		majorInfo.setFont(new Font("����", Font.PLAIN, 12));
		majorInfo.setBounds(0, 208, 87, 37);
		action_content.add(majorInfo);

		majorInfo.addActionListener(this);
		
		//�������� ��ư
		pInfo = new JButton("��������");
		pInfo.setFont(new Font("����", Font.PLAIN, 12));
		pInfo.setBounds(0, 138, 87, 37);
		action_content.add(pInfo);
		
		pInfo.addActionListener(this);
		
		//�α׾ƿ� ��ư
		lOut = new JButton("�α׾ƿ�"); // �α׾ƿ�
		lOut.setBounds(0, 279, 87, 37);
		action_content.add(lOut);
		
		lOut.addActionListener(this);

		main_content = new JPanel();
		main_content.setBounds(90, 35, 494, 327);
		getContentPane().add(main_content);
		main_content.setLayout(new BorderLayout(0, 0));

		setLocation(50, 100);
		setPreferredSize(new Dimension(600, 400)); // frame size
		setDefaultCloseOperation(EXIT_ON_CLOSE); // [x]�� ���� �� ���α׷� ���� ����  EXIT ON CLOSE  =  System.exit(0); ����
		pack();//setPreferredSize �� ���� ��� panel���� ���� ���� �� �ڵ����� ����
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == gradeMng) { // ��������
			
			main_content.removeAll();
			Professor_About_Grade ag = new Professor_About_Grade(pNum);
			main_content.add(ag, BorderLayout.CENTER);
			setVisible(true);

		}

		if (e.getSource() == majorInfo) { // �а�����
			
			main_content.removeAll();
			Professor_MajorInfo pro_major = new Professor_MajorInfo(pMajor);
			main_content.add(pro_major, BorderLayout.CENTER);
			setVisible(true);
		}

		if (e.getSource() == pInfo) { //��������
			// �̱��� 19.08.21
		}
		
		if (e.getSource() == lOut) { //�α׾ƿ�

			new Login();
			this.dispose();
		}
	}
}