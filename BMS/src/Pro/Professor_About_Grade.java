package Pro;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.DAO_pro;
import DTO.DTO_mark;
import DTO.DTO_pro;

import javax.swing.JTextField;



public class Professor_About_Grade extends JPanel implements ActionListener {

	DefaultTableModel model;

	String[] grade_lv = { "A+", "A", "B+", "B", "C+", "C", "D", "F" }; // combo
																	// box에 들어가는
																	// 학점
	String title[] = { "학 번", "이 름", "학 년", "성 적" };
	JTable gd_dt_model; // 학번/이름/학년/성적  표시를 위한 테이블
	
	JButton gd_search,gd_assign,gd_commit; //  조회 / 부여 / 등록

	JComboBox gd_lv_box; //학점 선택 콤보 박스
	JLabel gd_lb = new JLabel(); // 학점 Lable
	
	String table_list[] = new String[7];
	
	String pNum; //로그인에서 받아온 넘버
	
	DAO_pro dao_pro = new DAO_pro();
	DTO_pro dto_pro = new DTO_pro();
	
	private JTextField crsName_tf;
	private JLabel lblNewLabel_1;

	public Professor_About_Grade(String pNum_lg) {
		
		this.pNum = pNum_lg;
		setLayout(null);

		JPanel ag_panel = new JPanel(); // Professor_About_Grade 클래스 패널
		ag_panel.setBounds(0, 0, 494, 327);
		add(ag_panel);
		ag_panel.setLayout(null);
		
		//등록
		gd_commit = new JButton("등록");
		gd_commit.setBounds(400, 280, 61, 23);
		ag_panel.add(gd_commit);
		gd_commit.setFont(new Font("굴림", Font.PLAIN, 12));
		gd_commit.addActionListener(this);
		
		//부여
		gd_assign = new JButton("부여");
		gd_assign.setBounds(400, 16, 61, 23);
		ag_panel.add(gd_assign);
		gd_assign.setFont(new Font("굴림", Font.PLAIN, 12));
		gd_assign.addActionListener(this);
		
		// 콤보박스
		gd_lv_box = new JComboBox();
		gd_lv_box.setBounds(285, 16, 84, 23);
		ag_panel.add(gd_lv_box);
		
		gd_lv_box.addItem("학점 선택"); 
		for (int i = 0; i < grade_lv.length; i++)
			gd_lv_box.addItem(grade_lv[i]);

		gd_lb = new JLabel("학 점:");
		gd_lb.setBounds(236, 13, 37, 29);
		ag_panel.add(gd_lb);

		model = new DefaultTableModel(title, 0);
		gd_dt_model = new JTable(model);
		JScrollPane gd_list_scr = new JScrollPane(gd_dt_model);// 학생 리스트 표시 리미트 이상일시 스크롤
		gd_list_scr.setBounds(12, 89, 470, 179);
		ag_panel.add(gd_list_scr);

		gd_search = new JButton("조회"); // gd_search 조회버튼
		gd_search.setFont(new Font("굴림", Font.PLAIN, 12));
		gd_search.setBounds(315, 280, 61, 23);
		ag_panel.add(gd_search);
		
		dto_pro = dao_pro.select_pro_info(pNum);// 교수 전공, 과목 이름 가져오기
		
		JLabel crsName_lb = new JLabel("과목");  // 교수 과목
		crsName_lb.setBounds(12, 20, 31, 15);
		ag_panel.add(crsName_lb);
		
		crsName_tf = new JTextField();
		crsName_tf.setText(dto_pro.get_crsName());
		crsName_tf.setEditable(false);
		crsName_tf.setBounds(55, 17, 169, 21);
		ag_panel.add(crsName_tf);
		crsName_tf.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("부여) 성적을 부여할 한 명의 학생의 성적을 선택해주세요.");
		lblNewLabel.setBounds(12, 45, 431, 15);
		ag_panel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("등록) 한 번에 단 한 명의 학생의 성적만 부여 가능합니다.");
		lblNewLabel_1.setBounds(12, 64, 431, 15);
		ag_panel.add(lblNewLabel_1);

		gd_search.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {


		if (e.getSource() == gd_search) //  조회 버튼 클릭시
		{

			int cnt = model.getRowCount();  // 현재 List 되 있는 table 의 row수  만큼의 수를 cnt 에 넣는다
			for (int i = 0; i < cnt; i++) { // cnt 만큼 돌림
				model.removeRow(0);         // 조회버튼 클릭시 마다 이전 데이터 삭제를 위해 cnt 만큼씩 한줄씩 삭제
				
			}

			ArrayList<DTO_mark> list = dao_pro.select_mark(pNum);
			for (DTO_mark dto : list) {
				
				table_list[0] = dto.get_stdNum();
				table_list[1] = dto.get_stdName();
				table_list[2] = dto.get_stdGrade();
				table_list[3] = dto.get_tkMark();
				
				model = (DefaultTableModel) gd_dt_model.getModel(); // 
				model.addRow(table_list);

			}
			
		}
		if (e.getSource() == gd_assign) //  부여 버튼
		{
			// setValueAt( 특정값<value>, 특정행<row>, 특정열<column> ) = JTable의 특정 위치에 값을 바꾸는 함수
			gd_dt_model.setValueAt((String) gd_lv_box.getSelectedItem(), gd_dt_model.getSelectedRow(), 3); // 학점은 3번째 column 에 있다.
			
		}
		
		if (e.getSource() == gd_commit) // 등록 버튼
		{

			DTO_mark dto_mark = new DTO_mark();
			
			dto_mark.set_tkMark((String) gd_dt_model.getValueAt(gd_dt_model.getSelectedRow(), 3));
			dto_mark.set_crsName(dto_pro.get_crsName());
			dto_mark.set_stdNum((String) gd_dt_model.getValueAt(gd_dt_model.getSelectedRow(), 0));
			dto_mark = dao_pro.update_grade(dto_mark);
					
			JOptionPane.showMessageDialog(null, "["+ (String) gd_dt_model.getValueAt(gd_dt_model.getSelectedRow(), 1)+"] 학생의 성적이 ["+dto_mark.get_tkMark()+ "] 으로 등록 완료 되었습니다.");
		
		}
	}
}
