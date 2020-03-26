import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class PreStatTest extends JFrame
{
	JTextField tfno = new JTextField(20);
	JTextField tfname = new JTextField(20);
	JTextField tfsal = new JTextField(20);
	JTextArea ta = new JTextArea(7,50);

	PreparedStatement ps;
	Connection c;


	PreStatTest()
	{
		setSize(600,500);
		JPanel p = new JPanel();
		p.add(new JLabel("Emp No :"));
		p.add(tfno);
		p.add(new JLabel("Name :"));
		p.add(tfname);
		p.add(new JLabel("Salary:"));
		p.add(tfsal);
		JButton save = new JButton("Save");
		p.add(save);
		JButton disp = new JButton("Display");
		p.add(disp);
		JScrollPane sp = new JScrollPane(ta);
		p.add(sp);
		add(p);
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			 c = DriverManager.getConnection("jdbc:odbc:infoDSN","planetinfo","planetinfo");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		save.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					ps = c.prepareStatement("insert into emp values(?,?,?)");
					ps.setInt(1,Integer.parseInt(tfno.getText()));
					ps.setString(2, tfname.getText());
					ps.setDouble(3, Double.parseDouble(tfsal.getText()));
					ps.execute();
					JOptionPane.showMessageDialog(null,"Record is saved");
					tfno.setText("");
					tfname.setText("");
					tfsal.setText("");
				}
				catch(Exception evt)
				{
					System.out.println(evt);
				}
			}
		});
		disp.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					ps = c.prepareStatement("select * from emp");
					ta.setBackground(Color.LIGHT_GRAY);
					ta.setText("");
					ta.setText("------------------------------------------------------------------------------------------------------------");
					ta.append("\n\r\tEmpNo\tName\tSalary");
					ta.append("\n\r--------------------------------------------------------------------------------------------------------");
					ResultSet set = ps.executeQuery();
					while(set.next())
					{
						ta.append("\n\r\t"+set.getInt(1)+"\t"+set.getString(2)
						                         +"\t\t"+set.getDouble(3));
					}
					ta.append("\n\r-------------------------------------------------------------------------------------------------------");
					set.close();
				}
				catch(Exception ex)
				{
					System.out.println(ex);
				}
			}
		});
	}//end of constructor
		public static void main(String args[])
		{
			PreStatTest f = new PreStatTest();
			f.setVisible(true);
			f.setDefaultCloseOperation(3);
		}
}
/////////////////////////E N D   O F  PROGRAM/////////////////////
