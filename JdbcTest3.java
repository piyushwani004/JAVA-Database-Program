import java.sql.*;
import java.util.*;

class JdbcTest3
{
	public static void main(String args[]) throws Exception
	{



	//Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	Connection conn = DriverManager.getConnection("jdbc:ucanaccess://c:\\xyz\\myproj.accdb");
	Statement stat = conn.createStatement();

	boolean done = false;

	while(!done)
	{
			Scanner in = new Scanner(System.in);
		System.out.print("\nSQL> ");
		String sql = in.nextLine();
		try
		{
			if(sql.equals("Exit"))
			done=true;
			else
			if(stat.execute(sql))
			{
				ResultSet rs = stat.getResultSet();
				ResultSetMetaData mt = rs.getMetaData();
				System.out.println("No. of columns :"+mt.getColumnCount());
				System.out.println("Table Name : "+ mt.getTableName(1));

				System.out.println("----------------------------------------------------------------------");
				for(int i=1;i<=mt.getColumnCount();i++)
				System.out.print(mt.getColumnName(i)+"\t");
				System.out.println("\n----------------------------------------------------------------------");

				while(rs.next())
				{
					for(int i=1;i<=mt.getColumnCount();i++)
					System.out.print(rs.getString(i)+"\t");
					System.out.println();
				}
				rs.close();
			}
			else
			System.out.println("Query is executed");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
}


  //"jdbc:oracle:thin:@localhost:1521:xe","info","planet"








