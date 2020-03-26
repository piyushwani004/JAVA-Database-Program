/*Program to store the employee information in the Employee table created
in MSACCESS. Create a data entry form for getting employee information from
user and when user clicks on insert button save the record to the Employee
table. Provide clear Button to clear all the textFields on the dataEntry form.*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class JDBCMgmt
{
	public static void main(String args[])
	{
		CFrame frame = new CFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.show();
	}
}

class CFrame extends JFrame implements ActionListener
{
	JButton insert,update,delete,clear;
	JLabel lbl1,lbl2,lbl3,lbl4;
	JTextField EmpNo,EmpName,Department,Salary;
	DefaultListModel list ;
	PreparedStatement ps;
	Connection con;

	JPanel panel,DPanel;

	public CFrame()
	{
					list = new DefaultListModel();
					try
					{
						Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
						con = DriverManager.getConnection("jdbc:odbc:myDSN");
						ps = con.prepareStatement("Select * from emp");
						ResultSet set = ps.executeQuery();
						list.clear();
						while(set.next())
						{
								list.addElement(set.getString(1)+","+
								set.getString(2)+","+set.getString(3)+","+set.getString(4));
						}
						set.close();
					}
					catch(Exception e)
					{
						System.out.println(e+"asdf");
					}
		JList dlist= new JList(list);
		JScrollPane pane = new JScrollPane(dlist);
		add(pane,"East");

		insert = new JButton("Insert");
		update = new JButton("Update");
		delete = new JButton("Delete");
		clear = new JButton("Clear");

		lbl1 = new JLabel("Employee No ");
		lbl2 = new JLabel("Employee Name ");
		lbl3 = new JLabel("Department ");
		lbl4 = new JLabel("Salary ");

		EmpNo = new JTextField(5);
		EmpName = new JTextField(15);
		Department = new JTextField(25);
		Salary = new JTextField(5);

		panel = new JPanel();
		DPanel = new JPanel();

		add(panel,"South");
		add(DPanel,"Center");

		DPanel.setLayout(new GridLayout(4,2));

		DPanel.add(lbl1);
		DPanel.add(EmpNo);
		DPanel.add(lbl2);
		DPanel.add(EmpName);
		DPanel.add(lbl3);
		DPanel.add(Department);
		DPanel.add(lbl4);
		DPanel.add(Salary);

		panel.add(insert);
		panel.add(update);
		panel.add(delete);
		panel.add(clear);

		insert.addActionListener(this);
		update.addActionListener(this);
		delete.addActionListener(this);
		clear.addActionListener(this);

		setSize(400,300);
		setTitle("Employee Details");
	}
	public void actionPerformed(ActionEvent e)
	{
		Object obj = e.getSource();

		try
		{
			if(obj == insert)
			{
				ps = con.prepareStatement("insert into Emp(EmpNo,EmpName,Department,Salary) values(?,?,?,?)");

				ps.setString(1,EmpNo.getText());
				ps.setString(2,EmpName.getText());
				ps.setString(3,Department.getText());
				ps.setString(4,Salary.getText());
				ps.executeUpdate();

				JOptionPane.showMessageDialog(this,"Record Inserted");
			}
			else if(obj == update)
			{
				ps = con.prepareStatement("update Emp set EmpName = ?,Department = ?, Salary = ? where EmpNo = ?");
				ps.setString(1,EmpName.getText());
				ps.setString(2,Department.getText());
				ps.setString(3,Salary.getText());
				ps.setString(4,EmpNo.getText());
				int x=ps.executeUpdate();
				if(x!=0)
				JOptionPane.showMessageDialog(this,"Record Updated");
				else
				JOptionPane.showMessageDialog(this,"Record Not Found");
			}
			else if(obj == delete)
			{
				ps = con.prepareStatement("delete from Emp where EmpNo = ?");
				ps.setString(1,EmpNo.getText());
				int x=ps.executeUpdate();
				if(x!=0)
				JOptionPane.showMessageDialog(this,"Record Deleted");
				else
				JOptionPane.showMessageDialog(this,"Record is Not Found");
			}
             else  if(obj==clear)
			{
				EmpNo.setText("");
				EmpName.setText("");
				Department.setText("");
				Salary.setText("");
			}
			ps = con.prepareStatement("Select * from emp");
			ResultSet set = ps.executeQuery();
			list.clear();
			while(set.next())
			{
				list.addElement(set.getString(1)+","+set.getString(2)+","+
				set.getString(3)+","+set.getString(4));
			}
			set.close();
		}
		catch(NumberFormatException e1)
         {                System.out.println(e1);		}
		catch(SQLException e2)
		{              System.out.println(e2);		}
	}
}
