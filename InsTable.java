 import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class InsTable extends JFrame implements ActionListener
{
	JTextField tf = new JTextField(20);
	Statement stat;
	JButton b = new JButton("Save");

	InsTable()
	{
		setSize(700,600);
		try
		{
		Connection conn = DriverManager.getConnection("jdbc:ucanaccess://c:\\xyz\\info.accdb","jalgaon","jalgaon");

		 stat = conn.createStatement();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

		setLayout(new FlowLayout());
		add(new JLabel("Name:"));
		add(tf);
		add(b);
		b.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		try
		{
		String sql = "insert into greetings values ('" +tf.getText()+" ')";
		System.out.println(sql);
		stat.execute(sql);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
	public static void main(String args[]) throws Exception
	{
		InsTable f = new InsTable();
		f.setDefaultCloseOperation(3);
		f.setVisible(true);
	}

}


  //"jdbc:oracle:thin:@localhost:1521:xe","info","planet"








