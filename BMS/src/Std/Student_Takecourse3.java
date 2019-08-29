package Std;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

public class Student_Takecourse3 extends JPanel implements ActionListener{

	
	// table 과목에 중복 제거를 위한 코드
	ArrayList<String> list = new ArrayList<String>(); 
	int count = 0;

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
	String [] arr = {"과목 선택", "없음" ,"없음" };
	DTO dto_takecourse = new DTO();
	String stdNum;
	// --END
	
	Student_Takecourse3(String stdNum_lg) {
		
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
			try {
				
					
					if(!arr[0].equals(course_box.getSelectedItem())){
						
//						try {
//
//									arr[0] = course_box.getSelectedItem().toString(); // 과목명
//									arr[1] = crsCredit_tf.getText();  // 시수/학점
//									arr[2] = crsProName_tf.getText();  // 교수명
////									
////									
//									model.addRow(arr);
//									
//									boolean i = true;
//									count = 0;
//									
//									while(i) {
//										
//										if(!course_box.getSelectedItem().equals(model.getValueAt(count, 0).toString())) {
//											
//											
//											System.out.println(count);
//											System.out.println(course_box.getSelectedItem());
//											System.out.println(model.getValueAt(count, 0).toString());
//											
//											
//										}else if(course_box.getSelectedItem().equals(model.getValueAt(count, 0).toString())) {
//											
//											System.out.println(count);
//											System.out.println(course_box.getSelectedItem() + " = " + model.getValueAt(count, 0).toString() );
//											i = false;
//											
//										}
//										count += 1;
//										
//										
//									}
//									} catch (Exception e2) {
//										// TODO: handle exception
//										e2.getMessage();
//									}
						
									
				
			
					}else {
						
						JOptionPane.showMessageDialog(null, "동일한 과목을 선택하셨습니다!");
						
					}
					
						
			}catch(Exception e1) {
				e1.printStackTrace();
			}
			
			
			
		
		} // [ 추가 ] button END --
		
		if(e.getSource() == commit ){ // -- [ 등록 ] button
			
			dao_std.insert_course_add( stdNum, dto_takecourse.get_crsNum(), arr[0]); // DB table: takecourse 에 들어갈 항목 : 학번/과목번호/과목이름
			
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
