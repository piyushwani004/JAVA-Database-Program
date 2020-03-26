import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class JDBCEmpTest extends JFrame
{

	JTextField tfno = new JTextField(20);
	JTextField tfname = new JTextField(20);
	JTextField tfsal = new JTextField(20);
	JTextArea ta = new JTextArea(10,50);

	Statement stat;

	JDBCEmpTest()
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

		p.add(new JScrollPane(ta));
		add(p);
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		    Connection c = DriverManager.getConnection("jdbc:odbc:myDSN" );
			stat = c.createStatement();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		save.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String sql = "insert into Emp values(" +
				tfno.getText()+ ", ' "+tfname.getText() +" ' ,"
				+ tfsal.getText()+")";

			   System.out.println(sql);
				try
				{
					stat.execute(sql);
					JOptionPane.showMessageDialog(null,"Record is saved");
					tfno.setText("");
					tfname.setText("");
					tfsal.setText("");
				}
				catch(Exception evt)
				{
					System.out.println(evt);
				}
			}// end of actionPerformed
		});

		disp.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					ResultSet set = stat.executeQuery("Select * from emp");
					ta.setEditable(false);
					ta.setText("");
					ta.append("============================================\n");
					ta.append("EMP_NO\tEMP_NAME\t\tSALARY\n");
					ta.append("=============================================\n");

				while(set.next())
					ta.append(set.getInt(1)+"\t"+set.getString(2)+
					     "\t\t"+set.getDouble(3)+"\n");

				ta.append("==============================================\n");
				set.close();
				}
				catch(Exception ex)
				{
					System.out.println(ex);
				}
			}
		});
	}// end of frame constructor
	public static void main(String args[])
	{
		JDBCEmpTest f = new JDBCEmpTest();
		f.setVisible(true);
		f.setDefaultCloseOperation(3);
	}
}			//==================END OF PROGRAM==================
