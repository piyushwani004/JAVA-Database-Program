import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
class PrepareTest extends JFrame
{

	JTextField tfname ;
	JTextField tfph;
	PreparedStatement ps;
	Connection conn;
	PrepareTest()
	{
		setSize(300,300);
		setTitle("Data Test");
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			conn = DriverManager.getConnection("jdbc:odbc:myDSN");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
			JPanel center = new JPanel();
			center.add(new JLabel("Name :"));
			tfname = new JTextField(20);
			center.add(tfname);
			center.add(new JLabel("Phone :"));
			tfph= new JTextField(10);
			center.add(tfph);

			JButton save = new JButton("Save");
			save.addActionListener (new ActionListener()
			{
							public void actionPerformed(ActionEvent e)
							{
								try
								{
									ps = conn.prepareStatement("insert into Stud values(?,?)");
									ps.setString(1,tfname.getText());
									ps.setInt(2,Integer.parseInt(tfph.getText()));
									ps.execute();
									JOptionPane.showMessageDialog(null,"Record is saved");
									tfname.setText("");
									tfph.setText("");
								}catch(Exception ex)
								{
									System.out.println(ex);
								}
							}
						});

			JButton delete = new JButton("Delete");
			delete.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					try
					{
						ps = conn.prepareStatement ("delete from stud where s_name=?");
						ps.setString(1,tfname.getText());
						int x = ps.executeUpdate();
						if(x>=1)
							JOptionPane.showMessageDialog(null,"Record is deleted");
						else
							JOptionPane.showMessageDialog(null, "Record is not found ");
					}
					catch(Exception ex)
					{
						System.out.println(ex);
					}
				}
			});

			JPanel south = new JPanel();
			south.add(save);
			south.add(delete);
			add(center);
			add(south,"South");
			} // end of constructor
		public static void main(String args[])
		{
			PrepareTest f = new PrepareTest();
			f.setDefaultCloseOperation(3);
			f.setVisible(true);
		}
}
