package Std;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.DAO_std;
import DTO.DTO;




public class Student_Takecourse_chk extends JPanel implements ActionListener {

	String[] caption = { "�����ȣ", "�����̸�", "�ü�/����", "��米��" };
	DefaultTableModel model;
	JTable table;

	JButton tckCBtn; // ��ҹ�ư
	JButton tckSBtn; // ��ȸ��ư
	
	String stdNum ; //�α��ο��� �޾ƿ��� �л� ��ȣ
	
	Student_Takecourse_chk(String stdNum_lg) {
		this.stdNum = stdNum_lg;
		setLayout(null);
		
		model = new DefaultTableModel(caption, 0) {
			
			public boolean isCellEditable(int row, int column) {
				return false;
			};
			
		};

		JPanel panel = new JPanel(); // �г� ����
		panel.setBounds(12, 10, 480, 300);
		add(panel);
		panel.setLayout(null);

		table = new JTable(model); // ���̺� ����
		JScrollPane cChkTable = new JScrollPane(table);
		cChkTable.setBounds(12, 10, 381, 217);
		panel.add(cChkTable);

		tckCBtn = new JButton("���"); // ��ҹ�ư ����
		tckCBtn.setBounds(332, 237, 63, 23);
		panel.add(tckCBtn);
		tckCBtn.addActionListener(this);

		tckSBtn = new JButton("��ȸ");
		tckSBtn.setBounds(262, 237, 63, 23);
		panel.add(tckSBtn);
		tckSBtn.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		DAO_std std = new DAO_std();
		DTO	dto = new DTO();
		model = (DefaultTableModel) table.getModel();
		String arr[] = new String[4];
		
		if (e.getSource() == tckSBtn) // ��ȸ��ư ������	
		{
			//String arr[] = new String[4]; // map table �� �� �÷��� �� ��ŭ�� �迭- 4���� �մٸ� String �迭�� 4ĭ�� �ʿ�
			
			int cnt = model.getRowCount();
			for (int i = 0; i < cnt; i++) {
				model.removeRow(0);

			}//--
			ArrayList<DTO> list  = std.select_takecourse_list(stdNum);
			
			
			for (DTO dto_tk : list) { //"�����ȣ", "�����̸�", "�ü�/����", "��米��" 
				
				arr[0] = dto_tk.get_crsNum();
				arr[1] = dto_tk.get_crsName();
				arr[2] = dto_tk.get_tkMark();
				arr[3] = dto_tk.get_proName();
				
						
				//model = (DefaultTableModel) table.getModel();
				model.addRow(arr);
			}
		}
		if (e.getSource() == tckCBtn) // ��ҹ�ư ������
		{
			try {
				
				int row = table.getSelectedRow();
				
				if (row == (-1)) {// ������ ���� �� ��ҹ�ư ������ �������� ����
					JOptionPane.showMessageDialog(null, "����� ���� ������ �������ּ���");
					return;
				}
					
				
				
				std.delete_takecourse(stdNum, model.getValueAt(table.getSelectedRow(), 1).toString());
				
				//dao.deleteDB(Integer.toString(dto1.getSub_num()), s_num, Integer.toString(dto1.getP_num()));

				JOptionPane.showMessageDialog(null, "���õ� ������ ��� �Ǿ����ϴ�!");
				model = (DefaultTableModel) table.getModel();
				model.removeRow(row); // ���õ� �� ����
				
			}catch(Exception e1) {
				e1.printStackTrace();
			}
			
			
			
		}

	}
}