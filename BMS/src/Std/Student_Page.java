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
	
	JButton classChk; 	// 수강조회
	JButton classRegi;  // 수강신청
	JButton gradeChk;	// 성적조회
	JButton sInfo;		// 개인정보
	JButton lOut;		// 로그아웃
	
	private JPanel panel_1;
	private JPanel panel_2;
	
	String stdNum ; // dao_lgin 에서 학번 받아오기
	 
	public Student_Page(String stdNum_lg ) {
		this.stdNum = stdNum_lg ;
		
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel(); // 위쪽 라벨 및 텍스트필드 들어가는 패널생성
		panel.setBounds(101, 0, 483, 56);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		//--DAO id와 매칭되는 학생 정보 호출
		DAO_login dao_lg = new DAO_login();
		// DAO--
		
		JLabel sDept = new JLabel("학과 : "); // 학과 라벨 생성
		sDept.setBounds(35, 25, 57, 15);
		panel.add(sDept);
		
		sMajor_tf = new JTextField(); // 학과 텍스트필드 생성
		sMajor_tf.setEditable(false);
		sMajor_tf.setText(dao_lg.select_std_num(stdNum).get_stdMajor()); // 매칭된 학생 DB 값 불러오기
		sMajor_tf.setBounds(78, 22, 81, 21);
		panel.add(sMajor_tf);
		sMajor_tf.setColumns(10);
		
		JLabel sName = new JLabel("이름 : "); // 이름 라벨 생성
		sName.setBounds(182, 25, 57, 15);
		panel.add(sName);
		
		sName_tf = new JTextField(); // 이름 텍스트필드
		sName_tf.setEditable(false);
		sName_tf.setText(dao_lg.select_std_num(stdNum).get_stdName());
		sName_tf.setBounds(219, 22, 64, 21);
		panel.add(sName_tf);
		sName_tf.setColumns(10);
		
		JLabel sNum = new JLabel("학번 : "); // 학번 라벨 생성
		sNum.setBounds(295, 25, 57, 15);
		panel.add(sNum);
		
		sNum_tf = new JTextField(); // 학번 텍스트필드
		sNum_tf.setEditable(false);
		sNum_tf.setText(dao_lg.select_std_num(stdNum).get_stdNum());
		sNum_tf.setBounds(337, 22, 43, 21);
		panel.add(sNum_tf);
		sNum_tf.setColumns(10);
		
		panel_1 = new JPanel(); // 버튼 들어가는 패널생성
		panel_1.setBounds(0, 0, 99, 362);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		classChk = new JButton("수강조회"); // 수강조회 버튼 생성
		classChk.setBounds(0, 141, 96, 23);
		panel_1.add(classChk);
		
		classRegi = new JButton("수강신청"); // 수강신청 버튼 생성
		classRegi.setBounds(0, 96, 96, 23);
		panel_1.add(classRegi);
		
		gradeChk = new JButton("성적조회"); // 성적조회 버튼생성
		gradeChk.setBounds(0, 188, 96, 23);
		panel_1.add(gradeChk);
		
		sInfo = new JButton("정보수정"); // 정보수정 버튼생성
		sInfo.setBounds(0, 236, 96, 23);
		panel_1.add(sInfo);
		
		lOut = new JButton("로그아웃"); // 로그아웃 버튼
		lOut.setBounds(0, 293, 96, 23);
		panel_1.add(lOut);
		
		panel_2 = new JPanel(); // 센터(버튼 누를 시 계속 변하는 패널)패널 생성
		panel_2.setBounds(100, 62, 484, 300);
		getContentPane().add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		// 각 버튼 이벤트 설정
		sInfo.addActionListener(this); 
		gradeChk.addActionListener(this);
		classRegi.addActionListener(this);
		classChk.addActionListener(this);
		lOut.addActionListener(this);
		
		// 전체화면설정
		setLocation(100, 100);
		setPreferredSize(new Dimension(600, 400));

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		if (e.getSource() == classRegi) { // 수강신청 버튼
			
			panel_2.removeAll();
			Student_Takecourse stk = new Student_Takecourse(stdNum);
			panel_2.add(stk, BorderLayout.CENTER);
			setVisible(true);
			
		}
		
		if (e.getSource() == classChk) { // 수강조회 버튼
			
			panel_2.removeAll();
			Student_Takecourse_chk stk_chk = new Student_Takecourse_chk(stdNum);
			panel_2.add(stk_chk, BorderLayout.CENTER);
			setVisible(true);
			
			
		}
		
		if (e.getSource() == gradeChk) { // 성적조회 버튼
			
			panel_2.removeAll();
			Student_Grade_chk chk = new Student_Grade_chk(stdNum);
			panel_2.add(chk, BorderLayout.CENTER);
			setVisible(true);
	
		}
		
		if (e.getSource() == sInfo) { // 정보수정 버튼
			
			// 구현 예정 19.08.23
		
		}
		
		if (e.getSource() == lOut) { // 로그아웃
			new Login();
			dispose();
		}
		
	}
}
