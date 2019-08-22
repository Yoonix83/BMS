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

	private final ButtonGroup lGroup = new ButtonGroup(); //여러 버튼 중 하나의 버튼만 선택할 수 있게 하기 위해 버튼그룹 클래스로 묶어버린다.
	private JTextField Num_tf; // JFrame 아이디 입력할 네모칸
	private JPasswordField Pw_tf; // JFrame 비번 입력할 네모칸 *** 로 표시됨
	JButton login, find, register; // 로그인, PW 찾기, 등록 버튼
	JRadioButton stdRB, profRB, empRB; // 라디오 버튼

	public Login() {
		setTitle("로그인 "); // JFrame 제목
		getContentPane().setLayout(null); // 현재 Pane의 레이아웃(배치관리자)은  자유롭게 설정할 것이기 때문에 null 값으로 설정

		stdRB = new JRadioButton("학생"); // 학생
		lGroup.add(stdRB);
		stdRB.setBounds(187, 45, 60, 37); // setBounds 버튼의 위치 설정 (or setSize, setLocation 두가지를 같이 설정해야함)
		getContentPane().add(stdRB); // 현재 ContentPane 에 추가 하겠다는 의미 함수

		profRB = new JRadioButton("교수"); // 교수
		lGroup.add(profRB);
		profRB.setBounds(267, 45, 60, 37);
		getContentPane().add(profRB);

		empRB = new JRadioButton("관리자"); // 관리자
		lGroup.add(empRB);
		empRB.setBounds(348, 45, 82, 37);
		getContentPane().add(empRB);

		JLabel lnum = new JLabel("번호");
		lnum.setBounds(187, 129, 57, 15);
		getContentPane().add(lnum);

		JLabel lpw = new JLabel("비번");
		lpw.setBounds(187, 196, 32, 15);
		getContentPane().add(lpw);

		// 아이디text
		Num_tf = new JTextField();
		Num_tf.setBounds(293, 125, 126, 21);
		getContentPane().add(Num_tf);
		Num_tf.setColumns(10);

		// 비번text
		Pw_tf = new JPasswordField();
		Pw_tf.setBounds(293, 192, 126, 21);
		getContentPane().add(Pw_tf);
		Pw_tf.setColumns(10);

		// --버튼
		login = new JButton("로그인");
		login.setBounds(113, 263, 90, 37);
		getContentPane().add(login);
		login.addActionListener(this); // addActionListener ActionListener를 구현(implements)하는 아무 인자가 오면 된다
		                               // 여기서 this는 ActionListener를 implements 받는 Login 클래스 자기자신을 말하는 것이다.
		
		find = new JButton("PW 찾기");
		find.setBounds(242, 263, 111, 37);
		getContentPane().add(find);
		find.addActionListener(this);

		register = new JButton("가입");
		register.setBounds(396, 263, 82, 37);
		getContentPane().add(register);
		register.addActionListener(this);

		// 버튼--

		setLocation(100, 100); // JFrame 이 켜지는 윈도우 위치
	    setPreferredSize(new Dimension(600, 400)); //JFrame의 기준이 되는 사이즈

		setDefaultCloseOperation(EXIT_ON_CLOSE); // JFrame 윈도우창 종료시 (x 표시 클릭) 메모리에서 프로세스 종료
		pack(); // JFrame 창안에 있는 모든 서브 Component 를 setPreferredSize 기준으로 자동으로 맞춰주는 함수
		setVisible(true); //JFrame을 보일 것인지의 함수 *반드시 true, false 필요 없으면 창이 뜨질 않음
	} // public Login() -- END

	@Override
	public void actionPerformed(ActionEvent e) { //ActionListener 가 동작할때 반드시 나와야되는 함수 , 버튼 눌렸을때 동작함수

		DTO_std dto_std = new DTO_std();
		DTO_pro dto_pro = new DTO_pro();
		DTO_emp dto_emp = new DTO_emp();
		DAO_login dao_lg = new DAO_login();

		

		// 로그인 부분
		if (e.getSource() == login) { // login 버튼 누른게 맞는지 확인

			if (stdRB.isSelected() == true) { // 학생 라디오버튼 클릭 된건지 확인 

				dto_std = dao_lg.select_std_num(Num_tf.getText()); // std 테이블, 아이디
				
				if (Num_tf.getText().equals(dto_std.get_stdNum())) { // -- text 값과 디비 name 값 비교
	
					if (String.valueOf(Pw_tf.getPassword()).equals(dto_std.get_stdPw())) { //String.valueOf() : char 값을 string으로 변환

						JOptionPane.showMessageDialog(null, "반갑습니다. \t " + " \t " + dto_std.get_stdName() + "님 ^-^");
						
						new Student_Page(dto_std.get_stdNum());
						
						dispose(); // 본인 창 닫기

					}else {
						JOptionPane.showMessageDialog(null, " 비번 을 다시 확인하여 주십시요.^-^");
					} 
					
				} // if (Num_tf.getText().equals(dto_std.get_stdNum())) -- END
				
			}//if (stdRB.isSelected() == true) -- END
			else if (profRB.isSelected() == true) { // 교수 확인 

				dto_pro = dao_lg.select_pro_num(Num_tf.getText()); 
				
				if (Num_tf.getText().equals(dto_pro.get_proNum())) {
					
					if (String.valueOf(Pw_tf.getPassword()).equals(dto_pro.get_proPw())) {
						
						JOptionPane.showMessageDialog(null, "반갑습니다. \t " + " \t " + dto_pro.get_proName() + "님 ^-^");
						
						new Professor_Page(dto_pro.get_proNum(), dto_pro.get_proMajor());//로그인 한 교수 번호 값 넘겨주기
						
						dispose(); //  창 닫기

						}else {
							JOptionPane.showMessageDialog(null, " 비번 을 다시 확인하여 주십시요.^-^");
						}
					
					}//if (Num_tf.getText().equals(dto_pro.get_proNum()))  -- END
				
			}//else if (profRB.isSelected() == true) -- END
			else if (empRB.isSelected() == true) { // 직원 확인 

				dto_emp = dao_lg.select_emp_num(Num_tf.getText()); 

				if (Num_tf.getText().equals(dto_emp.get_empNum())) {

					if (String.valueOf(Pw_tf.getPassword()).equals(dto_emp.get_empPw())) {
						
						JOptionPane.showMessageDialog(null, "반갑습니다. \t " + " \t " + dto_emp.get_empName() + "님 ^-^");

						dispose(); //  창 닫기

						}else {
							JOptionPane.showMessageDialog(null, " 비번 을 다시 확인하여 주십시요.^-^");
						}
					}//if (Num_tf.getText().equals(dto_emp.get_empNum())) -- END
				
			}//else if (empRB.isSelected() == true) -- END
			
		}//if (e.getSource() == login) -- END
		else if(e.getSource() == find) {
			
			new Find_Main();
			
			dispose(); //현재 프레임 종료
			
		}else if (e.getSource() == register) { // 가입
			
			new Register();
			
		}

		
		
	}// public void actionPerformed(ActionEvent e) -- END
	
}// public class Login extends JFrame implements ActionListener -- END
