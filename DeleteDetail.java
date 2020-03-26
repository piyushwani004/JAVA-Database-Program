
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.sql.Date.*;
class DeleteDetail extends JFrame
{

	JTextField tfempid;
	DeleteDetail()
	{
		setTitle("Delete Employee Details");
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		int screenHeight=screenSize.height;
		int screenWidth=screenSize.width;
		setSize(screenWidth/2,screenHeight/2);
		setLocation(screenWidth/4,screenHeight/4);
		//Windowhandler handler=new Windowhandler();
		//addWindowListener(handler);

		Font f=new Font("Arial",Font.ROMAN_BASELINE,24);
		JLabel head=new JLabel("Enter Employee ID to Delete Details");
		head.setFont(f);
		JPanel north=new JPanel();
		north.add(head);
		add(north,"North");

		JPanel center=new JPanel();
		JLabel empid=new JLabel("Enter Employee ID");
		tfempid=new JTextField(20);

		center.add(empid);
		center.add(tfempid);
		add(center,"Center");

		JPanel south=new JPanel();
		JButton delete=new JButton("Delete Record");
		JButton cancel=new JButton("Cancel Delete");
		south.add(delete);
		south.add(cancel);
		add(south,"South");
		delete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent AE)
			{
				try
				   {
				         Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				  		 Connection con=DriverManager.getConnection("jdbc:odbc:ProjectDsn");
				 		 String sqlstr="delete from Emp where EmpID="+tfempid.getText();
				  	     Statement st=con.createStatement();
   					     st.executeUpdate(sqlstr);
					}
					catch(Exception e)
					{
						System.out.println(e);
					}

			}
		});

		cancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent AE)
			{
				setVisible(false);
			}
		});
	}
	public static void main(String args[])
	{
		DeleteDetail d=new DeleteDetail();
		d.setVisible(true);
	}
}