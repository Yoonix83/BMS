package Std;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.DAO_std;
import DTO.DTO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class Student_Grade_chk extends JPanel implements ActionListener {

	JTable table;
	DefaultTableModel model;
	String[] caption = { "�����", "�ü�/����", "����", "��� ����" };
	JButton search;

	String table_list[] = new String[4];
	
	String stdNum; // �л���ȣ
	
	Student_Grade_chk(String stdNum_lg) {
		this.stdNum = stdNum_lg;
		
		setLayout(null);
		
		//data_table.getTableHeader().setReorderingAllowed(false);
		
		
		model = new DefaultTableModel(caption, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		
		JPanel panel = new JPanel(); // �г� ����
		panel.setBounds(12, 10, 480, 300);
		add(panel);
		panel.setLayout(null);
		
		table = new JTable(model); // ���̺� ����
		table.getTableHeader().setReorderingAllowed(false); // user�� mouse drag �� ���� table ���� �� ���� �Ұ���.
		
		JScrollPane gd_list_scr = new JScrollPane(table);
		gd_list_scr.setEnabled(false);
		gd_list_scr.setBounds(25, 24, 388, 213);
		panel.add(gd_list_scr);

		search = new JButton("��ȸ"); // ��ȸ ��ư
		search.setBounds(342, 247, 71, 23);
		panel.add(search); 
		search.addActionListener(this);
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
			DAO_std dao_std = new DAO_std();
			
			if (e.getSource() == search) {
				
				int cnt = model.getRowCount();
				for (int i = 0; i < cnt; i++) {
					model.removeRow(0);

				}
				
				ArrayList<DTO> list  = dao_std.select_mark(stdNum);
				
				for (DTO dto_mark : list) {
					
					table_list[0] = dto_mark.get_crsName();
					table_list[1] = dto_mark.get_crsCredit();
					table_list[2] = dto_mark.get_tkMark();
					table_list[3] = dto_mark.get_proName();
				
					model = (DefaultTableModel) table.getModel();
					model.addRow(table_list);
				}

			}

		}
	}

