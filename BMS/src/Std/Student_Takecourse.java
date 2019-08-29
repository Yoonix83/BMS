package Std;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.swing.DefaultCellEditor;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


import DAO.DAO_login;
import DAO.DAO_std;
import DTO.DTO;

public class Student_Takecourse extends JPanel implements ActionListener{

	
	// table 과목에 중복 제거를 위한 코드

	int count = 0;
	ArrayList<String> addList = new ArrayList<String>();
	ArrayList<String> crsNum = new ArrayList<String>();
	ArrayList<String> crsName = new ArrayList<String>();

	
	JTextField crsCredit_tf;
	JTextField crsProName_tf;
	
	String[] caption = {"과목명", "시수/학점", "담당 교수"};
	
	JTable table;
	DefaultTableModel model;
	
	JButton add, commit ,delete;//추가, 등록, 삭제 버튼
	
	JComboBox course_box ; // 과목 콤보박스 생성
	
	//-- 버튼 기능들의 정보 공유를 위한 리스너의 글로벌 변수
	DAO_std dao_std = new DAO_std();
	DAO_login dao_login = new DAO_login();
	String [] arr = new String[4];
	DTO dto_takecourse = new DTO();
	String stdNum;
	// --END
	
	Student_Takecourse(String stdNum_lg) {
		
		this.stdNum = stdNum_lg;
		
		setLayout(null);
	
		//입력 방지
		model = new DefaultTableModel(caption, 0){
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		//테이블 스크롤패인 구현
		table = new JTable(model);
		JScrollPane mModeScr = new JScrollPane(table);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 480, 300);
		panel.setLayout(null);
		
		JLabel course = new JLabel("과목 : "); // 과목 라벨 생성
		course.setBounds(31, 22, 57, 15);
		
		//-- 과목 콤보 박스
		course_box = new JComboBox();
		course_box.setBounds(94, 19, 112, 21);
			// -- 과목 콤보박스 DB 가져오기
		course_box.addItem("과목 선택");
		 
		ArrayList<DTO> crs_list = dao_std.select_course_info();
		
			for (DTO dto_course : crs_list) {
				course_box.addItem(dto_course.get_crsName());
			}

			panel.add(course_box);
			course_box.addActionListener(this);
			
		// 학과 콤보 박스 --
		
		JLabel grade = new JLabel("시수/학점 : "); // 시수, 학점 라벨 생성
		grade.setBounds(31, 54, 71, 15);
		
		crsCredit_tf = new JTextField(); // 시수, 학점 텍스트필드
		crsCredit_tf.setBounds(104, 51, 24, 21);
		crsCredit_tf.setColumns(10);
		crsCredit_tf.setEditable(false);
		
		JLabel crsProName = new JLabel("담당교수 : "); // 담당교수 라벨
		crsProName.setBounds(216, 54, 63, 15);
			
		crsProName_tf = new JTextField(); // 담당교수 텍스트필드
		crsProName_tf.setBounds(280, 51, 48, 21);
		crsProName_tf.setColumns(10);
		crsProName_tf.setEditable(false);
		
		table = new JTable(model);
		JScrollPane stk_list = new JScrollPane(table);
		stk_list.setBounds(31, 100, 398, 168);
			
		add = new JButton("추 가"); // 추가 버튼
		add.setBounds(348, 50, 63, 23);
		panel.add(add);
		add.addActionListener(this);
			
			// 테이블 안 내용
		add(panel);
		panel.add(course);
		panel.add(course_box);
		panel.add(grade);
		panel.add(crsCredit_tf);			
		panel.add(crsProName);
		panel.add(crsProName_tf);
		panel.add(stk_list);
		
		commit = new JButton("등록"); // 등록 버튼
		commit.addActionListener(this);
		commit.setBounds(366, 269, 63, 21);
		panel.add(commit);
		
		JLabel lblNewLabel = new JLabel("* 수강등록 철회는 [수강조회] 에서 가능합니다.");
		lblNewLabel.setBounds(31, 85, 398, 15);
		panel.add(lblNewLabel);
		
		delete = new JButton("삭제");
		delete.addActionListener(this);
		delete.setBounds(291, 269, 63, 21);
		panel.add(delete);
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
		if(e.getSource() == course_box){  // 수강 과목 선택 기능  course_box
			
			if(!course_box.getSelectedItem().equals("과목 선택")){ //-- 과목 선택 로직
				
				dto_takecourse = dao_std.select_course_list(course_box.getSelectedItem().toString());
					
				crsCredit_tf.setText(dto_takecourse.get_crsCredit());
			
				crsProName_tf.setText(dto_takecourse.get_proName());
			
			}else{
				
				JOptionPane.showMessageDialog(null, " 과목를 선택하여 주십시요.");
				
				crsCredit_tf.setText("");	
				crsProName_tf.setText("");
				
			}// 과목 선택 로직 END--
				
		}// course_box END--
		
		if(e.getSource() == add){ // -- [ 추가 ] button 
			
			
			model = (DefaultTableModel) table.getModel();
			//table.getColumnModel().equals(course_box.getSelectedItem());
			
					if(!course_box.getSelectedItem().equals("과목 선택")){
											
						arr[0] =  course_box.getSelectedItem().toString(); // 과목명
						arr[1] = crsCredit_tf.getText();  // 시수/학점
						arr[2] = crsProName_tf.getText();  // 교수
						
						model.addRow(arr);
									
						// 추가 된 값 전역 list 에 추가
						addList.add(arr[0]);
						addList.add(dto_takecourse.get_crsNum());
									
							if(model.getRowCount()>1) {
								for(int i = 0 ; i <= model.getRowCount()-2 ; i++) {
											
									if(course_box.getSelectedItem().toString().equals(model.getValueAt(i, 0))){
												
										model.removeRow(model.getRowCount()-1);
	
													//list 에서 중뵉되는 최신 값을 제거 하기 위한 로직. size()-index 1 부터 시작
										addList.remove(addList.size()-2);												
										addList.remove(addList.size()-2);				
										System.out.println("remove 후 : "+addList);
										}
									}
								}
												
						}else if(course_box.getSelectedItem().equals(model.getValueAt(model.getRowCount(), 0))){
						
							JOptionPane.showMessageDialog(null, "동일한 과목을 선택하셨습니다!");
						}
					 				 
		} // [ 추가 ] button END --
		
		if(e.getSource() == commit ){ // -- [ 등록 ] button
			
			// addList 의 값을 홀수 로직, 짝수 로직 으로 나누어 crsName, crsNum 의 List 에 나누어 담는다.
			for(int i = 0 ; i < addList.size() ; i = i+2) {
				
				crsName.add(addList.get(i));
				
			}
			for(int i = 1 ; i < addList.size() ; i = i+2) {
				
				crsNum.add(addList.get(i));
				
			}

			// 두개의 List 배열을 각각 맞는 param 에 넣어준다.
			for(int i = 0 ; i < crsName.size() ; i++) {
				
				//Data 에 저장 로직
				dao_std.insert_course_add( stdNum, crsNum.get(i), crsName.get(i)); // 학번/ 과목 번호 /과목 이름
			}
			
			JOptionPane.showMessageDialog(null, "등록 완료 되었습니다.!");

			
		}// [ 등록 ] button END --
		
		if(e.getSource() == delete ){ // -- [ 삭제 ] button
		
			// -- 예외 발생시 처리문 
			try {
				
				model.removeRow(table.getSelectedRow()); // 현재 선택된 row 값 삭제
				
			} catch (Exception x) { 
				// 예외 발생 이벤트
				JOptionPane.showMessageDialog(null, "더이상 삭제할 과목이 없습니다.");
			} 
			//예외 발생시 처리문 -- END
			

		}// [ 삭제 ] button END --
	}
}
