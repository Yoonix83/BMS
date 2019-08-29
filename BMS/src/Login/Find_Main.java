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
import DTO.DTO;


public class Find_Main extends JFrame implements ActionListener {
	
	private JTextField fNum_tf; // ��ȣ
	private JTextField fName_tf; // �̸�
	private final ButtonGroup fGroup = new ButtonGroup();

	JRadioButton stdRB, profRB;
	JButton loginWd, find, clear;

	public Find_Main() {
		setTitle("��� ã�� ");
		getContentPane().setLayout(null);

		stdRB = new JRadioButton("�л�");
		fGroup.add(stdRB);
		stdRB.setBounds(186, 47, 61, 23);
		getContentPane().add(stdRB);

		profRB = new JRadioButton("����");
		fGroup.add(profRB);
		profRB.setBounds(329, 47, 61, 23);
		getContentPane().add(profRB);

		JLabel fNum = new JLabel("��ȣ");
		fNum.setBounds(158, 115, 31, 15);
		getContentPane().add(fNum);

		JLabel fName = new JLabel("�̸�");
		fName.setBounds(158, 169, 31, 15);
		getContentPane().add(fName);

		// ��ȣtext
		fNum_tf = new JTextField();
		fNum_tf.setBounds(240, 112, 141, 21);
		getContentPane().add(fNum_tf);
		fNum_tf.setColumns(10);

		// �̸�text
		fName_tf = new JTextField();
		fName_tf.setBounds(240, 166, 141, 21);
		getContentPane().add(fName_tf);
		fName_tf.setColumns(10);

		// --��ư
		loginWd = new JButton("�α���â"); // �α���â
		loginWd.setBounds(138, 242, 97, 23);
		getContentPane().add(loginWd);
		loginWd.addActionListener(this);

		find = new JButton("ã��"); // ã��
		find.setBounds(249, 242, 97, 23);
		getContentPane().add(find);
		find.addActionListener(this);

		clear = new JButton("CLEAR"); // CLEAR
		clear.setBounds(358, 242, 97, 23);
		getContentPane().add(clear);
		clear.addActionListener(this);
		// ��ư--

		setLocation(100, 100);
		setPreferredSize(new Dimension(600, 400));

		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		DAO_login dao_lg = new DAO_login();
		DTO dto_std = new DTO();
		DTO dto_pro = new DTO();

		if (e.getSource() == loginWd) { // �α���â ��ư : �α��� ȭ������ ���ư���
			
			new Login();
			dispose();// Find_Main close
			
		}else if (e.getSource() == find) { // ã���ư
			if (stdRB.isSelected() == true) { // -- �л� ��

				dto_std = dao_lg.select_std_num(fNum_tf.getText());
				
					if (fName_tf.getText().equals(dto_std.get_stdName())) {

						JOptionPane.showMessageDialog(null, "�ݰ����ϴ�. \t " + " \t " + dto_std.get_stdName() + "�� ^-^");

						new Find_Final(dto_std.get_stdName(), dto_std.get_stdNum(), dto_std.get_stdPw());

						dispose(); // ���� â �ݱ�
						
					}else {
						
						JOptionPane.showMessageDialog(null, " [ �̸� ]�� �ٽ� Ȯ���Ͽ� �ֽʽÿ�.^-^");
						
					}

				} // �л� �� -- END

			else if (profRB.isSelected() == true) { // -- ���� ��

				dto_pro = dao_lg.select_pro_num(fNum_tf.getText()); // prof ���̺�

					if (fName_tf.getText().equals(dto_pro.get_proName())) {

						JOptionPane.showMessageDialog(null, "�ݰ����ϴ�. \t " + " \t " + dto_pro.get_proName() + "�� ^-^");

						new Find_Final(dto_pro.get_proName(), dto_pro.get_proNum(), dto_pro.get_proPw());

						dispose(); // ���� â �ݱ�

					} else {
						
						JOptionPane.showMessageDialog(null, " [ �̸� ]�� �ٽ� Ȯ���Ͽ� �ֽʽÿ�.^-^");
					
					}
				} // ���� �� -- END
//
			
		}

		if (e.getSource() == clear) { // Clear��ư
			fNum_tf.setText("");
			fName_tf.setText("");
		}

	}

}
