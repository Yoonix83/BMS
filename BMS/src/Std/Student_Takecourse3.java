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

	
	// table ���� �ߺ� ���Ÿ� ���� �ڵ�
	ArrayList<String> list = new ArrayList<String>(); 
	int count = 0;

	JTextField crsCredit_tf;
	JTextField crsProName_tf;
	
	String[] caption = {"�����", "�ü�/����", "��� ����"};
	
	JTable table;
	DefaultTableModel model;
	
	JButton add, commit ,delete;//�߰�, ���, ���� ��ư
	
	JComboBox course_box ; // ���� �޺��ڽ� ����
	
	//-- ��ư ��ɵ��� ���� ������ ���� �������� �۷ι� ����
	DAO_std dao_std = new DAO_std();
	DAO_login dao_login = new DAO_login();
	String [] arr = {"���� ����", "����" ,"����" };
	DTO dto_takecourse = new DTO();
	String stdNum;
	// --END
	
	Student_Takecourse3(String stdNum_lg) {
		
		this.stdNum = stdNum_lg;
		
		setLayout(null);
	
		//�Է� ����
		model = new DefaultTableModel(caption, 0){
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		//���̺� ��ũ������ ����
		table = new JTable(model);
		JScrollPane mModeScr = new JScrollPane(table);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 480, 300);
		panel.setLayout(null);
		
		JLabel course = new JLabel("���� : "); // ���� �� ����
		course.setBounds(31, 22, 57, 15);
		
		//-- ���� �޺� �ڽ�
		course_box = new JComboBox();
		course_box.setBounds(94, 19, 112, 21);
			// -- ���� �޺��ڽ� DB ��������
		course_box.addItem("���� ����");
		
		ArrayList<DTO> crs_list = dao_std.select_course_info();
		
			for (DTO dto_course : crs_list) {
				course_box.addItem(dto_course.get_crsName());
			}

			panel.add(course_box);
			course_box.addActionListener(this);
			
		// �а� �޺� �ڽ� --
		
		JLabel grade = new JLabel("�ü�/���� : "); // �ü�, ���� �� ����
		grade.setBounds(31, 54, 71, 15);
		
		crsCredit_tf = new JTextField(); // �ü�, ���� �ؽ�Ʈ�ʵ�
		crsCredit_tf.setBounds(104, 51, 24, 21);
		crsCredit_tf.setColumns(10);
		crsCredit_tf.setEditable(false);
		
		JLabel crsProName = new JLabel("��米�� : "); // ��米�� ��
		crsProName.setBounds(216, 54, 63, 15);
			
		crsProName_tf = new JTextField(); // ��米�� �ؽ�Ʈ�ʵ�
		crsProName_tf.setBounds(280, 51, 48, 21);
		crsProName_tf.setColumns(10);
		crsProName_tf.setEditable(false);
		
		table = new JTable(model);
		JScrollPane stk_list = new JScrollPane(table);
		stk_list.setBounds(31, 100, 398, 168);
			
		add = new JButton("�� ��"); // �߰� ��ư
		add.setBounds(348, 50, 63, 23);
		panel.add(add);
		add.addActionListener(this);
			
			// ���̺� �� ����
		add(panel);
		panel.add(course);
		panel.add(course_box);
		panel.add(grade);
		panel.add(crsCredit_tf);			
		panel.add(crsProName);
		panel.add(crsProName_tf);
		panel.add(stk_list);
		
		commit = new JButton("���"); // ��� ��ư
		commit.addActionListener(this);
		commit.setBounds(366, 269, 63, 21);
		panel.add(commit);
		
		JLabel lblNewLabel = new JLabel("* ������� öȸ�� [������ȸ] ���� �����մϴ�.");
		lblNewLabel.setBounds(31, 85, 398, 15);
		panel.add(lblNewLabel);
		
		delete = new JButton("����");
		delete.addActionListener(this);
		delete.setBounds(291, 269, 63, 21);
		panel.add(delete);
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
		if(e.getSource() == course_box){  // ���� ���� ���� ���  course_box
			
			if(!course_box.getSelectedItem().equals("���� ����")){ //-- ���� ���� ����
				
				dto_takecourse = dao_std.select_course_list(course_box.getSelectedItem().toString());
					
				crsCredit_tf.setText(dto_takecourse.get_crsCredit());
			
				crsProName_tf.setText(dto_takecourse.get_proName());
			
			}else{
				
				JOptionPane.showMessageDialog(null, " ���� �����Ͽ� �ֽʽÿ�.");
				
				crsCredit_tf.setText("");	
				crsProName_tf.setText("");
				
			}// ���� ���� ���� END--
				
		}// course_box END--
		
		if(e.getSource() == add){ // -- [ �߰� ] button 
			
			
			model = (DefaultTableModel) table.getModel();
			//table.getColumnModel().equals(course_box.getSelectedItem());
			try {
				
					
					if(!arr[0].equals(course_box.getSelectedItem())){
						
//						try {
//
//									arr[0] = course_box.getSelectedItem().toString(); // �����
//									arr[1] = crsCredit_tf.getText();  // �ü�/����
//									arr[2] = crsProName_tf.getText();  // ������
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
						
						JOptionPane.showMessageDialog(null, "������ ������ �����ϼ̽��ϴ�!");
						
					}
					
						
			}catch(Exception e1) {
				e1.printStackTrace();
			}
			
			
			
		
		} // [ �߰� ] button END --
		
		if(e.getSource() == commit ){ // -- [ ��� ] button
			
			dao_std.insert_course_add( stdNum, dto_takecourse.get_crsNum(), arr[0]); // DB table: takecourse �� �� �׸� : �й�/�����ȣ/�����̸�
			
			JOptionPane.showMessageDialog(null, "��� �Ϸ� �Ǿ����ϴ�.!");

			
		}// [ ��� ] button END --
		
		if(e.getSource() == delete ){ // -- [ ���� ] button
		
			// -- ���� �߻��� ó���� 
			try {
				
				model.removeRow(table.getSelectedRow()); // ���� ���õ� row �� ����
				
			} catch (Exception x) { 
				// ���� �߻� �̺�Ʈ
				JOptionPane.showMessageDialog(null, "���̻� ������ ������ �����ϴ�.");
			} 
			//���� �߻��� ó���� -- END
			

		}// [ ���� ] button END --
	}
}
