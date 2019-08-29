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

	 JTextField pMajor_tf; //학과:
	 JTextField pName_tf; //이름:
	 JTextField pNum_tf; //교수번호:

	JButton gradeMng; //성적관리 클릭하는 버튼
	JButton majorInfo; // 학과정보 클릭하는 버튼
	JButton pInfo; // 교수정보수정 클릭하는 버튼
	JButton lOut; // 로그아웃 버튼
	
	private JPanel main_content; //내용물 출력에 들어가는 패널

	String pNum; // 로그인에서 받아온 교수번호 저장공간
	String pMajor;
	
	public Professor_Page(String pNum_lg, String pMajor_lg) // Login.java 에서 매개변수로 교수 번호, 교수 전공 값을 받아온다
	{
		this.pNum = pNum_lg;
		this.pMajor = pMajor_lg;
		
		getContentPane().setFont(new Font("굴림", Font.PLAIN, 10));
		getContentPane().setLayout(null);

		JPanel text_content = new JPanel(); //  학과/ 이름 / 교수번호 가 들어가는 panel칸
		text_content.setBounds(90, 0, 494, 31);
		getContentPane().add(text_content);
		text_content.setLayout(null);

		
		//-- 해당 교수 번호 정보 불러오기
		DAO_pro dao_pro = new DAO_pro();
		DTO dto_pro = new DTO();
		
		dto_pro = dao_pro.select_pro_info(pNum);
		//  해당 교수 번호 정보 불러오기 --END
		

		JLabel lmajor = new JLabel("학 과:");
		lmajor.setBounds(76, 10, 32, 15);
		text_content.add(lmajor);
			
		pMajor_tf = new JTextField();  // 학과.텍스트 필드
		pMajor_tf.setText(dto_pro.get_proMajor());
		pMajor_tf.setEditable(false);
		pMajor_tf.setColumns(10);
		pMajor_tf.setBounds(113, 7, 92, 21);
		text_content.add(pMajor_tf);

		JLabel pName = new JLabel("이 름:");
		pName.setBounds(227, 10, 32, 15);
		text_content.add(pName);
			
		pName_tf = new JTextField();// 이름.텍스트 필드
		pName_tf.setText(dto_pro.get_proName());
		pName_tf.setEditable(false);
		pName_tf.setBounds(261, 7, 49, 21);
		text_content.add(pName_tf);
		pName_tf.setColumns(10);

		JLabel lnum = new JLabel("교수번호:");
		lnum.setBounds(321, 10, 57, 15);
		text_content.add(lnum);

		pNum_tf = new JTextField();  // 교수 번호 .텍스트 필드
		pNum_tf.setText(dto_pro.get_proNum());
		pNum_tf.setEditable(false);
		pNum_tf.setBounds(378, 7, 49, 21);
		text_content.add(pNum_tf);
		pNum_tf.setColumns(10);

		JPanel action_content = new JPanel(); // 성적관리 학과정보 정보수정 Action 버튼  panel칸
		action_content.setBounds(0, 0, 87, 362);
		getContentPane().add(action_content);
		action_content.setLayout(null);

		// 성적 버튼
		gradeMng = new JButton("성적관리");
		gradeMng.setFont(new Font("굴림", Font.PLAIN, 12));
		gradeMng.setBounds(0, 69, 87, 37);
		action_content.add(gradeMng);

		gradeMng.addActionListener(this);
		
		//학과 버튼
		majorInfo = new JButton("학과정보");
		majorInfo.setFont(new Font("굴림", Font.PLAIN, 12));
		majorInfo.setBounds(0, 208, 87, 37);
		action_content.add(majorInfo);

		majorInfo.addActionListener(this);
		
		//정보수정 버튼
		pInfo = new JButton("정보수정");
		pInfo.setFont(new Font("굴림", Font.PLAIN, 12));
		pInfo.setBounds(0, 138, 87, 37);
		action_content.add(pInfo);
		
		pInfo.addActionListener(this);
		
		//로그아웃 버튼
		lOut = new JButton("로그아웃"); // 로그아웃
		lOut.setBounds(0, 279, 87, 37);
		action_content.add(lOut);
		
		lOut.addActionListener(this);

		main_content = new JPanel();
		main_content.setBounds(90, 35, 494, 327);
		getContentPane().add(main_content);
		main_content.setLayout(new BorderLayout(0, 0));

		setLocation(50, 100);
		setPreferredSize(new Dimension(600, 400)); // frame size
		setDefaultCloseOperation(EXIT_ON_CLOSE); // [x]로 닫을 시 프로그램 완전 종료  EXIT ON CLOSE  =  System.exit(0); 같다
		pack();//setPreferredSize 에 맞춰 모든 panel들의 높이 넓이 값 자동으로 조정
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == gradeMng) { // 성적관리
			
			main_content.removeAll();
			Professor_About_Grade ag = new Professor_About_Grade(pNum);
			main_content.add(ag, BorderLayout.CENTER);
			setVisible(true);

		}

		if (e.getSource() == majorInfo) { // 학과정보
			
			main_content.removeAll();
			Professor_MajorInfo pro_major = new Professor_MajorInfo(pMajor);
			main_content.add(pro_major, BorderLayout.CENTER);
			setVisible(true);
		}

		if (e.getSource() == pInfo) { //정보수정
			// 미구현 19.08.21
		}
		
		if (e.getSource() == lOut) { //로그아웃

			new Login();
			this.dispose();
		}
	}
}