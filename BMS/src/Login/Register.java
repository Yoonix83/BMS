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
	String Year[] = { "1", "2", "3", "4" }; // 학년
	
	JComboBox year_combo, major_combo;

	DAO_login dao_lg = new DAO_login();
	DTO_std dto_std = new DTO_std();
	DTO_mjr dto_mjr = new DTO_mjr();
	
	
	public Register() {
		
		setTitle("등록 가입");
		getContentPane().setLayout(null);

		stdRd = new JRadioButton("학생");
		rGroup.add(stdRd);
		stdRd.setBounds(195, 34, 57, 23);
		getContentPane().add(stdRd);

//		profRd = new JRadioButton("교수"); // 2019.08.10 차후 구현 예정
//		rGroup.add(profRd);
//		profRd.setBounds(333, 34, 57, 23);
//		getContentPane().add(profRd);

		JLabel num = new JLabel("번호");
		num.setBounds(195, 89, 30, 15);
		getContentPane().add(num);

		JLabel name = new JLabel("이름");
		name.setBounds(195, 120, 30, 15);
		getContentPane().add(name);

		JLabel dept = new JLabel("학과");
		dept.setBounds(196, 151, 30, 15);
		getContentPane().add(dept);

		JLabel year = new JLabel("학년");
		year.setBounds(196, 182, 30, 15);
		getContentPane().add(year);

		JLabel pw = new JLabel("비번");
		pw.setBounds(195, 217, 30, 15);
		getContentPane().add(pw);

		JLabel tel = new JLabel("전화번호");
		tel.setBounds(178, 248, 57, 15);
		getContentPane().add(tel);

		// 번호text
		rNum_tf = new JTextField();
		rNum_tf.setBounds(260, 86, 145, 21);
		getContentPane().add(rNum_tf);
		rNum_tf.setColumns(10);	 

		// 이름text
		rName_tf = new JTextField();
		rName_tf.setBounds(260, 117, 145, 21);
		getContentPane().add(rName_tf);
		rName_tf.setColumns(10);

		// 비번text
		rPw_tf = new JTextField();
		rPw_tf.setBounds(260, 214, 145, 21);
		getContentPane().add(rPw_tf);
		rPw_tf.setColumns(10);

		// 전화번호text
		rPhone_tf = new JTextField();
		rPhone_tf.setBounds(260, 245, 145, 21);
		getContentPane().add(rPhone_tf);
		rPhone_tf.setColumns(10);

		// --버튼
		loginWd = new JButton("로그인창"); // 로그인창
		loginWd.setBounds(164, 314, 90, 23);
		getContentPane().add(loginWd);
		loginWd.addActionListener(this);

		regist = new JButton("등록"); // 등록
		regist.setBounds(265, 314, 78, 23);
		getContentPane().add(regist);
		regist.addActionListener(this);

		clear = new JButton("CLEAR"); // CLEAR
		clear.setBounds(354, 314, 81, 23);
		getContentPane().add(clear);
		clear.addActionListener(this);
		// 버튼--

		// --JComboBox
		DAO_login dao_lg_list = new DAO_login<>();

		// -- 학과
		//
		String arr[] = new String[8];

		major_combo = new JComboBox();
		major_combo.setBounds(260, 148, 145, 21);
		getContentPane().add(major_combo);
		major_combo.addItem("선택");

		ArrayList<DTO_mjr> list = dao_lg_list.select_mjr_info(); // DAO 에서 list 타입으로 담긴 dto 정보를 ArrayList<> list 에 다시 담는다
		
		for (DTO_mjr dto_mjr : list) { // list 문에 있는 정보수 만큼 for 문을 이용해 Name 값을 ComboBox 에 차례로 넣는다.

			major_combo.addItem(dto_mjr.get_mjrName());
		}

		// -- 학년

		year_combo = new JComboBox();
		year_combo.setBounds(260, 179, 145, 21);
		getContentPane().add(year_combo);
		year_combo.addItem("선택");
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
	

		if (e.getSource() == loginWd) { // 로그인창 버튼
			
			new Login();
			
			dispose();
		
		}

		if (e.getSource() == regist) { // 등록 버튼

			if (stdRd.isSelected() == true) { // 학생 비교

				if (!("선택").equals(year_combo.getSelectedItem().toString())
						&& !("선택").equals(major_combo.getSelectedItem().toString())) { // 학년/전공 ComboBox 가 "선택"으로 정하지 않고 무엇이든 값을 선택했다면

					// - insert std dao (학생)실행 부분
					dao_lg.insert_std_info(rNum_tf.getText(), rName_tf.getText(),year_combo.getSelectedItem().toString(),
							major_combo.getSelectedItem().toString(), rPw_tf.getText(), rPhone_tf.getText());

					// id 값으로 name을 찾아와 alert 창에 가입환영 메세지를 띄워준다.
					JOptionPane.showMessageDialog(null,
							dao_lg.select_std_num(rNum_tf.getText()).get_stdName() + "님 KOSEA의 학생이 되신 걸 환영합니다. ^- ^/");

					dispose(); // 기존 화면을 닫고 원래 화면으로 보이게 하기위해 
					
				} else {

					JOptionPane.showMessageDialog(null, "[ 학과 ] 와 [ 학년 ] 다시 확인해주세여 !! ㅠ_ ㅠ");

				}

			}
		} // 등록 END --

		if (e.getSource() == clear)

		{ // Clear버튼
			rNum_tf.setText("");
			rName_tf.setText("");
			rPw_tf.setText("");
			rPhone_tf.setText("");
		}

	}// action END --

}
