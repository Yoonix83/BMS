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
	
	private JTextField fNum_tf; // 번호
	private JTextField fName_tf; // 이름
	private final ButtonGroup fGroup = new ButtonGroup();

	JRadioButton stdRB, profRB;
	JButton loginWd, find, clear;

	public Find_Main() {
		setTitle("비번 찾기 ");
		getContentPane().setLayout(null);

		stdRB = new JRadioButton("학생");
		fGroup.add(stdRB);
		stdRB.setBounds(186, 47, 61, 23);
		getContentPane().add(stdRB);

		profRB = new JRadioButton("교수");
		fGroup.add(profRB);
		profRB.setBounds(329, 47, 61, 23);
		getContentPane().add(profRB);

		JLabel fNum = new JLabel("번호");
		fNum.setBounds(158, 115, 31, 15);
		getContentPane().add(fNum);

		JLabel fName = new JLabel("이름");
		fName.setBounds(158, 169, 31, 15);
		getContentPane().add(fName);

		// 번호text
		fNum_tf = new JTextField();
		fNum_tf.setBounds(240, 112, 141, 21);
		getContentPane().add(fNum_tf);
		fNum_tf.setColumns(10);

		// 이름text
		fName_tf = new JTextField();
		fName_tf.setBounds(240, 166, 141, 21);
		getContentPane().add(fName_tf);
		fName_tf.setColumns(10);

		// --버튼
		loginWd = new JButton("로그인창"); // 로그인창
		loginWd.setBounds(138, 242, 97, 23);
		getContentPane().add(loginWd);
		loginWd.addActionListener(this);

		find = new JButton("찾기"); // 찾기
		find.setBounds(249, 242, 97, 23);
		getContentPane().add(find);
		find.addActionListener(this);

		clear = new JButton("CLEAR"); // CLEAR
		clear.setBounds(358, 242, 97, 23);
		getContentPane().add(clear);
		clear.addActionListener(this);
		// 버튼--

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

		if (e.getSource() == loginWd) { // 로그인창 버튼 : 로그인 화면으로 돌아가기
			
			new Login();
			dispose();// Find_Main close
			
		}else if (e.getSource() == find) { // 찾기버튼
			if (stdRB.isSelected() == true) { // -- 학생 비교

				dto_std = dao_lg.select_std_num(fNum_tf.getText());
				
					if (fName_tf.getText().equals(dto_std.get_stdName())) {

						JOptionPane.showMessageDialog(null, "반갑습니다. \t " + " \t " + dto_std.get_stdName() + "님 ^-^");

						new Find_Final(dto_std.get_stdName(), dto_std.get_stdNum(), dto_std.get_stdPw());

						dispose(); // 본인 창 닫기
						
					}else {
						
						JOptionPane.showMessageDialog(null, " [ 이름 ]을 다시 확인하여 주십시요.^-^");
						
					}

				} // 학생 비교 -- END

			else if (profRB.isSelected() == true) { // -- 교수 비교

				dto_pro = dao_lg.select_pro_num(fNum_tf.getText()); // prof 테이블

					if (fName_tf.getText().equals(dto_pro.get_proName())) {

						JOptionPane.showMessageDialog(null, "반갑습니다. \t " + " \t " + dto_pro.get_proName() + "님 ^-^");

						new Find_Final(dto_pro.get_proName(), dto_pro.get_proNum(), dto_pro.get_proPw());

						dispose(); // 본인 창 닫기

					} else {
						
						JOptionPane.showMessageDialog(null, " [ 이름 ]을 다시 확인하여 주십시요.^-^");
					
					}
				} // 교수 비교 -- END
//
			
		}

		if (e.getSource() == clear) { // Clear버튼
			fNum_tf.setText("");
			fName_tf.setText("");
		}

	}

}
