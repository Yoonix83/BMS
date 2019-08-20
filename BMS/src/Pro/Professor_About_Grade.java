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
																	// box�� ����
																	// ����
	String title[] = { "�� ��", "�� ��", "�� ��", "�� ��" };
	JTable gd_dt_model; // �й�/�̸�/�г�/����  ǥ�ø� ���� ���̺�
	
	JButton gd_search,gd_assign,gd_commit; //  ��ȸ / �ο� / ���

	JComboBox gd_lv_box; //���� ���� �޺� �ڽ�
	JLabel gd_lb = new JLabel(); // ���� Lable
	
	String table_list[] = new String[7];
	
	String pNum; //�α��ο��� �޾ƿ� �ѹ�
	
	DAO_pro dao_pro = new DAO_pro();
	DTO_pro dto_pro = new DTO_pro();
	
	private JTextField crsName_tf;
	private JLabel lblNewLabel_1;

	public Professor_About_Grade(String pNum_lg) {
		
		this.pNum = pNum_lg;
		setLayout(null);

		JPanel ag_panel = new JPanel(); // Professor_About_Grade Ŭ���� �г�
		ag_panel.setBounds(0, 0, 494, 327);
		add(ag_panel);
		ag_panel.setLayout(null);
		
		//���
		gd_commit = new JButton("���");
		gd_commit.setBounds(400, 280, 61, 23);
		ag_panel.add(gd_commit);
		gd_commit.setFont(new Font("����", Font.PLAIN, 12));
		gd_commit.addActionListener(this);
		
		//�ο�
		gd_assign = new JButton("�ο�");
		gd_assign.setBounds(400, 16, 61, 23);
		ag_panel.add(gd_assign);
		gd_assign.setFont(new Font("����", Font.PLAIN, 12));
		gd_assign.addActionListener(this);
		
		// �޺��ڽ�
		gd_lv_box = new JComboBox();
		gd_lv_box.setBounds(285, 16, 84, 23);
		ag_panel.add(gd_lv_box);
		
		gd_lv_box.addItem("���� ����"); 
		for (int i = 0; i < grade_lv.length; i++)
			gd_lv_box.addItem(grade_lv[i]);

		gd_lb = new JLabel("�� ��:");
		gd_lb.setBounds(236, 13, 37, 29);
		ag_panel.add(gd_lb);

		model = new DefaultTableModel(title, 0);
		gd_dt_model = new JTable(model);
		JScrollPane gd_list_scr = new JScrollPane(gd_dt_model);// �л� ����Ʈ ǥ�� ����Ʈ �̻��Ͻ� ��ũ��
		gd_list_scr.setBounds(12, 89, 470, 179);
		ag_panel.add(gd_list_scr);

		gd_search = new JButton("��ȸ"); // gd_search ��ȸ��ư
		gd_search.setFont(new Font("����", Font.PLAIN, 12));
		gd_search.setBounds(315, 280, 61, 23);
		ag_panel.add(gd_search);
		
		dto_pro = dao_pro.select_pro_info(pNum);// ���� ����, ���� �̸� ��������
		
		JLabel crsName_lb = new JLabel("����");  // ���� ����
		crsName_lb.setBounds(12, 20, 31, 15);
		ag_panel.add(crsName_lb);
		
		crsName_tf = new JTextField();
		crsName_tf.setText(dto_pro.get_crsName());
		crsName_tf.setEditable(false);
		crsName_tf.setBounds(55, 17, 169, 21);
		ag_panel.add(crsName_tf);
		crsName_tf.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("�ο�) ������ �ο��� �� ���� �л��� ������ �������ּ���.");
		lblNewLabel.setBounds(12, 45, 431, 15);
		ag_panel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("���) �� ���� �� �� ���� �л��� ������ �ο� �����մϴ�.");
		lblNewLabel_1.setBounds(12, 64, 431, 15);
		ag_panel.add(lblNewLabel_1);

		gd_search.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {


		if (e.getSource() == gd_search) //  ��ȸ ��ư Ŭ����
		{

			int cnt = model.getRowCount();  // ���� List �� �ִ� table �� row��  ��ŭ�� ���� cnt �� �ִ´�
			for (int i = 0; i < cnt; i++) { // cnt ��ŭ ����
				model.removeRow(0);         // ��ȸ��ư Ŭ���� ���� ���� ������ ������ ���� cnt ��ŭ�� ���پ� ����
				
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
		if (e.getSource() == gd_assign) //  �ο� ��ư
		{
			// setValueAt( Ư����<value>, Ư����<row>, Ư����<column> ) = JTable�� Ư�� ��ġ�� ���� �ٲٴ� �Լ�
			gd_dt_model.setValueAt((String) gd_lv_box.getSelectedItem(), gd_dt_model.getSelectedRow(), 3); // ������ 3��° column �� �ִ�.
			
		}
		
		if (e.getSource() == gd_commit) // ��� ��ư
		{

			DTO_mark dto_mark = new DTO_mark();
			
			dto_mark.set_tkMark((String) gd_dt_model.getValueAt(gd_dt_model.getSelectedRow(), 3));
			dto_mark.set_crsName(dto_pro.get_crsName());
			dto_mark.set_stdNum((String) gd_dt_model.getValueAt(gd_dt_model.getSelectedRow(), 0));
			dto_mark = dao_pro.update_grade(dto_mark);
					
			JOptionPane.showMessageDialog(null, "["+ (String) gd_dt_model.getValueAt(gd_dt_model.getSelectedRow(), 1)+"] �л��� ������ ["+dto_mark.get_tkMark()+ "] ���� ��� �Ϸ� �Ǿ����ϴ�.");
		
		}
	}
}
